package pro.sky.listspring.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.listspring.Employee;
import pro.sky.listspring.service.DepartmentService;
import pro.sky.listspring.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    // Тест для эндпоинта /department/employees, который возвращает Map<Integer, Employee>
    @Test
    void getEmployeesShouldReturnAllEmployees() throws Exception {
        Map<Integer, Employee> mockEmployees = Map.of(
                1, new Employee("test", "test", 1, 12000),
                2, new Employee("test2", "test2", 2, 12000)
        );
        when(departmentService.getEmployees()).thenReturn(mockEmployees);

        mockMvc.perform(get("/department/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.1.name").value("test"))
                .andExpect(jsonPath("$.2.name").value("test2"));
    }

    // Тест для эндпоинта /department/max-salary?departmentId=1
    @Test
    void getEmployeeMaxSalaryShouldReturnEmployee() throws Exception {
        Employee employee = new Employee("max", "salary", 1, 15000);
        when(departmentService.employeeWithMaxSalaryDepartment(1)).thenReturn(employee);

        mockMvc.perform(get("/department/max-salary")
                        .param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("max"))
                .andExpect(jsonPath("$.salary").value(15000));
    }

    // Тест для эндпоинта /department/min-salary?departmentId=1
    @Test
    void getEmployeeMinSalaryShouldReturnEmployee() throws Exception {
        Employee employee = new Employee("min", "salary", 1, 8000);
        when(departmentService.employeeWithMinSalaryDepartment(1)).thenReturn(employee);

        mockMvc.perform(get("/department/min-salary")
                        .param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("min"))
                .andExpect(jsonPath("$.salary").value(8000));
    }

    // Тест для эндпоинта /department/department_employees?departmentId=1, возвращающего список сотрудников
    @Test
    void getEmployeesDepartmentShouldReturnEmployeeList() throws Exception {
        List<Employee> employeeList = List.of(
                new Employee("emp1", "test", 1, 12000),
                new Employee("emp2", "test", 1, 11000)
        );
        when(departmentService.getEmployeesInDepartment(1)).thenReturn(employeeList);

        mockMvc.perform(get("/department/department_employees")
                        .param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("emp1"))
                .andExpect(jsonPath("$[1].name").value("emp2"));
    }

    // Тест для эндпоинта /department/{id}/salary/sum
    @Test
    void getSumSalaryInDepartmentShouldReturnSum() throws Exception {
        int sum = 23000;
        when(departmentService.getSumOfSalaryInDepartment(1)).thenReturn(sum);

        mockMvc.perform(get("/department/1/salary/sum"))
                .andExpect(status().isOk())
                .andExpect(content().string("Сумма зарплат в отделе 1: " + sum));
    }

    // Тест для эндпоинта /department/{id}/salary/max
    @Test
    void getMaxSalaryInDepartmentShouldReturnMaxSalary() throws Exception {
        Employee employee = new Employee("max", "salary", 1, 15000);
        when(departmentService.employeeWithMaxSalaryDepartment(1)).thenReturn(employee);

        mockMvc.perform(get("/department/1/salary/max"))
                .andExpect(status().isOk())
                .andExpect(content().string("Самая большая зарплата в отделе 1: " + employee.getSalary()));
    }

    // Тест для эндпоинта /department/{id}/salary/min
    @Test
    void getMinSalaryInDepartmentShouldReturnMinSalary() throws Exception {
        Employee employee = new Employee("min", "salary", 1, 8000);
        when(departmentService.employeeWithMinSalaryDepartment(1)).thenReturn(employee);

        mockMvc.perform(get("/department/1/salary/min"))
                .andExpect(status().isOk())
                .andExpect(content().string("Самая маленькая зарплата в отделе 1: " + employee.getSalary()));
    }
}