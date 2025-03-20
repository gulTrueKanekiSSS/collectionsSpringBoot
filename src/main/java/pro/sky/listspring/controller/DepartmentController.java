package pro.sky.listspring.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.listspring.Employee;
import pro.sky.listspring.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeMaxSalary(@RequestParam(value = "departmentId", required = true)
                                                       Integer departmentId){
        return departmentService.employeeWithMaxSalaryDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeMinSalary(@RequestParam(value = "departmentId", required = true)
                                                   Integer departmentId){
        return departmentService.employeeWithMinSalaryDepartment(departmentId);
    }

    @GetMapping("/department_employees")
    public List getEmployeesDepartment(@RequestParam(value = "departmentId", required = true) Integer departmentId){
        return departmentService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public String getSumSalaryInDepartment(@PathVariable("id") int departmentId) {
        return "Сумма зарплат в отделе " + departmentId + ": " + departmentService.getSumOfSalaryInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public String getMaxSalaryInDepartment(@PathVariable("id") int departmentId) {
        return "Самая большая зарплата в отделе " + departmentId + ": " + departmentService.employeeWithMaxSalaryDepartment(departmentId).getSalary();
    }

    @GetMapping("/{id}/salary/min")
    public String getMinSalaryInDepartment(@PathVariable("id") int departmentId) {
        return "Самая маленькая зарплата в отделе " + departmentId + ": " + departmentService.employeeWithMinSalaryDepartment(departmentId).getSalary();
    }

    @GetMapping("/employees")
    public Map<Integer, Employee> getEmployees() {
        return departmentService.getEmployees();
    }
}
