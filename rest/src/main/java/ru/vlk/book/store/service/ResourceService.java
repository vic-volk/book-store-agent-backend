package ru.vlk.book.store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.repository.ResourceRepository;
import ru.vlk.book.store.model.Resource;

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
