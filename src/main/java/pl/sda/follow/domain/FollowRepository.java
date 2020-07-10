package pl.sda.follow.domain;

import pl.sda.user.domain.User;

import java.util.List;

public interface FollowRepository {

    //Dowolny użytkownik może followować innego użytkownika (dodać do obserwowanych).
    String follow(String follower, String followee);

    //Dowolny użytkownik może zrezygnować ze followowania (śledzenia) danego użytkownika.
    void unfollow(String follower, String followee);

    //Znajdź subskrybentów użytkownika
    List<User> getUserFollowers(String followee);
}
