package com.UniCharity.UniCharity.services;

import com.UniCharity.UniCharity.dto.request.PolicyViolationCreateRequest;
import com.UniCharity.UniCharity.dto.request.PolicyViolationUpdateRequest;
import com.UniCharity.UniCharity.dto.response.policyViolation.PolicyViolationResponse;
import com.UniCharity.UniCharity.exception.AppException;
import com.UniCharity.UniCharity.exception.ErrorCode;
import com.UniCharity.UniCharity.mapper.PolicyViolationMapper;
import com.UniCharity.UniCharity.entities.Policy;
import com.UniCharity.UniCharity.entities.PolicyViolation;
import com.UniCharity.UniCharity.repositories.PolicyRepository;
import com.UniCharity.UniCharity.repositories.PolicyViolationRepository;
import com.UniCharity.UniCharity.services.iservices.IPolicyViolationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PolicyViolationService implements IPolicyViolationService {
    PolicyViolationRepository policyViolationRepository;
    PolicyRepository policyRepository;

    @Override
    public PolicyViolationResponse createPolicyViolation(PolicyViolationCreateRequest request) {
        Policy policy = policyRepository.findById(request.getPolicy()).orElseThrow(() -> new AppException(ErrorCode.POLICY_NOT_EXISTED));
        PolicyViolation policyViolation = PolicyViolationMapper.toPolicyViolation(request);
        policyViolation.setPolicy(policy);
        policyViolationRepository.save(policyViolation);
        return PolicyViolationMapper.toPolicyViolationResponse(policyViolation);
    }

    @Override
    public List<PolicyViolationResponse> getPolicyViolations() {
        return policyViolationRepository.findAll().stream().map(PolicyViolationMapper::toPolicyViolationResponse).toList().reversed();
    }

    @Override
    public PolicyViolationResponse getPolicyViolation(int policyViolationId) {
        return PolicyViolationMapper.toPolicyViolationResponse(policyViolationRepository.findById(policyViolationId).orElseThrow(() -> new AppException(ErrorCode.POLICY_VIOLATION_NOT_EXISTED)));
    }

    @Override
    public PolicyViolationResponse updatePolicyViolation(int policyViolationId, PolicyViolationUpdateRequest request) {
        PolicyViolation policyViolation = policyViolationRepository.findById(policyViolationId).orElseThrow(() -> new AppException(ErrorCode.POLICY_VIOLATION_NOT_EXISTED));
        PolicyViolationMapper.updatePolicyViolation(policyViolation, request);
        policyViolationRepository.save(policyViolation);
        return PolicyViolationMapper.toPolicyViolationResponse(policyViolation);
    }
}
