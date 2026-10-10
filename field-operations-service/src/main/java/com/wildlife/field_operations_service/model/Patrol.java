
package com.wildlife.field_operations_service.model;

import java.time.LocalDate;

public class Patrol {

    private long patrol_id;
    private long zone_id;
    private long leader_staff_id;
    private LocalDate patrol_date;
    private String route_description;
    private String activities;
    private String status;

    public long getPatrol_id() {
        return patrol_id;
    }

    public void setPatrol_id(long patrol_id) {
        this.patrol_id = patrol_id;
    }

    public long getZone_id() {
        return zone_id;
    }

    public void setZone_id(long zone_id) {
        this.zone_id = zone_id;
    }

    public long getLeader_staff_id() {
        return leader_staff_id;
    }

    public void setLeader_staff_id(long leader_staff_id) {
        this.leader_staff_id = leader_staff_id;
    }

    public LocalDate getPatrol_date() {
        return patrol_date;
    }

    public void setPatrol_date(LocalDate patrol_date) {
        this.patrol_date = patrol_date;
    }

    public String getRoute_description() {
        return route_description;
    }

    public void setRoute_description(String route_description) {
        this.route_description = route_description;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
