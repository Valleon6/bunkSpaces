package com.valleon.bunkspaces.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String firstname;
    private String lastName;
    private String password;
    private Gender gender;
    private LocalDateTime registrationTime;
    private String bedSpaceId;
    private String matricNo;

    public Student(String firstname, String lastName, String password, Gender gender,
                   LocalDateTime registrationTime,  String matricNo) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.registrationTime = registrationTime;
        this.matricNo = matricNo;
    }


    public String getId(){
        return matricNo;
    }
    public String getName(){
        return firstname + " " + lastName;
    }
}
