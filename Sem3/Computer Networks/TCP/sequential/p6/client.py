# 6.   The client sends to the server a string and a character.
# The server returns to the client a list of all positions in the string where specified character is found.

import pickle
import socket

HOST = "127.0.0.1"
PORT = PORT = 65433

ls = ["asdfsdazaa", "a"]

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    data = pickle.dumps(ls)
    s.sendall(data)
    data = s.recv(1024)

print('Received: ', pickle.loads(data))
