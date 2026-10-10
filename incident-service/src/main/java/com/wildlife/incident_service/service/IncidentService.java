
package com.wildlife.incident_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.incident_service.model.Incident;
import com.wildlife.incident_service.repository.IncidentRepo;

@Service
public class IncidentService {

    private final IncidentRepo incidentRepo;

    public IncidentService(IncidentRepo incidentRepo) {
        this.incidentRepo = incidentRepo;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepo.getAllIncidents();
    }

    public Incident getIncidentById(long id) {
        return incidentRepo.getIncidentById(id);
    }

    public int addIncident(Incident incident) {
        return incidentRepo.addIncident(incident);
    }

    public int updateIncident(long id, Incident incident) {
        return incidentRepo.updateIncident(id, incident);
    }

    public int deleteIncident(long id) {
        return incidentRepo.deleteIncident(id);
    }
}
