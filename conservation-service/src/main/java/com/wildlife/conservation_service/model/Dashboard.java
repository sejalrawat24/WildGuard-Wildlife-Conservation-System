
package com.wildlife.conservation_service.model;

public class Dashboard {

    private int totalAnimals;
    private int totalProtectedAreas;
    private int totalProgrammes;
    private int totalIncidents;
    private int totalRescueCases;

    public Dashboard() {
    }

    public int getTotalAnimals() {
        return totalAnimals;
    }

    public void setTotalAnimals(int totalAnimals) {
        this.totalAnimals = totalAnimals;
    }

    public int getTotalProtectedAreas() {
        return totalProtectedAreas;
    }

    public void setTotalProtectedAreas(int totalProtectedAreas) {
        this.totalProtectedAreas = totalProtectedAreas;
    }

    public int getTotalProgrammes() {
        return totalProgrammes;
    }

    public void setTotalProgrammes(int totalProgrammes) {
        this.totalProgrammes = totalProgrammes;
    }

    public int getTotalIncidents() {
        return totalIncidents;
    }

    public void setTotalIncidents(int totalIncidents) {
        this.totalIncidents = totalIncidents;
    }

    public int getTotalRescueCases() {
        return totalRescueCases;
    }

    public void setTotalRescueCases(int totalRescueCases) {
        this.totalRescueCases = totalRescueCases;
    }
}
