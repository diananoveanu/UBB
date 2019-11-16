import pickle
import socket

HOST = "127.0.0.1"
PORT = PORT = 65433


def positions(st, c):
    lst = []
    for i in range(0, len(st)):
        if st[i] == c:
            lst.append(i)
    return lst


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
                s = pickle.dumps(positions(data[0], data[1]))
                conn.sendall(s.encode())
