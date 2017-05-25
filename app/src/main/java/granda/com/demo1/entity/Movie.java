package granda.com.demo1.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Granda on 5/25/2017.
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = 222L ;

    private String id;
    private String name;
    private String alias;
    private String tags;
    private String region;
    private String year;
    private String director;
    private String screenWriter;
    private String actor;
    private String point;
    private String douBanId;
    private String picUrl;
    private List<MoviePic> moviePics;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getScreenWriter() {
        return screenWriter;
    }
    public void setScreenWriter(String screenWriter) {
        this.screenWriter = screenWriter;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    public String getDouBanId() {
        return douBanId;
    }
    public void setDouBanId(String douBanId) {
        this.douBanId = douBanId;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public List<MoviePic> getMoviePics() {
        return moviePics;
    }
    public void setMoviePics(List<MoviePic> moviePics) {
        this.moviePics = moviePics;
    }


}
