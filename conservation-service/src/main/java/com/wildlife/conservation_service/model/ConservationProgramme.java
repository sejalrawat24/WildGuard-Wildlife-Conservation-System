
package com.wildlife.conservation_service.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConservationProgramme {

    private long programme_id;
    private String programme_name;
    private String description;
    private long coordinator_id;
    private BigDecimal target;
    private BigDecimal achieved;
    private LocalDate start_date;
    private LocalDate end_date;
    private String status;

    public ConservationProgramme() {
    }

    public long getProgramme_id() {
        return programme_id;
    }

    public void setProgramme_id(long programme_id) {
        this.programme_id = programme_id;
    }

    public String getProgramme_name() {
        return programme_name;
    }

    public void setProgramme_name(String programme_name) {
        this.programme_name = programme_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(long coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public BigDecimal getAchieved() {
        return achieved;
    }

    public void setAchieved(BigDecimal achieved) {
        this.achieved = achieved;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
