package com.UniCharity.UniCharity.controllers;

import com.UniCharity.UniCharity.dto.request.UserCreateRequest;
import com.UniCharity.UniCharity.dto.request.UserUpdateRequest;
import com.UniCharity.UniCharity.dto.response.ApiResponse;
import com.UniCharity.UniCharity.dto.response.page.PageResponse;
import com.UniCharity.UniCharity.dto.response.user.UserResponse;
import com.UniCharity.UniCharity.services.iservices.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.createUser(request)).build();
    }

    @GetMapping
    ApiResponse<PageResponse<UserResponse>> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortField, @RequestParam(defaultValue = "asc") String sortDirection) {
        return ApiResponse.<PageResponse<UserResponse>>builder().result(userService.getUsers(page, size, sortField, sortDirection)).build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") int userId) {
        return ApiResponse.<UserResponse>builder().result(userService.getUser(userId)).build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") int userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.updateUser(userId, request)).build();
    }

    @PutMapping("/update_status/{userId}")
    ApiResponse<UserResponse> updateUserStatus(@PathVariable("userId") int userId) {
        return ApiResponse.<UserResponse>builder().result(userService.updateUserStatus(userId)).build();
    }
}
