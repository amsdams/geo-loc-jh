package com.amsdams.jh.service.dto;
import java.io.Serializable;
import java.util.Objects;

import org.geolatte.geom.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A DTO for the {@link com.amsdams.jh.domain.Geocity} entity.
 */
public class GeocityDTO implements Serializable {

    private Long id;

    private String name;

    private String asciiname;

    private String alternatenames;
    @JsonIgnore
    private Point location;

    private Double lat;

    private Double lon;

    private String featureclass;

    private String featuretype;

    private String countrycode;

    private String cc2;

    private String admin1code;

    private String admin2code;

    private String admin3code;

    private String admin4code;

    private Integer population;

    private Integer elevation;

    private String dem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getFeatureclass() {
        return featureclass;
    }

    public void setFeatureclass(String featureclass) {
        this.featureclass = featureclass;
    }

    public String getFeaturetype() {
        return featuretype;
    }

    public void setFeaturetype(String featuretype) {
        this.featuretype = featuretype;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1code() {
        return admin1code;
    }

    public void setAdmin1code(String admin1code) {
        this.admin1code = admin1code;
    }

    public String getAdmin2code() {
        return admin2code;
    }

    public void setAdmin2code(String admin2code) {
        this.admin2code = admin2code;
    }

    public String getAdmin3code() {
        return admin3code;
    }

    public void setAdmin3code(String admin3code) {
        this.admin3code = admin3code;
    }

    public String getAdmin4code() {
        return admin4code;
    }

    public void setAdmin4code(String admin4code) {
        this.admin4code = admin4code;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getDem() {
        return dem;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocityDTO geocityDTO = (GeocityDTO) o;
        if (geocityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geocityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeocityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", asciiname='" + getAsciiname() + "'" +
            ", alternatenames='" + getAlternatenames() + "'" +
            ", location=" + getLocation() +
            ", lat=" + getLat() +
            ", lon=" + getLon() +
            ", featureclass='" + getFeatureclass() + "'" +
            ", featuretype='" + getFeaturetype() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            ", cc2='" + getCc2() + "'" +
            ", admin1code='" + getAdmin1code() + "'" +
            ", admin2code='" + getAdmin2code() + "'" +
            ", admin3code='" + getAdmin3code() + "'" +
            ", admin4code='" + getAdmin4code() + "'" +
            ", population=" + getPopulation() +
            ", elevation=" + getElevation() +
            ", dem='" + getDem() + "'" +
            "}";
    }
}
