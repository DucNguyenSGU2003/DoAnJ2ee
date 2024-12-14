package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.PolicyViolationCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyViolationUpdateRequest;
import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationResponse;
import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationSimple;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionResponse;
import com.UniCharity.UniCharity.entities.PolicyViolation;
import com.UniCharity.UniCharity.entities.ViolationAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PolicyViolationMapper {
    public static PolicyViolation toPolicyViolation(PolicyViolationCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        PolicyViolation policyViolation = new PolicyViolation();

        policyViolation.setViolationDescription(request.getViolationDescription());
        policyViolation.setViolationDate(request.getViolationDate());
        policyViolation.setStatus(request.getStatus());
        policyViolation.setCreatedAt(request.getCreatedAt());
        policyViolation.setUpdatedAt(request.getUpdatedAt());

        return policyViolation;
    }

    public static PolicyViolationResponse toPolicyViolationResponse(PolicyViolation policyViolation) {
        if (policyViolation == null) {
            return null;
        }

        PolicyViolationResponse.PolicyViolationResponseBuilder policyViolationResponse = PolicyViolationResponse.builder();

        policyViolationResponse.id(policyViolation.getId());
        policyViolationResponse.violationDescription(policyViolation.getViolationDescription());
        policyViolationResponse.violationDate(policyViolation.getViolationDate());
        policyViolationResponse.status(policyViolation.getStatus());
        policyViolationResponse.createdAt(policyViolation.getCreatedAt());
        policyViolationResponse.updatedAt(policyViolation.getUpdatedAt());
        policyViolationResponse.policy(PolicyMapper.toPolicySimple(policyViolation.getPolicy()));
        policyViolationResponse.violationActions(violationActionSetToViolationActionResponseList(policyViolation.getViolationActions()));

        return policyViolationResponse.build();
    }

    public static PolicyViolationSimple toPolicyViolationSimple(PolicyViolation policyViolation) {
        if (policyViolation == null) {
            return null;
        }

        PolicyViolationSimple.PolicyViolationSimpleBuilder policyViolationSimple = PolicyViolationSimple.builder();

        policyViolationSimple.id(policyViolation.getId());
        policyViolationSimple.violationDescription(policyViolation.getViolationDescription());
        policyViolationSimple.violationDate(policyViolation.getViolationDate());
        policyViolationSimple.status(policyViolation.getStatus());
        policyViolationSimple.createdAt(policyViolation.getCreatedAt());
        policyViolationSimple.updatedAt(policyViolation.getUpdatedAt());

        return policyViolationSimple.build();
    }

    public static void updatePolicyViolation(PolicyViolation policyViolation, PolicyViolationUpdateRequest request) {
        if (request == null) {
            return;
        }

        policyViolation.setViolationDescription(request.getViolationDescription());
        policyViolation.setViolationDate(request.getViolationDate());
        policyViolation.setStatus(request.getStatus());
        policyViolation.setUpdatedAt(request.getUpdatedAt());
    }

    protected static List<ViolationActionResponse> violationActionSetToViolationActionResponseList(Set<ViolationAction> set) {
        if(set == null) {
            return null;
        }

        List<ViolationActionResponse> list = new ArrayList<>(set.size());
        for (ViolationAction violationAction : set) {
            list.add(ViolationActionMapper.toViolationActionResponse(violationAction));
        }

        return list;
    }
}
