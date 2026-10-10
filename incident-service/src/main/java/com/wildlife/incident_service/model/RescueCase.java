
package com.wildlife.incident_service.model;

import java.time.LocalDate;

public class RescueCase {

    private long rescue_Id;
    private long incident_id;
    private long animal_id;
    private long assigned_staff_id;
    private String treatment_details;
    private String rehabilitation_status;
    private String outcome;
    private LocalDate rescue_date;
    private LocalDate release_date;

    public RescueCase() {
    }

    public long getRescue_id() {
        return rescue_Id;
    }

    public void setRescue_id(long rescue_id) {
        this.rescue_Id = rescue_id;
    }

    public long getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(long incident_id) {
        this.incident_id = incident_id;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }

    public long getAssigned_staff_id() {
        return assigned_staff_id;
    }

    public void setAssigned_staff_id(long assigned_staff_id) {
        this.assigned_staff_id = assigned_staff_id;
    }

    public String getTreatment_details() {
        return treatment_details;
    }

    public void setTreatment_details(String treatment_details) {
        this.treatment_details = treatment_details;
    }

    public String getRehabilitation_status() {
        return rehabilitation_status;
    }

    public void setRehabilitation_status(String rehabilitation_status) {
        this.rehabilitation_status = rehabilitation_status;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDate getRescue_date() {
        return rescue_date;
    }

    public void setRescue_date(LocalDate rescue_date) {
        this.rescue_date = rescue_date;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }
}
