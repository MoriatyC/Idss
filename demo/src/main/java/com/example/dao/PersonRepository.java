package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findById(Integer id);
    Person findByName(String name);
    List<Person> findByCollege(String college);
//    Person findByNameAndCollege(String name, String college);
//    @Query("select p from Person p where p.name = :name and p.college=:college")
//    Person withNameAndCollegeQuery(@Param("name")String name, @Param("college")String college);
//    Person withNameAndCollegeNamedQuery(String name, String college);
}
