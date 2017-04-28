package com.soa4.controller;

import com.soa4.model.Report;
import com.soa4.model.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Boeing on 4/28/2017.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/request")
    public ResponseEntity<?> requestReport(@RequestBody Report report){
        this.reportRepository.requestReport(report.getBannerID(), report.getBannedAccountID(), report.getDescription());
        return new ResponseEntity<>("Request report complete.", HttpStatus.OK);
    }
}