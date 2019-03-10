package com.ratiose.testtask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataDTO {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private String _email;
    private String _password;

    public UserDataDTO() {
    }

    @JsonProperty(EMAIL)
    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    @JsonProperty(PASSWORD)
    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public UserDataDTO(String email, String password) {
        _email = email;
        _password = password;
    }
}
