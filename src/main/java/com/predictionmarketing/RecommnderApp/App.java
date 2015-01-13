package com.predictionmarketing.RecommnderApp;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * It’s time to experiment with collaborative filtering within Mahout
 * by creating some simple input and finding recommendations based on the input.
 * @author Ramzi Sh. Alqrainy
 * @version 0.3
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	// Load data Model.
    	// A DataModel implementation stores and provides access to all the preference,
    	// user, and item data needed in the computation.
    	DataModel model = new FileDataModel(new File("data/dataset.csv"));
    	UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
    	// A group of users that are most similar to a given user
    	UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
    	// Create Recommender Engine.
    	UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    	
    	// For user 2, recommend 3 items
    	List<RecommendedItem> recommendations = recommender.recommend(2, 3);
    	for (RecommendedItem recommendation : recommendations) {
    	  System.out.println(recommendation);
    	}
    }
}
