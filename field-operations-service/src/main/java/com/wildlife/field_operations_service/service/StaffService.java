
package com.wildlife.field_operations_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wildlife.field_operations_service.model.Staff;
import com.wildlife.field_operations_service.repository.StaffRepo;

@Service
public class StaffService {

    private final StaffRepo repo;

    public StaffService(StaffRepo repo) {
        this.repo = repo;
    }

    public List<Staff> getAllStaff() {
        return repo.getAllStaff();
    }

    public Staff getStaffById(long id) {
        return repo.getStaffById(id);
    }

    public Staff getStaffByUsername(String username) {
        return repo.getStaffByUsername(username);
    }

    public int addStaff(Staff s) {
        return repo.addStaff(s);
    }

    public int updateStaff(long id, Staff s) {
        return repo.updateStaff(id, s);
    }

    public int deleteStaff(long id) {
        return repo.deleteStaff(id);
    }
}
