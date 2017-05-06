package com.example.eric.tutorversity.models;

import java.util.Optional;

/**
 * Created by amcinnis on 5/5/17.
 */

public class AuthResponse {

    int status;
    Optional<String> userID;
    Optional<String> userSecret;
}
