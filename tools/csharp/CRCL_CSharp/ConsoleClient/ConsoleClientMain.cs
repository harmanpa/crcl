using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Text;
using System.Xml;
using System.Xml.Serialization;
using System.IO;
using Schemas; 



namespace ConsoleClient
{
	// State object for receiving data from remote device.
	public class StateObject {
		// Client socket.
//		public Socket workSocket = null;
		// Size of receive buffer.
		public const int BufferSize = 4096;
		// Receive buffer.
		public byte[] buffer = new byte[BufferSize];
		// Received data string.
		public StringBuilder sb = new StringBuilder();
	}

	public class AsynchronousClient {
		// The port number for the remote device.
		private int port;
		private String host;
		private bool debug;

		// ManualResetEvent instances signal completion.
		private  ManualResetEvent connectDone = 
			new ManualResetEvent(false);
		private  ManualResetEvent sendDone = 
			new ManualResetEvent(false);
		private  ManualResetEvent receiveDone = 
			new ManualResetEvent(false);

		// The response from the remote device.
//		private  String response = String.Empty;
		private String content = String.Empty;

		public delegate void UpdateStatusDelegate(Schemas.CRCL.Status.CRCLStatusType status);

		private UpdateStatusDelegate updateStatus;
		private Socket client = null;

		public AsynchronousClient(String host, 
							int port,
							UpdateStatusDelegate updateStatus,
			bool debug) {
			this.host = host;
			this.port = port;
			this.updateStatus = updateStatus;
			this.debug = debug;
		}
			
		public void StartClient() {
			// Connect to a remote device.
			try {
				// Establish the remote endpoint for the socket.
				// The name of the 
				// remote device is "host.contoso.com".
//				IPHostEntry ipHostInfo = Dns.GetHostAddresses(host);
				IPAddress ipAddress = Dns.GetHostAddresses(host)[0];
				IPEndPoint remoteEP = new IPEndPoint(ipAddress, port);

				// Create a TCP/IP socket.
				client = new Socket(AddressFamily.InterNetwork,
					SocketType.Stream, ProtocolType.Tcp);

				// Connect to the remote endpoint.
				client.BeginConnect( remoteEP, 
					new AsyncCallback(ConnectCallback), null);
				connectDone.WaitOne();
				Receive(client);

//				// Send test data to the remote device.
//				Send(client,"This is a test<EOF>");
//				sendDone.WaitOne();
//				while(true) {
//					// Receive the response from the remote device.
//					Receive(client);
//					receiveDone.WaitOne();
//				
//					// Write the response to the console.
//					Console.WriteLine("Response received : {0}", response);
//					content += response;
//					String ender = "</CRCLStatus>";
//					int endindex = -1;
//					while((endindex = content.IndexOf(ender)) > -1) {
//						String statusxml = content.Substring(0,endindex + ender.Length);
//						Schemas.CRCL.Status.CRCLStatusType newStatus =
//							Schemas.CRCL.Utils.FromXML<Schemas.CRCL.Status.CRCLStatusType>(statusxml);
//						if(newStatus != null) {
//							updateStatus(newStatus);
//						}
//						content = content.Substring(endindex + ender.Length);
//					}
//				}

			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			} 
		}

		public void Close() {
			try {
				Socket c = client;
				client = null;
				if(null != c) { 
					c.Shutdown(SocketShutdown.Both);
					c.Close();
				}
			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}

		private void ConnectCallback(IAsyncResult ar) {
			try {
				// Retrieve the socket from the state object.
//				Socket client = (Socket) ar.AsyncState;
				if(null == client) {
					return;
				}
				// Complete the connection.
				client.EndConnect(ar);

				if(debug) {
					Console.WriteLine("Socket connected to {0}",
						client.RemoteEndPoint.ToString());
				}
				// Signal that the connection has been made.
				connectDone.Set();
			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}

		private void Receive(Socket client) {
			try {
				// Create the state object.
				StateObject state = new StateObject();
//				state.workSocket = client;

				// Begin receiving the data from the remote device.
				client.BeginReceive( state.buffer, 0, StateObject.BufferSize, 0,
					new AsyncCallback(ReceiveCallback), state);
			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}

		private void parseContent(StateObject state) {
			String ender = "</CRCLStatus>";
			int endindex = -1;
			while((endindex = content.IndexOf(ender)) > -1) {
				String statusxml = content.Substring(0,endindex + ender.Length);
				Schemas.CRCL.Status.CRCLStatusType newStatus =
					Schemas.CRCL.Utils.FromXML<Schemas.CRCL.Status.CRCLStatusType>(statusxml);
				if(newStatus != null) {
					updateStatus(newStatus);
				}
				content = content.Substring(endindex + ender.Length);
				state.sb.Clear();
				state.sb.Append(content);
			}
		}
		private void ReceiveCallback( IAsyncResult ar ) {
			try {
				
				// Retrieve the state object and the client socket 
				// from the asynchronous state object.
				StateObject state = (StateObject) ar.AsyncState;
//				Socket client = state.workSocket;
				if(null == client) {
					return;
				}
				// Read data from the remote device.
				int bytesRead = client.EndReceive(ar);

				if (bytesRead > 0) {
					if(debug) Console.WriteLine("Recieved {0} bytes from server.", bytesRead);
					// There might be more data, so store the data received so far.
					state.sb.Append(Encoding.ASCII.GetString(state.buffer,0,bytesRead));

					content = state.sb.ToString();
					if(debug) Console.WriteLine("Content from server:" +content);
					this.parseContent(state);
					// Get the rest of the data.
					client.BeginReceive(state.buffer,0,StateObject.BufferSize,0,
						new AsyncCallback(ReceiveCallback), state);
				} else {
					// All the data has arrived; put it in response.
					if (state.sb.Length > 1) {
						content = state.sb.ToString();
						this.parseContent(state);
					}
					// Signal that all bytes have been received.
					receiveDone.Set();
					Console.WriteLine("Connection closed by server.");
					this.Close();
					System.Environment.Exit(1);
				}
			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}

		public void Send(String data) {

			if(debug) Console.WriteLine("Sending data to server:" + data);
			// Convert the string data to byte data using ASCII encoding.
			byte[] byteData = Encoding.ASCII.GetBytes(data);

			// Begin sending the data to the remote device.
			client.BeginSend(byteData, 0, byteData.Length, 0,
				new AsyncCallback(SendCallback), null);
		}

		public void sendDoneWaitOne() {
			sendDone.WaitOne();
		}

		private void SendCallback(IAsyncResult ar) {
			try {
				// Retrieve the socket from the state object.
//				Socket client = (Socket) ar.AsyncState;

				// Complete sending the data to the remote device.
				int bytesSent = client.EndSend(ar);
				if(debug) Console.WriteLine("Sent {0} bytes to server.", bytesSent);

				// Signal that all bytes have been sent.
				sendDone.Set();
			} catch (Exception e) {
				Console.WriteLine(e.ToString());
			}
		}
	}

	class MainClass
	{
		private static Schemas.CRCL.Status.CRCLStatusType status = new Schemas.CRCL.Status.CRCLStatusType ();
		//		private static Schemas.CRCL.CommandInstance.GetStatusType getStatus 
		//			= new Schemas.CRCL.CommandInstance.GetStatusType ();
		private static Schemas.CRCL.CommandInstance.CRCLCommandInstanceType cmdInstance 
			= new Schemas.CRCL.CommandInstance.CRCLCommandInstanceType ();

		private static Schemas.CRCL.CommandInstance.CRCLCommandInstanceType getStatusCmdInstance 
			= new Schemas.CRCL.CommandInstance.CRCLCommandInstanceType ();
		private static Schemas.CRCL.CommandInstance.GetStatusType getStatusCmd 
			= new Schemas.CRCL.CommandInstance.GetStatusType ();

		private static void updateStatus(Schemas.CRCL.Status.CRCLStatusType newStatus) {
			status = newStatus;
			showStatus ();
		}
	    

		public static void showStatus() {
			Console.WriteLine ("status = " + status);
			if (null != status) {
				Schemas.CRCL.Status.CommandStatusType cst = status.CommandStatus;
				if (null != cst) {
					Console.WriteLine ("CommandId = " + cst.CommandID);
					Console.WriteLine ("State = " + cst.CommandState);
					Console.WriteLine ("StatusId = " + cst.StatusID);
				}
				Schemas.CRCL.Status.PoseType pose = status.PoseStatus.Pose;
				if (null != pose) {
					Console.WriteLine ("Pose.Point = " + pose.Point.X + "," + pose.Point.Y + "," + pose.Point.Z);
					Console.WriteLine ("Pose.XAxis = " + pose.XAxis.I + "," + pose.XAxis.J + "," + pose.XAxis.K);
					Console.WriteLine ("Pose.ZAxis = " + pose.ZAxis.I + "," + pose.ZAxis.J + "," + pose.ZAxis.K);
				}
			}
		}
		
		public static void Main (string[] args)
		{
			AsynchronousClient ac = null;
			try {
				int port = Schemas.CRCL.Defaults.PORT;
				String host = "127.0.0.1";
				bool debug = false;
				for (int i = 0; i < args.Length - 1; i++) {
					if (args [i].Equals ("-p")) {
						port = Convert.ToInt32 (args [i + 1]);
						i++;
					} else if (args [i].Equals ("-h")) {
						host = args [i + 1];
						i++;
					} else if (args [i].Equals ("-d")) {
						debug = true;
					} 
				}
				if(args.Length > 0 && args[args.Length-1].Equals("-d")) {
					debug = true;
				}
				ac = new AsynchronousClient (host, port, 
						new AsynchronousClient.UpdateStatusDelegate (updateStatus),
						debug);
				ac.StartClient ();
				Console.WriteLine ("Starting C# ConsoleClient connected to " + host + " on port " + port + ".");
				Console.WriteLine ("Use -p [port] to change port.");
				Console.WriteLine ("Use -h [host] to change host.");
				Console.WriteLine ("Use -d to set debug flag.");
				String l = null;
				int cid = 1;
				while (!"quit".Equals(l = Console.ReadLine ())) {
					if(l == null) {
						Console.WriteLine("Console.ReadLine() returned null. (Bad Terminal ??)");
						break;
					}
					if (l.Length == 0) {
						getStatusCmdInstance.CRCLCommand = getStatusCmd;
						getStatusCmd.CommandID = cid.ToString ();
						String statusRequestXml = Schemas.CRCL.Utils.ToXML (getStatusCmdInstance);
						ac.Send (statusRequestXml);
						ac.sendDoneWaitOne ();
					} else if(l.StartsWith("init")) {
						Schemas.CRCL.CommandInstance.InitCanonType initCmd = new Schemas.CRCL.CommandInstance.InitCanonType();
						cmdInstance.CRCLCommand = initCmd;
						initCmd.CommandID = cid.ToString();
						String cmdXml = Schemas.CRCL.Utils.ToXML (cmdInstance);
						ac.Send (cmdXml);
						ac.sendDoneWaitOne();
					} else if(l.StartsWith("end")) {
						Schemas.CRCL.CommandInstance.EndCanonType endCmd = new Schemas.CRCL.CommandInstance.EndCanonType();
						cmdInstance.CRCLCommand = endCmd;
						endCmd.CommandID = cid.ToString();
						String cmdXml = Schemas.CRCL.Utils.ToXML (cmdInstance);
						ac.Send (cmdXml);
						ac.sendDoneWaitOne();
					} else if(l.StartsWith("move")) {
						Schemas.CRCL.CommandInstance.MoveToType moveCmd = new Schemas.CRCL.CommandInstance.MoveToType();
						cmdInstance.CRCLCommand = moveCmd;

						string[] fields = l.Split(" ".ToCharArray(),10);
						moveCmd.EndPosition = new Schemas.CRCL.CommandInstance.PoseType();
						moveCmd.EndPosition.Point = new Schemas.CRCL.CommandInstance.PointType();
						moveCmd.EndPosition.Point.X = fields.Length > 1 ? decimal.Parse(fields[1]) : 0;
						moveCmd.EndPosition.Point.Y = fields.Length > 2 ? decimal.Parse(fields[2]) : 0;
						moveCmd.EndPosition.Point.Z = fields.Length > 3 ? decimal.Parse(fields[3]) : 0;
						moveCmd.EndPosition.XAxis = new Schemas.CRCL.CommandInstance.VectorType();
						moveCmd.EndPosition.XAxis.I = fields.Length > 4 ? decimal.Parse(fields[4]) : 0;
						moveCmd.EndPosition.XAxis.J = fields.Length > 5 ? decimal.Parse(fields[5]) : 0;
						moveCmd.EndPosition.XAxis.K = fields.Length > 6 ? decimal.Parse(fields[6]) : 0;
						moveCmd.EndPosition.ZAxis = new Schemas.CRCL.CommandInstance.VectorType();
						moveCmd.EndPosition.ZAxis.I = fields.Length > 7 ? decimal.Parse(fields[7]) : 0;
						moveCmd.EndPosition.ZAxis.J = fields.Length > 8 ? decimal.Parse(fields[8]) : 0;
						moveCmd.EndPosition.ZAxis.K = fields.Length > 9 ? decimal.Parse(fields[9]) : 0;
						moveCmd.CommandID = cid.ToString();
						String cmdXml = Schemas.CRCL.Utils.ToXML (cmdInstance);
						ac.Send (cmdXml);
						ac.sendDoneWaitOne();
					} else {
						Console.WriteLine("Command not recognized: "+l);
						Console.WriteLine("Commands include:");
						Console.WriteLine("init");
						Console.WriteLine("end");
						Console.WriteLine("quit");
						Console.WriteLine("move [x] [y] [z] [x.i] [x.j] [x.z] [z.i] [z.j] [z.k]");
						Console.WriteLine("");
						Console.WriteLine("Press enter on empty line to see status.");
					}
					if(debug) {
						Console.WriteLine("cid = "+cid);
					}
					cid++;
				}
			} catch (Exception e) {
				Console.WriteLine (e.ToString ());
			} finally {
				if (null != ac) {
					ac.Close ();
					ac = null;
				}
			}
		}
	}
}
