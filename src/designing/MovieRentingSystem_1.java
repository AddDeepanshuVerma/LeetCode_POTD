package designing;

import java.util.*;

// using multiple structure & (instead of trie) using HashMap<Integer, HashMap<Integer, Integer>>
// Wrong Answer 26 / 42 testcases passed
class MovieRentingSystem_1 {

    private final HashMap<Integer, PriorityQueue<Shop_Price>> movieBelongs; // map< movies -> pq of{shop, price}} >
    private final HashMap<Integer, HashSet<Integer>> availableMovies;       // map<shop, Set.of(movie)> available/ unRanted
    private final HashMap<Integer, HashSet<Integer>> rentedMovies;          // map<shop, Set.of(movie)> unavailable / ranted
    private final PriorityQueue<Price_Shop_Movie> rentedReport;             // pq of {price, shop, movie}
    private final HashMap<Integer, HashMap<Integer, Integer>> shopMoviePrice; // replacement of Trie; keeps shop ->movie ->price


    public MovieRentingSystem_1(int n, int[][] entries) {
        availableMovies = new HashMap<>();
        rentedMovies = new HashMap<>();
        movieBelongs = new HashMap<>();
        rentedReport = new PriorityQueue<>();
        shopMoviePrice = new HashMap<>();

        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            movieBelongs.computeIfAbsent(movie, pq -> new PriorityQueue<>()).offer(new Shop_Price(shop, price));
            availableMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);
            shopMoviePrice.computeIfAbsent(shop, map -> new HashMap<>()).put(movie, price);
        }

    }

    // need to return list of such 5 shops whose movies are unRanted within custom comparator
    public List<Integer> search(int movie) {
        // let's say that this movie belong to m number of shops(if it was available even once)
        PriorityQueue<Shop_Price> pq = movieBelongs.getOrDefault(movie, new PriorityQueue<>());
        // now this pq contains both movies that are rented & no rented
        // we need to share the list of those shops which have unRanted/available movies right at this moment

        List<Integer> shopList = new ArrayList<>();
        PriorityQueue<Shop_Price> temp = new PriorityQueue<>();
        while (!pq.isEmpty() && shopList.size() <= 5) {
            Shop_Price poll = pq.poll();
            int shop = poll.shop;
            int price = poll.price;
            if (availableMovies.getOrDefault(shop, new HashSet<>()).contains(movie)) {
                shopList.add(shop);
            }
            temp.offer(poll);
        }
        pq.addAll(temp); // adding back all rented/unRanted movies

        return shopList;
    }

    public void rent(int shop, int movie) {
        // remove this movie from unrent first
        availableMovies.getOrDefault(shop, new HashSet<>()).remove(movie);
        // put this movie as rented
        rentedMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);

        int price = shopMoviePrice.getOrDefault(shop, new HashMap<>()).getOrDefault(movie, 0);
        rentedReport.offer(new Price_Shop_Movie(price, shop, movie));
    }

    // we are receiving this movie back
    public void drop(int shop, int movie) {
        // remove this movie from rented first
        rentedMovies.getOrDefault(shop, new HashSet<>()).remove(movie);
        // put this movie as unRanted now
        availableMovies.computeIfAbsent(shop, set -> new HashSet<>()).add(movie);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();

        PriorityQueue<Price_Shop_Movie> temp = new PriorityQueue<>();
        while (!rentedReport.isEmpty() && res.size() <= 5) {
            Price_Shop_Movie poll = rentedReport.poll();
            int price = poll.price;
            int shop = poll.shop;
            int movie = poll.movie;
            // only add in result if this movie is rented from his belonging shop
            if (rentedMovies.getOrDefault(shop, new HashSet<>()).contains(movie)) {
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
