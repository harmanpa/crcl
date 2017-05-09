using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Timers;
using System.Xml;
using System.Xml.Serialization;
using System.IO;
using Schemas; 

namespace SimRobotServer
{

	// State object for reading client data asynchronously
	public class StateObject {
		// Client  socket.
		public Socket workSocket = null;
		// Size of receive buffer.
		public const int BufferSize = 1024;
		// Receive buffer.
		public byte[] buffer = new byte[BufferSize];
		// Received data string.
		public StringBuilder sb = new StringBuilder();  
	}

	public class AsynchronousSocketListener {
		// Thread signal.
		public ManualResetEvent allDone = new ManualResetEvent(false);

		private Schemas.CRCL.Status.CRCLStatusType status;

		public delegate void HandleNewCommandDelegate(Schemas.CRCL.CommandInstance.CRCLCommandInstanceType cmdInstance);

		HandleNewCommandDelegate handleNewCommand;

		private int port;
		public AsynchronousSocketListener(Schemas.CRCL.Status.CRCLStatusType status,
			HandleNewCommandDelegate handleNewCommand,
			int port) {
			this.status = status;
			this.handleNewCommand = handleNewCommand;
			this.port = port;
		}


		public void StartListening() {
			// Data buffer for incoming data.
//			byte[] bytes = new Byte[1024];

			// Establish the local endpoint for the socket.
			// The DNS name of the computer
			// running the listener is "host.contoso.com".
//			IPHostEntry ipHostInfo =  Dns.Resolve(Dns.GetHostName());
//			IPAddress ipAddress = ipHostInfo.AddressList[0];
			IPEndPoint localEndPoint = new IPEndPoint(IPAddress.Any, port);

			// Create a TCP/IP socket.
			Socket listener = new Socket(AddressFamily.InterNetwork,
			                             SocketType.Stream, ProtocolType.Tcp );

			// Bind the socket to the local endpoint and listen for incoming connections.
			try {
				listener.Bind(localEndPoint);
				listener.Listen(100);

				while (true) {
					// Set the event to nonsignaled state.
					allDone.Reset();

					// Start an asynchronous socket to listen for connections.
					Console.WriteLine("Waiting for a connection...");
					listener.BeginAccept( 
					                     new AsyncCallback(AcceptCallback),
					                     listener );

					// Wait until a connection is made before continuing.
					allDone.WaitOne();
				}

			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}

			Console.WriteLine("\nPress ENTER to continue...");
			Console.Read();

		}

		public void AcceptCallback(IAsyncResult ar) {
			// Signal the main thread to continue.
			allDone.Set();

			// Get the socket that handles the client request.
			Socket listener = (Socket) ar.AsyncState;
			Socket handler = listener.EndAccept(ar);

			// Create the state object.
			StateObject state = new StateObject();
			state.workSocket = handler;
			Console.WriteLine ("Accepted connection from " + state.workSocket.RemoteEndPoint);
			handler.BeginReceive( state.buffer, 0, StateObject.BufferSize, 0,
			                     new AsyncCallback(ReadCallback), state);
		}

		public void parseContent(String content, String ender, Socket handler, StateObject state) {
			int endlineIndex = content.IndexOf (ender);
			String statusXml = Schemas.CRCL.Utils.ToXML (status);
			Console.WriteLine ("Status XML:");
			Console.WriteLine (statusXml);
			Console.WriteLine ("");
			while (endlineIndex > -1) {
				String line = content.Substring (0, endlineIndex+ender.Length);
				Console.WriteLine("Read {0} bytes from socket. \n Data : {1}",
					line.Length, line );
				// Echo the data back to the client.
//				Send(handler, line);
				Send (handler, statusXml);
				content = content.Substring (endlineIndex + ender.Length);
				Schemas.CRCL.CommandInstance.CRCLCommandInstanceType cmdInstanceRecvd 
				= Schemas.CRCL.Utils.FromXML<Schemas.CRCL.CommandInstance.CRCLCommandInstanceType> (line);
				if (null != cmdInstanceRecvd
					&& null != cmdInstanceRecvd.CRCLCommand
					&& !typeof(Schemas.CRCL.CommandInstance.GetStatusType).IsAssignableFrom (cmdInstanceRecvd.CRCLCommand.GetType ())) {
					handleNewCommand (cmdInstanceRecvd);
				}
				endlineIndex = content.IndexOf (ender);
			}
			state.sb.Clear ();
			state.sb.Append (content);
		}

		public void ReadCallback(IAsyncResult ar) {
			String content = String.Empty;

			// Retrieve the state object and the handler socket
			// from the asynchronous state object.
			StateObject state = (StateObject) ar.AsyncState;
			Socket handler = state.workSocket;

			// Read data from the client socket. 
			int bytesRead = handler.EndReceive(ar);

			if (bytesRead > 0) {
				// There  might be more data, so store the data received so far.
				state.sb.Append(Encoding.ASCII.GetString(
					state.buffer,0,bytesRead));

				// Check for end-of-file tag. If it is not there, read 
				// more data.
				content = state.sb.ToString();
				Console.WriteLine ("content = " + content);
				if (content.IndexOf("<EOF>") > -1) {
					// All the data has been read from the 
					// client. Display it on the console.
					Console.WriteLine("Read {0} bytes from socket. \n Data : {1}",
					                  content.Length, content );
					// Echo the data back to the client.
					SendAndClose(handler, content);
				} else {
					parseContent (content, "</CRCLCommandInstance>", handler, state);
//					parseContent (content, "\n", handler, state);
					// Not all data received. Get more.
					handler.BeginReceive(state.buffer, 0, StateObject.BufferSize, 0,
					                     new AsyncCallback(ReadCallback), state);
				}
			}
		}



//		public string ToXML(object o)
//		{
//			var stringwriter = new System.IO.StringWriter();
//			var serializer = new XmlSerializer(o.GetType());
//			serializer.Serialize(stringwriter, o);
//			return stringwriter.ToString();
//		}

		private void Send(Socket handler, String data) {
			
			// Convert the string data to byte data using ASCII encoding.
			byte[] byteData = Encoding.ASCII.GetBytes(data);


			// Begin sending the data to the remote device.
			handler.BeginSend(byteData, 0, byteData.Length, 0,
				new AsyncCallback(SendCallback), handler);
		}

		private void SendCallback(IAsyncResult ar) {
			try {
				// Retrieve the socket from the state object.
				Socket handler = (Socket) ar.AsyncState;

				// Complete sending the data to the remote device.
				int bytesSent = handler.EndSend(ar);
				Console.WriteLine("Sent {0} bytes to client.", bytesSent);

//				handler.Shutdown(SocketShutdown.Both);
//				handler.Close();

			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}

		private void SendAndClose(Socket handler, String data) {
			// Convert the string data to byte data using ASCII encoding.
			byte[] byteData = Encoding.ASCII.GetBytes(data);

			// Begin sending the data to the remote device.
			handler.BeginSend(byteData, 0, byteData.Length, 0,
				new AsyncCallback(SendAndCloseCallback), handler);
		}

		private void SendAndCloseCallback(IAsyncResult ar) {
			try {
				// Retrieve the socket from the state object.
				Socket handler = (Socket) ar.AsyncState;

				// Complete sending the data to the remote device.
				int bytesSent = handler.EndSend(ar);
				Console.WriteLine("Sent {0} bytes to client.", bytesSent);

				Console.WriteLine("Closing Connection to "+ handler.RemoteEndPoint);
				handler.Shutdown(SocketShutdown.Both);
				handler.Close();

			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}
	}

	class MainClass
	{
		private static System.Timers.Timer aTimer;
		private static Schemas.CRCL.Status.CRCLStatusType status = new Schemas.CRCL.Status.CRCLStatusType ();
//		private static Schemas.CRCL.CommandInstance.GetStatusType getStatus 
//			= new Schemas.CRCL.CommandInstance.GetStatusType ();
		private static Schemas.CRCL.CommandInstance.CRCLCommandInstanceType cmdInstance 
			= new Schemas.CRCL.CommandInstance.CRCLCommandInstanceType ();

		private static void handleNewCommand(Schemas.CRCL.CommandInstance.CRCLCommandInstanceType newCmdInstance) {
			cmdInstance.CRCLCommand = newCmdInstance.CRCLCommand;
		}
		private static void OnTimedEvent(Object source, ElapsedEventArgs e)
		{
//			Console.WriteLine("The Elapsed event was raised at {0}", e.SignalTime);
			if (null == status.CommandStatus) {
				status.CommandStatus = new Schemas.CRCL.Status.CommandStatusType ();
				status.CommandStatus.CommandID = 1;
				status.CommandStatus.StatusID = 1;
				status.CommandStatus.CommandState = Schemas.CRCL.Status.CommandStateEnumType.CRCL_Working;
			}
			long statId = 1;
			try {
				statId = Convert.ToInt32(status.CommandStatus.StatusID);
			} catch(Exception ex) {
				Console.WriteLine(ex.ToString());
			}
			statId++;
			status.CommandStatus.StatusID = statId;
			if (cmdInstance.CRCLCommand != null
				&& !status.CommandStatus.CommandID.Equals(cmdInstance.CRCLCommand.CommandID)) {
				status.CommandStatus.CommandID = cmdInstance.CRCLCommand.CommandID;
				Console.WriteLine ("CommandID = " + cmdInstance.CRCLCommand.CommandID);
				if (typeof(Schemas.CRCL.CommandInstance.InitCanonType).IsAssignableFrom (cmdInstance.CRCLCommand.GetType ())) {
					Schemas.CRCL.CommandInstance.InitCanonType initCmd =
						(Schemas.CRCL.CommandInstance.InitCanonType)cmdInstance.CRCLCommand;
					Console.WriteLine ("initCmd = " + initCmd);
				} else if (typeof(Schemas.CRCL.CommandInstance.EndCanonType).IsAssignableFrom (cmdInstance.CRCLCommand.GetType ())) {
					Schemas.CRCL.CommandInstance.EndCanonType endCmd =
						(Schemas.CRCL.CommandInstance.EndCanonType)cmdInstance.CRCLCommand;
					Console.WriteLine ("endCmd = " + endCmd);
				} else if (typeof(Schemas.CRCL.CommandInstance.MoveToType).IsAssignableFrom (cmdInstance.CRCLCommand.GetType ())) {
					Schemas.CRCL.CommandInstance.MoveToType moveToCmd =
						(Schemas.CRCL.CommandInstance.MoveToType)cmdInstance.CRCLCommand;
					Console.WriteLine ("moveToCmd = " + moveToCmd);
					if (null != moveToCmd.EndPosition) {
						status.PoseStatus = new Schemas.CRCL.Status.PoseStatusType ();
						status.PoseStatus.Pose = new Schemas.CRCL.Status.PoseType ();
						if (null != moveToCmd.EndPosition.Point) {
							Console.WriteLine ("moveToCmd.EndPosition.Point = "
							+ moveToCmd.EndPosition.Point.X + ","
							+ moveToCmd.EndPosition.Point.Y + ","
							+ moveToCmd.EndPosition.Point.Z
							);
							status.PoseStatus.Pose.Point = new Schemas.CRCL.Status.PointType ();
							status.PoseStatus.Pose.Point.X = moveToCmd.EndPosition.Point.X;
							status.PoseStatus.Pose.Point.Y = moveToCmd.EndPosition.Point.Y;
							status.PoseStatus.Pose.Point.Z = moveToCmd.EndPosition.Point.Z;
						}
						if (null != moveToCmd.EndPosition.XAxis) {
							Console.WriteLine ("moveToCmd.EndPosition.XAxis = "
								+ moveToCmd.EndPosition.XAxis.I + ","
								+ moveToCmd.EndPosition.XAxis.J + ","
								+ moveToCmd.EndPosition.XAxis.K
							);
							status.PoseStatus.Pose.XAxis = new Schemas.CRCL.Status.VectorType ();
							status.PoseStatus.Pose.XAxis.I = moveToCmd.EndPosition.XAxis.I;
							status.PoseStatus.Pose.XAxis.J = moveToCmd.EndPosition.XAxis.J;
							status.PoseStatus.Pose.XAxis.K = moveToCmd.EndPosition.XAxis.K;
						}
						if (null != moveToCmd.EndPosition.XAxis) {
							Console.WriteLine ("moveToCmd.EndPosition.ZAxis = "
								+ moveToCmd.EndPosition.ZAxis.I + ","
								+ moveToCmd.EndPosition.ZAxis.J + ","
								+ moveToCmd.EndPosition.ZAxis.K
							);
							status.PoseStatus.Pose.ZAxis = new Schemas.CRCL.Status.VectorType ();
							status.PoseStatus.Pose.ZAxis.I = moveToCmd.EndPosition.ZAxis.I;
							status.PoseStatus.Pose.ZAxis.J = moveToCmd.EndPosition.ZAxis.J;
							status.PoseStatus.Pose.ZAxis.K = moveToCmd.EndPosition.ZAxis.K;
						}
					}
				} else {
					Console.WriteLine ("Command " + cmdInstance.CRCLCommand + " unrecognized");
				}
			}

		}

//		static public string ToXML(object o)
//		{
//			var stringwriter = new System.IO.StringWriter();
//			var serializer = new XmlSerializer(o.GetType());
//			serializer.Serialize(stringwriter, o);
//			return stringwriter.ToString();
//		}

		public static void Main (string[] args)
		{
//			cmdInstance.CRCLCommand = getStatus;
//			Console.WriteLine (ToXML (cmdInstance));	
			int port = Schemas.CRCL.Defaults.PORT;
			long cycle_millis = 200;
			for (int i = 0; i < args.Length - 1; i++) {
				if (args [i].Equals ("-p")) {
					port = Convert.ToInt32 (args [i + 1]);
					i++;
				} else if (args [i].Equals ("-t")) {
					cycle_millis = Convert.ToInt32 (args [i + 1]);
					i++;
				}
			}
			aTimer = new System.Timers.Timer(cycle_millis);
			// Hook up the Elapsed event for the timer. 
			aTimer.Elapsed += OnTimedEvent;
			aTimer.Enabled = true;

			Console.WriteLine ("Starting C# SimRobotServer on port "+port +" with "+cycle_millis +"ms cycle_time");
			Console.WriteLine ("Use -p [port] to change port.");
			Console.WriteLine ("Use -t [millis] to change cycletime.");
			AsynchronousSocketListener asl = new AsynchronousSocketListener (status,
				new AsynchronousSocketListener.HandleNewCommandDelegate(handleNewCommand), port);
			asl.StartListening ();
//			Console.WriteLine ("Starting C# SimRobotServer on port "+port);
		}
	}
}
