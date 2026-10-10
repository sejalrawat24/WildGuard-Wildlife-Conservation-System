
package com.wildlife.wildlife_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import com.wildlife.wildlife_service.model.Animal;
import com.wildlife.wildlife_service.service.AnimalService;

@CrossOrigin(origins = "*")
@Controller
public class AnimalControler {

    private final AnimalService animalService;

    public AnimalControler(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Display the Thymeleaf HTML page
    @GetMapping("/animals")
    public String animalsPage() {
        return "animals";
    }

    // Get all animals
    @GetMapping("/api/animals")
    @ResponseBody
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimalsSer();
    }

    // Get animal by ID
    @GetMapping("/api/animals/{animalId}")
    @ResponseBody
    public Animal getAnimalById(
            @PathVariable("animalId") int id) {
        return animalService.getAnimalByIDSer(id);
    }

    // Add animal
    @PostMapping("/api/animals")
    @ResponseBody
    public int addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimalSer(animal);
    }

    // Update animal
    @PutMapping("/api/animals/{animalId}")
    @ResponseBody
    public int updateAnimal(
            @PathVariable("animalId") int id,
            @RequestBody Animal animal) {
        return animalService.updateAnimalSer(id, animal);
    }

    // Update animal health
    @PatchMapping("/api/animals/{animalId}/incidents")
    @ResponseBody
    public int updateHealth(
            @PathVariable("animalId") int id,
            @RequestBody String status) {
        return animalService.updateHealthSer(id, status);
    }

    // Update animal location
    @PatchMapping("/api/animals/{animalId}/rescues")
    @ResponseBody
    public int updateLocation(
            @PathVariable("animalId") int id,
            @RequestBody String location) {
        return animalService.updateLocSer(id, location);
    }

    // Delete animal
    @DeleteMapping("/api/animals/{animalId}")
    @ResponseBody
    public int deleteAnimal(
            @PathVariable("animalId") int id) {
        return animalService.deleteAnimalSer(id);
    }
}
