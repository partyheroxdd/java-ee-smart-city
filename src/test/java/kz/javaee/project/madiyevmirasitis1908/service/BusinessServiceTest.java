package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.model.BusinessNews;
import kz.javaee.project.madiyevmirasitis1908.repository.BusinessNewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BusinessServiceTest {
    @Mock
    BusinessNewsRepository businessNewsRepository;
    @InjectMocks
    BusinessService businessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(businessNewsRepository.getAll()).thenReturn(Arrays.<BusinessNews>asList(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt")));

        List<BusinessNews> result = businessService.getAll();
        Assertions.assertEquals(Arrays.<BusinessNews>asList(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt")), result);
    }

    @Test
    void testGetBusinessNewsById() {
        when(businessNewsRepository.findById(anyLong(), any())).thenReturn(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"));

        BusinessNews result = businessService.getBusinessNewsById(Long.valueOf(1));
        Assertions.assertEquals(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"), result);
    }

    @Test
    void testCreateNewBusinessNews() {
        businessService.createNewBusinessNews(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"));
    }

    @Test
    void testUpdateBusinessNews() {
        when(businessNewsRepository.findById(anyLong(), any())).thenReturn(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"));

        businessService.updateBusinessNews(Long.valueOf(1), "title");
    }

    @Test
    void testDeleteBusinessNewsById() {
        when(businessNewsRepository.findById(anyLong(), any())).thenReturn(new BusinessNews(Long.valueOf(1), "title", "company", "description", "createdAt"));

        businessService.deleteBusinessNewsById(Long.valueOf(1));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme