package com.UniCharity.UniCharity.services.iservices;

import com.UniCharity.UniCharity.dto.request.ViolationActionCreateRequest;
import com.UniCharity.UniCharity.dto.request.ViolationActionUpdateRequest;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionResponse;

import java.util.List;

public interface IViolationActionService {
    public ViolationActionResponse createViolationAction(ViolationActionCreateRequest request);
    public List<ViolationActionResponse> getViolationActions();
    public ViolationActionResponse getViolationAction(int violationActionId);
    public ViolationActionResponse updateViolationAction(int violationActionId, ViolationActionUpdateRequest request);
}
