package com.devstaff.farmcollector.service;


import com.devstaff.farmcollector.modal.Plantation;
import com.devstaff.farmcollector.modal.enums.CropType;
import com.devstaff.farmcollector.modal.enums.Season;
import com.devstaff.farmcollector.service.impl.FarmServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FarmServiceImplTest {

    private FarmServiceImpl farmService = null;

    @Test
    void testAddPlantation() {
        farmService = new FarmServiceImpl();
        final var plantation = Plantation.builder()
                .farmName("farmName")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .plantingArea(100.0)
                .expectedAmount(200.0)
                .build();
        farmService.addPlantation(plantation);
        Assertions.assertEquals(1, farmService.getStore().size());
    }

    @Test
    void testAddPlantationWithDifferentCrop() {
        farmService = new FarmServiceImpl();
        final var plantation = Plantation.builder()
                .farmName("farmName")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .plantingArea(100.0)
                .expectedAmount(200.0)
                .build();
        farmService.addPlantation(plantation);

        final var plantation2 = Plantation.builder()
                .farmName("farmName")
                .crop(CropType.POTATOES)
                .season(Season.SUMMER)
                .plantingArea(100.0)
                .expectedAmount(200.0)
                .build();
        farmService.addPlantation(plantation2);
        Assertions.assertEquals(2, farmService.getStore().size());
    }

    @Test
    void testUpdateHarvest() {
        farmService = new FarmServiceImpl();
        final var plantation = Plantation.builder()
                .farmName("farmName")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .plantingArea(100.0)
                .expectedAmount(200.0)
                .build();

        final var plantationWithHarvest = Plantation.builder()
                .farmName("farmName")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .harvestedAmount(200.0)
                .build();

        farmService.addPlantation(plantation);
        farmService.updateHarvest(plantationWithHarvest);
        Assertions.assertEquals(1, farmService.getStore().size());
        Assertions.assertEquals(200.0, farmService.getStore().getFirst().getHarvestedAmount());
    }

    @Test
    void testReportByFarmName() throws JsonProcessingException {
        farmService = new FarmServiceImpl();
        final var plantation1 = Plantation.builder()
                .farmName("farm1WithCorn")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .plantingArea(1.0)
                .expectedAmount(100.0)
                .build();

        final var plantationWithHarvest1 = Plantation.builder()
                .farmName("farm1WithCorn")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .harvestedAmount(200.0)
                .build();

        farmService.addPlantation(plantation1);
        farmService.updateHarvest(plantationWithHarvest1);

        final var plantationP = Plantation.builder()
                .farmName("farm1WithCorn")
                .crop(CropType.POTATOES)
                .season(Season.SUMMER)
                .plantingArea(1.0)
                .expectedAmount(100.0)
                .build();

        final var plantationWithHarvestP = Plantation.builder()
                .farmName("farm1WithCorn")
                .crop(CropType.POTATOES)
                .season(Season.SUMMER)
                .harvestedAmount(200.0)
                .build();

        farmService.addPlantation(plantationP);
        farmService.updateHarvest(plantationWithHarvestP);

        final var plantation2 = Plantation.builder()
                .farmName("farm2WithCorn")
                .crop(CropType.CORN)
                .season(Season.WINTER)
                .plantingArea(3.0)
                .expectedAmount(50.0)
                .build();

        final var plantationWithHarvest2 = Plantation.builder()
                .farmName("farm2WithCorn")
                .crop(CropType.CORN)
                .season(Season.WINTER)
                .harvestedAmount(130.0)
                .build();

        farmService.addPlantation(plantation2);
        farmService.updateHarvest(plantationWithHarvest2);

        final var plantation3 = Plantation.builder()
                .farmName("farm3WithCorn")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .plantingArea(1.0)
                .expectedAmount(100.0)
                .build();

        final var plantationWithHarvest3 = Plantation.builder()
                .farmName("farm3WithCorn")
                .crop(CropType.CORN)
                .season(Season.SUMMER)
                .harvestedAmount(200.0)
                .build();

        farmService.addPlantation(plantation3);
        farmService.updateHarvest(plantationWithHarvest3);

        final var result = farmService.reportByFarm();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        System.out.println(json);
        Assertions.assertEquals(4, farmService.getStore().size());
    }
}
