package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.request.PolicyCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyUpdateRequest;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;

import java.util.List;

public interface IPolicyService  {
    public PolicyResponse createPolicy(PolicyCreateRequest request);
    public List<PolicyResponse> createPolicyList(List<PolicyCreateRequest> requests, int campaignId);
    public PageResponse<PolicyResponse> getPolicies(int page, int size, String sortField, String sortDirection);
    public PolicyResponse getPolicy(int policyId);
    public PageResponse<PolicyResponse> getPoliciesByCampaignId(int campaignId, int page, int size, String sortField, String sortDirection);
    public PolicyResponse updatePolicy(int policyId, PolicyUpdateRequest request);
    public List<PolicyResponse> updateListPolicies (int campaignId);
    public void deleteAllPoliciesByCampaignId (int campaignId);
}
