import socket
import pickle

HOST = '192.168.43.149'
PORT = 65433

N = 400
M = 300

matr = [[0 for i in range(N)] for j in range(M)]


def get_let():
    return input("Give letter")[0]


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))

    while True:
        letter = get_let()
        s.sendall(pickle.dumps(letter))
        data = s.recv(1024).decode()
        mistakes = s.recv(1024).decode()
        print("Word is: " + data)
