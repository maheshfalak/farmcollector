package com.devstaff.farmcollector.modal;

import com.devstaff.farmcollector.modal.enums.CropType;
import com.devstaff.farmcollector.modal.enums.Season;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Plantation {
    private String farmName;
    private Season season;
    private CropType crop;
    private Double plantingArea;
    private Double expectedAmount;
    private Double harvestedAmount;
}
