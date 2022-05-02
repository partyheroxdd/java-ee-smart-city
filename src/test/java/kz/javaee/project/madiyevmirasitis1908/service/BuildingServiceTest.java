package kz.javaee.project.madiyevmirasitis1908.service;

import kz.javaee.project.madiyevmirasitis1908.model.Building;
import kz.javaee.project.madiyevmirasitis1908.repository.BuildingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BuildingServiceTest {
    @Mock
    BuildingRepository buildingRepository;
    @InjectMocks
    BuildingService buildingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(buildingRepository.getAll()).thenReturn(Arrays.<Building>asList(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt")));

        List<Building> result = buildingService.getAll();
        Assertions.assertEquals(Arrays.<Building>asList(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt")), result);
    }

    @Test
    void testGetBuildingById() {
        when(buildingRepository.findById(anyLong(), any())).thenReturn(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"));

        Building result = buildingService.getBuildingById(Long.valueOf(1));
        Assertions.assertEquals(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"), result);
    }

    @Test
    void testCreateNewBuilding() {
        buildingService.createNewBuilding(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"));
    }

    @Test
    void testUpdateBuilding() {
        when(buildingRepository.findById(anyLong(), any())).thenReturn(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"));

        buildingService.updateBuilding(Long.valueOf(1), "name", "category", "address", "contactInfo");
    }

    @Test
    void testDeleteBuilidngById() {
        when(buildingRepository.findById(anyLong(), any())).thenReturn(new Building(Long.valueOf(1), "name", "category", "address", "contactInfo", "createdAt"));

        buildingService.deleteBuilidngById(Long.valueOf(1));
    }
}

