import pickle
import socket

HOST = '127.0.0.1'
PORT = 65433

while True:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                data = pickle.loads(data)
                s = sum(data)
                s = str(s)
                conn.sendall(s.encode())
