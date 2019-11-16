import socket

HOST = socket.gethostname()
PORT = 65434
BUFFER_SIZE = 1024

tcpClient = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcpClient.connect((HOST, PORT))
msg = input("Insert your coordinates or type 'exit': ")
while msg != 'exit':
    tcpClient.sendall(msg.encode('ascii'))
    data = tcpClient.recv(BUFFER_SIZE).decode('ascii')
    print("Received: ", data)
    msg = input("Insert your coordinates or type 'exit': ")

tcpClient.close()
