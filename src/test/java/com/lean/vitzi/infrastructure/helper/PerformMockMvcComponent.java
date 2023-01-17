package com.lean.vitzi.infrastructure.helper;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@Slf4j
public class PerformMockMvcComponent {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    HttpMockMvcComponent httpMockMvcComponent;

    public ResultActions performGetAll(String uri) throws Exception {
        return mockMvc.perform(httpMockMvcComponent.getAll(uri))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    public ResultActions performGetOne(String uri, Long id) throws Exception {
        return mockMvc.perform(httpMockMvcComponent.getById(uri, id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    public ResultActions performAdd(String uri, Object model) throws Exception {
        Gson gson = new Gson();
        String o = gson.toJson(model);

        return mockMvc.perform(httpMockMvcComponent.post(uri, o))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    public ResultActions performEdit(String uri, Object model) throws Exception {
        Gson gson = new Gson();
        String o = gson.toJson(model);

        return mockMvc.perform(httpMockMvcComponent.put(uri, o))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    public ResultActions performDelete(String uri) throws Exception {
        return mockMvc.perform(httpMockMvcComponent.delete(uri))
                .andExpect(status().isOk());
    }
}
