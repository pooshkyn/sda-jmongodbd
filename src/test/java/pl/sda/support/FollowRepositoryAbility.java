package pl.sda.support;

import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import pl.sda.follow.domain.Follow;
import pl.sda.infrastructure.MongoSettingsProvider;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public interface FollowRepositoryAbility extends CommonRepositoryAbility {

    String FOLLOW_ID = "dad2c22b-7322-4def-bebb-de69005cc584";
    String FOLLOW_ID_2 = "5ff2afd2-3d99-4a38-a39c-dd87440579b9";
    String FOLLOW_ID_3 = "91c6c188-7306-4406-9c5a-0eab297e9f56s";

    default Optional<Follow> findFollow(String id) {
        try {
            return executeInFollowCollection(c -> Optional.ofNullable(c.find(eq("_id", id)).first()));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    default void addFollows() {
        LocalDateTime timestamp = NOW.minusHours(6);
        LocalDateTime timestamp2 = NOW.minusHours(2);
        LocalDateTime timestamp3 = NOW.minusDays(2);

        try {
            executeInFollowCollection(c -> c.insertMany(Arrays.asList(
                Follow.builder().id(FOLLOW_ID).followerUsername("jan").followeeUsername("mietek").creationDate(timestamp).build(),
                Follow.builder().id(FOLLOW_ID_2).followerUsername("mietek").followeeUsername("jan").creationDate(timestamp2).build(),
                Follow.builder().id(FOLLOW_ID_3).followerUsername("testuser").followeeUsername("jan").creationDate(timestamp3).build()
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> T executeInFollowCollection(Function<MongoCollection<Follow>, T> task) {
        try (MongoClient mongoClient = MongoClients.create(MongoSettingsProvider.getClientSettings())) {
            MongoDatabase database = mongoClient.getDatabase("twitterexample");
            MongoCollection<Follow> collection = database.getCollection("follow", Follow.class);

            return task.apply(collection);
        }
    }
}
