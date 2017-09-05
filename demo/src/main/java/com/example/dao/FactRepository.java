package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Fact;


public interface FactRepository extends JpaRepository<Fact, Integer>{
    Fact findById(Integer id);
}
