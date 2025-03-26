package pro.sky.listspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("departmentService")
public class DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    DepartmentService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesInDepartment(int departmentId){
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == departmentId).toList();
    }

    // for count employees in certain department
    public int amountEmployeesInDepartment(int department) {
        return (int) employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department).count();
    }

    // for count sum of salaries' employees in all departments
    public int getSumOfSalaryAllDepartment() {
        return employeeService.getEmployees().values().stream().mapToInt(Employee::getSalary).sum();
    }

    // for count sum of salaries' employees in certain department
    public int getSumOfSalaryInDepartment(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
    // for find minimal salary
    public Employee employeeWithMinSalaryDepartment(int departmentId) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }
    //
//    // for fiend the biggest salary
    public Employee employeeWithMaxSalaryDepartment(int departmentId) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparing(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }

    // for count average salary in all departments
    public int avgSalaryAllDepartments(){
        return getSumOfSalaryAllDepartment() / employeeService.getEmployees().size();
    }

    // for count average salary in certain department
    public int avgSalaryInDepartment(int department){
        return getSumOfSalaryInDepartment(department) / amountEmployeesInDepartment(department);
    }

    public Map<Integer, Employee> getEmployees(){
        return employeeService.getEmployees();
    }

}
