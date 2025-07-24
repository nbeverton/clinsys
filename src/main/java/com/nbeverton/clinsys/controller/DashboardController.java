package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.DashboardDTO;
import com.nbeverton.clinsys.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboardData(){
        DashboardDTO dto = dashboardService.getDashboardData();
        return ResponseEntity.ok(dto);
    }

}
