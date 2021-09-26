package com.project.egloo.healthCheck;

import com.project.egloo.healthCheck.dto.HealthCheckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheckController {

    @GetMapping("/api/v1/healthCheck")
    public ResponseEntity<HealthCheckDTO> healthCheckResponse() {
        HealthCheckDTO checkDTO = new HealthCheckDTO(true);
        return ResponseEntity.ok(checkDTO);
    }
}
