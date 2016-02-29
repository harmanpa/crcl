using System;

namespace  Schemas.CRCL
{
	public class Utils
	{
		public Utils ()
		{
		}

		public class Utf8StringWriter : System.IO.StringWriter
		{
			public override System.Text.Encoding Encoding
			{
				get { return System.Text.Encoding.UTF8; }
			}
		}
		public static string ToXML<T>(T dataToSerialize)
		{
			var stringwriter = new Utf8StringWriter();
			var serializer = new System.Xml.Serialization.XmlSerializer(typeof(T));
			//			var memoryStream = new System.IO.MemoryStream();
			//			var streamWriter = new System.IO.StreamWriter(memoryStream, System.Text.Encoding.UTF8);
			serializer.Serialize(stringwriter, dataToSerialize);
			return stringwriter.ToString ();
		}

		public static T FromXML<T>(string xmlText)
		{
			var stringReader = new System.IO.StringReader(xmlText);
			var serializer = new System.Xml.Serialization.XmlSerializer(typeof(T));
			return (T)serializer.Deserialize(stringReader);
		}
	}
}

