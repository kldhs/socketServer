import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        sendByThread("127.0.0.1", 8088, "a");
        System.out.println("aaaaa");
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(20000);
                    System.out.println("love");
                    sendByThread("127.0.0.1", 8088, "b");
                } catch (InterruptedException e) { }
            }
        }.start();
        System.out.println("bbbbb");


        //sendByThread("127.0.0.1", 8088, "c");
        //sendByThread("127.0.0.1", 8089, "D");
        //sendByThread("127.0.0.1", 8089, "E");
        //sendByThread("127.0.0.1", 8089, "F");
    }



}
