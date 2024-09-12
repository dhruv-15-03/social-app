package com.example.demo.Implementation;

import com.example.demo.Classes.Story;
import com.example.demo.Classes.User;
import com.example.demo.DataBase.StoryAll;
import com.example.demo.DataBase.UserAll;
import com.example.demo.Exception.StoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class StoryImplementation implements StoryMethods{
    @Autowired
    private StoryAll storyAll;
    @Autowired
    private UserAll userAll;

    @Override
    public Story post(Story story, User user) {
        story.setTime(LocalDateTime.now());
        story.setMain(user);
        List<Story> temp=user.getStory();
        temp.add(story);
        user.setStory(temp);
        userAll.save(user);
        storyAll.save(story);
        return story;
    }

    @Override
    public Story like(Integer storyId, User user) throws StoryException {
        Story story=storyAll.getReferenceById(storyId);
        User acc=story.getMain();
        if(!user.getFollowing().contains(acc))
            throw new StoryException("Not Following can't like the Story");
        List<User> like=story.getLiked();
        if(like.contains(user))
            like.remove(user);
        else
            like.add(user);
        story.setLiked(like);
        storyAll.save(story);
        return story;
    }

    @Override
    public String Delete(Integer storyId, User user) throws StoryException {
        Story story=storyAll.getReferenceById(storyId);
        if(!Objects.equals(user.getId(), story.getMain().getId()))
            throw new StoryException("Not Your Story");
        storyAll.delete(story);
        List<Story> store=user.getStory();
        store.remove(story);
        user.setStory(store);
        userAll.save(user);
        return "Deleted Successfully";
    }

    @Override
    public Story view(Integer storyId, User user) throws StoryException {
        Story story=storyAll.getReferenceById(storyId);
        User acc=story.getMain();
        if(!user.getFollowing().contains(acc))
            throw new StoryException("Not Following can't view the Story");
        List<User> view=story.getViews();
        if(view.contains(user))
            view.remove(user);
        else
            view.add(user);
        story.setViews(view);
        storyAll.save(story);
        return story;
    }
}
