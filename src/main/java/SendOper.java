import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SendOper {
    private static HashMap<String, SendOper> map = new HashMap<String, SendOper>();
    private String ip;
    private int port;
    static int a = 0;

    private SendOper(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    private synchronized static SendOper getSendOper(String ip, int port) {
//		synchronized (SendOper.class) {
        String key = getKey(ip, port);
        if (map.containsKey(key)) {
            a = 1;
            return map.get(key);
        } else {
            a = 0;
            SendOper oper = new SendOper(ip, port);
            map.put(key, oper);
            return oper;
        }
//		}
    }

    private static String getKey(String ip, int port) {
        return ip + ":" + port;
    }

    private String send(String cmd) {
        synchronized (this) {
            //synchronized (SendOper.class) {
            Socket s = null;
            BufferedWriter bw = null;
            BufferedReader br = null;
            try {
                if (a == 0) {
                    ServerSocket ss = new ServerSocket(port);
                    System.out.println("启动服务器....");
                    s = ss.accept();
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                }
                System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");
                //读取客户端发送来的消息
                String mess = br.readLine();
                String result = "send:" + getKey(ip, port) + mess;
                //向客户端发送来的消息
                String toClient = cmd;
                bw.write(toClient + "\n");
                bw.flush();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                
            }
        }
        return null;
    }


    public static String send(String ip, int port, String cmd) {
        SendOper oper = getSendOper(ip, port);
        return oper.send(cmd);
    }
}