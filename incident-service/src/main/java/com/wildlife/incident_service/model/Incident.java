
package com.wildlife.incident_service.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Incident {

    private long incident_id;
    private String incident_type;
    private String description;
    private long zone_id;
    private long animal_id;
    private long reported_by;
    private long assigned_to;
    private String priority;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reported_at;

    public Incident() {
    }

    public long getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(long incident_id) {
        this.incident_id = incident_id;
    }

    public String getIncident_type() {
        return incident_type;
    }

    public void setIncident_type(String incident_type) {
        this.incident_type = incident_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getZone_id() {
        return zone_id;
    }

    public void setZone_id(long zone_id) {
        this.zone_id = zone_id;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }

    public long getReported_by() {
        return reported_by;
    }

    public void setReported_by(long reported_by) {
        this.reported_by = reported_by;
    }

    public long getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(long assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getReported_at() {
        return reported_at;
    }

    public void setReported_at(LocalDateTime reported_at) {
        this.reported_at = reported_at;
    }
}
