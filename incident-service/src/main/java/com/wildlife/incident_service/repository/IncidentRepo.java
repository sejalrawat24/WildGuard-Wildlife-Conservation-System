
package com.wildlife.incident_service.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wildlife.incident_service.model.Incident;

@Repository
public class IncidentRepo {

    private final JdbcTemplate jdbcTemplate;

    public IncidentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Incident> incidentRowMapper =
            new RowMapper<Incident>() {

        @Override
        public Incident mapRow(ResultSet rs, int rowNum)
                throws SQLException {

            Incident incident = new Incident();

            incident.setIncident_id(rs.getLong("incident_id"));
            incident.setIncident_type(rs.getString("incident_type"));
            incident.setDescription(rs.getString("description"));
            incident.setZone_id(rs.getLong("zone_id"));
            incident.setAnimal_id(rs.getLong("animal_id"));
            incident.setReported_by(rs.getLong("reported_by"));
            incident.setAssigned_to(rs.getLong("assigned_to"));
            incident.setPriority(rs.getString("priority"));
            incident.setStatus(rs.getString("status"));

            Timestamp timestamp = rs.getTimestamp("reported_at");

            if (timestamp != null) {
                incident.setReported_at(timestamp.toLocalDateTime());
            }

            return incident;
        }
    };

    public List<Incident> getAllIncidents() {

        String sql = """
            SELECT incident_id, incident_type, description,
                   zone_id, animal_id, reported_by, assigned_to,
                   priority, status, reported_at
            FROM incidents
            ORDER BY incident_id
            """;

        return jdbcTemplate.query(sql, incidentRowMapper);
    }

    public Incident getIncidentById(long id) {

        String sql = """
            SELECT incident_id, incident_type, description,
                   zone_id, animal_id, reported_by, assigned_to,
                   priority, status, reported_at
            FROM incidents
            WHERE incident_id = ?
            """;

        List<Incident> incidents =
                jdbcTemplate.query(sql, incidentRowMapper, id);

        return incidents.isEmpty() ? null : incidents.get(0);
    }

    public int addIncident(Incident incident) {

        String sql = """
            INSERT INTO incidents
                (incident_type, description, zone_id, animal_id,
                 reported_by, assigned_to, priority, status, reported_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        return jdbcTemplate.update(
                sql,
                incident.getIncident_type(),
                incident.getDescription(),
                incident.getZone_id(),
                incident.getAnimal_id(),
                incident.getReported_by(),
                incident.getAssigned_to(),
                incident.getPriority(),
                incident.getStatus(),
                incident.getReported_at()
        );
    }

    public int updateIncident(long id, Incident incident) {

        String sql = """
            UPDATE incidents
            SET incident_type = ?,
                description = ?,
                zone_id = ?,
                animal_id = ?,
                reported_by = ?,
                assigned_to = ?,
                priority = ?,
                status = ?,
                reported_at = ?
            WHERE incident_id = ?
            """;

        return jdbcTemplate.update(
                sql,
                incident.getIncident_type(),
                incident.getDescription(),
                incident.getZone_id(),
                incident.getAnimal_id(),
                incident.getReported_by(),
                incident.getAssigned_to(),
                incident.getPriority(),
                incident.getStatus(),
                incident.getReported_at(),
                id
        );
    }

    public int deleteIncident(long id) {

        String sql = """
            DELETE FROM incidents
            WHERE incident_id = ?
            """;

        return jdbcTemplate.update(sql, id);
    }
}
