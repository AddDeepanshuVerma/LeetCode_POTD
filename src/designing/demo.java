package designing;

public class demo {
    public static void main(String[] args) {
        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};
        FoodRatings_4 fr = new FoodRatings_4(foods, cuisines, ratings);

        String s = fr.highestRated("korean");
        System.out.println("s = " + s);
    }
}
