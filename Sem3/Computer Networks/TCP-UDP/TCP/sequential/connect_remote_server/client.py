import pickle
import socket
import struct

HOST = '172.20.10.3'
PORT = 65433

# connect to python server

passwd = "Teiu"

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    data = pickle.dumps(passwd)
    s.sendall(data)
    data = s.recv(1024)

print('Received: ', data.decode())

# connect to C server

HOST = '172.20.10.2'
PORT = 65437
string = 'sda'
# send string
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall(bytes(string, encoding='utf-8'))
    # data = s.recv(1024)

# send int
i = 87

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall(struct.pack('!I', i))
# if 2 bytes -> h
# if 4 bytes -> i
print('Received: ', struct.unpack('!h', data)[0])
