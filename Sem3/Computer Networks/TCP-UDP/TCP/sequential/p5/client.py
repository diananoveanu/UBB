# 5.   The client sends to the server an integer. The server returns the list of divisors for the specified number.
import pickle
import socket

HOST = "127.0.0.1"
PORT = 65433

nb = 12

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    data = pickle.dumps(nb)
    s.sendall(data)
    data = s.recv(1024)

print('Received: ', pickle.loads(data))
