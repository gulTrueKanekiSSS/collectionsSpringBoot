package pro.sky.listspring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.listspring.Employee;
import pro.sky.listspring.exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartmentServiceTests {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        employeeService = mock(EmployeeService.class);

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, new Employee("Alice", "Shreider", 1, 10000));
        employees.put(2, new Employee("Kevin", "Dolberg", 1, 13000));
        employees.put(3, new Employee("Kez", "Birdsamurai", 2, 5000));
        when(employeeService.getEmployees()).thenReturn(employees);

        departmentService = new DepartmentService(employeeService);
    }

    @Test
    void testGetEmpsInDepartment(){
        List<Employee> dep1 = departmentService.getEmployeesInDepartment(1);
        List<Employee> dep2 = departmentService.getEmployeesInDepartment(2);

        assertEquals(2, dep1.size());
        assertEquals(1, dep2.size());

    }

    @Test
    void testAmountOfEmpsInDepartment(){
        assertEquals(2, departmentService.amountEmployeesInDepartment(1));
        assertEquals(1, departmentService.amountEmployeesInDepartment(2));
    }

    @Test
    void testSumOfAllSalary(){
        assertEquals(28000, departmentService.getSumOfSalaryAllDepartment());
    }

    @Test
    void testSumOfSalaryInDepartment(){
        assertEquals(23000, departmentService.getSumOfSalaryInDepartment(1));
    }

    @Test
    void testEmpWithMinSalary(){
        assertEquals(10000, departmentService.employeeWithMinSalaryDepartment(1).getSalary());
    }

    @Test
    void testEmpWithMaxSalary(){
        assertEquals(13000, departmentService.employeeWithMaxSalaryDepartment(1).getSalary());
    }

    @Test
    void testAvgSalaryAllDepartments() {
        assertEquals(9333, departmentService.avgSalaryAllDepartments(), "Средняя зарплата по всем отделам должна равняться 9333");
    }

    @Test
    void testAvgSalaryInDepartment() {
        assertEquals(11500, departmentService.avgSalaryInDepartment(1), "Средняя зарплата отдела 1 должна равняться 11500");
        assertEquals(5000, departmentService.avgSalaryInDepartment(2), "Средняя зарплата отдела 2 должна равняться 5000");
    }

    @Test
    void testEmployeeWithMinSalaryDepartmentThrowsExceptionForEmptyDept() {
        assertThrows(EmployeeNotFoundException.class, () ->
                departmentService.employeeWithMinSalaryDepartment(99)
        );
    }

    @Test
    void testEmployeeWithMaxSalaryDepartmentThrowsExceptionForEmptyDept() {
        assertThrows(EmployeeNotFoundException.class, () ->
                departmentService.employeeWithMaxSalaryDepartment(99)
        );
    }


}
