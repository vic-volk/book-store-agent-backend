import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageHandler {

    String handleMessage(List<String> message);
}
