import socket

s = socket.socket()
host = "localhost"
port = 12345
s.bind((host, port))

s.listen(5)
while True:
   c, addr = s.accept()
   print("Got connection from", addr)
   c.send("Thank you for connecting")
   print c.recv(1024)
   messageToClient = raw_input("Answer to client: ")
   c.send(messageToClient)
   c.send("\nGoodbye Client")
   print c.recv(1024)
   c.close
