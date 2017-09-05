package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
    Subject findById(Integer id);
    Subject findBySubjectName(String sunjectName);
}
