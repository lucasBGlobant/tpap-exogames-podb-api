package com.exogames.rest_controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
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

    @Schema(description = "Acces token", example = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJFeG9nYW1lcyIsInN1YiI6IkpXVCBUb2tlbiIsInVzZXJuYW1lIjoicHJ1ZWJhQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiMSIsImlhdCI6MTY1MTY5NjEzMywiZXhwIjoxNjUxNjk3MTMzfQ.8On2qYsw8UpQnbk8wKh_RTiv21yKNM7qiOeMrRlBrLE", required = true)
    String accessToken;

    @Schema(description = "User role", example = "Organizer", required = true)
    String userRole;

    public AuthenticationResponse(Integer responseCode, String responseMessage, String messageId, String operationResult, String username, String accessToken, String userRole) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.messageId = messageId;
        this.operationResult = operationResult;
        this.username = username;
        this.accessToken = accessToken;
        this.userRole = userRole;
    }

    public AuthenticationResponse(Integer responseCode, String responseMessage, String messageId, String operationResult, String username, String userRole) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.messageId = messageId;
        this.operationResult = operationResult;
        this.username = username;
        this.userRole = userRole;
    }

}
