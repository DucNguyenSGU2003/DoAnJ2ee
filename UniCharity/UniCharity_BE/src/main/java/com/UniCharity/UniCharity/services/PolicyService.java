package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.PolicyCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyUpdateRequest;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.entities.Campaign;
import com.UniCharity.UniCharity.entities.Policy;
import com.UniCharity.UniCharity.mapper.PolicyMapper;
import com.UniCharity.UniCharity.repositories.CampaignRepository;
import com.UniCharity.UniCharity.repositories.PolicyRepository;
import com.UniCharity.UniCharity.services.iservices.IPolicyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PolicyService implements IPolicyService {
    PolicyRepository policyRepository;
    CampaignRepository campaignRepository;

    @Override
    public PolicyResponse createPolicy(PolicyCreateRequest request) {
        Campaign campaign = campaignRepository.findById(request.getCampaign()).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        Policy policy = PolicyMapper.toPolicy(request);
        policy.setCampaign(campaign);
        policyRepository.save(policy);
        return PolicyMapper.toPolicyResponse(policy);
    }

    @Override
    public List<PolicyResponse> createPolicyList(List<PolicyCreateRequest> requests, int campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        List<Policy> policies = requests.stream().map(PolicyMapper::toPolicy).toList();
        for (Policy policy : policies) {
            policy.setCampaign(campaign);
        }
        policyRepository.saveAll(policies);
        return policies.stream().map(PolicyMapper::toPolicyResponse).toList();
    }


    @Override
    public PageResponse<PolicyResponse> getPolicies(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<PolicyResponse> policyResponses = policyRepository.findAll(sort).stream().map(PolicyMapper::toPolicyResponse).toList();

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), policyResponses.size());
        Page<PolicyResponse> policyPage = new PageImpl<>(policyResponses.subList(start, end), pageable, policyResponses.size());

        return new PageResponse<>(
                policyPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(policyPage.getTotalElements())
                        .currentPage(policyPage.getNumber())
                        .totalPages(policyPage.getTotalPages())
                        .pageSize(policyPage.getSize())
                        .build()
        );
    }

    @Override
    public PolicyResponse getPolicy(int policyId) {
        return PolicyMapper.toPolicyResponse(policyRepository.findById(policyId).orElseThrow(() -> new AppException(ErrorCode.POLICY_NOT_EXISTED)));
    }

    @Override
    public PageResponse<PolicyResponse> getPoliciesByCampaignId(int campaignId, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        List<PolicyResponse> policyResponses = policyRepository.findAllByCampaignId(campaignId, sort).stream().map(PolicyMapper::toPolicyResponse).toList();

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), policyResponses.size());
        Page<PolicyResponse> policyPage = new PageImpl<>(policyResponses.subList(start, end), pageable, policyResponses.size());

        return new PageResponse<>(
                policyPage.getContent(),
                com.UniCharity.UniCharity.dto.response.page.Page.builder()
                        .totalItem(policyPage.getTotalElements())
                        .currentPage(policyPage.getNumber())
                        .totalPages(policyPage.getTotalPages())
                        .pageSize(policyPage.getSize())
                        .build()
        );
    }


    @Override
    public PolicyResponse updatePolicy(int policyId, PolicyUpdateRequest request) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new AppException(ErrorCode.POLICY_NOT_EXISTED));
        PolicyMapper.updatePolicy(policy, request);
        return PolicyMapper.toPolicyResponse(policyRepository.save(policy));
    }

    @Override
    public List<PolicyResponse> updateListPolicies(int campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        policyRepository.deleteAll(campaign.getPolicies());

        return List.of();
    }

    @Override
    public void deleteAllPoliciesByCampaignId(int campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new AppException(ErrorCode.CAMPAIGN_NOT_EXISTED));
        policyRepository.deleteAll(campaign.getPolicies());
    }
}
