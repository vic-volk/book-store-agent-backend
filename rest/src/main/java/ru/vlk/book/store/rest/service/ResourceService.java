package ru.vlk.book.store.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Resource;
import ru.vlk.book.store.elastic.repository.ResourceRepository;

import javax.inject.Inject;

@Component
public class ResourceService {

    @Inject
    private ResourceRepository resourceRepository;

    public void indexSample() {
        Resource resource = new Resource();
        resource.setTitle("title");

        resourceRepository.save(resource);
    }

    public Page<Resource> listAll() {
        return resourceRepository.findAll(new PageRequest(0, 10));
    }


}
