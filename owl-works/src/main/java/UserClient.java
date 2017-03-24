import java.util.List;
import java.util.Scanner;

public class UserClient implements Runnable {

    private List<String> messageBox;
    private Scanner scanner;

    public UserClient(List<String> messageBox) {
        this.messageBox = messageBox;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Please, enter question:");
                messageBox.add(scanner.nextLine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
