package com.devstaff.farmcollector.modal.enums;

import lombok.Getter;

@Getter
public enum CropType {
    CORN("corn"),
    POTATOES("potatoes");

    private final String desc;

    CropType(String desc) {
        this.desc = desc;
    }
}
