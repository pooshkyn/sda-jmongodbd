package pl.sda.post.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.support.FollowRepositoryAbility;
import pl.sda.support.PostRepositoryAbility;
import pl.sda.support.UserRepositoryAbility;
import pl.sda.user.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostRepositoryTest implements PostRepositoryAbility, UserRepositoryAbility, FollowRepositoryAbility {

    private final static String USERNAME = "jan";
    private final static int ID = 1;
    private final static User USER = User.builder().username(USERNAME).password("alternatywy4").build();
    private final static User USER2 = new User("testuser", "haslo");
    private final static User USER3 = new User("mietek", "qwerty123");
    private final static Post POST = new Post(1, "test", "jan", NOW);
    private final static Post POST2 = new Post(2, "lorem ipsum", "mietek", NOW.minusDays(1));
    private final static Post POST3 = new Post(3, "ipsum lorem", "jan", NOW.minusHours(4));

    PostRepository postRepository = new MongoPostRepository();

    @BeforeEach
    void init() {
       clearDB();
    }

    @Test
    void testFindUserPosts() {
        //given
        addUsers();
        addPosts();
        //when
        List<Post> result = postRepository.findUserPosts(USERNAME);
        //then
        assertTrue(result.contains(POST));
        assertTrue(result.contains(POST3));
    }

    @Test
    void testAddPost() {
        //when
        addUsers();
        postRepository.addPost(new Post(1, "test", USERNAME, NOW));
        //then
        Optional<Post> post = findPost(1);
        assertTrue(post.isPresent());
        assertEquals(Post.builder().id(1).text("test").username(USERNAME).creationDate(NOW).build(), post.get());
    }

    @Test
    void testFindDailyPosts() {
        //given
        addUsers();
        addPosts();
        //when
        List<Post> result = postRepository.findDailyPosts();
        //then
        assertTrue(result.contains(POST));
        assertTrue(result.contains(POST2));
    }

    @Test
    void testFindById() {
        //given
        addUsers();
        addPosts();
        //when
        Optional<Post> result = postRepository.findById(ID);
        //then
        assertTrue(result.isPresent());
        assertEquals(POST, result.get());
    }

    @Test
    void testFindById_postDoesNotExists() {
        //when
        Optional<Post> result = postRepository.findById(ID);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void testEditPost() {
        //given
        String newContent = "some new content";
        Post expectedPost = Post.builder().id(ID).text(newContent).username(USERNAME).creationDate(NOW).build();
        addUsers();
        addPosts();
        //when
        postRepository.editPost(ID, "some new content");
        //then
        Optional<Post> post = findPost(ID);
        assertTrue(post.isPresent());
        assertEquals(expectedPost, post.get());
    }

    @Test
    void testDeletePost() {
        //given
        addUsers();
        addPosts();
        //when
        postRepository.deletePost(1);
        //then
        assertFalse(findPost(ID).isPresent());
    }
}
