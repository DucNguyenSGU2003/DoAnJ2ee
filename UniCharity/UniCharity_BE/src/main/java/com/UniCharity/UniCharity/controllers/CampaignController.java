package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.CampaignCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignRequest;
import com.UniCharity.UniCharity.dto.request.CampaignUpdateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.entities.Donation;
import com.UniCharity.UniCharity.entities.Image;
import com.UniCharity.UniCharity.services.iservices.ICampaignService;
import com.UniCharity.UniCharity.services.iservices.IImageService;
import com.UniCharity.UniCharity.services.iservices.IPolicyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/campaigns")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CampaignController {
    ICampaignService campaignService;
    IPolicyService policyService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<CampaignResponse> createCampaign(@RequestBody @Valid CampaignCreateRequest request) {
        CampaignResponse campaignResponse = campaignService.createCampaign(request);
        List<PolicyResponse> policyResponseList = policyService.createPolicyList(request.getPolicies(), campaignResponse.getId());
        campaignResponse = campaignService.getCampaign(campaignResponse.getId());
        return ApiResponse.<CampaignResponse>builder().result(campaignResponse).build();
    }

    @PostMapping("/create-request-campaign")

    ApiResponse<CampaignResponse> createRequest(@RequestBody @Valid CampaignRequest request) {
            return ApiResponse.<CampaignResponse>builder().result(campaignService.createRequest(request)).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<PageResponse<CampaignResponse>> getCampaigns(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<CampaignResponse>>builder().result(campaignService.getCampaigns(page, size, sortField, sortDirection)).build();
    }


    @GetMapping("/get-by-status/{status}")

    ApiResponse<PageResponse<CampaignResponse>> getCampaignsByStatus(@PathVariable("status") String status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<CampaignResponse>>builder().result(campaignService.getCampaignsByStatus(status, page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/get-by-title/{title}")
    ApiResponse<PageResponse<CampaignResponse>> getCampaignsByTitle(@PathVariable("title") String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<CampaignResponse>>builder().result(campaignService.getCampaignsByTitle(title, page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/get-by-id/{campaignId}")
    ApiResponse<CampaignResponse> getCampaign(@PathVariable("campaignId") int campaignId) {
        return ApiResponse.<CampaignResponse>builder().result(campaignService.getCampaign(campaignId)).build();
    }

    @GetMapping("/get-users-donated/{campaignId}")
    ApiResponse<List<Donation>> getAllUserDonated(@PathVariable("campaignId") int campaignId) {
        return  ApiResponse.<List<Donation>>builder().result(campaignService.getAllUserDonation(campaignId)).build();
    }

    @GetMapping("/get-by-year/{year}")
    ApiResponse<PageResponse<CampaignResponse>> getCampaignsByYear(@PathVariable("year") int year, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<CampaignResponse>>builder().result(campaignService.getCampaignsByYear(year, page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/count-by-month/{year}")
    ApiResponse<Map<Integer, Long>> countCampaignsByMonth(@PathVariable("year") int year) {
        return ApiResponse.<Map<Integer, Long>>builder().result(campaignService.countCampaignsByMonth(year)).build();
    }

    @PutMapping("/update/{campaignId}")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<CampaignResponse> updateCampaign(@PathVariable("campaignId") int campaignId, @RequestBody @Valid CampaignUpdateRequest request) {
        return ApiResponse.<CampaignResponse>builder().result(campaignService.updateCampaign(campaignId, request)).build();
    }

    @PutMapping("/update-status/{campaignId}")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<CampaignResponse> updateCampaignStatus(@PathVariable("campaignId") int campaignId) {
        return ApiResponse.<CampaignResponse>builder().result(campaignService.updateCampaignStatus(campaignId)).build();
    }
}
