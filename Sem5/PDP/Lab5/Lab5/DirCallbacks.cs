using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Lab5
{
    public class DirCallbacks
    {
        private static List<string> HOSTS; // list of websites

        /// <summary>
        /// run direct callbacks mechanism
        /// </summary>
        /// <param name="hostnames"> list of host names </param>
        public static void run(List<string> hostnames)
        {
            HOSTS = hostnames;

            for (var i = 0; i < HOSTS.Count; i++)
            {
                StartClient(HOSTS[i], i);
                Thread.Sleep(2000);
            }
        }

        /// <summary>
        /// start client server
        /// </summary>
        /// <param name="host"> host name </param>
        /// <param name="id"> id of host/task </param>
        private static void StartClient(string host, int id)
        {
            var ipHostInfo = Dns.GetHostEntry(host.Split('/')[0]); // get host dns entry
            var ipAddress = ipHostInfo.AddressList[0]; // separate ip of host
            var remoteEndpoint = new IPEndPoint(ipAddress, HttpParser.PORT); // create endpoint

            var client =
                new Socket(ipAddress.AddressFamily, SocketType.Stream,
                    ProtocolType.Tcp); // create client socket

            var state = new Obj
            {
                sock = client,
                hostname = host.Split('/')[0],
                endpoint = host.Contains("/") ? host.Substring(host.IndexOf("/")) : "/",
                remoteEndPoint = remoteEndpoint,
                id = id
            }; // state object

            state.sock.BeginConnect(state.remoteEndPoint, Connected, state); // connect to the remote endpoint  
        }

        /// <summary>
        /// connect callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void Connected(IAsyncResult ar)
        {
            var state = (Obj) ar.AsyncState; // conn state
            var clientSocket = state.sock;
            var clientId = state.id;
            var hostname = state.hostname;

            clientSocket.EndConnect(ar); // end connection
            Console.WriteLine("{0} --> Socket connected to {1} ({2})", clientId, hostname, clientSocket.RemoteEndPoint);

            // convert the string data to byte data using ASCII encoding.  
            var byteData = Encoding.ASCII.GetBytes(HttpParser.GetRequestString(state.hostname, state.endpoint));

            // begin sending the data to the server  
            state.sock.BeginSend(byteData, 0, byteData.Length, 0, Sent, state);
        }

        /// <summary>
        /// sent callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void Sent(IAsyncResult ar)
        {
            var state = (Obj) ar.AsyncState;
            var clientSocket = state.sock;
            var clientId = state.id;

            // send data to server
            var bytesSent = clientSocket.EndSend(ar);
            Console.WriteLine("{0} --> Sent {1} bytes to server.", clientId, bytesSent);
            
            // server response (data)
            state.sock.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, Receiving, state);
        }

        /// <summary>
        /// receive callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void Receiving(IAsyncResult ar)
        {
            // retrieve the details from the connection information wrapper
            var state = (Obj) ar.AsyncState;
            var clientSocket = state.sock;
            var clientId = state.id;

            try
            {
                var bytesRead = clientSocket.EndReceive(ar); // read data from remove

                // get from the buffer, a number of characters <= to the buffer size, and store it in the responseContent
                state.responseContent.Append(Encoding.ASCII.GetString(state.buffer, 0, bytesRead));

                // if the response header has not been fully obtained, get the next chunk of data
                if (!HttpParser.ResponseHeaderObtained(state.responseContent.ToString()))
                {
                    clientSocket.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, Receiving, state);
                }
                else
                {
                    var responseBody =
                        HttpParser.GetRespBody(state.responseContent
                            .ToString()); // get body when header is fully obtained

                    // the custom header parser is being used to check if the data received so far has the length
                    // specified in the response headers
                    var contentLengthHeaderValue = HttpParser.GetContentLen(state.responseContent.ToString());
                    if (responseBody.Length < contentLengthHeaderValue) // case when there is more data to receive
                    {
                        clientSocket.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, Receiving, state);
                    }
                    else // case when there is no more data to be received
                    {
                        foreach (var i in state.responseContent.ToString().Split('\r', '\n'))
                            Console.WriteLine(i);
                        Console.WriteLine(
                            "{0} --> Response received: expected {1} chars in body, got {2} chars (headers + body)",
                            clientId, contentLengthHeaderValue, state.responseContent.Length);

                        clientSocket.Shutdown(SocketShutdown.Both); // free socket
                        clientSocket.Close();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }
    }
}