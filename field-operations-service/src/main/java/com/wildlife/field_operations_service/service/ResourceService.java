package com.wildlife.field_operations_service.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.wildlife.field_operations_service.model.Resource;
import com.wildlife.field_operations_service.repository.ResourceRepo;

@Service
public class ResourceService {
    private final ResourceRepo repo;

    public ResourceService(ResourceRepo repo) {
        this.repo = repo;
    }

    public List<Resource> getAllResources() {
        return repo.getAllResources();
    }

    public Resource getResourceById(long id) {
        return repo.getResourceById(id);
    }

    public int addResource(Resource r) {
        return repo.addResource(r);
    }

    public int updateResource(long id, Resource r) {
        return repo.updateResource(id, r);
    }

    public int deleteResource(long id) {
        return repo.deleteResource(id);
    }
}