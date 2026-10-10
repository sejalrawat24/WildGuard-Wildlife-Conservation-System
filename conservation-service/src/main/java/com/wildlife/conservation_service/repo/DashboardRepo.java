
package com.wildlife.conservation_service.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wildlife.conservation_service.model.Dashboard;

@Repository
public class DashboardRepo {

    private final JdbcTemplate jdbcTemplate;

    public DashboardRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Dashboard getDashboard() {

        Dashboard dashboard = new Dashboard();

        dashboard.setTotalAnimals(
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM animals", Integer.class));

        dashboard.setTotalProtectedAreas(
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM protected_areas", Integer.class));

        dashboard.setTotalProgrammes(
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM conservation_programmes", Integer.class));

        dashboard.setTotalIncidents(
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM incidents", Integer.class));

        dashboard.setTotalRescueCases(
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM rescue_cases", Integer.class));

        return dashboard;
    }
}
