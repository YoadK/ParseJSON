package com.example.parsejson.ModelsPackage;

import java.io.Serializable;

public class OpeningHours implements Serializable {

    private Boolean open_now;

    public Boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(Boolean open_now) {
        this.open_now = open_now;
    }

}