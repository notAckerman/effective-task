package org.example.effectivetask.service.authentication;

import org.example.effectivetask.model.dto.authentication.AuthenticationRequest;
import org.example.effectivetask.model.dto.authentication.AuthenticationResponse;
import org.example.effectivetask.model.dto.authentication.RegistrationRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegistrationRequest request);
}
