package org.example.effectivetask.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.model.dto.authentication.AuthenticationRequest;
import org.example.effectivetask.model.dto.authentication.AuthenticationResponse;
import org.example.effectivetask.model.dto.authentication.RegistrationRequest;
import org.example.effectivetask.service.authentication.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/api/v1/login")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Authenticate a user", description = "Authenticates the user and generates a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/api/v1/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user", description = "Registers a new user and generates a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid registration data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegistrationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }
}
