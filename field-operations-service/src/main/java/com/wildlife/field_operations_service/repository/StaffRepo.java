
package com.wildlife.field_operations_service.repository;

import com.wildlife.field_operations_service.model.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepo {

    private final JdbcTemplate jdbcTemplate;

    public StaffRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Staff> rowMapper = (rs, rowNum) -> {
        Staff s = new Staff();

        s.setStaff_id(rs.getLong("staff_id"));
        s.setStaff_name(rs.getString("staff_name"));
        s.setUsername(rs.getString("username"));
        s.setPassword_hash(rs.getString("password_hash"));
        s.setRole(rs.getString("role"));
        s.setPhone(rs.getString("phone"));
        s.setArea_id(rs.getLong("area_id"));
        s.setStatus(rs.getString("status"));

        return s;
    };

    public List<Staff> getAllStaff() {
        return jdbcTemplate.query(
            "SELECT * FROM staff ORDER BY staff_id",
            rowMapper
        );
    }

    public Staff getStaffById(long id) {
        List<Staff> list = jdbcTemplate.query(
            "SELECT * FROM staff WHERE staff_id = ?",
            rowMapper,
            id
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // Find staff by username for login
    public Staff getStaffByUsername(String username) {
        List<Staff> list = jdbcTemplate.query(
            "SELECT * FROM staff WHERE username = ?",
            rowMapper,
            username
        );

        return list.isEmpty() ? null : list.get(0);
    }

    public int addStaff(Staff s) {
        String sql = "INSERT INTO staff " +
            "(staff_name, username, password_hash, role, phone, area_id, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
            sql,
            s.getStaff_name(),
            s.getUsername(),
            s.getPassword_hash(),
            s.getRole(),
            s.getPhone(),
            s.getArea_id(),
            s.getStatus()
        );
    }

    public int updateStaff(long id, Staff s) {
        String sql = "UPDATE staff SET staff_name = ?, username = ?, " +
            "password_hash = ?, role = ?, phone = ?, area_id = ?, status = ? " +
            "WHERE staff_id = ?";

        return jdbcTemplate.update(
            sql,
            s.getStaff_name(),
            s.getUsername(),
            s.getPassword_hash(),
            s.getRole(),
            s.getPhone(),
            s.getArea_id(),
            s.getStatus(),
            id
        );
    }

    public int deleteStaff(long id) {
        return jdbcTemplate.update(
            "DELETE FROM staff WHERE staff_id = ?",
            id
        );
    }
}
