package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.PolicyCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyUpdateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.services.iservices.IPolicyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PolicyController {
    IPolicyService policyService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<PolicyResponse> createPolicy(@RequestBody @Valid PolicyCreateRequest request) {
        return ApiResponse.<PolicyResponse>builder().result(policyService.createPolicy(request)).build();
    }

    @GetMapping
    ApiResponse<PageResponse<PolicyResponse>> getPolicies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<PolicyResponse>>builder().result(policyService.getPolicies(page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/get-by-id/{policyId}")
    ApiResponse<PolicyResponse> getPolicy(@PathVariable("policyId") int policyId) {
        return ApiResponse.<PolicyResponse>builder().result(policyService.getPolicy(policyId)).build();
    }

    @GetMapping("/get-by-campaign-id/{campaignId}")
    ApiResponse<PageResponse<PolicyResponse>> getPoliciesByCampaignId(@PathVariable("campaignId") int campaignId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "createdAt") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<PolicyResponse>>builder().result(policyService.getPoliciesByCampaignId(campaignId, page, size, sortField, sortDirection)).build();
    }

    @PutMapping("/update/{policyId}")
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<PolicyResponse> updatePolicy(@PathVariable("policyId") int policyId, @RequestBody @Valid PolicyUpdateRequest request) {
        return ApiResponse.<PolicyResponse>builder().result(policyService.updatePolicy(policyId, request)).build();
    }
}
