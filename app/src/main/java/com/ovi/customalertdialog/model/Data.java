package com.ovi.customalertdialog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BLACK HAT on 28-Feb-17.
 */

public class Data {
    @SerializedName("batches")
    @Expose
    private List<Batch> batches = null;

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
