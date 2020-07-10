package pl.sda.follow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.support.FollowRepositoryAbility;
import pl.sda.support.UserRepositoryAbility;
import pl.sda.user.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FollowRepositoryTest implements FollowRepositoryAbility, UserRepositoryAbility {

    private final static User USER = new User("jan", "alternatywy4");
    private final static User USER2 = new User("testuser", "haslo");
    private final static User USER3 = new User("mietek", "qwerty123");

    FollowRepository followRepository = new MongoFollowRepository();

    @BeforeEach
    void init() { clearDB(); }

    @Test
    void testFollow() {
        //given
        addUsers();
        //when
        String followId = followRepository.follow(USER.getUsername(), USER3.getUsername());
        //then
        Optional<Follow> follow = findFollow(followId);

        assertTrue(follow.isPresent());
        assertEquals(USER.getUsername(), follow.get().getFollowerUsername());
        assertEquals(USER3.getUsername(), follow.get().getFolloweeUsername());
    }

    @Test
    void testUnfollow() {
        //given
        addUsers();
        addFollows();
        //when
        followRepository.unfollow("jan", "mietek");
        //then
        Optional<Follow> follow = findFollow(FOLLOW_ID);
        assertFalse(follow.isPresent());
    }

    @Test
    void testGetUserFollowers() {
        //given
        addUsers();
        addFollows();
        //when
        List<User> result = followRepository.getUserFollowers(USER.getUsername());
        //then
        assertEquals(2, result.size());
        assertTrue(result.contains(USER2));
        assertTrue(result.contains(USER3));
    }
}
