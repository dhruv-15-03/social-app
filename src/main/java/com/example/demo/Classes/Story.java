package com.example.demo.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User main;
    @ManyToMany
    @JsonIgnore
    private List<User> liked=new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    private List<User> views=new ArrayList<>();
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getMain() {
        return main;
    }

    public void setMain(User main) {
        this.main = main;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public List<User> getViews() {
        return views;
    }

    public void setViews(List<User> views) {
        this.views = views;
    }

    public Story() {
    }

    public Story(Integer id, String content, User main, List<User> liked,LocalDateTime time,List<User> views) {
        this.id = id;
        this.content = content;
        this.main = main;
        this.liked = liked;
        this.time=time;
        this.views=views;
    }
}
