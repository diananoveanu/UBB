# 5.   The client sends to the server an integer.
# The server returns the list of divisors for the specified number.
import pickle
import socket

HOST = "127.0.0.1"
PORT = 65433


def all_div(n):
    lst = []
    for i in range(1, n + 1):
        if n % i is 0:
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
                s = pickle.dumps(all_div(data))
                conn.sendall(s)
