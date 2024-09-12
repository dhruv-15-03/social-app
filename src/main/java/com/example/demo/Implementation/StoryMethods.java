package com.example.demo.Implementation;

import com.example.demo.Classes.Story;
import com.example.demo.Classes.User;
import com.example.demo.Exception.StoryException;

public interface StoryMethods {
    public Story post(Story story, User user);
    public Story like(Integer storyId,User user) throws StoryException;
    public String Delete(Integer storyId,User user) throws StoryException;
    public  Story view(Integer userId,User user) throws StoryException;

}
