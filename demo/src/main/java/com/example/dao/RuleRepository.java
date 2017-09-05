package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Project;
import com.example.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Integer>{
	Rule findById(Integer id);
	Rule findByRuleName(String ruleName);
   
}
