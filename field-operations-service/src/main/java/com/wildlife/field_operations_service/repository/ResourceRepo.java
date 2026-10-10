
package com.wildlife.field_operations_service.repository;

import com.wildlife.field_operations_service.model.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceRepo {

    private final JdbcTemplate jdbcTemplate;

    public ResourceRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Resource> rowMapper = (rs, rowNum) -> {
        Resource r = new Resource();

        r.setResource_id(rs.getLong("resource_id"));
        r.setResource_name(rs.getString("resource_name"));
        r.setResource_type(rs.getString("resource_type"));
        r.setRegistration_number(rs.getString("registration_number"));
        r.setCondition_status(rs.getString("condition_status"));
        r.setAvailability_status(rs.getString("availability_status"));
        r.setAssigned_staff_id(rs.getLong("assigned_staff_id"));

        return r;
    };

    public List<Resource> getAllResources() {
        return jdbcTemplate.query(
            "SELECT * FROM resources ORDER BY resource_id",
            rowMapper
        );
    }

    public Resource getResourceById(long id) {
        List<Resource> list = jdbcTemplate.query(
            "SELECT * FROM resources WHERE resource_id = ?",
            rowMapper, id
        );

        return list.isEmpty() ? null : list.get(0);
    }

    public int addResource(Resource r) {
        String sql = "INSERT INTO resources " +
            "(resource_name, resource_type, registration_number, " +
            "condition_status, availability_status, assigned_staff_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            r.getResource_name(),
            r.getResource_type(),
            r.getRegistration_number(),
            r.getCondition_status(),
            r.getAvailability_status(),
            r.getAssigned_staff_id()
        );
    }

    public int updateResource(long id, Resource r) {
        String sql = "UPDATE resources SET resource_name = ?, " +
            "resource_type = ?, registration_number = ?, " +
            "condition_status = ?, availability_status = ?, " +
            "assigned_staff_id = ? WHERE resource_id = ?";

        return jdbcTemplate.update(sql,
            r.getResource_name(),
            r.getResource_type(),
            r.getRegistration_number(),
            r.getCondition_status(),
            r.getAvailability_status(),
            r.getAssigned_staff_id(),
            id
        );
    }

    public int deleteResource(long id) {
        return jdbcTemplate.update(
            "DELETE FROM resources WHERE resource_id = ?", id
        );
    }
}
