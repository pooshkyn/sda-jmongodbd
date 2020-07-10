package pl.sda.post.domain;

import java.util.List;
import java.util.Optional;

public class MongoPostRepository implements PostRepository {

    @Override
    public List<Post> findUserPosts(String username) {
        return null;
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public List<Post> findDailyPosts() {
        return null;
    }

    @Override
    public Optional<Post> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void editPost(int id, String newText) {

    }

    @Override
    public void deletePost(int id) {

    }
}
