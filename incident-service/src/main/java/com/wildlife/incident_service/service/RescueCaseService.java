
package com.wildlife.incident_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.incident_service.model.RescueCase;
import com.wildlife.incident_service.repository.RescueCaseRepo;

@Service
public class RescueCaseService {

    private final RescueCaseRepo rescueCaseRepo;

    public RescueCaseService(RescueCaseRepo rescueCaseRepo) {
        this.rescueCaseRepo = rescueCaseRepo;
    }

    // Get all rescue cases
    public List<RescueCase> getAllRescueCases() {
        return rescueCaseRepo.getAllRescueCases();
    }

    // Get rescue case by ID
    public RescueCase getRescueCaseById(long id) {
        return rescueCaseRepo.getRescueCaseById(id);
    }

    // Add rescue case
    public int addRescueCase(RescueCase rescueCase) {
        return rescueCaseRepo.addRescueCase(rescueCase);
    }

    // Update rescue case
    public int updateRescueCase(long id, RescueCase rescueCase) {
    	
    	return rescueCaseRepo.updateRescueCase(rescueCase);
    }

    // Delete rescue case
    public int deleteRescueCase(long id) {
        return rescueCaseRepo.deleteRescueCase(id);
    }
}
