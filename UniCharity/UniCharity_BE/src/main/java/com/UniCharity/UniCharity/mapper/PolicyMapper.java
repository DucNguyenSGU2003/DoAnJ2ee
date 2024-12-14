package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.PolicyCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyUpdateRequest;
import com.UniCharity.UniCharity.dto.response.policy.PolicyResponse;
import com.UniCharity.UniCharity.dto.response.policy.PolicySimple;
import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationResponse;
import com.UniCharity.UniCharity.entities.Policy;
import com.UniCharity.UniCharity.entities.PolicyViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PolicyMapper {
    public static Policy toPolicy(PolicyCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Policy policy = new Policy();

        policy.setPolicyDescription( request.getPolicyDescription() );
        policy.setEligibilityCriteria( request.getEligibilityCriteria() );
        policy.setApprovalRequired( request.getApprovalRequired() );
        policy.setCreatedAt( request.getCreatedAt() );
        policy.setUpdatedAt( request.getUpdatedAt() );

        return policy;
    }

    public static PolicyResponse toPolicyResponse(Policy policy) {
        if ( policy == null ) {
            return null;
        }

        PolicyResponse.PolicyResponseBuilder policyResponse = PolicyResponse.builder();

        policyResponse.policyViolations(policyViolationSetToPolicyViolationResponseList(policy.getPolicyViolations()));
        policyResponse.campaign(CampaignMapper.toCampaignSimple(policy.getCampaign()));
        policyResponse.id( policy.getId() );
        policyResponse.policyDescription( policy.getPolicyDescription() );
        policyResponse.eligibilityCriteria( policy.getEligibilityCriteria() );
        policyResponse.approvalRequired( policy.getApprovalRequired() );
        policyResponse.createdAt( policy.getCreatedAt() );
        policyResponse.updatedAt( policy.getUpdatedAt() );

        return policyResponse.build();
    }

    public static PolicySimple toPolicySimple(Policy policy) {
        if (policy == null) {
            return null;
        }

        PolicySimple.PolicySimpleBuilder policySimple = PolicySimple.builder();

        policySimple.id(policy.getId());
        policySimple.policyDescription(policy.getPolicyDescription());
        policySimple.eligibilityCriteria(policy.getEligibilityCriteria());
        policySimple.approvalRequired(policy.getApprovalRequired());
        policySimple.createdAt(policy.getCreatedAt());
        policySimple.updatedAt(policy.getUpdatedAt());

        return policySimple.build();
    }

    public static void updatePolicy(Policy policy, PolicyUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        policy.setPolicyDescription( request.getPolicyDescription() );
        policy.setEligibilityCriteria( request.getEligibilityCriteria() );
        policy.setApprovalRequired( request.getApprovalRequired() );
        policy.setUpdatedAt( request.getUpdatedAt() );
    }

    protected static List<PolicyViolationResponse> policyViolationSetToPolicyViolationResponseList(Set<PolicyViolation> set) {
        if ( set == null ) {
            return null;
        }

        List<PolicyViolationResponse> list = new ArrayList<PolicyViolationResponse>( set.size() );
        for ( PolicyViolation policyViolation : set ) {
            list.add( PolicyViolationMapper.toPolicyViolationResponse(policyViolation) );
        }

        return list;
    }
}
