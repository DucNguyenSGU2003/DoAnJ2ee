package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.FundAllocationCreateRequest;
import com.UniCharity.UniCharity.dto.request.FundAllocationUpdateRequest;
import com.UniCharity.UniCharity.dto.response.donation.DonationResponse;
import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.entities.CampaignReport;
import com.UniCharity.UniCharity.entities.FundAllocation;
import com.UniCharity.UniCharity.entities.User;
import com.UniCharity.UniCharity.mapper.FundAllocationMapper;
import com.UniCharity.UniCharity.repositories.CampaignReportRepository;
import com.UniCharity.UniCharity.repositories.FundAllocationRepository;
import com.UniCharity.UniCharity.repositories.UserRepository;
import com.UniCharity.UniCharity.services.iservices.IFundAllocationService;
import com.UniCharity.UniCharity.utils.PageUtils;
import com.UniCharity.UniCharity.utils.SortUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FundAllocationService implements IFundAllocationService {
    FundAllocationRepository fundAllocationRepository;
    UserRepository userRepository;
    CampaignReportRepository campaignReportRepository;

    @Override
    public FundAllocationResponse createFundAllocation(FundAllocationCreateRequest request) {
        User user = null;
        if(request.getUser() != null) user = userRepository.findByIdAndRole(request.getUser(), "user").orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        CampaignReport campaignReport = campaignReportRepository.findById(request.getReport()).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_REPORT_NOT_EXISTED));
        FundAllocation fundAllocation = FundAllocationMapper.toFundAllocation(request);
        fundAllocation.setUser(user);
        fundAllocation.setReport(campaignReport);
        fundAllocation = fundAllocationRepository.save(fundAllocation);
        return FundAllocationMapper.toFundAllocationResponse(fundAllocation);
    }

    @Override
    public List<FundAllocationResponse> getFundAllocations() {
        return fundAllocationRepository.findAll().stream().map(FundAllocationMapper::toFundAllocationResponse).toList().reversed();
    }

    @Override
    public PageResponse<FundAllocationResponse> getFundAllocationsByReortId(int reportId, int page, int size, String sortField, String sortDirection) {
        CampaignReport campaignReport = campaignReportRepository.findById(reportId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_REPORT_NOT_EXISTED));

        List<FundAllocationResponse> fundAllocationResponses = fundAllocationRepository.findByReportId(reportId).stream().map(FundAllocationMapper::toFundAllocationResponse).collect(Collectors.toList());

        SortUtils.sortList(fundAllocationResponses, sortField, sortDirection);

        Page<FundAllocationResponse> fundAllocationsPage = PageUtils.paginateList(fundAllocationResponses, page, size);

        return new PageResponse<>(
                fundAllocationsPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(fundAllocationsPage.getTotalElements())
                        .currentPage(fundAllocationsPage.getNumber())
                        .totalPages(fundAllocationsPage.getTotalPages())
                        .pageSize(fundAllocationsPage.getSize())
                        .build()
        );
    }

    @Override
    public FundAllocationResponse getFundAllocation(int fundAllocationId) {
        return FundAllocationMapper.toFundAllocationResponse(fundAllocationRepository.findById(fundAllocationId).orElseThrow(() -> new AppException(ErrorCode.FUND_ALLOCATION_NOT_EXISTED)));
    }

    @Override
    public FundAllocationResponse updateFundAllocation(int fundAllocationId, FundAllocationUpdateRequest request) {
        FundAllocation fundAllocation = fundAllocationRepository.findById(fundAllocationId).orElseThrow(() -> new AppException(ErrorCode.FUND_ALLOCATION_NOT_EXISTED));
        FundAllocationMapper.updateFundAllocation(fundAllocation, request);
        return FundAllocationMapper.toFundAllocationResponse(fundAllocationRepository.save(fundAllocation));
    }
}
