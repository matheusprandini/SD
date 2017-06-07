import socket
import struct
import sys

multicast_addr = '224.0.0.1'
port = 3000

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
ttl = struct.pack('b', 1)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, ttl)

messageToServer = raw_input("Message to Server: ")

sock.sendto(messageToServer, (multicast_addr, port))
sock.close()
