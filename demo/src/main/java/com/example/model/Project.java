package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String projectName;
    @OneToOne
    private Person person;
    private String description;
    @OneToOne
    private Subject subject;
    private String date;
    private String state;
}