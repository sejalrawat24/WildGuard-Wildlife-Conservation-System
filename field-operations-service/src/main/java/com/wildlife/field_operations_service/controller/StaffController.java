
package com.wildlife.field_operations_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wildlife.field_operations_service.model.Staff;
import com.wildlife.field_operations_service.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    // Show staff registration form
    @GetMapping("/form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff";
    }

    // Get all staff
    @GetMapping
    public String getAllStaff(Model model) {
        List<Staff> staffList = service.getAllStaff();

        model.addAttribute("staffList", staffList);
        model.addAttribute("staff", new Staff());

        return "staff";
    }

    // Get staff by ID
    @GetMapping("/{id}")
    public String getStaffById(
            @PathVariable long id,
            Model model) {

        Staff staff = service.getStaffById(id);

        if (staff == null) {
            return "redirect:/staff?error=notfound";
        }

        model.addAttribute("staff", staff);
        return "staff";
    }

    // Register staff
    @PostMapping
    public String addStaff(
            @ModelAttribute("staff") Staff staff) {

        int result = service.addStaff(staff);

        if (result > 0) {
            return "redirect:/staff/form?success";
        }

        return "redirect:/staff/form?error";
    }

    // Update staff
    @PostMapping("/update/{id}")
    public String updateStaff(
            @PathVariable long id,
            @ModelAttribute("staff") Staff staff) {

        int result = service.updateStaff(id, staff);

        if (result > 0) {
            return "redirect:/staff?updated";
        }

        return "redirect:/staff?error";
    }

    // Delete staff
    @PostMapping("/delete/{id}")
    public String deleteStaff(@PathVariable long id) {
        int result = service.deleteStaff(id);

        if (result > 0) {
            return "redirect:/staff?deleted";
        }

        return "redirect:/staff?error";
    }

    // Authenticate staff for UI login
    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<Map<String, String>> authenticate(
            @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "message",
                            "Username and password are required"
                    ));
        }

        Staff staff = service.getStaffByUsername(username);

        if (staff == null
                || !"ACTIVE".equalsIgnoreCase(staff.getStatus())
                || staff.getPassword_hash() == null
                || !password.equals(staff.getPassword_hash())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message",
                            "Invalid username or password"
                    ));
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "staffName", staff.getStaff_name(),
                        "role", staff.getRole()
                )
        );
    }
    @GetMapping("/api/all")
    @ResponseBody
    public List<Staff> getAllStaffAsJson() {
        return service.getAllStaff();
    }
}
