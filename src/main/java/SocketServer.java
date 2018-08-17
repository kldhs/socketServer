public class SocketServer {

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
        //new Thread(){
        //    public void run(){
        //        try {
        //            Thread.sleep(20000);
        //            System.out.println("love");
        //
        //        } catch (InterruptedException e) { }
        //    }
        //}.start();
        sendByThread("127.0.0.1", 8088, "b");
        //sendByThread("127.0.0.1", 8088, "c");
        //sendByThread("127.0.0.1", 8089, "D");
        //sendByThread("127.0.0.1", 8089, "E");
        //sendByThread("127.0.0.1", 8089, "F");
    }
}
