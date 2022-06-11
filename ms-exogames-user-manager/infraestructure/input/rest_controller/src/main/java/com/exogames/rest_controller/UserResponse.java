package com.exogames.rest_controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    @Schema(description = "Response code", example = "200", required = true)
    Integer responseCode;

    @Schema(description = "Response message", example = "OK", required = true)
    String responseMessage;

    @Schema(description = "Message identifier", example = "2d09257a-cc8e-46e3-9a28-29c76dec4027", required = true)
    String messageId;

    @Schema(description = "Message of Operation result", example = "Success", required = true)
    String operationResult;
    @Schema(description = "Username", example = "example@gmail.com", required = true)
    String username;
    @Schema(description = "User rol", example = "Organizer", required = true)
    String userRole;

    public UserResponse(Integer responseCode, String responseMessage, String messageId, String operationResult, String username, String userRole) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.messageId = messageId;
        this.operationResult = operationResult;
        this.username = username;
        this.userRole = userRole;
    }

    public UserResponse(Integer responseCode, String responseMessage, String messageId, String operationResult) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.messageId = messageId;
        this.operationResult = operationResult;
    }

}
