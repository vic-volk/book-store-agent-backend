package ru.vlk.book.store.rest.model;

import ru.vlk.book.store.elastic.model.Notice;

import java.util.Date;

public class NoticeBean {

    private String heading;
    private String text;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Notice notice(NoticeBean bean) {
        Notice notice = new Notice();
        notice.setDate(new Date());
        notice.setText(bean.getText());
        notice.setHeading(bean.getHeading());
        return notice;
    }
}
