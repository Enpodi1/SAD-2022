import socket
import threading
from tokenize import String


sc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sc.connect(('127.0.0.1', 8080))

def recibir_msg():
    while True:
        try:
            msg = sc.recv(1024).decode(encoding="utf-8").rstrip()
            if msg == 'CLOSE':
                print("\033[1;31m"+"Conexion cerrada"+"\033[0m")
                self.sc.close()
                break
            else:
                print(msg)
        except Exception as e:
            print(e)
            print("\033[2;31m"+"Ha habido algun error"+"\033[0m")
            sc.close()
            break

def enviar_msg(self):
    while True:
        msg = input()
        if(msg == 'CLOSE'):
            print("\n\033[6;31m"+"Cerrando conexion..."+"\033[0m")
            sc.send('CLOSE'.encode('ascii'))
            quit()
        else:
            msg_f = "\033[3;33m"+msg+"\033[0m"
            sc.send(str.encode(msg_f))

receive_thread = threading.Thread(target=mysc.recibir_msg)
receive_thread.start()
write_thread = threading.Thread(target=mysc.enviar_msg)
write_thread.start()