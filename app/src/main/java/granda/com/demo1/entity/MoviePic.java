package granda.com.demo1.entity;

import java.io.Serializable;

/**
 * Created by Granda on 5/25/2017.
 */
public class MoviePic implements Serializable {
    private static final long serialVersionUID = 222L ;

    private int id;
    private String movieId;
    private String picUrl;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMovieId() {
        return movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


}