package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    Project findById(Integer id);

   
}
