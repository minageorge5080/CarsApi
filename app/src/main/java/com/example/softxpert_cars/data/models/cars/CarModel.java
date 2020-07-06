package com.example.softxpert_cars.data.models.cars;

import com.google.gson.annotations.SerializedName;

public class CarModel {

    @SerializedName("id")
    private int id;
    @SerializedName("brand")
    private String brand;
    @SerializedName("constructionYear")
    private String constructionYear;
    @SerializedName("isUsed")
    private Boolean isUsed;
    @SerializedName("imageUrl")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
