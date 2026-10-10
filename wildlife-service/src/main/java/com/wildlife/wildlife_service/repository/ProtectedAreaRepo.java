
package com.wildlife.wildlife_service.repository;

import java.util.List;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wildlife.wildlife_service.model.ProtectedArea;

@Repository
public class ProtectedAreaRepo {

    private final JdbcTemplate jdbcTemplate;

    public ProtectedAreaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ProtectedArea> rowMapper = (rs, rowNum) -> {
        ProtectedArea area = new ProtectedArea();

        area.setArea_id(rs.getLong("area_id"));
        area.setArea_name(rs.getString("area_name"));
        area.setLocation(rs.getString("location"));
        area.setArea_size(rs.getDouble("area_size"));
        area.setDescription(rs.getString("description"));

        return area;
    };

    public List<ProtectedArea> getAllAreas() {
        String sql = "SELECT * FROM protected_areas ORDER BY area_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public ProtectedArea getAreaById(long id) {
        String sql = "SELECT * FROM protected_areas WHERE area_id = ?";
        List<ProtectedArea> result =
                jdbcTemplate.query(sql, rowMapper, id);

        return result.isEmpty() ? null : result.get(0);
    }

    public int addArea(ProtectedArea area) {
        String sql = """
                INSERT INTO protected_areas
                (area_name, location, area_size, description)
                VALUES (?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                area.getArea_name(),
                area.getLocation(),
                area.getArea_size(),
                area.getDescription()
        );
    }

    public int updateArea(long id, ProtectedArea area) {
        String sql = """
                UPDATE protected_areas
                SET area_name = ?, location = ?,
                    area_size = ?, description = ?
                WHERE area_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                area.getArea_name(),
                area.getLocation(),
                area.getArea_size(),
                area.getDescription(),
                id
        );
    }

    public int deleteArea(long id) {
        String sql = "DELETE FROM protected_areas WHERE area_id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    
    
    

    // ================= ZONE FUNCTIONALITY =================

    // Get all zones
    public List<Map<String, Object>> getAllZones() {
        String sql = """
                SELECT zone_id, area_id, zone_name, habitat_type,
                       water_status, latitude, longitude
                FROM zones
                ORDER BY zone_id
                """;

        return jdbcTemplate.queryForList(sql);
    }

    // Get zones belonging to a protected area
    public List<Map<String, Object>> getZonesByAreaId(long areaId) {
        String sql = """
                SELECT zone_id, area_id, zone_name, habitat_type,
                       water_status, latitude, longitude
                FROM zones
                WHERE area_id = ?
                ORDER BY zone_id
                """;

        return jdbcTemplate.queryForList(sql, areaId);
    }

    // Get one zone by its ID
    public Map<String, Object> getZoneById(long zoneId) {
        String sql = """
                SELECT zone_id, area_id, zone_name, habitat_type,
                       water_status, latitude, longitude
                FROM zones
                WHERE zone_id = ?
                """;

        List<Map<String, Object>> result =
                jdbcTemplate.queryForList(sql, zoneId);

        return result.isEmpty() ? null : result.get(0);
    }

    // Add a zone
    public int addZone(
            long areaId,
            String zoneName,
            String habitatType,
            String waterStatus,
            double latitude,
            double longitude) {

        String sql = """
                INSERT INTO zones
                (area_id, zone_name, habitat_type, water_status,
                 latitude, longitude)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql, areaId, zoneName, habitatType,
                waterStatus, latitude, longitude);
    }

    // Update a zone
    public int updateZone(
            long zoneId,
            long areaId,
            String zoneName,
            String habitatType,
            String waterStatus,
            double latitude,
            double longitude) {

        String sql = """
                UPDATE zones
                SET area_id = ?, zone_name = ?, habitat_type = ?,
                    water_status = ?, latitude = ?, longitude = ?
                WHERE zone_id = ?
                """;

        return jdbcTemplate.update(
                sql, areaId, zoneName, habitatType,
                waterStatus, latitude, longitude, zoneId);
    }

    // Delete a zone
    public int deleteZone(long zoneId) {
        String sql = "DELETE FROM zones WHERE zone_id = ?";
        return jdbcTemplate.update(sql, zoneId);
    }

}