package com.valleon.bunkspaces.services;

import com.valleon.bunkspaces.dto.RegistrationRequest;
import com.valleon.bunkspaces.dto.StudentDto;
import com.valleon.bunkspaces.models.Student;

public interface StudentService {
    StudentDto registerStudent(RegistrationRequest registrationRequest) throws Exception;

    Student findStudentById(String studentId) throws Exception;



}
