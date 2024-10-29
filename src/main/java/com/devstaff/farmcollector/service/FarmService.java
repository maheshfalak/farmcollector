package com.devstaff.farmcollector.service;

import com.devstaff.farmcollector.modal.Plantation;
import com.devstaff.farmcollector.modal.enums.CropType;
import com.devstaff.farmcollector.modal.enums.Season;

import java.util.List;
import java.util.Map;

public interface FarmService {

    Plantation addPlantation(Plantation plantation);

    Plantation updateHarvest(Plantation plantation);

    Map<Season, Map<String, List<Plantation>>> reportByFarm();

    Map<Season, Map<CropType, List<Plantation>>> reportByCrop();
}
