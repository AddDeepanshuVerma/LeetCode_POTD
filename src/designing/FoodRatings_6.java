package designing;

import jdk.jfr.Description;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

@Description("All testcases passed : instead of priorityQueue using TreeSet with custom comparator")
class FoodRatings_6 {

    private final HashMap<String, Pair<Integer, String>> foodMap;
    private final HashMap<String, TreeSet<Pair<Integer, String>>> cuisineMap;

    public FoodRatings_6(String[] foods, String[] cuisines, int[] ratings) {
        Comparator<Pair<Integer, String>> comp = Comparator.comparing((Pair<Integer, String> a) -> a.key)
                                                           .reversed()
                                                           .thenComparing(a -> a.value);

        foodMap = new HashMap<String, Pair<Integer, String>>();// contains : foodName -> {rating, cuisine}
        cuisineMap = new HashMap<String, TreeSet<Pair<Integer, String>>>();// contains : cuisine -> queue OF {rating, foodName}
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];

            foodMap.put(food, new Pair<>(rating, cuisine));
            cuisineMap.computeIfAbsent(cuisine, q -> new TreeSet<>(comp))
                      .add(new Pair<>(rating, food));
        }
    }

    public void changeRating(String food, int newRating) {
        Pair<Integer, String> pair = foodMap.get(food);
        Integer prevRating = pair.key;
        String cuisine = pair.value;

        foodMap.put(food, new Pair<>(newRating, cuisine));

        TreeSet<Pair<Integer, String>> treeSet = cuisineMap.get(cuisine);
        treeSet.remove(new Pair<>(prevRating, food)); // remove previous
        treeSet.add(new Pair<>(newRating, food));   // offer updated rating
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine)
                         .first().value;
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