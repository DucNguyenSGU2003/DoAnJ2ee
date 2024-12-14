package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.ViolationActionCreateRequest;
import com.UniCharity.UniCharity.dto.request.ViolationActionUpdateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.violationAction.ViolationActionResponse;
import com.UniCharity.UniCharity.services.iservices.IViolationActionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/violation_actions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ViolationActionController {
    IViolationActionService service;

    @PostMapping("/create")
    ApiResponse<ViolationActionResponse> createViolationAction(@RequestBody @Valid ViolationActionCreateRequest request) {
        return ApiResponse.<ViolationActionResponse>builder().result(service.createViolationAction(request)).build();
    }

    @GetMapping
    ApiResponse<List<ViolationActionResponse>> getViolationActions(){
        return ApiResponse.<List<ViolationActionResponse>>builder().result(service.getViolationActions()).build();
    }

    @GetMapping("/get-by-id/{ViolationActionId}")
    ApiResponse<ViolationActionResponse> getViolationAction(@PathVariable("ViolationActionId") int ViolationActionId) {
        return ApiResponse.<ViolationActionResponse>builder().result(service.getViolationAction(ViolationActionId)).build();
    }

    @PutMapping("/update/{ViolationActionId}")
    ApiResponse<ViolationActionResponse> updateViolationAtion(@PathVariable("ViolationActionId") int ViolationActionId, @RequestBody @Valid ViolationActionUpdateRequest request) {
        return ApiResponse.<ViolationActionResponse>builder().result(service.updateViolationAction(ViolationActionId, request)).build();
    }
}
