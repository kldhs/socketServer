import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SendOper {
    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    OutputStream os = null;
    PrintWriter pw = null;
    private static HashMap<String, SendOper> map = new HashMap<String, SendOper>();
    private String ip;
    private int port;

    private SendOper(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("已建立"+ip+":"+port+"的ServerSocket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized static SendOper getSendOper(String ip, int port) {
//		synchronized (SendOper.class) {
        String key = getKey(ip, port);
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
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
            try {
                System.out.println("发送消息："+cmd+"的服务端socket建立，等待客户端连接");
                socket = serverSocket.accept();
                //从客户端读取消息
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String info = null;
                while ((info = br.readLine()) != null) {
                    System.out.println("我是服务器，客户端说：" + info);
                    System.out.println("*************接收客户端消息完毕");
                }
                //关闭socket输入流
                socket.shutdownInput();

                //向客户端输出消息
                os = socket.getOutputStream();
                pw = new PrintWriter(os);
                pw.write(cmd);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pw != null) {
                        pw.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                    if (isr != null) {
                        isr.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "*************与"+getKey(ip, port)+"相互通讯完毕";
        }
    }

    public static String send(String ip, int port, String cmd) {
        SendOper oper = getSendOper(ip, port);
        return oper.send(cmd);
    }

}