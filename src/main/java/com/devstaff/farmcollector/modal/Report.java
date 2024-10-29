package com.devstaff.farmcollector.modal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Report {
    private Double expectedAmount;
    private Double harvestedAmount;
}
