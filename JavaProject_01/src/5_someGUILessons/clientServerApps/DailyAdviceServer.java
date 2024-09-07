import java.io.*;
import java.net.*;

public class DailyAdviceServer {
    String[] adviceList = {
        "Пей пиво!",
        "Ешь мясо!", 
        "Пей пиво!", 
        "Ешь мясо!",
        "Закусывай!",
        "Возможно вам стоит выпить холодненького!",
        "Ешьте мясо, пейте пиво!"};
    
    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) { 
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }

}
