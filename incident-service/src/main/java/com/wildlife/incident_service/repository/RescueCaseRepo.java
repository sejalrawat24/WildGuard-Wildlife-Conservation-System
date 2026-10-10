
package com.wildlife.incident_service.repository;

import com.wildlife.incident_service.model.RescueCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class RescueCaseRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RescueCase> rowMapper = (rs, rowNum) -> {
        RescueCase rescue = new RescueCase();

        rescue.setRescue_id(rs.getLong("rescue_id"));
        rescue.setIncident_id(rs.getLong("incident_id"));
        rescue.setAnimal_id(rs.getLong("animal_id"));
        rescue.setAssigned_staff_id(rs.getLong("assigned_staff_id"));
        rescue.setTreatment_details(rs.getString("treatment_details"));
        rescue.setRehabilitation_status(rs.getString("rehabilitation_status"));
        rescue.setOutcome(rs.getString("outcome"));

        Date rescueDate = rs.getDate("rescue_date");
        if (rescueDate != null) {
            rescue.setRescue_date(rescueDate.toLocalDate());
        }

        Date releaseDate = rs.getDate("release_date");
        if (releaseDate != null) {
            rescue.setRelease_date(releaseDate.toLocalDate());
        }

        return rescue;
    };

    public List<RescueCase> getAllRescueCases() {
        String sql = "SELECT * FROM rescue_cases ORDER BY rescue_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public RescueCase getRescueCaseById(long id) {
        String sql = "SELECT * FROM rescue_cases WHERE rescue_id = ?";
        List<RescueCase> cases =
                jdbcTemplate.query(sql, rowMapper, id);

        return cases.isEmpty() ? null : cases.get(0);
    }

    public int addRescueCase(RescueCase rescue) {
        String sql = """
            INSERT INTO rescue_cases
            (incident_id, animal_id, assigned_staff_id,
             treatment_details, rehabilitation_status, outcome,
             rescue_date, release_date)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        return jdbcTemplate.update(
                sql,
                rescue.getIncident_id(),
                rescue.getAnimal_id(),
                rescue.getAssigned_staff_id(),
                rescue.getTreatment_details(),
                rescue.getRehabilitation_status(),
                rescue.getOutcome(),
                rescue.getRescue_date(),
                rescue.getRelease_date()
        );
    }

    public int updateRescueCase(RescueCase rescue) {
        String sql = """
            UPDATE rescue_cases SET
            incident_id = ?,
            animal_id = ?,
            assigned_staff_id = ?,
            treatment_details = ?,
            rehabilitation_status = ?,
            outcome = ?,
            rescue_date = ?,
            release_date = ?
            WHERE rescue_id = ?
            """;

        return jdbcTemplate.update(
                sql,
                rescue.getIncident_id(),
                rescue.getAnimal_id(),
                rescue.getAssigned_staff_id(),
                rescue.getTreatment_details(),
                rescue.getRehabilitation_status(),
                rescue.getOutcome(),
                rescue.getRescue_date(),
                rescue.getRelease_date(),
                rescue.getRescue_id()
        );
    }

    public int deleteRescueCase(long id) {
        String sql = "DELETE FROM rescue_cases WHERE rescue_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
