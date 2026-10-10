
package com.wildlife.incident_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wildlife.incident_service.model.RescueCase;
import com.wildlife.incident_service.service.RescueCaseService;

@Controller
@RequestMapping("/rescues")
public class RescueCaseController {

    private final RescueCaseService rescueCaseService;

    public RescueCaseController(RescueCaseService rescueCaseService) {
        this.rescueCaseService = rescueCaseService;
    }

    // Show rescue form
    @GetMapping("/form")
    public String showRescueForm(Model model) {
        model.addAttribute("rescueCase", new RescueCase());
        return "rescues";
    }

    // Get all rescue cases and show the form
    @GetMapping
    public String getAllRescueCases(Model model) {

        List<RescueCase> rescueCases =
                rescueCaseService.getAllRescueCases();

        model.addAttribute("rescueCases", rescueCases);

        // Required because rescues.html uses th:object="${rescueCase}"
        model.addAttribute("rescueCase", new RescueCase());

        return "rescues";
    }

    // Get rescue case by ID
    @GetMapping("/{id}")
    public String getRescueCaseById(
            @PathVariable long id, Model model) {

        RescueCase rescueCase =
                rescueCaseService.getRescueCaseById(id);

        if (rescueCase == null) {
            return "redirect:/rescues?error=notfound";
        }

        model.addAttribute("rescueCase", rescueCase);
        return "rescue-details";
    }

    // Add a new rescue case
    @PostMapping
    public String addRescueCase(
            @ModelAttribute("rescueCase") RescueCase rescueCase) {

        int result = rescueCaseService.addRescueCase(rescueCase);

        if (result > 0) {
            return "redirect:/rescues?success";
        }

        return "redirect:/rescues?error";
    }

    // Update an existing rescue case
    @PostMapping("/update/{id}")
    public String updateRescueCase(
            @PathVariable long id,
            @ModelAttribute("rescueCase") RescueCase rescueCase) {

        int result =
                rescueCaseService.updateRescueCase(id, rescueCase);

        if (result > 0) {
            return "redirect:/rescues?updated";
        }

        return "redirect:/rescues?error";
    }

    // Delete a rescue case
    @PostMapping("/delete/{id}")
    public String deleteRescueCase(@PathVariable long id) {

        rescueCaseService.deleteRescueCase(id);

        return "redirect:/rescues?deleted";
    }
    @GetMapping("/api/all")
    @ResponseBody
    public List<RescueCase> getAllRescueCasesAsJson() {
        return rescueCaseService.getAllRescueCases();
    }
}
