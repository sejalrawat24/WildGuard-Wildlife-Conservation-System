
package com.wildlife.wildlife_service.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.wildlife.wildlife_service.model.Animal;

@Repository
public class AnimalRepo {

    private final JdbcTemplate jdbcTemplate;

    public AnimalRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1. Get all animals
    public List<Animal> getAllAnimals() {
        String sql = "SELECT * FROM animals";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Animal animal = new Animal();

            animal.setAnimal_id(rs.getInt("animal_id"));
            animal.setSpecies_name(rs.getString("species_name"));
            animal.setSpecies_tag(rs.getString("animal_tag"));
            animal.setAge(rs.getInt("age"));
            animal.setHealth_Status(rs.getString("health_status"));
            animal.setZone_id(rs.getLong("zone_id"));
            animal.setLast_seen_location(
                rs.getString("last_seen_location")
            );

            return animal;
        });
    }

    // 2. Get animal by ID
    public Animal getAnimalById(long animalId) {
        String sql = "SELECT * FROM animals WHERE animal_id = ?";

        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> {
                Animal animal = new Animal();

                animal.setAnimal_id(rs.getInt("animal_id"));
                animal.setSpecies_name(rs.getString("species_name"));
                animal.setSpecies_tag(rs.getString("animal_tag"));
                animal.setAge(rs.getInt("age"));
                animal.setHealth_Status(rs.getString("health_status"));
                animal.setZone_id(rs.getLong("zone_id"));
                animal.setLast_seen_location(
                    rs.getString("last_seen_location")
                );

                return animal;
            },
            animalId
        );
    }

    // 3. Add an animal
    public int addAnimal(Animal animal) {
        String sql = "INSERT INTO animals " +
            "(species_name, animal_tag, age, health_status, zone_id, last_seen_location) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
            sql,
            animal.getSpecies_name(),
            animal.getSpecies_tag(),
            animal.getAge(),
            animal.getHealth_Status(),
            animal.getZone_id(),
            animal.getLast_seen_location()
        );
    }

    // 4. Update animal details
    public int updateAnimal(long animalId, Animal animal) {
        String sql = "UPDATE animals SET " +
            "species_name = ?, animal_tag = ?, age = ?, " +
            "health_status = ?, zone_id = ?, last_seen_location = ? " +
            "WHERE animal_id = ?";

        return jdbcTemplate.update(
            sql,
            animal.getSpecies_name(),
            animal.getSpecies_tag(),
            animal.getAge(),
            animal.getHealth_Status(),
            animal.getZone_id(),
            animal.getLast_seen_location(),
            animalId
        );
    }

    // 5. Update health status
    public int updateHealthStatus(long animalId, String status) {
        String sql = "UPDATE animals SET health_status = ? " +
                     "WHERE animal_id = ?";

        return jdbcTemplate.update(sql, status, animalId);
    }

    // 6. Update last-seen location
    public int updateLocation(long animalId, String location) {
        String sql = "UPDATE animals SET last_seen_location = ? " +
                     "WHERE animal_id = ?";

        return jdbcTemplate.update(sql, location, animalId);
    }

    // 7. Delete an animal
    public int deleteAnimal(long animalId) {
        String sql = "DELETE FROM animals WHERE animal_id = ?";

        return jdbcTemplate.update(sql, animalId);
    }
}