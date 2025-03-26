package pro.sky.listspring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.listspring.service.DepartmentService;
import pro.sky.listspring.service.EmployeeService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("employeeService")
    EmployeeService employeeService;

    @MockBean
    DepartmentService departmentService;

    @Test
    void testAddingEmployee() throws Exception {
        mockMvc.perform(get("/employee/add")
                .param("name", "test")
                .param("lastName", "test")
                .param("department", "2")
                .param("salary", "32000")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Employee added"));
    }

    @Test
    void testRemovingEmployee() throws Exception {
        mockMvc.perform(get("/employee/remove")
                .param("pk", "1")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted"));
    }

    @Test
    void testFindingEmployee() throws Exception {
        when(employeeService.foundEmployee(1)).thenReturn("test test");
        mockMvc.perform(get("/employee/find")
                .param("pk", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("test test"));
    }


}
