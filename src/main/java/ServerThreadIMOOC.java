import java.io.*;
import java.net.Socket;

public class ServerThreadIMOOC extends Thread {

    Socket socket = null;

    public ServerThreadIMOOC(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说：" + info);
            }
            socket.shutdownInput();
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("欢迎客户端");
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
                if (br !=null) {
                    br.close();
                }
                if (isr!=null) {
                    isr.close();
                }
                if (is!=null) {
                    is.close();
                }
                if (socket!=null) {
                    socket.close();
                }
                System.out.println("end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
