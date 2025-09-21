package designing;

import java.util.*;

// here comes the CHAT-GPT, the goat
class MovieRentingSystem_chatGPT_3 {
    private final TreeSet<Item> rentedMovies = new TreeSet<>();
    private final Map<Integer, TreeSet<Item>> unRentedMovies = new HashMap<>();
    private final Map<Integer, Map<Integer, Item>> shopMap = new HashMap<>();
    // we could also use the comparator chaining
    Comparator<Item> comp = Comparator.comparing(Item::price).thenComparing(Item::shopId).thenComparing(Item::movieId);


    public MovieRentingSystem_chatGPT_3(int n, int[][] entries) {
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            Item item = new Item(shop, movie, price);
            unRentedMovies.computeIfAbsent(movie, k -> new TreeSet<>()).add(item);
            shopMap.computeIfAbsent(shop, k -> new HashMap<>()).put(movie, item);
        }
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

    public List<Integer> search(int movie) {
        return Optional.ofNullable(unRentedMovies.get(movie)).orElseGet(TreeSet::new).stream().limit(5).map(item -> item.shopId).toList();
    }

    public List<List<Integer>> report() {
        return Optional.of(rentedMovies).orElseGet(TreeSet::new).stream().limit(5).map(item -> List.of(item.shopId, item.movieId)).toList();
    }

    record Item(int shopId, int movieId, int price) implements Comparable<Item> {
        @Override
        public int compareTo(Item that) {
            return this.price != that.price ? this.price - that.price : (this.shopId != that.shopId ? this.shopId - that.shopId : this.movieId - that.movieId);
        }
    }
}
