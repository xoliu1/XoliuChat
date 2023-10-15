package ChatRoomClient;

/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    // 定义当前线程所处理的Socket
    private Socket socket = null;
    // 该线程所处理的Socket所对应的输入流
    BufferedReader br = null;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        // 初始化该Socket对应的输入流
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                "utf-8"));
        System.out.println("读取数据成功！");
    }

    @Override
    public void run() {
        System.out.println("开始run");
        try {
            String content = null;
            // 采用循环不断从Socket中读取客户端发送过来的数据
            while ((content = readFromClient()) != null) {
                System.out.println("正在读取");
                // 遍历socketList中的每个Socket，将读到的内容向每个Socket发送一次
                for (Socket s : MyServer.socketList) {
                    OutputStream os = s.getOutputStream();
                    os.write((content + "\r\n").getBytes("utf-8"));
                    System.out.println(content);
                }
            }
        } catch (Exception e) {
            System.out.println("出现异常！");
            e.printStackTrace();
        }
    }

    /**
     * 定义读取客户端数据的方法
     *
     * @return
     */
    private String readFromClient() {
        try {
            System.out.println("fanhui");
            return br.readLine();
        }
        // 如果捕捉到异常，表明该Socket对应的客户端已经关闭
        catch (Exception e) {
            // 删除该Soc   ket
            System.out.println("出现异常");
            MyServer.socketList.remove(socket);
            e.printStackTrace();
        }
        return null;
    }
}
