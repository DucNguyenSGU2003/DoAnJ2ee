package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.CampaignReportCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignReportUpdateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.services.iservices.ICampaignReportService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaign_reports")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CampaignReportController {
    ICampaignReportService campaignReportService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<CampaignReportResponse> createCampaignReport(@RequestBody @Valid CampaignReportCreateRequest request) {
        return ApiResponse.<CampaignReportResponse>builder().result(campaignReportService.createCampaignReport(request)).build();
    }

    @GetMapping
    ApiResponse<PageResponse<CampaignReportResponse>> getCampaignReports(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sort) {
        return ApiResponse.<PageResponse<CampaignReportResponse>>builder().result(campaignReportService.getCampaignReports(page, size, sort)).build();
    }

    @GetMapping("/get-by-id/{campaignReportId}")
    ApiResponse<CampaignReportResponse> getCampaignReport(@PathVariable("campaignReportId") int campaignReportId) {
        return ApiResponse.<CampaignReportResponse>builder().result(campaignReportService.getCampaignReport(campaignReportId)).build();
    }

    @PutMapping("/update/{campaignReportId}")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<CampaignReportResponse> updateCampaignReport(@PathVariable("campaignReportId") int campaignReportId,@RequestBody @Valid CampaignReportUpdateRequest request) {
        return ApiResponse.<CampaignReportResponse>builder().result(campaignReportService.updateCampaignReport(campaignReportId, request)).build();
    }
}
