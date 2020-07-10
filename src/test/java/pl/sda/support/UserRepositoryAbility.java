package pl.sda.support;

import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import pl.sda.infrastructure.MongoSettingsProvider;
import pl.sda.user.domain.User;

import java.util.Arrays;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public interface UserRepositoryAbility extends CommonRepositoryAbility {

    default void addUser() {
        try {
            executeInUserCollection(c -> c.insertOne(User.builder().username("jan").password("alternatywy4").build()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default void addUsers() {
        try {
            executeInUserCollection(c -> c.insertMany(Arrays.asList(
                    User.builder().username("jan").password("alternatywy4").build(),
                    User.builder().username("testuser").password("haslo").build(),
                    User.builder().username("mietek").password("qwerty123").build()
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Optional<User> findUser(String username) {
        try {
            return executeInUserCollection(c -> Optional.ofNullable(c.find(eq("username", username)).first()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    static <T> T executeInUserCollection(Function<MongoCollection<User>, T> task) {
        try (MongoClient mongoClient = MongoClients.create(MongoSettingsProvider.getClientSettings())) {
            MongoDatabase database = mongoClient.getDatabase("twitterexample");
            MongoCollection<User> collection = database.getCollection("user", User.class);

            return task.apply(collection);
        }
    }
}
