package com.valleon.bunkspaces.models;

import lombok.Data;

@Data
public class BedSpace implements Comparable<BedSpace>{

    private String id;

    private Boolean isEmpty;

    public BedSpace(String id) {
        this.id = id;
        this.isEmpty = true;
    }

    @Override
    public int compareTo(BedSpace o) {
        return this.id.compareTo(o.id);
    }
}
