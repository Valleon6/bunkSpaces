package com.valleon.bunkspaces.controller;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.valleon.bunkspaces.config.ModelMapperConfig;
import com.valleon.bunkspaces.dto.RegistrationRequest;
import com.valleon.bunkspaces.dto.StudentDto;
import com.valleon.bunkspaces.exception.HostelManagementException;
import com.valleon.bunkspaces.models.Student;
import com.valleon.bunkspaces.repositories.HostelRepository;
import com.valleon.bunkspaces.repositories.StudentRepository;
import com.valleon.bunkspaces.services.StudentService;
import com.valleon.bunkspaces.services.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import static spark.Spark.*;

@Slf4j
public class HostelMgtController {

    private static HostelRepository hostelRepository;
    private static StudentRepository studentRepository;
    private static StudentService studentService;
    private static ObjectMapper objectMapper;

    public static void main(String[] args) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        hostelRepository = new HostelRepository();
        studentRepository = new StudentRepository();
        studentService = new StudentServiceImpl(studentRepository, hostelRepository);
        redirect.post("/register", "/assign-bed-space");

        path("/api/v1/students", () -> {
            post("/register", ((request, response) -> {
                RegistrationRequest registrationRequest = objectMapper.readValue(request.body(), RegistrationRequest.class);
                StudentDto studentDto = studentService.registerStudent(registrationRequest);
                return objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(studentDto);
            }));

            get("/get-student-info/:studentId", (request, response) -> {
                String studentId = request.params(":studentId");
                try {
                    Student student = studentService.findStudentById(studentId);
                    StudentDto studentDto = ModelMapperConfig.getMapper().map(student, StudentDto.class);
                    return objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(studentDto);
                } catch (HostelManagementException exception) {
                    log.info("Exception occured --> {}", exception.getMessage());
                    return objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(exception.getMessage());
                }
            });


        });
    }
}
