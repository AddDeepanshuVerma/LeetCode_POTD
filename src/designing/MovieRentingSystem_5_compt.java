package designing;

import java.util.*;

// using comparator instead of comparable interface
class MovieRentingSystem_5_compt {
    private final TreeSet<Item> rentedMovies;                           // TreeSet<shopId, movieId, price>()
    private final HashMap<Integer, TreeSet<Item>> unRentedMovies;       // {movie -> TreeSet<shopId, movieId, price>()}
    private final HashMap<Integer, HashMap<Integer, Item>> shopMap;     // {shop -> HashMap<movie, item>}

    public MovieRentingSystem_5_compt(int n, int[][] entries) {
        Comparator<Item> comp = Comparator.comparing(Item::price).thenComparing(Item::shopId).thenComparing(Item::movieId);
        unRentedMovies = new HashMap<>();
        rentedMovies = new TreeSet<>(comp);
        shopMap = new HashMap<>();

        for (int[] entry : entries) {
            int movie = entry[1], shop = entry[0], price = entry[2];
            Item item = new Item(shop, movie, price);
            unRentedMovies.computeIfAbsent(movie, set -> new TreeSet<>(comp)).add(item);
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

    record Item(int shopId, int movieId, int price) {
    }
}