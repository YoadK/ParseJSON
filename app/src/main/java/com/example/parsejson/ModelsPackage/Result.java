package com.example.parsejson.ModelsPackage;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    private String id;
    private Geometry geometry;
    private String icon;
    private String name;
    private List<Photo> photos;
    private String place_id;
    private String reference;
    private String scope;
    private List<String> types = null;
    private String vicinity;
    private String business_status;
    private OpeningHours opening_hours;
    private PlusCode plus_code;
    private Double rating;
    private Integer user_ratings_total;
    private Boolean permanently_closed;

    public Result(String name, String vicinity, Geometry geometry, List<Photo> photos) {
        this.name = name;
        this.vicinity = vicinity;
        this.geometry = geometry;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public PlusCode getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getUser_ratings_total() {
        return user_ratings_total;
    }

    public void setUser_ratings_total(Integer user_ratings_total) {
        this.user_ratings_total = user_ratings_total;
    }

    public Boolean getPermanently_closed() {
        return permanently_closed;
    }

    public void setPermanently_closed(Boolean permanently_closed) {
        this.permanently_closed = permanently_closed;
    }

}
