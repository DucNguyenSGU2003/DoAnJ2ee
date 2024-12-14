package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.FundAllocationCreateRequest;
import com.UniCharity.UniCharity.dto.request.FundAllocationUpdateRequest;
import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationResponse;
import com.UniCharity.UniCharity.dto.response.fundAllocation.FundAllocationSimple;
import com.UniCharity.UniCharity.entities.FundAllocation;

public class FundAllocationMapper {
    public static FundAllocation toFundAllocation(FundAllocationCreateRequest request) {
        if (request == null) {
            return null;
        }

        FundAllocation fundAllocation = new FundAllocation();

        fundAllocation.setCategory(request.getCategory());
        fundAllocation.setAmount(request.getAmount());
        fundAllocation.setDescription(request.getDescription());
        fundAllocation.setCreatedAt(request.getCreatedAt());

        return fundAllocation;
    }

    public static FundAllocationResponse toFundAllocationResponse(FundAllocation fundAllocation) {
        if (fundAllocation == null) {
            return null;
        }

        FundAllocationResponse.FundAllocationResponseBuilder fundAllocationResponse = FundAllocationResponse.builder();

        fundAllocationResponse.id(fundAllocation.getId());
        fundAllocationResponse.category(fundAllocation.getCategory());
        fundAllocationResponse.amount(fundAllocation.getAmount());
        fundAllocationResponse.description(fundAllocation.getDescription());
        fundAllocationResponse.createdAt(fundAllocation.getCreatedAt());
        fundAllocationResponse.report(CampaignReportMapper.toCampaignReportSimple(fundAllocation.getReport()));
        fundAllocationResponse.user(UserMapper.toUserSimple(fundAllocation.getUser()));

        return fundAllocationResponse.build();
    }

    public static FundAllocationSimple toFundAllocationSimple(FundAllocation fundAllocation) {
        if (fundAllocation == null) {
            return null;
        }

        FundAllocationSimple.FundAllocationSimpleBuilder fundAllocationSimple = FundAllocationSimple.builder();

        fundAllocationSimple.id(fundAllocation.getId());
        fundAllocationSimple.category(fundAllocation.getCategory());
        fundAllocationSimple.amount(fundAllocation.getAmount());
        fundAllocationSimple.description(fundAllocation.getDescription());
        fundAllocationSimple.createdAt(fundAllocation.getCreatedAt());

        return fundAllocationSimple.build();
    }

    public static void updateFundAllocation(FundAllocation fundAllocation, FundAllocationUpdateRequest request) {
        if(request == null) {
            return;
        }

        fundAllocation.setCategory(request.getCategory());
        fundAllocation.setAmount(request.getAmount());
        fundAllocation.setDescription(request.getDescription());
    }
}
