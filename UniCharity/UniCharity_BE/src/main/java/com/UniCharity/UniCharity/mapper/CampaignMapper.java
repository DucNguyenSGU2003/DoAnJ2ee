package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.CampaignCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignRequest;
import com.UniCharity.UniCharity.dto.request.CampaignUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportResponse;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.image.ImageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CampaignMapper {
    public static Campaign toCampaign(CampaignCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Campaign campaign = new Campaign();

        campaign.setTitle( request.getTitle() );
        campaign.setDescription( request.getDescription() );
        campaign.setTargetAmount( request.getTargetAmount() );
        campaign.setCurrentAmount( request.getCurrentAmount() );
        campaign.setCreatedAt( request.getCreatedAt() );
        campaign.setStartDate( request.getStartDate() );
        campaign.setEndDate( request.getEndDate() );
        campaign.setStatus( request.getStatus() );

        return campaign;
    }

    public static Campaign toCampaignFromRequest(CampaignRequest request) {
        if ( request == null ) {
            return null;
        }

        Campaign campaign = new Campaign();

        campaign.setTitle( request.getTitle() );
        campaign.setDescription( request.getDescription() );
        campaign.setTargetAmount( request.getTargetAmount() );
        campaign.setCreatedAt( request.getCreatedAt() );
        campaign.setStatus("Pending");

        return campaign;
    }

    public static CampaignResponse toCampaignResponse(Campaign campaign) {
        if (campaign == null){
            return null;
        }

        CampaignResponse campaignResponse = new CampaignResponse();

        campaignResponse.setId(campaign.getId());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setTargetAmount(campaign.getTargetAmount());
        if (campaign.getCurrentAmount() == null) {
            campaignResponse.setCurrentAmount(null);
        } else {
            campaignResponse.setCurrentAmount(campaign.getCurrentAmount());
        }
        campaignResponse.setCreatedAt(campaign.getCreatedAt());
        if (campaign.getStartDate() == null) {
            campaignResponse.setStartDate(null);
        } else {
            campaignResponse.setStartDate(campaign.getStartDate());
        }
        if (campaign.getEndDate() == null) {
            campaignResponse.setEndDate(null);
        } else {
            campaignResponse.setEndDate(campaign.getEndDate());
        }
        campaignResponse.setStatus(campaign.getStatus());
        campaignResponse.setCreatedBy(UserMapper.toUserSimple(campaign.getCreatedBy()));
        campaignResponse.setImages(imageSetToImageResponseList(campaign.getImages()));
        campaignResponse.setPolicies(policySetToPolicyResponseList(campaign.getPolicies()));
        campaignResponse.setDonations(donationSetToDonationResponse(campaign.getDonations()));
        campaignResponse.setCampaignReports(campaignReportSetToCampaignReportResponseList(campaign.getCampaignReports()));

        return campaignResponse;
    }

    public static CampaignSimple toCampaignSimple(Campaign campaign) {
        if (campaign == null){
            return null;
        }

        CampaignSimple campaignSimple = new CampaignSimple();

        campaignSimple.setId(campaign.getId());
        campaignSimple.setTitle(campaign.getTitle());
        campaignSimple.setDescription(campaign.getDescription());
        campaignSimple.setTargetAmount(campaign.getTargetAmount());
        campaignSimple.setCurrentAmount(campaign.getCurrentAmount());
        campaignSimple.setCreatedAt(campaign.getCreatedAt());
        campaignSimple.setStartDate(campaign.getStartDate());
        campaignSimple.setEndDate(campaign.getEndDate());
        campaignSimple.setStatus(campaign.getStatus());

        return campaignSimple;
    }

    public static void updateCampaign(Campaign campaign, CampaignUpdateRequest request) {
        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setTargetAmount(request.getTargetAmount());
        campaign.setCurrentAmount(request.getCurrentAmount());
        campaign.setCreatedAt(request.getCreateAt());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setStatus(request.getStatus());
    }

    protected static List<ImageResponse> imageSetToImageResponseList(Set<Image> set) {
        if (set == null) {
            return null;
        }

        List<ImageResponse> list = new ArrayList<>(set.size());
        for (Image image : set) {
            list.add(ImageMapper.toImageResponse(image));
        }

        return list;
    }

    protected static List<PolicyResponse> policySetToPolicyResponseList(Set<Policy> set) {
        if (set == null) {
            return null;
        }

        List<PolicyResponse> list = new ArrayList<>(set.size());
        for (Policy policy : set) {
            list.add(PolicyMapper.toPolicyResponse(policy));
        }

        return list;
    }

    protected static List<DonationResponse> donationSetToDonationResponse(Set<Donation> set) {
        if (set == null) {
            return null;
        }

        List<DonationResponse> list = new ArrayList<>(set.size());
        for (Donation donation : set) {
            list.add(DonationMapper.toDonationResponse(donation));
        }

        return list;
    }

    protected static List<CampaignReportResponse> campaignReportSetToCampaignReportResponseList(Set<CampaignReport> set) {
        if (set == null) {
            return null;
        }

        List<CampaignReportResponse> list = new ArrayList<>(set.size());
        for (CampaignReport campaignReport : set) {
            list.add(CampaignReportMapper.toCampaignReportResponse(campaignReport));
        }

        return list;
    }
}
