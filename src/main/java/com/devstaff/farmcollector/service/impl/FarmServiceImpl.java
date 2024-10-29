package com.devstaff.farmcollector.service.impl;

import com.devstaff.farmcollector.modal.Plantation;
import com.devstaff.farmcollector.modal.enums.CropType;
import com.devstaff.farmcollector.modal.enums.Season;
import com.devstaff.farmcollector.service.FarmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class FarmServiceImpl implements FarmService {

    List<Plantation> dataStore = new ArrayList<>();

    public List<Plantation> getStore() {
        return this.dataStore;
    }

    /**
     * add plantation to data store
     * @param plantation
     * @return
     */
    @Override
    public Plantation addPlantation(Plantation plantation) {
        for (Plantation p : dataStore) {
            if (p.getSeason().getDesc().equalsIgnoreCase(plantation.getSeason().getDesc())
                    && p.getCrop().getDesc().equalsIgnoreCase(plantation.getCrop().getDesc())
                    && p.getFarmName().equalsIgnoreCase(plantation.getFarmName())) {
                p.setPlantingArea(plantation.getPlantingArea());
                p.setExpectedAmount(plantation.getExpectedAmount());
                return p;
            }
        }
        dataStore.add(plantation);
        return plantation;
    }

    /**
     * update harvest in data store
     * @param plantation
     * @return
     */
    @Override
    public Plantation updateHarvest(Plantation plantation) {
        for (Plantation p : dataStore) {
            if (p.getSeason().getDesc().equalsIgnoreCase(plantation.getSeason().getDesc())
                    && p.getCrop().getDesc().equalsIgnoreCase(plantation.getCrop().getDesc())
                    && p.getFarmName().equalsIgnoreCase(plantation.getFarmName())) {
                p.setHarvestedAmount(plantation.getHarvestedAmount());
                return p;
            }
        }
        return plantation;
    }

    /**
     * report by farm name
     * @return
     */
    @Override
    public Map<Season, Map<String, List<Plantation>>> reportByFarm() {
        return dataStore.stream().collect(groupingBy(Plantation::getSeason, groupingBy(Plantation::getFarmName)));
    }

    /**
     * report by crop
     * @return
     */
    @Override
    public Map<Season, Map<CropType, List<Plantation>>> reportByCrop() {
        return dataStore.stream().collect(groupingBy(Plantation::getSeason, groupingBy(Plantation::getCrop)));
    }
}
