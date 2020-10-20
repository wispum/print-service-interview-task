package org.home.printservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.home.printservice.model.request.PrintFilterRequest;
import org.home.printservice.model.request.XmlDataJob;
import org.home.printservice.model.response.JobStatisticsResponse;
import org.home.printservice.service.PrintJobService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PrintJobController {
    private final PrintJobService printJobService;

    @PostMapping(value = "/job", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> loadData(@RequestBody XmlDataJob xmlDataJob) {
        log.info("Received {}", xmlDataJob);
        return ResponseEntity.ok(printJobService.saveJobRequest(xmlDataJob));
    }

    @GetMapping(value = "/getStatistics", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobStatisticsResponse> getStatistics(@RequestParam PrintFilterRequest request) {
        return ResponseEntity.ok(printJobService.getStatistics(request));
    }

    // here we can add additional exception handlers but no time for this
}
