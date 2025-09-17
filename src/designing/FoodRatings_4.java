package designing;

import jdk.jfr.Description;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

@Description("Time Limit Exceeded\n" + "73 / 78 testcases passed")
class FoodRatings_4 {

    private final HashMap<String, Pair<Integer, String>> foodMap;
    private final HashMap<String, PriorityQueue<Pair<Integer, String>>> cuisineMap;

    public FoodRatings_4(String[] foods, String[] cuisines, int[] ratings) {
        Comparator<Pair<Integer, String>> comp = Comparator.comparing((Pair<Integer, String> a) -> a.key)
                                                           .reversed()
                                                           .thenComparing(a -> a.value);

        foodMap = new HashMap<String, Pair<Integer, String>>();// contains : foodName -> {rating, cuisine}
        cuisineMap = new HashMap<String, PriorityQueue<Pair<Integer, String>>>();// contains : cuisine -> queue OF {rating, foodName}
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];

            foodMap.put(food, new Pair<>(rating, cuisine));
            cuisineMap.computeIfAbsent(cuisine, q -> new PriorityQueue<>(comp))
                      .offer(new Pair<>(rating, food));
        }
    }

    public void changeRating(String food, int newRating) {
        Pair<Integer, String> pair = foodMap.get(food);
        Integer prevRating = pair.key;
        String cuisine = pair.value;

        foodMap.put(food, new Pair<>(newRating, cuisine));

        PriorityQueue<Pair<Integer, String>> pairs = cuisineMap.get(cuisine);
        pairs.remove(new Pair<>(prevRating, food)); // remove previous
        pairs.offer(new Pair<>(newRating, food));   // offer updated rating
    }

    public String highestRated(String cuisine) {
        Pair<Integer, String> peek = cuisineMap.get(cuisine)
                                               .peek();
        return peek.value;
    }

    static record Pair<K, V>(K key, V value) {

    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(foodName,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */