package com.UniCharity.UniCharity.mapper;

import com.UniCharity.UniCharity.dto.request.ViolationActionCreateRequest;
import com.UniCharity.UniCharity.dto.request.ViolationActionUpdateRequest;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionResponse;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionSimple;
import com.UniCharity.UniCharity.entities.ViolationAction;

public class ViolationActionMapper {
    public static ViolationAction toViolationAction(ViolationActionCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        ViolationAction violationAction = new ViolationAction();

        violationAction.setActionDescription(request.getActionDescription());
        violationAction.setActionDate(request.getActionDate());
        violationAction.setStatus(request.getStatus());
        violationAction.setCreatedAt(request.getCreatedAt());

        return null;
    }

    public static ViolationActionResponse toViolationActionResponse(ViolationAction violationAction) {
        if ( violationAction == null ) {
            return null;
        }

        ViolationActionResponse.ViolationActionResponseBuilder violationActionResponse = ViolationActionResponse.builder();

        violationActionResponse.id(violationAction.getId());
        violationActionResponse.actionDescription(violationAction.getActionDescription());
        violationActionResponse.actionDate(violationAction.getActionDate());
        violationActionResponse.status(violationAction.getStatus());
        violationActionResponse.createdAt(violationAction.getCreatedAt());
        violationActionResponse.violation(PolicyViolationMapper.toPolicyViolationSimple(violationAction.getViolation()));

        return violationActionResponse.build();
    }

    public static ViolationActionSimple toViolationActionSimple(ViolationAction violationAction) {
        if ( violationAction == null ) {
            return null;
        }

        ViolationActionSimple.ViolationActionSimpleBuilder violationActionSimple = ViolationActionSimple.builder();

        violationActionSimple.id(violationAction.getId());
        violationActionSimple.actionDescription(violationAction.getActionDescription());
        violationActionSimple.actionDate(violationAction.getActionDate());
        violationActionSimple.status(violationAction.getStatus());
        violationActionSimple.createdAt(violationAction.getCreatedAt());

        return violationActionSimple.build();
    }

    public static void updateViolationAction(ViolationAction violationAction, ViolationActionUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        violationAction.setActionDescription(request.getActionDescription());
        violationAction.setActionDate(request.getActionDate());
        violationAction.setStatus(request.getStatus());
    }
}
