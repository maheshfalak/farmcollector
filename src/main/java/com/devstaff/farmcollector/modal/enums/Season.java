package com.devstaff.farmcollector.modal.enums;

import lombok.Getter;

@Getter
public enum Season {
    WINTER("winter"),
    SUMMER("summer");

    private final String desc;

    Season(String desc) {
        this.desc = desc;
    }
}
