package ru.vlk.book.store.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Notice;
import ru.vlk.book.store.rest.model.NoticeBean;
import ru.vlk.book.store.service.NoticeService;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("/notice")
public class NoticeResource {

    @Autowired
    private NoticeService noticeService;

    @POST
    public void indexSample() {
        Notice notice = new Notice();
        notice.setText("test text");
        noticeService.indexNotice(notice);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Notice indexNotice(NoticeBean bean) {
        return noticeService.indexNotice(NoticeBean.notice(bean));
    }

    @GET
    @Produces("application/json")
    public List<Notice> listAll() {
        return noticeService.listAll("*:*").getContent();
    }
}
