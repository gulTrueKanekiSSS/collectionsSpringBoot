package pro.sky.listspring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.listspring.exception.EmployeeNotFoundException;
import pro.sky.listspring.exception.EmployeeStorageIsFullException;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceTests {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        employeeService = new EmployeeService();
    }

    @Test
    void testAddingEmployeeSuccess(){
        int sizeEmployees = employeeService.getEmployees().size();

        employeeService.addEmployee("test", "test", 3, 12000);

        int newSize = employeeService.getEmployees().size();
        assertEquals(sizeEmployees + 1, newSize);

        boolean ifEmployeeIn = employeeService.getEmployees().values().stream()
                .anyMatch(employee -> "test".equals(employee.getName()) && "test".equals(employee.getLastname())
                && 3 == employee.getDepartment() && 12000 == employee.getSalary());

        assertTrue(ifEmployeeIn);
    }

    @Test
    void testStorageEmpsFull(){
        for (int i = employeeService.getEmployees().size(); i < 10; i++){
            employeeService.addEmployee("test" + i, "test" + i, 3, 3000);
        }

        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("Another", "One", 4, 12033));
    }

    @Test
    void testDeleteThrowsNotFound(){
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(1000));
    }


    @Test
    void testFoundEmployee(){
        String result = employeeService.foundEmployee(3);
        assertEquals(result, "Kez Birdsamurai");
    }

}
