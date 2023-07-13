package com.valleon.bunkspaces.services;

import com.valleon.bunkspaces.config.ModelMapperConfig;
import com.valleon.bunkspaces.dto.RegistrationRequest;
import com.valleon.bunkspaces.dto.StudentDto;
import com.valleon.bunkspaces.exception.DuplicateIdException;
import com.valleon.bunkspaces.exception.HostelManagementException;
import com.valleon.bunkspaces.models.Student;
import com.valleon.bunkspaces.repositories.HostelRepository;
import com.valleon.bunkspaces.repositories.StudentRepository;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final HostelRepository hostelRepository;

    public StudentServiceImpl(@NonNull StudentRepository studentRepository, HostelRepository hostelRepository) {
        this.studentRepository = studentRepository;
        this.hostelRepository = hostelRepository;
    }


    @Override
    public StudentDto registerStudent(RegistrationRequest registrationRequest) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(registrationRequest.matricNo());
        if(optionalStudent.isPresent()){
            throw new DuplicateIdException("Student record with matric number already exist.");
        }
        Student student = ModelMapperConfig.getMapper().map(registrationRequest, Student.class);
        student.setRegistrationTime(LocalDateTime.now());
        student = studentRepository.save(student);
        return ModelMapperConfig.getMapper().map(student, StudentDto.class);
    }

    @Override
    public Student findStudentById(String studentId) throws Exception {
        return studentRepository.findById(studentId).orElseThrow(() -> new HostelManagementException("Student with specified matric" +
               " number does not exist!"));
    }

}
