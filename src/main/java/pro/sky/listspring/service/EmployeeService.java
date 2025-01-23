package pro.sky.listspring.service;

import org.springframework.stereotype.Service;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;
import pro.sky.listspring.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {
    List <Employee> employees = new ArrayList<>();
    final int maxAmountOfEmp = 10;

    public EmployeeService(){
        employees.add(new Employee("Alice", "Shreider"));
        employees.add(new Employee("Kevin", "Dolberg"));
        employees.add(new Employee("Kez", "Birdsamurai"));
    }

    public void addEmployee(String name, String lastName) {
        if (employees.size() >= maxAmountOfEmp) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(name, lastName);
        if (!foundEmployee(employee.getName(), employee.getLastname())) {
            employees.add(employee);
        } else if (foundEmployee(employee.getName(), employee.getLastname())) {
            throw new EmployeeNotFoundException();
        }
    }

    public void deleteEmployee(String name, String lastName){
        Iterator<Employee> iterator = employees.iterator();
        while(iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getName().equals(name) && employee.getLastname().equals(lastName)){
                iterator.remove();
                System.out.println("Employee: " + name + " " + lastName + " has been removed");
                return;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public boolean foundEmployee(String name, String lastName) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getName().equals(name) && employee.getLastname().equals(lastName)) {
                System.out.println("Employee has been found");
                return true;
            }
        }
        return false;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
