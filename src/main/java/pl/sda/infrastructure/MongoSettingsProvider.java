package pl.sda.infrastructure;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class MongoSettingsProvider {

    private final static ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    private final static CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
    private final static CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

    private final static MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();

    public static MongoClientSettings getClientSettings() {
        return clientSettings;
    }
}
