package pl.sda.support;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import pl.sda.follow.domain.Follow;
import pl.sda.infrastructure.MongoSettingsProvider;
import pl.sda.post.domain.Post;
import pl.sda.user.domain.User;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface CommonRepositoryAbility {

    LocalDateTime NOW = LocalDateTime.now(ZoneId.systemDefault());

    default void clearDB() {
        try (MongoClient mongoClient = MongoClients.create(MongoSettingsProvider.getClientSettings())) {
            MongoDatabase database = mongoClient.getDatabase("twitterexample");
            MongoCollection<User> userCollection = database.getCollection("user", User.class);
            MongoCollection<Post> postCollection = database.getCollection("post", Post.class);
            MongoCollection<Follow> followCollection = database.getCollection("follow", Follow.class);

            userCollection.drop();
            postCollection.drop();
            followCollection.drop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
