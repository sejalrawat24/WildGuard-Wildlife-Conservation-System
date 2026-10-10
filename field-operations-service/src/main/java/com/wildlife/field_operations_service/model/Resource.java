
package com.wildlife.field_operations_service.model;

public class Resource {

    private long resource_id;
    private String resource_name;
    private String resource_type;
    private String registration_number;
    private String condition_status;
    private String availability_status;
    private long assigned_staff_id;

    public long getResource_id() {
        return resource_id;
    }

    public void setResource_id(long resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getCondition_status() {
        return condition_status;
    }

    public void setCondition_status(String condition_status) {
        this.condition_status = condition_status;
    }

    public String getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(String availability_status) {
        this.availability_status = availability_status;
    }

    public long getAssigned_staff_id() {
        return assigned_staff_id;
    }

    public void setAssigned_staff_id(long assigned_staff_id) {
        this.assigned_staff_id = assigned_staff_id;
    }
}
