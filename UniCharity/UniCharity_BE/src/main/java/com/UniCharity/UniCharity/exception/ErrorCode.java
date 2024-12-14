package com.UniCharity.UniCharity.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9998, "Invalid message key", HttpStatus.BAD_REQUEST),

    // User
    USER_NOT_EXISTED(1001, "User not existed", HttpStatus.NOT_FOUND),
    STUDENT_NOT_EXISTED(1002, "Student not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003, "Unauthenticated", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1004, "User existed", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1005, "Email already exists", HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS(1006, "Username already exists", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1007, "Invalid email address", HttpStatus.BAD_REQUEST),
    EMAIL_IS_REQUIRED(1008, "Email is required", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_REQUIRED(1009, "Password is required", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1010, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_MUST_CONTAIN_UPPERCASE(1011, "Password must contain at least one uppercase letter", HttpStatus.BAD_REQUEST),
    PASSWORD_MUST_CONTAIN_LOWERCASE(1012, "Password must contain at least one lowercase letter", HttpStatus.BAD_REQUEST),
    PASSWORD_MUST_CONTAIN_NUMBER(1013, "Password must contain at least one number", HttpStatus.BAD_REQUEST),
    PASSWORD_MUST_CONTAIN_SPECIAL_CHARACTER(1014, "Password must contain at least one special character", HttpStatus.BAD_REQUEST),
    CREATOR_ID_MUST_BE_POSITIVE(1015, "ID người tạo phải là số dương", HttpStatus.BAD_REQUEST),
    USER_ID_MUST_BE_POSITIVE(1016, "User ID must be positive", HttpStatus.BAD_REQUEST),

    // Campaign
    CAMPAIGN_NOT_EXISTED(1051, "Campaign not existed", HttpStatus.NOT_FOUND),
    CAMPAIGN_STATUS_NOT_PENDING(1052, "Campaign status must be 'Pending'", HttpStatus.BAD_REQUEST),
    CAMPAiGN_MUST_BE_COMPLETED(1053, "Campaign has to be completed", HttpStatus.BAD_REQUEST),
    CAMPAIGN_HAS_BEEN_REPORT(1056, "Campaign has already been reported", HttpStatus.BAD_REQUEST),

    // CampaignReport
    CAMPAIGN_REPORT_NOT_EXISTED(2001, "Campaign reposrt not existed", HttpStatus.NOT_FOUND),
    DONATION_AMOUNT_CANNOT_BE_NULL(2002, "Donation amount cannot be null", HttpStatus.BAD_REQUEST),
    DONATION_AMOUNT_MUST_BE_POSITIVE_OR_ZERO(2003, "Donation amount must be positive or zero", HttpStatus.BAD_REQUEST),
    NUMBER_OF_RECIPIENTS_CANNOT_BE_NULL(2004, "Number of recipients cannot be null", HttpStatus.BAD_REQUEST),
    NUMBER_OF_RECIPIENTS_MUST_BE_POSITIVE_OR_ZERO(2005, "Number of recipients must be positive or zero", HttpStatus.BAD_REQUEST),
    SUMMARY_CANNOT_BE_BLANK(2006, "Summary cannot be blank", HttpStatus.BAD_REQUEST),
    SUMMARY_TOO_LONG(2007, "Summary cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    LESSONS_LEARNED_CANNOT_BE_BLANK(2008, "Bài học rút ra không được để trống", HttpStatus.BAD_REQUEST),
    LESSONS_LEARNED_TOO_LONG(2009, "Lessons learned cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    REPORT_ID_MUST_BE_POSITIVE(2010, "Report ID must be positive", HttpStatus.BAD_REQUEST),

    // FundAllocation
    FUND_ALLOCATION_NOT_EXISTED(2051, "Fund allocation not existed", HttpStatus.NOT_FOUND),
    ALLOCATION_TYPE_CANNOT_BE_BLANK(2052, "Allocation type cannot be blank", HttpStatus.BAD_REQUEST),
    ALLOCATION_TYPE_TOO_LONG(2053, "Allocation type cannot exceed 100 characters", HttpStatus.BAD_REQUEST),
    AMOUNT_CANNOT_BE_NULL(2054, "Amount cannot be null", HttpStatus.BAD_REQUEST),
    AMOUNT_MUST_BE_POSITIVE_OR_ZERO(2055, "Amount must be positive or zero", HttpStatus.BAD_REQUEST),
    F_A_DESCRIPTION_CANNOT_BE_BLANK(2056, "Description cannot be blank", HttpStatus.BAD_REQUEST),
    F_A_DESCRIPTION_TOO_LONG(2057, "Description cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),

    // Image
    IMAGE_NOT_EXISTED(3001, "Image not existed", HttpStatus.NOT_FOUND),
    IMAGE_TYPE_CANNOT_BE_BLANK(3002, "Image type cannot be blank", HttpStatus.BAD_REQUEST),
    INVALID_IMAGE_TYPE(3003, "Image type must be either 'campaign' or 'result'", HttpStatus.BAD_REQUEST),

    // Policy
    POLICY_NOT_EXISTED(3051, "Policy not existed", HttpStatus.NOT_FOUND),
    POLICY_DESCRIPTION_CANNOT_BE_BLANK(3052, "Policy description cannot be blank", HttpStatus.BAD_REQUEST),
    POLICY_DESCRIPTION_TOO_LONG(3053, "Policy description cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    ELIGIBILITY_CRITERIA_CANNOT_BE_BLANK(3054, "Eligibility criteria cannot be blank", HttpStatus.BAD_REQUEST),
    ELIGIBILITY_CRITERIA_TOO_LONG(3055, "Eligibility criteria cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    APPROVAL_REQUEST_CANNOT_BE_BLANK(3056, "Approval request cannot be blank", HttpStatus.BAD_REQUEST),
    INVALID_APPROVAL_REQUEST(3057, "Approval request must be either 'approved' or 'pending'", HttpStatus.BAD_REQUEST),
    POLICY_ID_CANNOT_BE_NULL(3058, "Policy ID cannot be null", HttpStatus.BAD_REQUEST),
    POLICY_ID_MUST_BE_POSITIVE(3059, "Policy ID must be positive", HttpStatus.BAD_REQUEST),

    // PolicyViolation
    POLICY_VIOLATION_NOT_EXISTED(4001, "Policy violation not existed", HttpStatus.NOT_FOUND),
    VIOLATION_DESCRIPTION_CANNOT_BE_BLANK(4002, "Violation description cannot be blank", HttpStatus.BAD_REQUEST),
    VIOLATION_DESCRIPTION_TOO_LONG(4003, "Violation description cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    VIOLATION_STATUS_CANNOT_BE_BLANK(4004, "Status cannot be blank", HttpStatus.BAD_REQUEST),
    VIOLATION_ID_CANNOT_BE_NULL(4005, "Violation ID cannot be null", HttpStatus.BAD_REQUEST),
    VIOLATION_ID_MUST_BE_POSITIVE(4006, "Violation ID must be positive", HttpStatus.BAD_REQUEST),

    // Transaction
    TRANSACTION_NOT_EXISTED(4051, "Transcation not existed", HttpStatus.NOT_FOUND),

    // Donation
    DONATION_NOT_EXISTED(5001, "Donation not existed", HttpStatus.NOT_FOUND),

    // ViolationAction
    VIOLATION_ACTION_NOT_EXISTED(5051, "Violation action not existed", HttpStatus.NOT_FOUND),
    ACTION_DESCRIPTION_CANNOT_BE_BLANK(5052, "Action description cannot be blank", HttpStatus.BAD_REQUEST),
    ACTION_DESCRIPTION_TOO_LONG(5053, "Action description cannot exceed 1000 characters", HttpStatus.BAD_REQUEST),
    ACTION_DATE_CANNOT_BE_NULL(5054, "Action date cannot be null", HttpStatus.BAD_REQUEST),
    ACTION_DATE_MUST_BE_PAST_OR_PRESENT(5055, "Action date must be in the past or present", HttpStatus.BAD_REQUEST),
    ACTION_STATUS_CANNOT_BE_BLANK(5056, "Status cannot be blank", HttpStatus.BAD_REQUEST),
    INVALID_STATUS(5057, "Status must be either 'PENDING' or 'COMPLETED'", HttpStatus.BAD_REQUEST),

    // Image
    IMAGE_UPLOAD_FAILED(6001, "Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_TYPE_NOT_SUPPORTED(6002, "File type is not supported", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    EMPTY_FILE(6003, "File is empty", HttpStatus.BAD_REQUEST),
    IMAGE_PROCESSING_ERROR(6004, "Error occurred during image processing", HttpStatus.INTERNAL_SERVER_ERROR),

    // Date
    CREATED_DATE_MUST_BE_PAST_OR_PRESENT(6051, "Created date must be in the past or present", HttpStatus.BAD_REQUEST),
    START_DATE_MUST_BE_FUTURE_OR_PRESENT(6052, "Start date must be in the future or present", HttpStatus.BAD_REQUEST),
    END_DATE_MUST_BE_IN_FUTURE(6053, "End date must be in the future", HttpStatus.BAD_REQUEST),
    REPORT_DATE_MUST_BE_FUTURE_OR_PRESENT(6054, "Report date must be in the future or present", HttpStatus.BAD_REQUEST),
    UPDATE_DATE_MUST_BE_PAST_OR_PRESENT(6055, "Update date must be in the past or present", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;
}
