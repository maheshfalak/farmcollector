package com.devstaff.farmcollector.controller;

import com.devstaff.farmcollector.modal.Plantation;
import com.devstaff.farmcollector.modal.enums.CropType;
import com.devstaff.farmcollector.modal.enums.Season;
import com.devstaff.farmcollector.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/farm")
public class FarmController {

    @Autowired
    private FarmService farmService;

    /**
     * Posts a new plantation
     * @param plantation all fields in plantation required except harvest
     * @return plantation added
     */
    @PostMapping(value = "/plantation", produces = MediaType.APPLICATION_JSON_VALUE)
    public Plantation createPlant(@RequestBody Plantation plantation) {
        return farmService.addPlantation(plantation);
    }

    /**
     * Updates harvest for existing plantation for existing farm name, season and crop entity
     * @param plantation
     * @return
     */
    @PatchMapping(value = "/harvest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Plantation harvest(@RequestBody Plantation plantation) {
        return farmService.updateHarvest(plantation);
    }

    /**
     * Get a report in Json format, per season, then per farmname, values of expected and harvested amounts
     * @return
     */
    @GetMapping(value = "/report/farm",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Season, Map<String, List<Plantation>>> reportByFarm() {
        return farmService.reportByFarm();
    }

    /**
     * Get a report in Json format, per season, then per crop, values of expected and harvested amounts
     * @return
     */
    @GetMapping(value = "/report/crop", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Season, Map<CropType, List<Plantation>>> reportByCrop() {
        return farmService.reportByCrop();
    }
}
