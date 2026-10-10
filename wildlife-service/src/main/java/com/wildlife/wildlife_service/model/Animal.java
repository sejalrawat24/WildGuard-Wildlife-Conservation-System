package com.wildlife.wildlife_service.model;


public class Animal {

    private int animal_id;
    private String species_name;
    private String species_tag;
    private int age;
    private String health_Status;
    private long zone_id;
    private String last_seen_location;

    public Animal() {
    }

    public Animal(int animal_id, String species_name,
                  String species_tag, int age,
                  String health_Status, long zone_id,
                  String last_seen_location) {
        this.animal_id = animal_id;
        this.species_name = species_name;
        this.species_tag = species_tag;
        this.age = age;
        this.health_Status = health_Status;
        this.zone_id = zone_id;
        this.last_seen_location = last_seen_location;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    public String getSpecies_tag() {
        return species_tag;
    }

    public void setSpecies_tag(String species_tag) {
        this.species_tag = species_tag;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealth_Status() {
        return health_Status;
    }

    public void setHealth_Status(String health_Status) {
        this.health_Status = health_Status;
    }

    public long getZone_id() {
        return zone_id;
    }

    public void setZone_id(long zone_id) {
        this.zone_id = zone_id;
    }

    public String getLast_seen_location() {
        return last_seen_location;
    }

    public void setLast_seen_location(String last_seen_location) {
        this.last_seen_location = last_seen_location;
    }
}