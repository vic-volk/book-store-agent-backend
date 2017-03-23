import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Thread t = new Thread(new TalkingAgent());
        t.start();

        Scanner s = new Scanner(System.in);
        System.out.println(s.next());
    }
}
