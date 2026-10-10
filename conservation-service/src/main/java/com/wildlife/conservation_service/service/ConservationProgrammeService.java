
package com.wildlife.conservation_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.conservation_service.model.ConservationProgramme;
import com.wildlife.conservation_service.repo.ConservationProgrammeRepo;

@Service
public class ConservationProgrammeService {

    private final ConservationProgrammeRepo repo;

    public ConservationProgrammeService(ConservationProgrammeRepo repo) {
        this.repo = repo;
    }

    public List<ConservationProgramme> getAll() {
        return repo.getAll();
    }

    public ConservationProgramme getById(long id) {
        return repo.getById(id);
    }

    public int add(ConservationProgramme p) {
        return repo.add(p);
    }

    public int update(long id, ConservationProgramme p) {
        return repo.update(id, p);
    }

    public int delete(long id) {
        return repo.delete(id);
    }
}
