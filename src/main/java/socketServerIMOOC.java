import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServerIMOOC {

        public static void main(String[] args) {
        try {
            ServerSocket serSocket = new ServerSocket(8888);
            Socket socket =null;
            int count =0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            while (true){
                socket = serSocket.accept();
                ServerThreadIMOOC serverThread = new ServerThreadIMOOC(socket);
                serverThread.start();
                count++;
                System.out.println("客户端数量为："+count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
