package com.lean.vitzi.infrastructure.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class HttpMockMvcComponent {

    private String token;
    private String URL_BASE = "http://localhost:8080";

    @PostConstruct
    private void init() {
        log.info("*******************");
        log.info("token was generated -> {}", token);
        log.info("*******************");
    }

    /**
     * @param uri END_POINT to send GET request. i.e "/api/v1/s1/city/listAll"
     * @return RequestBuilder
     */
    public RequestBuilder getAll(String uri) {
        return MockMvcRequestBuilders
                .get(URL_BASE + uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    /**
     * @param uri END_POINT to send GET request. i.e "/api/v1/s1/city/1"
     * @param id  Key of POJO with Long data type
     * @return RequestBuilder
     */
    public RequestBuilder getById(String uri, Long id) {
        return MockMvcRequestBuilders
                .get(URL_BASE + uri + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    /**
     * @param uri     END_POINT to send GET request. i.e "/api/v1/s1/city/create"
     * @param content POJO with JSON format
     * @return RequestBuilder
     */
    public RequestBuilder post(String uri, String content) {
        return MockMvcRequestBuilders
                .post(URL_BASE + uri, content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
    }

    /**
     * @param uri     END_POINT to send GET request. i.e "/api/v1/s1/city/update"
     * @param content POJO with JSON format
     * @return RequestBuilder
     */
    public RequestBuilder put(String uri, String content) {
        return MockMvcRequestBuilders
                .put(URL_BASE + uri, content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
    }

    /**
     * @param uri END_POINT to send GET request. i.e "/api/v1/s1/city/delete/2"
     * @return
     */
    public RequestBuilder delete(String uri) {
        return MockMvcRequestBuilders
                .delete(URL_BASE + uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

}
