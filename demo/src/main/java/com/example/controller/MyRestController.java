package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.FactRepository;
import com.example.dao.PersonRepository;
import com.example.dao.ProjectRepository;
import com.example.dao.RuleRepository;
import com.example.dao.SubjectRepository;
import com.example.model.Fact;
import com.example.model.Person;
import com.example.model.Project;
import com.example.model.Rule;
import com.example.model.Subject;
import com.example.utils.*;
@RestController
public class MyRestController {
	public static Map<String, String> map;
	static {
		map = new HashMap<>();
		map.put("0", "讲师");
		map.put("1", "副教授");
		map.put("2", "教授");
	}
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    PersonRepository   personRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    FactRepository factRepository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    private Judge judge;
    @RequestMapping("/autojudge")
    public String autoJudge(Integer id) {
        Project project = projectRepository.findById(id);
        Subject subject = project.getSubject();
        String[] rules = subject.getRules().split(" ");
        boolean flag = true;
        for (String rule: rules) {
        	if (rule.equals("职称")) {
        		flag = flag && judge.titleJudge(project);
        	} else {
        		System.out.println("---------------------------------");
        		flag = flag && judge.ageJudge(project);
        	}
        }
        
        if (flag) {
        	project.setState("通过");
        	 projectRepository.save(project);
            return "OK";
        }
        else {
        	project.setState("未通过");
        	 projectRepository.save(project);
            return "FAIL";
        }
        
    }
     
    @GetMapping("/q1")
    public List<Person> q1() {
        List<Person> people = personRepository.findAll();
        return people;
    }
    
    @DeleteMapping("/index/{projectId}")
    public String deleteProject(@PathVariable("projectId") int id) {
        if (Integer.valueOf(id) == null) {
            return "FAIL";
        }
        projectRepository.delete(Integer.valueOf(id));
        return  "OK";
    }
    
    @RequestMapping("/searchPerson/{personId}")
    public Person searchPerson(@PathVariable("personId") int id) {
        if (Integer.valueOf(id) == null) {
            return null;
        }
        Person p = personRepository.findById(id);
        return  p;
    }
    
    @RequestMapping("/searchProject/{projectId}")
    public Project searchProject(@PathVariable("projectId") int id) {
        if (Integer.valueOf(id) == null) {
            return null;
        }
        Project p = projectRepository.findById(id);
        return  p;
    }
    
    @DeleteMapping("/people/{personId}")
    public String deletePerson(@PathVariable("personId") int id) {
        if (Integer.valueOf(id) == null) {
            return "FAIL";
        }
        personRepository.delete(Integer.valueOf(id));
        return  "OK";
    }
    
    @GetMapping("/saveEditPerson")
    public String peopleSave(int id, String name, int age, String college, 
            String sex, String title) {
        Person p = personRepository.findById(id);
        p.setAge(age);
        p.setCollege(college);
        p.setName(name);
        p.setSex(sex);
        p.setTitle(title);
        personRepository.save(p);
        return "OK";//好像是请求的什么方法就用什么方法请求return的地方
    }
    
    @GetMapping("/saveEditProject")
    public String projectSave(int id, String projectName, String description, int subjectId, 
            int personId, String date) {
    	Project p = projectRepository.findById(id);
    	p.setDate(date);
    	p.setDescription(description);
    	p.setPerson(personRepository.findById(personId));
    	p.setProjectName(projectName);
    	p.setSubject(subjectRepository.findById(subjectId));
    	projectRepository.save(p);
        return "OK";//好像是请求的什么方法就用什么方法请求return的地方
    }
    
    @GetMapping("/saveEditSubject")
    public String subjecttSave(int id, String name, String rules) {
    	Subject s = subjectRepository.findById(id);
        rules = rules.replace(",", " ");
        s.setSubjectName(name);
        s.setRules(rules);
        subjectRepository.save(s);
        System.out.println(s);
    	return "OK";
    }
    
    
    @GetMapping("/editFactSave")
    public String editFactSave(int id, String name, String desc) {
        Fact f = factRepository.findById(id);
        f.setDescription(desc);
        f.setName(name);
        factRepository.save(f);
    	return "OK";
    }
    
    
    @GetMapping("/editRuleSave")
    public String editRuleSave(int id, String min,String max, String name) {
    	Rule r = ruleRepository.findById(id);
    	if (name.equals("职称")) {
    		max = map.get(max);
    		min = map.get(min);
    	}
    	r.setMax(max);
    	r.setMin(min);
    	ruleRepository.save(r);
    	return "OK";
    }
    @PostMapping("addSubject")
    public String addSubject(String name, String rules) {
        rules = rules.replace(",", " ");
        System.out.println(rules);
        Subject s = new Subject(null, name, rules);
        subjectRepository.save(s);
    	return "OK";
    }
    
    
    
    @DeleteMapping("/subject/{subjectId}")
    public String deleteSubject(@PathVariable("subjectId") int id) {
        if (Integer.valueOf(id) == null) {
            return "FAIL";
        }
        subjectRepository.delete(id);
        return  "OK";
    }
    @DeleteMapping("/fact/{factId}")
    public String deleteFact(@PathVariable("factId") int id) {
        if (Integer.valueOf(id) == null) {
            return "FAIL";
        }
        factRepository.delete(id);
        return  "OK";
    }
    


}
