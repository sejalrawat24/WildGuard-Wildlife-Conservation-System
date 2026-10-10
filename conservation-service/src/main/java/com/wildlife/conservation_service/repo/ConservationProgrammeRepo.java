
package com.wildlife.conservation_service.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wildlife.conservation_service.model.ConservationProgramme;

@Repository
public class ConservationProgrammeRepo {

    private final JdbcTemplate jdbcTemplate;

    public ConservationProgrammeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ConservationProgramme> rowMapper = (rs, rowNum) -> {
        ConservationProgramme p = new ConservationProgramme();

        p.setProgramme_id(rs.getLong("programme_id"));
        p.setProgramme_name(rs.getString("programme_name"));
        p.setDescription(rs.getString("description"));
        p.setCoordinator_id(rs.getLong("coordinator_id"));
        p.setTarget(rs.getBigDecimal("target"));
        p.setAchieved(rs.getBigDecimal("achieved"));

        java.sql.Date start = rs.getDate("start_date");
        java.sql.Date end = rs.getDate("end_date");

        p.setStart_date(start == null ? null : start.toLocalDate());
        p.setEnd_date(end == null ? null : end.toLocalDate());
        p.setStatus(rs.getString("status"));

        return p;
    };

    public List<ConservationProgramme> getAll() {
        return jdbcTemplate.query(
            "SELECT * FROM conservation_programmes ORDER BY programme_id",
            rowMapper
        );
    }

    public ConservationProgramme getById(long id) {
        List<ConservationProgramme> result = jdbcTemplate.query(
            "SELECT * FROM conservation_programmes WHERE programme_id = ?",
            rowMapper,
            id
        );

        return result.isEmpty() ? null : result.get(0);
    }

    public int add(ConservationProgramme p) {

        p.setStatus(p.getStatus().toUpperCase().trim());

        String sql = """
            INSERT INTO conservation_programmes
            (programme_name, description, coordinator_id, target,
             achieved, start_date, end_date, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        return jdbcTemplate.update(
            sql,
            p.getProgramme_name(),
            p.getDescription(),
            p.getCoordinator_id(),
            p.getTarget(),
            p.getAchieved(),
            p.getStart_date(),
            p.getEnd_date(),
            p.getStatus()
        );
    }

    public int update(long id, ConservationProgramme p) {
        String sql = """
            UPDATE conservation_programmes
            SET programme_name = ?, description = ?, coordinator_id = ?,
                target = ?, achieved = ?, start_date = ?, end_date = ?,
                status = ?
            WHERE programme_id = ?
            """;

        return jdbcTemplate.update(
            sql,
            p.getProgramme_name(),
            p.getDescription(),
            p.getCoordinator_id(),
            p.getTarget(),
            p.getAchieved(),
            p.getStart_date(),
            p.getEnd_date(),
            p.getStatus(),
            id
        );
    }

    public int delete(long id) {
        return jdbcTemplate.update(
            "DELETE FROM conservation_programmes WHERE programme_id = ?",
            id
        );
    }
}
