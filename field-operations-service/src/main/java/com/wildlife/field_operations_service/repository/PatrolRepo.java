
package com.wildlife.field_operations_service.repository;

import com.wildlife.field_operations_service.model.Patrol;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class PatrolRepo {

    private final JdbcTemplate jdbcTemplate;

    public PatrolRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Patrol> rowMapper = (rs, rowNum) -> {
        Patrol p = new Patrol();

        p.setPatrol_id(rs.getLong("patrol_id"));
        p.setZone_id(rs.getLong("zone_id"));
        p.setLeader_staff_id(rs.getLong("leader_staff_id"));

        Date date = rs.getDate("patrol_date");
        if (date != null) {
            p.setPatrol_date(date.toLocalDate());
        }

        p.setRoute_description(rs.getString("route_description"));
        p.setActivities(rs.getString("activities"));
        p.setStatus(rs.getString("status"));

        return p;
    };

    public List<Patrol> getAllPatrols() {
        return jdbcTemplate.query(
            "SELECT * FROM patrols ORDER BY patrol_id",
            rowMapper
        );
    }

    public Patrol getPatrolById(long id) {
        List<Patrol> list = jdbcTemplate.query(
            "SELECT * FROM patrols WHERE patrol_id = ?",
            rowMapper, id
        );

        return list.isEmpty() ? null : list.get(0);
    }

    public int addPatrol(Patrol p) {
        String sql = "INSERT INTO patrols " +
            "(zone_id, leader_staff_id, patrol_date, route_description, activities, status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            p.getZone_id(),
            p.getLeader_staff_id(),
            p.getPatrol_date(),
            p.getRoute_description(),
            p.getActivities(),
            p.getStatus()
        );
    }

    public int updatePatrol(long id, Patrol p) {
        String sql = "UPDATE patrols SET zone_id = ?, leader_staff_id = ?, " +
            "patrol_date = ?, route_description = ?, activities = ?, status = ? " +
            "WHERE patrol_id = ?";

        return jdbcTemplate.update(sql,
            p.getZone_id(),
            p.getLeader_staff_id(),
            p.getPatrol_date(),
            p.getRoute_description(),
            p.getActivities(),
            p.getStatus(),
            id
        );
    }

    public int deletePatrol(long id) {
        return jdbcTemplate.update(
            "DELETE FROM patrols WHERE patrol_id = ?", id
        );
    }
}
