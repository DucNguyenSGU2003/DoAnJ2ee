package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.constant.CampaignStatus;
import com.UniCharity.UniCharity.dto.request.CampaignReportCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignReportUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.mapper.CampaignReportMapper;
import com.UniCharity.UniCharity.entities.Campaign;
import com.UniCharity.UniCharity.entities.CampaignReport;
import com.UniCharity.UniCharity.repositories.CampaignReportRepository;
import com.UniCharity.UniCharity.repositories.CampaignRepository;
import com.UniCharity.UniCharity.services.iservices.ICampaignReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CampaignReportService implements ICampaignReportService {
    CampaignReportRepository campaignReportRepository;
    CampaignRepository campaignRepository;

    @Override
    public CampaignReportResponse createCampaignReport(CampaignReportCreateRequest request) {
        Campaign campaign = campaignRepository.findById(request.getCampaign()).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        if(campaign.getStatus().equals(CampaignStatus.PENDING) || campaign.getStatus().equals(CampaignStatus.CANCELLED) || campaign.getStatus().equals(CampaignStatus.ACTIVE)) throw new AppException(ErrorCode.CAMPAiGN_MUST_BE_COMPLETED);
        if(campaign.getCampaignReports().size() > 0) throw new AppException(ErrorCode.CAMPAIGN_HAS_BEEN_REPORT);
        CampaignReport campaignReport = CampaignReportMapper.toCampaignReport(request);
        campaignReport.setCampaign(campaign);
        campaignReportRepository.save(campaignReport);
        return CampaignReportMapper.toCampaignReportResponse(campaignReport);
    }

    @Override
    public PageResponse<CampaignReportResponse> getCampaignReports(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<CampaignReportResponse> campaignReportPage = campaignReportRepository.findAll(pageable).map(CampaignReportMapper::toCampaignReportResponse);
        return new PageResponse<>(
                campaignReportPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(campaignReportPage.getTotalElements())
                        .currentPage(campaignReportPage.getNumber())
                        .totalPages(campaignReportPage.getTotalPages())
                        .pageSize(campaignReportPage.getSize())
                        .build()
        );
    }

    @Override
    public CampaignReportResponse getCampaignReport(int camportReportId) {
        return CampaignReportMapper.toCampaignReportResponse(campaignReportRepository.findById(camportReportId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_REPORT_NOT_EXISTED)));
    }

    @Override
    public CampaignReportResponse updateCampaignReport(int camportReportId, CampaignReportUpdateRequest request) {
        CampaignReport campaignReport = campaignReportRepository.findById(camportReportId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_REPORT_NOT_EXISTED));
        CampaignReportMapper.updateCampaignReport(campaignReport, request);
        return CampaignReportMapper.toCampaignReportResponse(campaignReportRepository.save(campaignReport));
    }
}
