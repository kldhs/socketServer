import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServer {

    //public static String send(String ip, int port, String cmd) {
    //    return SendOper.send(ip, port, cmd);
    //}
    //
    //public static void sendByThread(final String ip, final int port, final String cmd) {
    //    Thread thread = new Thread(new Runnable() {
    //        @Override
    //        public void run() {
    //            String result = send(ip, port, cmd);
    //            System.out.println(result);
    //        }
    //    });
    //    thread.start();
    //}
    //
    //public static void main(String[] args) {
    //    sendByThread("127.0.0.1", 8088, "a");
    //    sendByThread("127.0.0.1", 8088, "b");
    //    sendByThread("127.0.0.1", 8088, "c");
    //    sendByThread("127.0.0.1", 8089, "D");
    //    sendByThread("127.0.0.1", 8089, "E");
    //    sendByThread("127.0.0.1", 8089, "F");
    //}
    //

//    public static void main(String[] args) {
//        ServerSocket serSocket = null;
//        try {
//            serSocket = new ServerSocket(8888);
//            System.out.println("***服务器即将启动，等待客户端的连接***");
//            Socket socket = serSocket.accept();
//            InputStream is = socket.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            String info = null;
//            while ((info = br.readLine()) != null) {
//                System.out.println("我是服务器，客户端说：" + info);
//            }
//            socket.shutdownInput();
//
//            OutputStream os= socket.getOutputStream();
//            PrintWriter pw = new PrintWriter(os);
//            pw.write("欢迎客户端");
//            pw.flush();
//
//            pw.close();
//            os.close();
//
//            br.close();
//            isr.close();
//            is.close();
//            socket.close();
//            serSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

        public static void main(String[] args) {

        try {
            ServerSocket serSocket = new ServerSocket(8888);
            Socket socket =null;
            int count =0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            while (true){
                socket = serSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                count++;
                System.out.println("客户端数量为："+count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
