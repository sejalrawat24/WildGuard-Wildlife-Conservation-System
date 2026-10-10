
package com.wildlife.conservation_service.service;

import org.springframework.stereotype.Service;

import com.wildlife.conservation_service.model.Dashboard;
import com.wildlife.conservation_service.repo.DashboardRepo;

@Service
public class DashboardService {

    private final DashboardRepo dashboardRepo;

    public DashboardService(DashboardRepo dashboardRepo) {
        this.dashboardRepo = dashboardRepo;
    }

    public Dashboard getDashboard() {
        return dashboardRepo.getDashboard();
    }
}
