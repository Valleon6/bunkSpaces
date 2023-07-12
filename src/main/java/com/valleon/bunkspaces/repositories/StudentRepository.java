package com.valleon.bunkspaces.repositories;

import com.valleon.bunkspaces.exception.HostelManagementException;
import com.valleon.bunkspaces.exception.NullEntityException;
import com.valleon.bunkspaces.models.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentRepository {
    private Map<String, Student> database = new HashMap<>();

    private void validate(String id) throws HostelManagementException {
        if (id == null || id.trim().equals("")) {
            throw new HostelManagementException("provided an Invalid id.");
        }
    }

    public Optional<Student> findById(String id) throws Exception {
        validate(id);
        Student student = database.getOrDefault(id, null);
        if (student == null) {
            return Optional.empty();
        }
        return Optional.of(student);
    }

    public List<Student> findByName(String name) {
        return database.values().stream()
                .filter(student -> (student.getFirstname() + " " + student.getLastName()).contains(name))
                .collect(Collectors.toList());
    }

    public Student save(Student student) throws NullEntityException {
        if (student == null) {
            throw new NullEntityException("student object cannot be null");
        }
        database.put(student.getId(), student);
        return student;
    }

    public void delete(String id) {
        database.remove(id);
    }
    public void delete(Student student){
        database.remove(student.getId(), student);

    }
    public List<Student> findAll(){
        return new ArrayList<>(database.values());
    }

}
