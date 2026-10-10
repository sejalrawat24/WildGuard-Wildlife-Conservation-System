
package com.wildlife.incident_service.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import com.wildlife.incident_service.model.Incident;
import com.wildlife.incident_service.service.IncidentService;

@Controller
@RequestMapping("/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Open the Thymeleaf HTML page
   

    // Get all incidents as JSON
    @GetMapping("/all")
    @ResponseBody
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // Get incident by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Incident> getIncidentById(
            @PathVariable long id) {

        Incident incident = incidentService.getIncidentById(id);

        if (incident == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(incident);
    }

    // Add incident
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addIncident(
            @RequestBody Incident incident) {

        int result = incidentService.addIncident(incident);

        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Incident added successfully");
        }

        return ResponseEntity.badRequest()
                .body("Failed to add incident");
    }

    // Update incident
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateIncident(
            @PathVariable long id,
            @RequestBody Incident incident) {

        int result = incidentService.updateIncident(id, incident);

        if (result > 0) {
            return ResponseEntity.ok("Incident updated successfully");
        }

        return ResponseEntity.notFound().build();
    }

    // Delete incident
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteIncident(
            @PathVariable long id) {

        int result = incidentService.deleteIncident(id);

        if (result > 0) {
            return ResponseEntity.ok("Incident deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public String showIncidentPage(Model model) {
        model.addAttribute("incident", new Incident());
        return "incidents";
    }
}
