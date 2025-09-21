package designing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

// chepa hua + refined from my end like -record class -comparable interface usage -HashMap inliner compute method
class MovieRentingSystem_4_compt {
    private final TreeSet<Item> rentedMovies;                           // TreeSet<shopId, movieId, price>()
    private final HashMap<Integer, TreeSet<Item>> unRentedMovies;       // {movie -> TreeSet<shopId, movieId, price>()}
    private final HashMap<Integer, HashMap<Integer, Item>> shopMap;     // {shop -> HashMap<movie, item>}

    public MovieRentingSystem_4_compt(int n, int[][] entries) {
        unRentedMovies = new HashMap<>();
        rentedMovies = new TreeSet<>();
        shopMap = new HashMap<>();

        for (int[] entry : entries) {
            int movie = entry[1], shop = entry[0], price = entry[2];
            Item item = new Item(shop, movie, price);
            unRentedMovies.computeIfAbsent(movie, set -> new TreeSet<>()).add(item);
            shopMap.computeIfAbsent(shop, set -> new HashMap<>()).put(movie, item);
        }
    }

    public List<Integer> search(int movie) {
        TreeSet<Item> movies = unRentedMovies.getOrDefault(movie, new TreeSet<>());
        List<Integer> result = new ArrayList<>();

        for (Item eachMovie : movies) {
            if (result.size() == 5) break;
            result.add(eachMovie.shopId);
        }
        return result;
    }

    public void rent(int shop, int movie) {
        Item item = shopMap.get(shop).get(movie);
        unRentedMovies.get(movie).remove(item);
        rentedMovies.add(item);
    }

    public void drop(int shop, int movie) {
        Item item = shopMap.get(shop).get(movie);
        rentedMovies.remove(item);
        unRentedMovies.get(movie).add(item);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        for (Item item : rentedMovies) {
            if (res.size() == 5) break;
            res.add(List.of(item.shopId, item.movieId));
        }
        return res;
    }

    static record Item(int shopId, int movieId, int price) implements Comparable<Item> {
        @Override
        public int compareTo(Item that) {
            if (this.price != that.price) {
                return this.price - that.price;
            } else if (this.shopId != that.shopId) {
                return this.shopId - that.shopId;
            } else return this.movieId - that.movieId;
        }
    }
}