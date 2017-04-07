import socket

s = socket.socket()
host = "localhost"
port = 12345

s.connect((host, port))
print s.recv(1024)

messageToServer = raw_input("Message to Server: ")
s.send(messageToServer)
print s.recv(1024)

s.send("Goodbye Server")
s.close
