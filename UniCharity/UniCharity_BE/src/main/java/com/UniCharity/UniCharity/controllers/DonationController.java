package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.services.iservices.IDonationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DonationController {
    IDonationService donationService;

    @GetMapping
    ApiResponse<PageResponse<DonationResponse>> getDonations (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "donationDate") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<DonationResponse>>builder().result(donationService.getDonations(page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/get-by-id/{donationId}")
    ApiResponse<DonationResponse> getDonation (@PathVariable("donationId") int donationId) {
        return ApiResponse.<DonationResponse>builder().result(donationService.getDonation(donationId)).build();
    }

    @GetMapping("/get-by-user-id/{userId}")
    ApiResponse<PageResponse<DonationResponse>> getDonationsByUserId(@PathVariable("userId") int userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "donationDate") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<DonationResponse>>builder().result(donationService.getDonationsByUserId(userId, page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/get-by-campaign-id/{campaignId}")
    ApiResponse<PageResponse<DonationResponse>> getDonationsByCamppaignId(@PathVariable("campaignId") int campaignId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "donationDate") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<DonationResponse>>builder().result(donationService.getDonationsByCampaignId(campaignId, page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/top-donors-of-campaign/{campaignId}")
    ApiResponse<List<Object[]>> getTopUsersByCampaignId(@PathVariable("campaignId") int campaignId, @RequestParam(defaultValue = "5") int top) {
        return ApiResponse.<List<Object[]>>builder().result(donationService.getTopUserByCampaignId(campaignId, top)).build();
    }
}
