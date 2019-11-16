using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace Lab5
{
    public class BaseTask
    {
        private static List<string> hostNames;

        /// <summary>
        /// run sync or async tasks
        /// </summary>
        /// <param name="hostnames">hostnames - websites</param>
        /// <param name="async">True to run AsyncTaskMechanism, False to run TaskMechanism</param>
        public static void run(List<string> hostnames, bool async)
        {
            hostNames = hostnames;
            var tasks = new List<Task>();

            for (var i = 0; i < hostnames.Count; i++)
            {
                if (async)
                {
                    tasks.Add(Task.Factory.StartNew(DoStartAsync, i));
                }
                else
                {
                    tasks.Add(Task.Factory.StartNew(DoStart, i));
                }
            }

            Task.WaitAll(tasks.ToArray());
        }

        /// <summary>
        /// start async calls
        /// </summary>
        /// <param name="idObject"> id of task/host </param>
        private static void DoStartAsync(object idObject)
        {
            var id = (int) idObject;

            StartAsyncClient(hostNames[id], id);
        }

        /// <summary>
        /// start sync calls
        /// </summary>
        /// <param name="idObject"> id of task/host </param>
        private static void DoStart(object idObject)
        {
            var id = (int) idObject;

            StartClient(hostNames[id], id);
        }


        /// <summary>
        /// start sync client
        /// </summary>
        /// <param name="host"> host name </param>
        /// <param name="id"> host id </param>
        private static void StartClient(string host, int id)
        {
            var ipHostInfo = Dns.GetHostEntry(host.Split('/')[0]); // get dns record
            var ipAddr = ipHostInfo.AddressList[0];
            var remEndPoint = new IPEndPoint(ipAddr, HttpParser.PORT); // basically stuff after "/" in the url

            var cl = new Socket(ipAddr.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            var state = new Obj
            {
                sock = cl,
                hostname = host.Split('/')[0],
                endpoint = host.Contains("/") ? host.Substring(host.IndexOf("/", StringComparison.Ordinal)) : "/",
                remoteEndPoint = remEndPoint,
                id = id
            }; // state object

            ConnectWrapper(state).Wait(); // connect to remote server
            SendWrapper(state, HttpParser.GetRequestString(state.hostname, state.endpoint))
                .Wait(); // request data from server
            ReceiveWrapper(state).Wait(); // receive server response

            Console.WriteLine(
                "{0}) Response received : expected {1} chars in body, got {2} chars (headers + body)",
                id, HttpParser.GetContentLen(state.responseContent.ToString()), state.responseContent.Length);

            // release the socket
            cl.Shutdown(SocketShutdown.Both);
            cl.Close();
        }

        /// <summary>
        /// start async client
        /// </summary>
        /// <param name="host"> host name </param>
        /// <param name="id"> host id </param>
        private static async void StartAsyncClient(string host, int id)
        {
            var ipHostInfo = Dns.GetHostEntry(host.Split('/')[0]);
            var ipAddress = ipHostInfo.AddressList[0];
            var remoteEndpoint = new IPEndPoint(ipAddress, HttpParser.PORT);

            // create the TCP/IP socket
            var client =
                new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp); // create client socket

            var state = new Obj
            {
                sock = client,
                hostname = host.Split('/')[0],
                endpoint = host.Contains("/") ? host.Substring(host.IndexOf("/", StringComparison.Ordinal)) : "/",
                remoteEndPoint = remoteEndpoint,
                id = id
            }; // state object

            await ConnectAsyncWrapper(state); // connect to remote server

            await SendAsyncWrapper(state,
                HttpParser.GetRequestString(state.hostname, state.endpoint)); // request data from the server

            await ReceiveAsyncWrapper(state); // receive server response

            Console.WriteLine(
                "{0} --> Response received : expected {1} chars in body, got {2} chars (headers + body)",
                id, HttpParser.GetContentLen(state.responseContent.ToString()), state.responseContent.Length);

            // release the socket
            client.Shutdown(SocketShutdown.Both);
            client.Close();
        }


        /// <summary>
        /// wrapper for async connect
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <returns> task </returns>
        private static async Task ConnectAsyncWrapper(Obj state)
        {
            state.sock.BeginConnect(state.remoteEndPoint, ConnectCallback, state);

            await Task.FromResult<object>(state.connectDone.WaitOne());
        }

        /// <summary>
        /// wrapper for sync connect
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <returns> task </returns>
        private static Task ConnectWrapper(Obj state)
        {
            state.sock.BeginConnect(state.remoteEndPoint, ConnectCallback, state);

            return Task.FromResult(state.connectDone.WaitOne());
        }

        /// <summary>
        /// connect callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void ConnectCallback(IAsyncResult ar)
        {
            // retrieve the details from the connection information wrapper
            var state = (Obj) ar.AsyncState;
            var clientSocket = state.sock;
            var clientId = state.id;
            var hostname = state.hostname;

            clientSocket.EndConnect(ar); // complete connection

            Console.WriteLine("{0} --> Socket connected to {1} ({2})", clientId, hostname, clientSocket.RemoteEndPoint);

            state.connectDone.Set(); // signal connection is up
        }

        /// <summary>
        /// send async wrapper
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <param name="data"> request to be sent </param>
        /// <returns> task </returns>
        private static async Task SendAsyncWrapper(Obj state, string data)
        {
            var byteData = Encoding.ASCII.GetBytes(data);

            // begin sending the data to the server  
            state.sock.BeginSend(byteData, 0, byteData.Length, 0, SendCallback, state);

            await Task.FromResult<object>(state.sendDone.WaitOne());
        }

        /// <summary>
        /// send sync wrapper
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <param name="data"> request to be sent </param>
        /// <returns> task </returns>
        private static Task SendWrapper(Obj state, string data)
        {
            // convert the string data to byte data using ASCII encoding.  
            var byteData = Encoding.ASCII.GetBytes(data);

            // begin sending the data to the server  
            state.sock.BeginSend(byteData, 0, byteData.Length, 0, SendCallback, state);

            return Task.FromResult(state.sendDone.WaitOne());
        }

        /// <summary>
        /// send callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void SendCallback(IAsyncResult ar)
        {
            var state = (Obj) ar.AsyncState;
            var clientSocket = state.sock;
            var clientId = state.id;

            var bytesSent = clientSocket.EndSend(ar); // complete sending the data to the server  

            Console.WriteLine("{0} --> Sent {1} bytes to server.", clientId, bytesSent);

            state.sendDone.Set(); // signal that all bytes have been sent
        }


        /// <summary>
        /// receive async wrapper
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <returns> task </returns>
        private static async Task ReceiveAsyncWrapper(Obj state)
        {
            // begin receiving the data from the server
            state.sock.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, ReceiveCallback, state);

            await Task.FromResult<object>(state.receiveDone.WaitOne());
        }

        /// <summary>
        /// receive sync wrapper
        /// </summary>
        /// <param name="state"> state of request </param>
        /// <returns> task </returns>
        private static Task ReceiveWrapper(Obj state)
        {
            // begin receiving the data from the server
            state.sock.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, ReceiveCallback, state);

            return Task.FromResult(state.receiveDone.WaitOne());
        }


        /// <summary>
        /// receive callback
        /// </summary>
        /// <param name="ar"> async result </param>
        private static void ReceiveCallback(IAsyncResult ar)
        {
            // retrieve the details from the connection information wrapper
            var state = (Obj) ar.AsyncState;
            var clientSocket = state.sock;

            try
            {
                // read data from the remote device.  
                var bytesRead = clientSocket.EndReceive(ar);

                // get from the buffer, a number of characters <= to the buffer size, and store it in the responseContent
                state.responseContent.Append(Encoding.ASCII.GetString(state.buffer, 0, bytesRead));

                // if the response header has not been fully obtained, get the next chunk of data
                if (!HttpParser.ResponseHeaderObtained(state.responseContent.ToString()))
                {
                    clientSocket.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, ReceiveCallback, state);
                }
                else
                {
                    var responseBody =
                        HttpParser.GetRespBody(state.responseContent.ToString()); // get the body (header is obtained)
                    Console.WriteLine(responseBody);
                    if (responseBody.Length < HttpParser.GetContentLen(state.responseContent.ToString())
                    ) // case when there is more data to receive
                    {
                        clientSocket.BeginReceive(state.buffer, 0, Obj.BUFF_SIZE, 0, ReceiveCallback, state);
                    }
                    else // case when there is no more data to be received
                    {
                        state.receiveDone.Set(); // signal that all bytes have been received  
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