package kz.javaee.project.mirasazharitis1908.service;

import kz.javaee.project.mirasazharitis1908.model.BusinessNews;
import kz.javaee.project.mirasazharitis1908.repository.BusinessNewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
  void testGetBusinessNewsById() {
    when(businessNewsRepository.findById(anyLong(), any())).thenReturn(
        new BusinessNews(Long.valueOf(1), "title", "company", "description"));

    BusinessNews result = businessService.getBusinessNewsById(Long.valueOf(1));
    Assertions.assertEquals(new BusinessNews(Long.valueOf(1), "title", "company", "description"),
        result);
  }

  @Test
  void testCreateNewBusinessNews() {
    businessService.createNewBusinessNews(
        new BusinessNews(Long.valueOf(1), "title", "company", "description"));
  }

  @Test
  void testUpdateBusinessNews() {
    businessService.updateBusinessNews(
        new BusinessNews(Long.valueOf(1), "title", "company", "description"));
  }

  @Test
  void testDeleteBusinessNewsById() {
    when(businessNewsRepository.findById(anyLong(), any())).thenReturn(
        new BusinessNews(Long.valueOf(1), "title", "company", "description"));

    businessService.deleteBusinessNewsById(Long.valueOf(1));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme