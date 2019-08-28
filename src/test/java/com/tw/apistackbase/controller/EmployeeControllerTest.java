package com.tw.apistackbase.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //post
    @Test
    public void should_return_200_status_when_add_user()  throws Exception {
        Employee employee = new Employee(6,"张XX",15,"女");
        ObjectMapper objectMapper = new ObjectMapper();
        String example= objectMapper.writeValueAsString(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(example))
                .andDo(print())
                .andExpect(status().isCreated());

    }
    //get
    @Test
    public void should_return_list_when_query() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    //put
    @Test
    public void should_return_200_status_when_update_user()  throws Exception {
        Employee employee = new Employee(3,"张XX",15,"女");
        ObjectMapper objectMapper = new ObjectMapper();
        String example= objectMapper.writeValueAsString(employee);
        mockMvc.perform(MockMvcRequestBuilders.put("/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(example))
                .andDo(print())
                .andExpect(status().isOk());

    }
    //delete
    @Test
    public void should_return_204_when_delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
