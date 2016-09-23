package ru.vlk.book.store.model;

import java.util.Date;
import java.util.Map;

public class Answer {

    private String text;
    private Map<Date, String> messages;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<Date, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<Date, String> messages) {
        this.messages = messages;
    }
}
