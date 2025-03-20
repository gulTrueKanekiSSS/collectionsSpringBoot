package pro.sky.listspring.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;
import pro.sky.listspring.exception.EmployeeStorageIsFullException;
import java.util.*;

@Service("employeeService")
@Primary
public class EmployeeService {
    Map<Integer, Employee> employees = new HashMap<>();
    final int maxAmountOfEmp = 10;

    public EmployeeService() {
        employees.put(1, new Employee("Alice", "Shreider", 1, 10000));
        employees.put(2, new Employee("Kevin", "Dolberg", 1, 13000));
        employees.put(3, new Employee("Kez", "Birdsamurai", 2, 5000));
    }

    public void addEmployee(String name, String lastName, int department, int salary) {

        Integer pk = employees.size() + 1;  //Primary key for next employee in hashSet

        if (employees.size() >= maxAmountOfEmp) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(pk, new Employee(name, lastName, department, salary));
    }

    public void deleteEmployee(Integer pk) {

        if (employees.containsKey(pk)) {
            employees.remove(pk);
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public boolean checkEmployee(Integer pk) {
        return employees.containsKey(pk);
    }

    public String foundEmployee(Integer pk) {
        Employee employee = employees.get(pk);
        return employee.getName() + " " + employee.getLastname();
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

}
