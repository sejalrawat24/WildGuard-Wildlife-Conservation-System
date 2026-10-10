
package com.wildlife.wildlife_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wildlife.wildlife_service.model.ProtectedArea;
import com.wildlife.wildlife_service.service.ProtectedAreaService;

@Controller
@RequestMapping("/api/protected-areas")
@CrossOrigin(origins = "*")
public class ProtectedAreaController {

    private final ProtectedAreaService areaService;

    public ProtectedAreaController(ProtectedAreaService areaService) {
        this.areaService = areaService;
    }

    // Display HTML page
    @GetMapping
    public String protectedAreasPage() {
        return "protected-areas";
    }

    // Get all protected areas
    @GetMapping("/all")
    @ResponseBody
    public List<ProtectedArea> getAllAreas() {
        return areaService.getAllAreas();
    }

    // Get protected area by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProtectedArea> getAreaById(@PathVariable long id) {
        ProtectedArea area = areaService.getAreaById(id);

        if (area == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(area);
    }

    // Add protected area
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addArea(@RequestBody ProtectedArea area) {
        int result = areaService.addArea(area);

        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Protected area added successfully");
        }

        return ResponseEntity.badRequest()
                .body("Could not add protected area");
    }

    // Update protected area
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateArea(
            @PathVariable long id,
            @RequestBody ProtectedArea area) {

        int result = areaService.updateArea(id, area);

        if (result > 0) {
            return ResponseEntity.ok("Protected area updated successfully");
        }

        return ResponseEntity.notFound().build();
    }

    // Delete protected area
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteArea(@PathVariable long id) {
        int result = areaService.deleteArea(id);

        if (result > 0) {
            return ResponseEntity.ok("Protected area deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }

    // Get all zones
    @GetMapping("/zones")
    @ResponseBody
    public List<Map<String, Object>> getAllZones() {
        return areaService.getAllZones();
    }

    // Get zones belonging to a protected area
    @GetMapping("/{areaId}/zones")
    @ResponseBody
    public List<Map<String, Object>> getZonesByAreaId(
            @PathVariable long areaId) {
        return areaService.getZonesByAreaId(areaId);
    }

    // Get zone by ID
    @GetMapping("/zones/{zoneId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getZoneById(
            @PathVariable long zoneId) {

        Map<String, Object> zone = areaService.getZoneById(zoneId);

        if (zone == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(zone);
    }

    // Add zone
    @PostMapping("/zones")
    @ResponseBody
    public ResponseEntity<String> addZone(
            @RequestBody Map<String, Object> request) {
        try {
            long areaId = Long.parseLong(request.get("area_id").toString());
            String zoneName = request.get("zone_name").toString();
            String habitatType = request.get("habitat_type").toString();
            String waterStatus = request.get("water_status").toString();
            double latitude = Double.parseDouble(request.get("latitude").toString());
            double longitude = Double.parseDouble(request.get("longitude").toString());

            int result = areaService.addZone(
                    areaId, zoneName, habitatType,
                    waterStatus, latitude, longitude);

            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Zone added successfully");
            }

            return ResponseEntity.badRequest().body("Could not add zone");

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Invalid or missing zone data");
        }
    }

    // Update zone
    @PutMapping("/zones/{zoneId}")
    @ResponseBody
    public ResponseEntity<String> updateZone(
            @PathVariable long zoneId,
            @RequestBody Map<String, Object> request) {
        try {
            long areaId = Long.parseLong(request.get("area_id").toString());
            String zoneName = request.get("zone_name").toString();
            String habitatType = request.get("habitat_type").toString();
            String waterStatus = request.get("water_status").toString();
            double latitude = Double.parseDouble(request.get("latitude").toString());
            double longitude = Double.parseDouble(request.get("longitude").toString());

            int result = areaService.updateZone(
                    zoneId, areaId, zoneName, habitatType,
                    waterStatus, latitude, longitude);

            if (result > 0) {
                return ResponseEntity.ok("Zone updated successfully");
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Invalid or missing zone data");
        }
    }

    // Delete zone
    @DeleteMapping("/zones/{zoneId}")
    @ResponseBody
    public ResponseEntity<String> deleteZone(@PathVariable long zoneId) {
        int result = areaService.deleteZone(zoneId);

        if (result > 0) {
            return ResponseEntity.ok("Zone deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
