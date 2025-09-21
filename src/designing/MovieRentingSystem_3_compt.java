package designing;

import java.util.*;

// own code + compacted version
class MovieRentingSystem_3_compt {

    private final HashMap<Integer, PriorityQueue<Shop_Price>> movieBelongs; // map< movies -> pq of{shop, price}} >
    private final HashMap<Integer, HashSet<Integer>> rentedMovies;          // map<shop, Set.of(movie)> unavailable / ranted
    private final HashMap<Integer, HashSet<Integer>> unrentedMovies;       // map<shop, Set.of(movie)> available/ unRanted
    private final PriorityQueue<Price_Shop_Movie> rentedReport;             // pq of {price, shop, movie}
    private final HashMap<Integer, HashMap<Integer, Integer>> shopMoviePrice; // replacement of Trie; keeps shop ->movie ->price


    public MovieRentingSystem_3_compt(int n, int[][] entries) {
        rentedMovies = new HashMap<>();
        unrentedMovies = new HashMap<>();
        movieBelongs = new HashMap<>();
        rentedReport = new PriorityQueue<>();
        shopMoviePrice = new HashMap<>();

        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            movieBelongs.computeIfAbsent(movie, pq -> new PriorityQueue<>()).offer(new Shop_Price(shop, price));
            unrentedMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);
            shopMoviePrice.computeIfAbsent(shop, map -> new HashMap<>()).put(movie, price);
        }

    }

    public List<Integer> search(int movie) {
        PriorityQueue<Shop_Price> pq = movieBelongs.getOrDefault(movie, new PriorityQueue<>());
        List<Integer> shopList = new ArrayList<>();
        PriorityQueue<Shop_Price> temp = new PriorityQueue<>();
        while (!pq.isEmpty() && shopList.size() < 5) {
            Shop_Price poll = pq.poll();
            int shop = poll.shop;
            int price = poll.price;
            if (unrentedMovies.getOrDefault(shop, new HashSet<>()).contains(movie)) {
                shopList.add(shop);
            }
            temp.offer(poll);
        }
        pq.addAll(temp);

        return shopList;
    }

    public void rent(int shop, int movie) {
        unrentedMovies.getOrDefault(shop, new HashSet<>()).remove(movie);
        rentedMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);
        int price = shopMoviePrice.getOrDefault(shop, new HashMap<>()).getOrDefault(movie, 0);
        rentedReport.offer(new Price_Shop_Movie(price, shop, movie));
    }

    public void drop(int shop, int movie) {
        rentedMovies.getOrDefault(shop, new HashSet<>()).remove(movie);
        unrentedMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();

        TreeSet<Price_Shop_Movie> temp = new TreeSet<>();
        while (!rentedReport.isEmpty() && res.size() < 5) {
            Price_Shop_Movie poll = rentedReport.poll();
            int price = poll.price;
            int shop = poll.shop;
            int movie = poll.movie;
            if (rentedMovies.getOrDefault(shop, new HashSet<>()).contains(movie) && !temp.contains(poll)) {
                res.add(List.of(shop, movie));
                temp.add(poll);
            }
        }
        rentedReport.addAll(temp);

        return res;
    }

    record Price_Shop_Movie(int price, int shop, int movie) implements Comparable<Price_Shop_Movie> {
        @Override
        public int compareTo(Price_Shop_Movie that) {
            if (this.price != that.price) {
                return this.price - that.price;
            } else if (this.shop != that.shop) {
                return this.shop - that.shop;
            } else return this.movie - that.movie;
        }
    }

    record Shop_Price(int shop, int price) implements Comparable<Shop_Price> {
        @Override
        public int compareTo(Shop_Price that) {
            if (this.price != that.price) {
                return this.price - that.price;
            }
            return this.shop - that.shop;
        }
    }
}
