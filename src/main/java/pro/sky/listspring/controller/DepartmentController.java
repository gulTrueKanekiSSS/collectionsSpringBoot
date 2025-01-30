package pro.sky.listspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.listspring.Employee;
import pro.sky.listspring.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getEmployeeMaxSalary(@RequestParam(value = "departmentId", required = true)
                                                       Integer departmentId){
        return departmentService.employeeWithMaxSalaryDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getEmployeeMinSalary(@RequestParam(value = "departmentId", required = true)
                                                   Integer departmentId){
        return departmentService.employeeWithMinSalaryDepartment(departmentId);
    }

    @GetMapping("/all")
    public List getEmployeesDepartment(@RequestParam(value = "departmentId", required = true) Integer departmentId){
        return departmentService.getEmployeesInDepartment(departmentId);
    }
}
