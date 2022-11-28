import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


public class Server implements Runnable {

    public static ConcurrentHashMap<String, MySocket> llista = new ConcurrentHashMap<>();
    MySocket mysck;
    String nickname;
    public static boolean next = false;


    public Server(String nickname, MySocket mysck) throws IOException {
        this.mysck = mysck;
        this.nickname = nickname;
    }

    public static void main(String[] args) throws Exception {
        MyServerSocket serversocket = new MyServerSocket(5000);
        System.out.println("Servidor inicializado. Esperando a los usuarios...");

        while(true){
            MySocket mysocket = serversocket.accept();
            while (!next) {
                mysocket.printLine("Introduzca su nombre de usuario: ");
                String nickname = mysocket.readLine();

                if (llista.containsKey(nickname)) {
                    mysocket.printLine("El nombre d'usuario " + nickname + " ya está en uso.");

                }else {
                    System.out.println(nickname + " está en linea");
                    llista.put(nickname, mysocket);
                    new Thread(new Server(nickname, mysocket)).start();
                    next = true;
                }
            }
            next = false;
        }
    }

    @Override
    public void run() {
        String linea;

        while ((linea = mysck.readLine()) != null) {

                for (MySocket ms : llista.values()) {
                    if (ms != mysck) {
                        ms.printLine(nickname + ": " + linea);
                        System.out.println(nickname + " ha escrito: " + linea);
                    }
                }

        }
    }

    public boolean actiu(String nickname, String linea) {
        boolean activo = true;
        if (linea.equals("Desconectar")) {
            llista.remove(nickname);

            for (MySocket ms : llista.values()) {
                if (ms != mysck) {
                    ms.printLine(nickname + ": Desconnectar");
                    ms.printLine(nickname + ": : Ha acabado el chat.");
                    System.out.println(nickname + " ha escrit: Desconnectar");
                    System.out.println(nickname + " se encuentra desconectado.");
                }

            }
            activo = false;
        }
        return activo;
    }



}