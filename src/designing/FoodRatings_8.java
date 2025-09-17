package designing;

import jdk.jfr.Description;

import java.util.HashMap;
import java.util.PriorityQueue;

@Description("Passed all test cases but Runtime is better: Reason being not using PriorityQueue remove(O(N)) operation but with lazy polling(logN)")
class FoodRatings_8 {

    private final HashMap<String, PriorityQueue<FoodDetails>> cuisineMap;
    private final HashMap<String, FoodDetails> foodMap;

    public FoodRatings_8(String[] foods, String[] cuisines, int[] ratings) {
        cuisineMap = new HashMap<>();   // cuisine -> priorityQueue {foodName, rating}
        foodMap = new HashMap<>();      // foodName -> {cuisine, rating}

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodMap.put(food, new FoodDetails(rating, cuisine, ""));
            cuisineMap.computeIfAbsent(cuisine, pq -> new PriorityQueue<>())
                      .offer(new FoodDetails(rating, "", food));
        }
    }

    public void changeRating(String food, int newRating) {
        FoodDetails foodDetails = foodMap.get(food);
        String cuisine = foodDetails.cuisine;
        int prevRating = foodDetails.rating;
        foodMap.put(food, new FoodDetails(newRating, cuisine, ""));

        PriorityQueue<FoodDetails> pq = cuisineMap.get(cuisine);
        pq.offer(new FoodDetails(newRating, "", food));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodDetails> pq = cuisineMap.get(cuisine);
        while (!pq.isEmpty()) {
            FoodDetails peek = pq.peek();
            String food = peek.foodName;
            int rating = peek.rating;
            int latestRating = foodMap.get(food).rating;
            if (rating == latestRating) {
                return food;
            }
            pq.poll();
        }
        return "";
    }

    static class FoodDetails implements Comparable<FoodDetails> {
        int rating;
        String cuisine;
        String foodName;

        public FoodDetails(int rating, String cuisine, String foodName) {
            this.rating = rating;
            this.cuisine = cuisine;
            this.foodName = foodName;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            FoodDetails that = (FoodDetails) o;
            return rating == that.rating && this.cuisine.equals(that.cuisine) && this.foodName.equals(that.foodName);
        }


        @Override
        public int compareTo(FoodDetails that) {
            return this.rating == that.rating ? this.foodName.compareTo(that.foodName) : that.rating - this.rating;
        }
    }
}
