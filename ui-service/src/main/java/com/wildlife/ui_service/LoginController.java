
package com.wildlife.ui_service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private final RestTemplate restTemplate;

    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        String url = "http://localhost:8084/staff/authenticate";

        Map<String, String> request = new HashMap<>();
        request.put("username", username);
        request.put("password", password);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/";
            }

            model.addAttribute("error", "Invalid username or password");
            return "login";

        } catch (HttpStatusCodeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";

        } catch (Exception e) {
            model.addAttribute(
                    "error",
                    "Unable to connect to the Field Operations Service."
            );
            return "login";
        }
    }
}
