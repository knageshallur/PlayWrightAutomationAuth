package com.java.auth.helpers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseHelpers {
    public DatabaseHelpers() {
        super();
    }

    public static MongoDatabase getMongoDBConnection(String connecturl , String dbName){

        // MongoDB connection string
        String connectionURL = connecturl;

        // Create MongoDB client
        MongoClient mongoClient = MongoClients.create(connectionURL);

        // Get the database
        return mongoClient.getDatabase(dbName);
    }
}
