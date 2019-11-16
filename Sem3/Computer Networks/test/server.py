import socket
import pickle
import numpy as np
from threading import Thread, Lock

HOST = socket.gethostname()
PORT = 65434

N = 13  # rows
M = 13  # columns

matr = np.zeros((M, N), dtype=np.uint8)

TRIANGLE_DIM = 4
RECTANGLE_L = 8
RECTANGLE_H = 4

mylock = Lock()


def draw_triangle(x, y):
    if y + TRIANGLE_DIM > M or x + TRIANGLE_DIM > N:
        return False

    for i in range(x, x + TRIANGLE_DIM):
        matr[i][y] = 123
    for j in range(y, y + TRIANGLE_DIM):
        matr[x][j] = 123
    for j in range(y, y + TRIANGLE_DIM):
        for i in range(x, x + TRIANGLE_DIM):
            if i - x + j - y - 1 == TRIANGLE_DIM:
                matr[i - 1][j - 1] = 123
    return True


def draw_rectangle(x, y):
    if x + RECTANGLE_H >= M or y + RECTANGLE_L >= N:
        return False

    for i in range(x, x + RECTANGLE_L - 1):
        matr[i][y] = 76
        matr[i][y + RECTANGLE_H - 1] = 76
    for j in range(y, y + RECTANGLE_H - 1):
        matr[x][j] = 76
        matr[x + RECTANGLE_L - 1][j] = 76
    matr[x + RECTANGLE_L - 1][y + RECTANGLE_H - 1] = 76
    return True


class ClientThread(Thread):
    def __init__(self, conn, ip, port):
        Thread.__init__(self)
        self.conn = conn
        self.ip = ip
        self.port = port
        print("Thread created for " + ip + ":" + str(port))

    def run(self):
        while True:
            data = self.conn.recv(2048).decode('ascii')
            print("Receieved data: ", data)
            coord = data.split(',')
            is_possible = False
            mylock.acquire()
            if coord[2] == 'tri':
                is_possible = draw_triangle(int(coord[0]), int(coord[1]))
            elif coord[2] == 'rect':
                is_possible = draw_rectangle(int(coord[0]), int(coord[1]))
            print(matr)
            mylock.release()
            if is_possible:
                msg = "Success"
            else:
                msg = "Cannot draw"
            self.conn.send(msg.encode('ascii'))


tcpServer = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcpServer.bind((HOST, PORT))
threads = []

while True:
    tcpServer.listen(5)
    print("Waiting for connection from clients...")
    conn, (ip, port) = tcpServer.accept()
    newThread = ClientThread(conn, ip, port)
    newThread.start()
    threads.append(newThread)

for thread in threads:
    thread.join()
