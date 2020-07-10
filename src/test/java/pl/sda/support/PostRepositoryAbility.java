package pl.sda.support;

import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import pl.sda.infrastructure.MongoSettingsProvider;
import pl.sda.post.domain.Post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public interface PostRepositoryAbility extends CommonRepositoryAbility {

    default void addPosts() {
        LocalDateTime timestamp = NOW.minusHours(6);
        LocalDateTime timestamp2 = NOW.minusHours(2);
        LocalDateTime timestamp3 = NOW.minusDays(2);

        try  {
            executeInPostCollection(c -> c.insertMany(Arrays.asList(
                    Post.builder().id(1).text("test").username("jan").creationDate(timestamp).build(),
            Post.builder().id(2).text("lorem ipsum").username("mietek").creationDate(timestamp2).build(),
            Post.builder().id(3).text("ipsum lorem").username("jan").creationDate(timestamp3).build()

            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Optional<Post> findPost(int id) {
        try  {
            return executeInPostCollection(c -> Optional.ofNullable(c.find(eq("_id", id)).first()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> T executeInPostCollection(Function<MongoCollection<Post>, T> task) {
        try (MongoClient mongoClient = MongoClients.create(MongoSettingsProvider.getClientSettings())) {
            MongoDatabase database = mongoClient.getDatabase("twitterexample");
            MongoCollection<Post> collection = database.getCollection("post", Post.class);

            return task.apply(collection);
        }
    }
}
