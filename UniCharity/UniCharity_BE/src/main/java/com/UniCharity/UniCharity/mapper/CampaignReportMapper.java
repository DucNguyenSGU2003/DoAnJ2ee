package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.CampaignReportCreateRequest;
import com.UniCharity.UniCharity.dto.request.CampaignReportUpdateRequest;
import com.UniCharity.UniCharity.dto.response.campaign.CampaignSimple;
import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportResponse;
import com.UniCharity.UniCharity.dto.response.campaignReport.CampaignReportSimple;
import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationResponse;
import com.UniCharity.UniCharity.entities.CampaignReport;
import com.UniCharity.UniCharity.entities.FundAllocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CampaignReportMapper {
    public static CampaignReport toCampaignReport(CampaignReportCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        CampaignReport campaignReport = new CampaignReport();

        campaignReport.setTotalDonations( request.getTotalDonations() );
        campaignReport.setTotalRecipients( request.getTotalRecipients() );
        campaignReport.setResultsSummary( request.getResultsSummary() );
        campaignReport.setLessonsLearned( request.getLessonsLearned() );
        campaignReport.setReportDate( request.getReportDate() );
        campaignReport.setCreatedAt( request.getCreatedAt() );
        campaignReport.setUpdatedAt( request.getUpdatedAt() );

        return campaignReport;
    }

    public static CampaignReportResponse toCampaignReportResponse(CampaignReport campaignReport) {
        if ( campaignReport == null ) {
            return null;
        }

        CampaignReportResponse.CampaignReportResponseBuilder campaignReportResponse = CampaignReportResponse.builder();

        campaignReportResponse.campaign(CampaignMapper.toCampaignSimple(campaignReport.getCampaign()));
        campaignReportResponse.fundAllocations( fundAllocationSetToFundAllocationResponseList( campaignReport.getFundAllocations() ) );
        campaignReportResponse.id( campaignReport.getId() );
        campaignReportResponse.totalDonations( campaignReport.getTotalDonations() );
        campaignReportResponse.totalRecipients( campaignReport.getTotalRecipients() );
        campaignReportResponse.resultsSummary( campaignReport.getResultsSummary() );
        campaignReportResponse.lessonsLearned( campaignReport.getLessonsLearned() );
        campaignReportResponse.reportDate( campaignReport.getReportDate() );
        campaignReportResponse.createdAt( campaignReport.getCreatedAt() );
        campaignReportResponse.updatedAt( campaignReport.getUpdatedAt() );

        return campaignReportResponse.build();
    }

    public static CampaignReportSimple toCampaignReportSimple(CampaignReport campaignReport) {
        if ( campaignReport == null ) {
            return null;
        }

        CampaignReportSimple.CampaignReportSimpleBuilder campaignReportSimple = CampaignReportSimple.builder();

        campaignReportSimple.id(campaignReport.getId());
        campaignReportSimple.totalDonations(campaignReport.getTotalDonations());
        campaignReportSimple.totalRecipients(campaignReport.getTotalRecipients());
        campaignReportSimple.resultsSummary(campaignReport.getResultsSummary());
        campaignReportSimple.lessonsLearned(campaignReport.getLessonsLearned());
        campaignReportSimple.reportDate(campaignReport.getReportDate());
        campaignReportSimple.createdAt(campaignReport.getCreatedAt());
        campaignReportSimple.updatedAt(campaignReport.getUpdatedAt());

        return campaignReportSimple.build();
    }

    public  static void updateCampaignReport(CampaignReport campaignReport, CampaignReportUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        campaignReport.setTotalDonations( request.getTotalDonations() );
        campaignReport.setTotalRecipients( request.getTotalRecipients() );
        campaignReport.setResultsSummary( request.getResultsSummary() );
        campaignReport.setLessonsLearned( request.getLessonsLearned() );
        campaignReport.setReportDate( request.getReportDate() );
        campaignReport.setUpdatedAt( request.getUpdatedAt() );
    }

    protected static List<FundAllocationResponse> fundAllocationSetToFundAllocationResponseList(Set<FundAllocation> set) {
        if ( set == null ) {
            return null;
        }

        List<FundAllocationResponse> list = new ArrayList<FundAllocationResponse>( set.size() );
        for ( FundAllocation fundAllocation : set ) {
            list.add( FundAllocationMapper.toFundAllocationResponse( fundAllocation ) );
        }

        return list;
    }
}
