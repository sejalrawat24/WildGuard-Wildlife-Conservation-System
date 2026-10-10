
package com.wildlife.field_operations_service.controller;

import com.wildlife.field_operations_service.model.Patrol;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wildlife.field_operations_service.service.PatrolService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patrols")
public class PatrolController {

    private final PatrolService patrolService;

    public PatrolController(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @GetMapping
    public String showPatrols(Model model) {
        model.addAttribute("patrols", patrolService.getAllPatrols());
        model.addAttribute("patrol", new Patrol());
        return "patrols";
    }

    @GetMapping("/{id}")
    public String getPatrolById(@PathVariable long id, Model model) {
        Patrol patrol = patrolService.getPatrolById(id);

        if (patrol == null) {
            model.addAttribute("error", "Patrol not found.");
            model.addAttribute("patrols", patrolService.getAllPatrols());
            model.addAttribute("patrol", new Patrol());
            return "patrols";
        }

        model.addAttribute("patrol", new Patrol());
        model.addAttribute("patrols", patrolService.getAllPatrols());
        model.addAttribute("searchedPatrol", patrol);

        return "patrols";
    }


    @PostMapping
    public String addPatrol(@ModelAttribute Patrol patrol) {

        String status = patrol.getStatus();

        if ("In Progress".equalsIgnoreCase(status)) {
            patrol.setStatus("ONGOING");
        } else if ("Scheduled".equalsIgnoreCase(status)) {
            patrol.setStatus("SCHEDULED");
        } else if ("Completed".equalsIgnoreCase(status)) {
            patrol.setStatus("COMPLETED");
        }

        System.out.println("Received patrol status: " + patrol.getStatus());

        patrolService.addPatrol(patrol);

        return "redirect:/patrols";
    }
 

    @PostMapping("/update/{id}")
    public String updatePatrol(
            @PathVariable long id,
            @ModelAttribute Patrol patrol) {

        System.out.println("Updated patrol status: " + patrol.getStatus());

        patrolService.updatePatrol(id, patrol);
        return "redirect:/patrols";
    }

    @PostMapping("/delete/{id}")
    public String deletePatrol(@PathVariable long id) {
        patrolService.deletePatrol(id);
        return "redirect:/patrols";
    }
    @GetMapping("/api/all")
    @ResponseBody
    public List<Patrol> getAllPatrolsAsJson() {
        return patrolService.getAllPatrols();
    }
}
