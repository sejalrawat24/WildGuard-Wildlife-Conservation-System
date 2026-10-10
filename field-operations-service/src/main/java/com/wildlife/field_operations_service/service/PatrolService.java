package com.wildlife.field_operations_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.field_operations_service.model.Patrol;
import com.wildlife.field_operations_service.repository.PatrolRepo;

@Service
public class PatrolService {

    private final PatrolRepo patrolRepo;

    public PatrolService(PatrolRepo patrolRepo) {
        this.patrolRepo = patrolRepo;
    }

    public List<Patrol> getAllPatrols() {
        return patrolRepo.getAllPatrols();
    }

    public Patrol getPatrolById(long id) {
        return patrolRepo.getPatrolById(id);
    }

    public int addPatrol(Patrol patrol) {
        return patrolRepo.addPatrol(patrol);
    }

    public int updatePatrol(long id, Patrol patrol) {
        return patrolRepo.updatePatrol(id, patrol);
    }

    public int deletePatrol(long id) {
        return patrolRepo.deletePatrol(id);
    }
}