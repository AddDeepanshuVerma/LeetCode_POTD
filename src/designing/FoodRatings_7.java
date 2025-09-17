package designing;

import jdk.jfr.Description;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

@Description("All testcases passed : using TreeSet with 2 different record class")
class FoodRatings_7 {

    private final HashMap<String, ratingCuisine> foodMap;               // foodName -> {rating, cuisine}
    private final HashMap<String, TreeSet<ratingFood>> cuisineMap;      // cuisine -> TreeSet of {rating, foodName}

    public FoodRatings_7(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<String, ratingCuisine>();                 // contains : foodName -> {rating, cuisine}
        cuisineMap = new HashMap<String, TreeSet<ratingFood>>();        // contains : cuisine -> queue OF {rating, foodName}
        Comparator<ratingFood> comp = Comparator.comparing((ratingFood a) -> a.rating)
                                                .reversed()
                                                .thenComparing(a -> a.foodName); for (int i = 0; i < foods.length; i++) {
            String food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];

            foodMap.put(food, new ratingCuisine(rating, cuisine));
            cuisineMap.computeIfAbsent(cuisine, q -> new TreeSet<>(comp))
                      .add(new ratingFood(rating, food));
        }
    }

    public void changeRating(String food, int newRating) {
        ratingCuisine trio = foodMap.get(food);
        Integer prevRating = trio.rating;
        String cuisine = trio.cuisine;

        foodMap.put(food, new ratingCuisine(newRating, cuisine));

        TreeSet<ratingFood> pairs = cuisineMap.get(cuisine);
        pairs.remove(new ratingFood(prevRating, food)); // remove previous
        pairs.add(new ratingFood(newRating, food));   // offer updated rating
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine)
                         .first().foodName;
    }

    static record ratingCuisine(Integer rating, String cuisine) {

    }

    static record ratingFood(Integer rating, String foodName) {

    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(foodName,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */