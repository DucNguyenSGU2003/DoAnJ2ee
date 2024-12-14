package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.request.CampaignCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignRequest;
import com.UniCharity.UniCharity.dto.request.CampaignUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.entities.Donation;

import java.util.List;
import java.util.Map;

public interface ICampaignService {
    public CampaignResponse createCampaign(CampaignCreateRequest request);
    public CampaignResponse createRequest(CampaignRequest request);

    public PageResponse<CampaignResponse> getCampaigns(int page, int size, String sortField, String sortDirection);
    public PageResponse<CampaignResponse> getCampaignsByStatus(String status, int page, int size, String sortField, String sortDirection);
    public CampaignResponse getCampaign(int campaignId);
    public PageResponse<CampaignResponse> getCampaignsByTitle(String title, int page, int size, String sortField, String sortDirection);
    public List<Donation> getAllUserDonation(int campaignId);
    public Map<Integer, Long> countCampaignsByMonth(int year);
    public PageResponse<CampaignResponse> getCampaignsByYear(int year, int page, int size, String sortField, String sortDirection);

    public CampaignResponse updateCampaign(int campaignId, CampaignUpdateRequest request);
    public CampaignResponse updateCampaignStatus(int campaignId);
    public CampaignResponse updateCampaignCurrentAmount(int campaignId, long amout);
}
