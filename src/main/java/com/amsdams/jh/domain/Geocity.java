package com.amsdams.jh.domain;
import org.geolatte.geom.Point;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Geocity.
 */
@Entity
@Table(name = "geocity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "geocity")
public class Geocity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "asciiname")
    private String asciiname;

    @Column(name = "alternatenames")
    private String alternatenames;

    @Column(name = "location")
    private Point location;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "featureclass")
    private String featureclass;

    @Column(name = "featuretype")
    private String featuretype;

    @Column(name = "countrycode")
    private String countrycode;

    @Column(name = "cc_2")
    private String cc2;

    @Column(name = "admin_1_code")
    private String admin1code;

    @Column(name = "admin_2_code")
    private String admin2code;

    @Column(name = "admin_3_code")
    private String admin3code;

    @Column(name = "admin_4_code")
    private String admin4code;

    @Column(name = "population")
    private Integer population;

    @Column(name = "elevation")
    private Integer elevation;

    @Column(name = "dem")
    private String dem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Geocity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public Geocity asciiname(String asciiname) {
        this.asciiname = asciiname;
        return this;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public Geocity alternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
        return this;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public Point getLocation() {
        return location;
    }

    public Geocity location(Point location) {
        this.location = location;
        return this;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public Geocity lat(Double lat) {
        this.lat = lat;
        return this;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public Geocity lon(Double lon) {
        this.lon = lon;
        return this;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getFeatureclass() {
        return featureclass;
    }

    public Geocity featureclass(String featureclass) {
        this.featureclass = featureclass;
        return this;
    }

    public void setFeatureclass(String featureclass) {
        this.featureclass = featureclass;
    }

    public String getFeaturetype() {
        return featuretype;
    }

    public Geocity featuretype(String featuretype) {
        this.featuretype = featuretype;
        return this;
    }

    public void setFeaturetype(String featuretype) {
        this.featuretype = featuretype;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Geocity countrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCc2() {
        return cc2;
    }

    public Geocity cc2(String cc2) {
        this.cc2 = cc2;
        return this;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1code() {
        return admin1code;
    }

    public Geocity admin1code(String admin1code) {
        this.admin1code = admin1code;
        return this;
    }

    public void setAdmin1code(String admin1code) {
        this.admin1code = admin1code;
    }

    public String getAdmin2code() {
        return admin2code;
    }

    public Geocity admin2code(String admin2code) {
        this.admin2code = admin2code;
        return this;
    }

    public void setAdmin2code(String admin2code) {
        this.admin2code = admin2code;
    }

    public String getAdmin3code() {
        return admin3code;
    }

    public Geocity admin3code(String admin3code) {
        this.admin3code = admin3code;
        return this;
    }

    public void setAdmin3code(String admin3code) {
        this.admin3code = admin3code;
    }

    public String getAdmin4code() {
        return admin4code;
    }

    public Geocity admin4code(String admin4code) {
        this.admin4code = admin4code;
        return this;
    }

    public void setAdmin4code(String admin4code) {
        this.admin4code = admin4code;
    }

    public Integer getPopulation() {
        return population;
    }

    public Geocity population(Integer population) {
        this.population = population;
        return this;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getElevation() {
        return elevation;
    }

    public Geocity elevation(Integer elevation) {
        this.elevation = elevation;
        return this;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getDem() {
        return dem;
    }

    public Geocity dem(String dem) {
        this.dem = dem;
        return this;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geocity)) {
            return false;
        }
        return id != null && id.equals(((Geocity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Geocity{" +
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
