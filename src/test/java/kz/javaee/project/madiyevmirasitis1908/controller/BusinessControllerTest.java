package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import kz.javaee.project.madiyevmirasitis1908.service.BusinessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class BusinessControllerTest {
    @Mock
    BusinessService businessService;
    @Mock
    Logger logger;
    @InjectMocks
    BusinessController businessController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(businessService.getAll()).thenReturn(Arrays.<BusinessNews>asList(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt")));

        Response result = businessController.getAll();
        Assertions.assertEquals(result, result);
    }

    @Test
    void testGetNews() {
        when(businessService.getBusinessNewsById(anyLong())).thenReturn(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"));

        Response result = businessController.getNews(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testSaveNews() {
        Response result = businessController.saveNews("title", "company", "description", "createdAt");
        Assertions.assertEquals(result, result);
    }

    @Test
    void testUpdateNews() {
        Response result = businessController.updateNews("title", Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testDeleteById() {
        Response result = businessController.deleteById(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }
}

