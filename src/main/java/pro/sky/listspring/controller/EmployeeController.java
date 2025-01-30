package pro.sky.listspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.listspring.Employee;
import pro.sky.listspring.service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addingEmployee(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value="lastName", required = true) String lastName,
                                 @RequestParam(value="department", required = true) int department,
                                 @RequestParam(value="salary", required = true) int salary){
        if (name == null || lastName == null){
            throw new IllegalArgumentException("Вы не передали один из параметров");
        }
        employeeService.addEmployee(name, lastName, department, salary);
        return "Employee added";
    }

    @GetMapping("/remove")
    public String deletingEmployee(@RequestParam(value = "pk", required = true) Integer pk){
        if (pk == null){
            throw new IllegalArgumentException("Вы не передали один из параметров");
        }
        employeeService.deleteEmployee(pk);
        return "Employee deleted";
    }

    @GetMapping("/find")
    public String findingEmployee(@RequestParam(value = "pk", required = true) Integer pk) {
        if (pk == null) {
            throw new IllegalArgumentException("Вы не передали один из параметров");
        }

        return employeeService.foundEmployee(pk);
    }

    @GetMapping
    public Map<Integer, Employee> getAllEmployees(){
        Map<Integer, Employee> employees = employeeService.getEmployees();
        return employees;
    }
}