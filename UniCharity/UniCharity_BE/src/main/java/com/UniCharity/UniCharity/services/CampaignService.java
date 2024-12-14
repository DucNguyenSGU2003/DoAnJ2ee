package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.CampaignCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignRequest;
import com.UniCharity.UniCharity.dto.request.CampaignUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.entities.*;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.mapper.CampaignMapper;
import com.UniCharity.UniCharity.repositories.CampaignRepository;
import com.UniCharity.UniCharity.repositories.PolicyRepository;
import com.UniCharity.UniCharity.repositories.UserRepository;
import com.UniCharity.UniCharity.services.iservices.ICampaignService;
import com.UniCharity.UniCharity.utils.PageUtils;
import com.UniCharity.UniCharity.utils.SortUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CampaignService implements ICampaignService {
    CampaignRepository campaignRepository;
    UserRepository userRepository;
    PolicyRepository policyRepository;
    PolicyService policyService;


    @Override
    public CampaignResponse createCampaign(CampaignCreateRequest request) {
        User user = userRepository.findById(request.getCreatedBy()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Campaign campaign = CampaignMapper.toCampaign(request);
        campaign.setCreatedBy(user);
        campaign = campaignRepository.save(campaign);
        return CampaignMapper.toCampaignResponse(campaign);
    }

    @Override
    public CampaignResponse createRequest(CampaignRequest request) {
        User user = userRepository.findById(request.getCreatedBy()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Campaign campaign = CampaignMapper.toCampaignFromRequest(request);
        campaign.setCreatedBy(user);
        campaign = campaignRepository.save(campaign);
        return CampaignMapper.toCampaignResponse(campaign);
    }

    @Override
    public PageResponse<CampaignResponse> getCampaigns(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<CampaignResponse> campaignResponses = campaignRepository.findAll(sort).stream().map(CampaignMapper::toCampaignResponse).collect(Collectors.toList());

        Page<CampaignResponse> campaignPage = PageUtils.paginateList(campaignResponses, page, size);

        return new PageResponse<>(
                campaignPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(campaignPage.getTotalElements())
                        .currentPage(campaignPage.getNumber())
                        .totalPages(campaignPage.getTotalPages())
                        .pageSize(campaignPage.getSize())
                        .build()
        );
    }

    @Override
    public PageResponse<CampaignResponse> getCampaignsByStatus(String status, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<CampaignResponse> campaignResponses = campaignRepository.findByStatus(status, sort).stream().map(CampaignMapper::toCampaignResponse).toList();

        Page<CampaignResponse> campaignPage = PageUtils.paginateList(campaignResponses, page, size);

        return new PageResponse<>(
                campaignPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(campaignPage.getTotalElements())
                        .currentPage(campaignPage.getNumber())
                        .totalPages(campaignPage.getTotalPages())
                        .pageSize(campaignPage.getSize())
                        .build()
        );
    }

    @Override
    public CampaignResponse getCampaign(int campaignId) {
        return CampaignMapper.toCampaignResponse(campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED)));
    }

    @Override
    public PageResponse<CampaignResponse> getCampaignsByTitle(String title, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<CampaignResponse> campaignResponses = campaignRepository.findByTitleContainingIgnoreCase(title, sort).stream().map(CampaignMapper::toCampaignResponse).toList();

        Page<CampaignResponse> campaignPage = PageUtils.paginateList(campaignResponses, page, size);

        return new PageResponse<>(
                campaignPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(campaignPage.getTotalElements())
                        .currentPage(campaignPage.getNumber())
                        .totalPages(campaignPage.getTotalPages())
                        .pageSize(campaignPage.getSize())
                        .build()
        );
    }

    @Override
    public CampaignResponse updateCampaign(int campaignId, CampaignUpdateRequest request) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        if(!campaign.getStatus().equals("Pending")) throw new AppException(ErrorCode.CAMPAIGN_STATUS_NOT_PENDING);
        User user = userRepository.findById(request.getCreatedBy()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        CampaignMapper.updateCampaign(campaign, request);

        List<Policy> policies = policyRepository.findAllByCampaignId(campaignId);
        policyRepository.deleteAll(policies);

        policyService.createPolicyList(request.getPolicies(), campaignId);
        return CampaignMapper.toCampaignResponse(campaignRepository.save(campaign));
    }

    @Override
    public CampaignResponse updateCampaignStatus(int campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        campaign.setStatus("canceled");
        return CampaignMapper.toCampaignResponse(campaignRepository.save(campaign));
    }

    @Override
    public CampaignResponse updateCampaignCurrentAmount(int campaignId, long amout) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        long newCurrentAmount = campaign.getCurrentAmount() + (amout/100);
        campaign.setCurrentAmount(newCurrentAmount);
        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignResponse(campaign);
    }

    @Override
    public List<Donation> getAllUserDonation(int campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        List<Donation> userDonated = new ArrayList<>();
        for (Donation donation : campaign.getDonations()){
            boolean flag = false;
            for (Transaction transaction : donation.getTransactions()) {
                if(transaction.getTransactionStatus().equals("00")) {
                    flag = true;
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag == true) {
                userDonated.add(donation);
            }
        }
        return userDonated;
    }

    @Override
    public Map<Integer, Long> countCampaignsByMonth(int year) {
        List<Object[]> results = campaignRepository.countCampaignsByMonth(year);
        Map<Integer, Long> campaignsByMonth = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            campaignsByMonth.put(i, 0L);
        }
        for (Object[] result : results){
            Integer month = (Integer) result[0];
            Long count = (Long) result[1];
            campaignsByMonth.put(month, count);
        }
        return campaignsByMonth;
    }

    @Override
    public PageResponse<CampaignResponse> getCampaignsByYear(int year, int page, int size, String sortField, String sortDirection) {
        List<CampaignResponse> campaignResponses = campaignRepository.findCampaignsByYear(year).stream().map(CampaignMapper::toCampaignResponse).collect(Collectors.toList());

        SortUtils.sortList(campaignResponses, sortField, sortDirection);

        Page<CampaignResponse> campaignPage = PageUtils.paginateList(campaignResponses, page, size);

        return new PageResponse<>(
                campaignPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(campaignPage.getTotalElements())
                        .currentPage(campaignPage.getNumber())
                        .totalPages(campaignPage.getTotalPages())
                        .pageSize(campaignPage.getSize())
                        .build()
        );
    }
}
