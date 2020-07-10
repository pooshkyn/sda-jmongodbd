package pl.sda.follow.domain;

import pl.sda.user.domain.User;

import java.util.List;

public class MongoFollowRepository implements FollowRepository {

    @Override
    public String follow(String follower, String followee) {
        return null;
    }

    @Override
    public void unfollow(String follower, String followee) {

    }

    @Override
    public List<User> getUserFollowers(String followee) {
        return null;
    }
}
