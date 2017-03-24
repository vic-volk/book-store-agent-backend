import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TalkingAgent implements Runnable {

    @Autowired
    private MessageHandler messageHandler;

    private List<String> messageBox;
    private List<String> internalMemory;
    private int sleepTimeout = 2000;

    public TalkingAgent(List<String> messageBox) {
        this.messageBox = messageBox;
        this.internalMemory = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            mainCycle();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void mainCycle() throws InterruptedException {
        while (true) {
            Thread.sleep(sleepTimeout);
            if(isMessageBoxNotEmpty()) {
                readAndAnswer();
            }
        }
    }

    private boolean isMessageBoxNotEmpty() {
        return messageBox.size() > 0;
    }

    private void readAndAnswer() {
        synchronized (messageBox) {
            internalMemory.addAll(messageBox);
            messageBox.clear();
            System.out.println(handleMessage(internalMemory));
        }
    }

    private String handleMessage(List<String> message) {
        return "Hi!";
    }
}
