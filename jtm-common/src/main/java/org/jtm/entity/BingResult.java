
package org.jtm.entity;


import java.util.List;

public class BingResult {
    private List<BingImage> images;
    private String tooltips;

    public List<BingImage> getImages() {
        return images;
    }

    public void setImages(List<BingImage> images) {
        this.images = images;
    }

    public String getTooltips() {
        return tooltips;
    }

    public void setTooltips(String tooltips) {
        this.tooltips = tooltips;
    }
}
