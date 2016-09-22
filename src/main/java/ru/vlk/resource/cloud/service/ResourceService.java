package ru.vlk.resource.cloud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.resource.cloud.model.Resource;
import ru.vlk.resource.cloud.repository.ResourceRepository;

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
