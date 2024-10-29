package com.devstaff.farmcollector.modal;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Farm {
    private String name;
    private Set<Plantation> plantations;
}
