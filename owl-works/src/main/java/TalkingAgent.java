import java.util.List;

public class TalkingAgent implements Runnable {

    private static List<String> messageBox;
    private int sleepTimeout = 2000;

    @Override
    public void run() {
        try {
            mainCycle();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void mainCycle() throws InterruptedException {
        Thread.sleep(sleepTimeout);
        if(isMessageBoxNotEmpty()) {
            readAndAnswer();
        }
    }

    private boolean isMessageBoxNotEmpty() {
        return messageBox.size() > 0;
    }

    private void readAndAnswer() {
        System.out.println("Hi!");
    }
}
