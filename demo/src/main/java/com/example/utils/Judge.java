package com.example.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.RuleRepository;
import com.example.model.Project;
import com.example.model.Rule;

@Component
public class Judge {
	@Autowired
    RuleRepository ruleRepository;

    public  boolean ageJudge(Project project) {
    	try {
			Rule rule = ruleRepository.findById(2);
			int min = Integer.valueOf(rule.getMin());
			int max = Integer.valueOf(rule.getMax());
			int cur = project.getPerson().getAge();
			if (cur >= min && cur <= max) {
				return true;
			} 
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    //职称大于等于目标职称的返回true
    public  boolean titleJudge(Project project) {
    	Map<String, Integer> map =new HashMap<>();
    	map.put("讲师", 0);
    	map.put("副教授", 1);
    	map.put("教授", 2);
    	Rule rule = ruleRepository.findByRuleName("职称");
    	int min = map.get(rule.getMin());
    	int cur = map.get(project.getPerson().getTitle());
        if (cur >= min) {
        	return true;
        } 
        return false;
    }
    
    
    //时间小于目标时间的返回true
    public static boolean timeJudge(Project project, String time) {
        String[] target = time.split("-");
        String[] myTime = project.getDate().split("-");
        for (int i = 0; i <= 2; i++) {
            if (Integer.valueOf(myTime[i]) < Integer.valueOf(target[i])) {
                return true;
            }
        }
        return false;
    }
}
