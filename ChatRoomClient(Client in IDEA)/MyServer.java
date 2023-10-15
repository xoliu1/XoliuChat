package ChatRoomClient;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

    // 定义保存所有Socket的集合
    public static ArrayList<Socket> socketList = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(11233);
        System.out.println("服务器创建成功！");
        System.out.println("等待客戶端的连接。。。");
        while (true) {
            // 此行代码会阻塞，等待用户的连接
            Socket socket = ss.accept();
            System.out.println("有客户端连接进来！");
            socketList.add(socket);
            // 每当客户端连接后启动一条ServerThread线程为该客户端服务;
            new Thread(new ServerThread(socket)).start();
        }
    }

}
