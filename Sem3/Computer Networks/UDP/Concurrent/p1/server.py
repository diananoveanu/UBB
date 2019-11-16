import socket
import sys
from threading import Thread
import pickle

HOST = '127.0.0.1'
PORT = 65433


def listenToClient(client, data, address):
    size = 1024
    while True:
        try:
            sent = sock.sendto(data, address)
            print('sent %s bytes b,ack to %s' % (sent, address))
            data, addr = client.recvfrom(size)
            if not data:
                break
        except:
            client.close()
            return False


# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the port
server_address = (HOST, PORT)
print('starting up on %s port %s' % server_address)
sock.bind(server_address)

threads = []

while True:
    print('\nwaiting to receive message')
    data, addr = sock.recvfrom(1024)

    print('received %s bytes from %s' % (len(data), addr))

    if data:
        t = Thread(target=listenToClient, args=(sock, data, addr))
        threads.append(t)
        t.start()
    else:
        raise socket.error('Client disconnected')

for t in threads:
    t.join()

