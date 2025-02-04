package pro.sky.listspring.service;

import org.springframework.stereotype.Service;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService extends EmployeeService{

    public List<Employee> getEmployeesInDepartment(int departmentId){
        return employees.values().stream()
                .filter(e -> e.getDepartment() == departmentId).toList();
    }

    // for count employees in certain department
    public int amountEmployeesInDepartment(int department) {
        return (int) employees.values().stream()
                .filter(employee -> employee.getDepartment() == department).count();
    }

    // for count sum of salaries' employees in all departments
    public int getSumOfSalaryAllDepartment() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    // for count sum of salaries' employees in certain department
    public int getSumOfSalaryInDepartment(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
    // for find minimal salary
    public Employee employeeWithMinSalaryDepartment(int departmentId) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }
    //
//    // for fiend the biggest salary
    public Employee employeeWithMaxSalaryDepartment(int departmentId) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparing(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }

    // for count average salary in all departments
    public int avgSalaryAllDepartments(){
        return getSumOfSalaryAllDepartment() / employees.size();
    }

    // for count average salary in certain department
    public int avgSalaryInDepartment(int department){
        return getSumOfSalaryInDepartment(department) / amountEmployeesInDepartment(department);
    }
}
