import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServer {
    public static String send(String ip, int port, String cmd) {
        return SendOper.send(ip, port, cmd);
    }


    public static void sendByThread(final String ip, final int port, final String cmd) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = send(ip, port, cmd);
                System.out.println(result);
            }
        });
        thread.start();
    }
    public static void main(String[] args) {
        sendByThread("127.0.0.1", 8088, "a");
        //sendByThread("127.0.0.1", 8088, "b");
        //sendByThread("127.0.0.1", 8088, "c");
        sendByThread("127.0.0.1", 8089, "D");
        //sendByThread("127.0.0.1", 8089, "E");
        //sendByThread("127.0.0.1", 8089, "F");
    }
    //public static void main(String[] args) {
    //    try {
    //        ServerSocket ss = new ServerSocket(8888);
    //        System.out.println("启动服务器....");
    //        Socket s = ss.accept();
    //        System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
    //        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    //        //读取客户端发送来的消息
    //        String mess = br.readLine();
    //        System.out.println("服务器读取到客户端的消息为："+mess);
    //        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    //        String toClient = "服务器发送过来的消息";
    //        bw.write(toClient+"\n");
    //        bw.flush();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}
}
