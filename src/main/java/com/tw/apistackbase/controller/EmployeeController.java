package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static List<Employee> employees = new ArrayList<Employee>(){
        {
            add(new Employee(1, "刘梦瑶", 18, "女"));
            add(new Employee(2, "1111", 18, "女"));
            add(new Employee(3, "333", 18, "女"));
            add(new Employee(4, "444", 18, "女"));
            add(new Employee(4, "444", 18, "女"));
            add(new Employee(4, "444", 18, "女"));
            add(new Employee(4, "444", 18, "女"));
        }
    };

    @GetMapping
    public ResponseEntity<List<Employee>> queryEmpolyees(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pagesize){
       if(page == null||pagesize == null){
           return ResponseEntity.ok(employees);
       }else {
           List<Employee> list = employees.subList((page - 1) * pagesize, page * pagesize);
           return ResponseEntity.ok(list);
       }
     }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e){
        employees.add(e);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping(consumes = "application/json")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e){
        Optional<Employee> optionalEmployee = employees.stream()
                .filter(employee -> employee.getId() == e.getId())
                .findAny();
        optionalEmployee.orElse(null).setName(e.getName());
        optionalEmployee.orElse(null).setAge(e.getAge());
        optionalEmployee.orElse(null).setGender(e.getGender());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        for (Employee employee : employees) {
            if (employee.getId() == Integer.parseInt(id)) {
                employees.remove(employee);
                break;
            }
        }
        return ResponseEntity.noContent().build();
    }
}
