
package com.wildlife.conservation_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wildlife.conservation_service.model.ConservationProgramme;
import com.wildlife.conservation_service.service.ConservationProgrammeService;

@Controller
@RequestMapping("/programmes")
public class ConservationProgrammeController {

    private final ConservationProgrammeService service;

    public ConservationProgrammeController(
            ConservationProgrammeService service) {
        this.service = service;
    }

    // Show conservation programme form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute(
                "programme", new ConservationProgramme());
        return "programmes";
    }

    // Display all programmes
    @GetMapping
    public String getAll(Model model) {
        List<ConservationProgramme> programmes = service.getAll();

        model.addAttribute("programmes", programmes);
        model.addAttribute("programme", new ConservationProgramme());

        return "programmes";
    }

    // Get programme by ID
    @GetMapping("/{id}")
    public String getById(
            @PathVariable("id") long id,
            Model model) {

        ConservationProgramme programme = service.getById(id);

        if (programme == null) {
            return "redirect:/programmes?error=notfound";
        }

        model.addAttribute("programme", programme);
        return "programmes";
    }

    // Add programme
    @PostMapping
    public String add(
            @ModelAttribute("programme")
            ConservationProgramme programme) {

        int result = service.add(programme);

        if (result > 0) {
            return "redirect:/programmes?success";
        }

        return "redirect:/programmes?error";
    }

    // Update programme
    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") long id,
            @ModelAttribute("programme")
            ConservationProgramme programme) {

        int result = service.update(id, programme);

        if (result > 0) {
            return "redirect:/programmes?updated";
        }

        return "redirect:/programmes?error";
    }

    // Delete programme
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        int result = service.delete(id);

        if (result > 0) {
            return "redirect:/programmes?deleted";
        }

        return "redirect:/programmes?error";
    }
    @GetMapping("/api/all")
    @ResponseBody
    public List<ConservationProgramme> getAllAsJson() {
        return service.getAll();
    }
}
