package com.valleon.bunkspaces.dto;

import com.valleon.bunkspaces.models.Gender;

public record RegistrationRequest (String firstName, String lastName, String password, String matricNo, Gender gender){

}
