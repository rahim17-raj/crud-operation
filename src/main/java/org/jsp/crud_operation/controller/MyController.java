package org.jsp.crud_operation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.jsp.crud_operation.dto.Student;
import org.jsp.crud_operation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/")
	public String loadMain(ModelMap map)
	{
		List<Student> list=studentRepository.findAll();
		if(list.isEmpty())
		{
			map.put("fail", "No data found");
		}
		else {
			map.put("list", list);
		}
		return "main";
	}
	
	@GetMapping("/insert")
	public String insert(ModelMap map)
	{
		map.put("insert", "insert");
		return loadMain(map);
		
	}
	@PostMapping("/insert")
	public String insert(Student st,ModelMap map)
	{
		studentRepository.save(st);
		map.put("success", "Rescods inserted successfully");
		return loadMain(map);
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id,ModelMap map) //also use @PathVariable insted of @RequestParam and url ("/edit/{id}") and in html "'/edit/'+${student.id}"
	{
		Student student=studentRepository.findById(id).orElseThrow();
		map.put("student", student);
		map.put("update", "update");
		return loadMain(map);
	}
	
	@PostMapping("/edit")
	public String edit(Student st,ModelMap map)
	{
		studentRepository.save(st);
		map.put("edit", "Rescods updated successfully");
		return loadMain(map);
	}
	
	@GetMapping("/delete")
	public String delete(ModelMap map,@RequestParam int id) //also use @PathVariable insted of @RequestParam and url ("/delete/{id}") and in html "'/delete/'+${student.id}"
	{
		studentRepository.deleteById(id);
		map.put("delete","Record deleted");
		return loadMain(map);
	}
	
	@PostMapping("/search")
    public String search(@RequestParam String data, @RequestParam String searchValue, ModelMap map) {
        switch (data) {
            case "name":
            	if(studentRepository.findByName(searchValue).isEmpty())
        		{
        			map.put("fail", "No data found");
        		}
        		else {
        			map.put("list", studentRepository.findByName(searchValue));
        			map.put("success", "Record Found");
        		}
                break;
            case "mobile":
            	if(studentRepository.findByMobile(Long.parseLong(searchValue)).isEmpty())
        		{
        			map.put("fail", "No data found");
        		}
        		else {
        			map.put("list", studentRepository.findByMobile(Long.parseLong(searchValue)));
        			map.put("success", "Record Found");
        		}
                break;
            case "gender":
            	if(studentRepository.findByGender(searchValue).isEmpty())
        		{
        			map.put("fail", "No data found");
        		}
        		else {
        			map.put("list", studentRepository.findByGender(searchValue));
        			map.put("success", "Record Found");
        		}
                break;
            case "marks":
            	if(studentRepository.findBySub1GreaterThanAndSub2GreaterThanAndSub3GreaterThan(Integer.parseInt(searchValue),Integer.parseInt(searchValue),Integer.parseInt(searchValue)).isEmpty())
        		{
        			map.put("fail", "No data found");
        		}
        		else {
        			map.put("list", studentRepository.findBySub1GreaterThanAndSub2GreaterThanAndSub3GreaterThan(Integer.parseInt(searchValue),Integer.parseInt(searchValue),Integer.parseInt(searchValue)));
        			map.put("success", "Record Found");
        		}
            	break;
            case "percentage":
            	List<Student> list=studentRepository.findAll().stream().filter(student -> student.getPercentage() >= Double.parseDouble(searchValue)).collect(Collectors.toList());
            	if(list.isEmpty())
        		{
        			map.put("fail", "No data found");
        		}
        		else {
        			map.put("list", list);
        			map.put("success", "Record Found");
        		}
                break;
            default:
                map.put("results", "Invalid search option");
        }
        return "main";
    }
}
