package com.wildlife.field_operations_service.controller;

import com.wildlife.field_operations_service.model.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wildlife.field_operations_service.service.ResourceService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    // Display resource form
    @GetMapping("/form")
    public String showResourceForm(Model model) {
        model.addAttribute("resource", new Resource());
        return "field-operations";
    }

    // Save resource from HTML form using JSON API
    // Keep this endpoint for your existing REST API
    @ResponseBody
    @PostMapping("/api")
    public org.springframework.http.ResponseEntity<String> addResource(
            @RequestBody Resource resource) {

        int result = service.addResource(resource);

        if (result > 0) {
            return org.springframework.http.ResponseEntity.ok(
                    "Resource saved successfully!");
        }

        return org.springframework.http.ResponseEntity.badRequest()
                .body("Failed to save resource.");
    }

    // Display all resources
    @GetMapping
    public String getAllResources(Model model) {
        model.addAttribute("resources", service.getAllResources());
        model.addAttribute("resource", new Resource());
        return "resources";
    }

    // Get resource by ID (REST API)
    @ResponseBody
    @GetMapping("/{id}")
    public org.springframework.http.ResponseEntity<Resource> getResourceById(
            @PathVariable long id) {

        Resource resource = service.getResourceById(id);

        if (resource == null) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }

        return org.springframework.http.ResponseEntity.ok(resource);
    }

    // Update resource (REST API)
    @ResponseBody
    @PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<String> updateResource(
            @PathVariable long id,
            @RequestBody Resource resource) {

        int result = service.updateResource(id, resource);

        if (result > 0) {
            return org.springframework.http.ResponseEntity.ok(
                    "Resource updated successfully!");
        }

        return org.springframework.http.ResponseEntity.badRequest()
                .body("Failed to update resource.");
    }

    // Delete resource (REST API)
    @ResponseBody
    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<String> deleteResource(
            @PathVariable long id) {

        int result = service.deleteResource(id);

        if (result > 0) {
            return org.springframework.http.ResponseEntity.ok(
                    "Resource deleted successfully!");
        }

        return org.springframework.http.ResponseEntity.badRequest()
                .body("Failed to delete resource.");
    }
    @GetMapping("/api/all")
    @ResponseBody
    public List<Resource> getAllResourcesAsJson() {
        return service.getAllResources();
    }
}