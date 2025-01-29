package pro.sky.listspring.service;

import org.springframework.stereotype.Service;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;
import pro.sky.listspring.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    Map <Integer, Employee> employees = new HashMap<>();
    final int maxAmountOfEmp = 10;

    public EmployeeService(){
        employees.put(1, new Employee("Alice", "Shreider"));
        employees.put(2, new Employee("Kevin", "Dolberg"));
        employees.put(3, new Employee("Kez", "Birdsamurai"));
    }

    public void addEmployee(String name, String lastName) {

        Integer pk = employees.size() + 1;  //Primary key for next employee in hashSet

        if (employees.size() >= maxAmountOfEmp) {
            throw new EmployeeStorageIsFullException();
        }
        employees.put(pk, new Employee(name, lastName));
    }

    public void deleteEmployee(Integer pk){

        if (employees.containsKey(pk)){
            employees.remove(pk);
        }
        else {
            throw new EmployeeNotFoundException();
        }
    }

    public boolean checkEmployee(Integer pk){
        if (employees.containsKey(pk)){
            return true;
        }
        return false;
    }

    public String foundEmployee(Integer pk) {
        Employee employee = employees.get(pk);
        return employee.getName() + " " + employee.getLastname();
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }
}
