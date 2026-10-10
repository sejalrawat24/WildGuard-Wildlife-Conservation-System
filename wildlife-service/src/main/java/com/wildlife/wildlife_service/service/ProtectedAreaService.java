
package com.wildlife.wildlife_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wildlife.wildlife_service.model.ProtectedArea;
import com.wildlife.wildlife_service.repository.ProtectedAreaRepo;

@Service
public class ProtectedAreaService {

    private final ProtectedAreaRepo areaRepo;

    public ProtectedAreaService(ProtectedAreaRepo areaRepo) {
        this.areaRepo = areaRepo;
    }

    // ========== PROTECTED AREA FUNCTIONALITY ==========

    public List<ProtectedArea> getAllAreas() {
        return areaRepo.getAllAreas();
    }

    public ProtectedArea getAreaById(long id) {
        return areaRepo.getAreaById(id);
    }

    public int addArea(ProtectedArea area) {
        return areaRepo.addArea(area);
    }

    public int updateArea(long id, ProtectedArea area) {
        return areaRepo.updateArea(id, area);
    }

    public int deleteArea(long id) {
        return areaRepo.deleteArea(id);
    }

    // ========== ZONE FUNCTIONALITY ==========

    public List<Map<String, Object>> getAllZones() {
        return areaRepo.getAllZones();
    }

    public List<Map<String, Object>> getZonesByAreaId(long areaId) {
        return areaRepo.getZonesByAreaId(areaId);
    }

    public Map<String, Object> getZoneById(long zoneId) {
        return areaRepo.getZoneById(zoneId);
    }

    public int addZone(
            long areaId,
            String zoneName,
            String habitatType,
            String waterStatus,
            double latitude,
            double longitude) {

        return areaRepo.addZone(
                areaId, zoneName, habitatType,
                waterStatus, latitude, longitude);
    }

    public int updateZone(
            long zoneId,
            long areaId,
            String zoneName,
            String habitatType,
            String waterStatus,
            double latitude,
            double longitude) {

        return areaRepo.updateZone(
                zoneId, areaId, zoneName, habitatType,
                waterStatus, latitude, longitude);
    }

    public int deleteZone(long zoneId) {
        return areaRepo.deleteZone(zoneId);
    }
}
