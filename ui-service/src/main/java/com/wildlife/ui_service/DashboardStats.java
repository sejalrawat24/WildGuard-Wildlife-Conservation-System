
package com.wildlife.ui_service;
public class DashboardStats {

    private long totalAnimals;
    private long healthyAnimals;
    private long injuredAnimals;

    private long totalProgrammes;
    private long totalIncidents;
    private long totalRescues;

    private long totalPatrols;
    private long scheduledPatrols;
    private long ongoingPatrols;
    private long completedPatrols;

    private long totalStaff;
    private long totalResources;

    public long getTotalAnimals() { return totalAnimals; }
    public void setTotalAnimals(long value) { totalAnimals = value; }

    public long getHealthyAnimals() { return healthyAnimals; }
    public void setHealthyAnimals(long value) { healthyAnimals = value; }

    public long getInjuredAnimals() { return injuredAnimals; }
    public void setInjuredAnimals(long value) { injuredAnimals = value; }

    public long getTotalProgrammes() { return totalProgrammes; }
    public void setTotalProgrammes(long value) { totalProgrammes = value; }

    public long getTotalIncidents() { return totalIncidents; }
    public void setTotalIncidents(long value) { totalIncidents = value; }

    public long getTotalRescues() { return totalRescues; }
    public void setTotalRescues(long value) { totalRescues = value; }

    public long getTotalPatrols() { return totalPatrols; }
    public void setTotalPatrols(long value) { totalPatrols = value; }

    public long getScheduledPatrols() { return scheduledPatrols; }
    public void setScheduledPatrols(long value) {
        scheduledPatrols = value;
    }

    public long getOngoingPatrols() { return ongoingPatrols; }
    public void setOngoingPatrols(long value) {
        ongoingPatrols = value;
    }

    public long getCompletedPatrols() { return completedPatrols; }
    public void setCompletedPatrols(long value) {
        completedPatrols = value;
    }

    public long getTotalStaff() { return totalStaff; }
    public void setTotalStaff(long value) { totalStaff = value; }

    public long getTotalResources() { return totalResources; }
    public void setTotalResources(long value) { totalResources = value; }
}
