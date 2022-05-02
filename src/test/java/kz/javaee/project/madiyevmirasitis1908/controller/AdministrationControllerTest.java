package kz.javaee.project.madiyevmirasitis1908.controller;

import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.service.BuildingService;
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

class AdministrationControllerTest {
    @Mock
    BuildingService buildingService;
    @Mock
    Logger logger;
    @InjectMocks
    AdministrationController administrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(buildingService.getAll()).thenReturn(Arrays.<Building>asList(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt")));

        Response result = administrationController.getAll();
        Assertions.assertEquals(result, result);
    }

    @Test
    void testGetBuilding() {
        when(buildingService.getBuildingById(anyLong())).thenReturn(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"));

        Response result = administrationController.getBuilding(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testSaveBuilding() {
        Response result = administrationController.saveBuilding("name", "category", "address", "contactInfo", "createdAt");
        Assertions.assertEquals(result, result);
    }

    @Test
    void testUpdateBuilding() {
        Response result = administrationController.updateBuilding("name", "category", "address", "contactInfo", Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }

    @Test
    void testDeleteById() {
        Response result = administrationController.deleteById(Long.valueOf(1));
        Assertions.assertEquals(result, result);
    }
}

