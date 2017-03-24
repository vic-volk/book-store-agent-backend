import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> messageBox = new ArrayList<>();

        Thread t1 = new Thread(new TalkingAgent(messageBox));
        Thread t2 = new Thread(new UserClient(messageBox));
        t1.start();
        t2.start();
    }
}
