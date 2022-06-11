package com.exogames.rest_controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserRequest {

    @NotNull
    @Parameter(description = "Message identifier")
    @Schema( example = "2d09257a-cc8e-46e3-9a28-29c76dec4027", required = true)
    String messageId;

    @Parameter(description = "Local date time")
    @Schema(example = "2022-04-29T09:31:25TAM", required = true)
    String invokeDate;

    @NotNull
    @Parameter(description = "Application identifier")
    @Schema( example = "001", required = true)
    String applicationId;

    @Size(min = 8, max = 30)
    @Parameter(description = "User name")
    @Schema(example = "example@gmail.com", required = true)
    String username;

    @Size(min = 8, max = 30)
    @Parameter(description = "User password")
    @Schema( example = "", required = true)
    String userPassword;

    @NotNull
    @Parameter(description = "User role")
    @Schema(example = "Organizer", required = true)
    String userRole;

}
