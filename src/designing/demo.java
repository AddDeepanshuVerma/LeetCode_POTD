package designing;

import java.util.Arrays;

public class demo {
    public static void main(String[] args) {
        /*String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};
        FoodRatings_4 fr = new FoodRatings_4(foods, cuisines, ratings);

        String s = fr.highestRated("korean");
        System.out.println("s = " + s);*/

        /*List<List<Integer>> tasks = List.of(List.of(1,101,8), List.of(2,102,20), List.of(3,103,5));
        TaskManager_2 taskManager = new TaskManager_2(tasks);
        taskManager.add(4,104,5); // Adds task 104 with priority 5 for User 4.
        taskManager.edit(102,9); // Updates priority of task 102 to 8.
        System.out.println(taskManager.execTop()); // return 3. Executes task 103 for User 3.
        taskManager.rmv(101); // Removes task 101 from the system.
        taskManager.add(50,101,8); // Adds task 105 with priority 15 for User 5.
        System.out.println(taskManager.execTop()); // return 5. Executes task 105 for User 5.
        System.out.println("[null,null,null,2,null,null,50]");*/

        /*List<List<Integer>> tasks = List.of(List.of(3, 12, 11), List.of(6, 2, 46), List.of(2, 1, 46), List.of(5, 23, 21));
        TaskManager_1 taskManager = new TaskManager_1(tasks);
        System.out.println(taskManager.execTop()); // return 3. Executes task 103 for User 3.*/

        /*Spreadsheet spreadsheet = new Spreadsheet(458);
        System.out.println(spreadsheet.getValue("=O126+10272"));*/

        /*Router router = new Router(4);
        System.out.println(router.addPacket(5, 3, 6));
        System.out.println(router.addPacket(2, 3, 6));
        System.out.println(Arrays.toString(router.forwardPacket()));
        System.out.println(router.getCount(3, 3, 5));*/

        int[][] entries = {{16, 4156, 1511}, {20, 8501, 8417}, {34, 7901, 7776}, {54, 6691, 9511}, {44, 8931, 8434}, {42, 9640, 5251}, {22, 4534, 9161},
                {32, 6506, 6831}, {13, 8501, 731}, {4, 7610, 8474}, {33, 820, 2341}, {17, 6490, 1161}, {29, 7120, 2703}, {8, 8723, 7613}, {38, 9544, 1804},
                {30, 8723, 1047}, {1, 5015, 7763}, {60, 1625, 2383}, {29, 3336, 3542}, {39, 7535, 6066}, {1, 9074, 9400}, {39, 1625, 7944}, {26, 9160, 6874},
                {55, 2465, 888}, {35, 8530, 6025}};
        MovieRentingSystem mrs = new MovieRentingSystem(69, entries);System.out.println("null");
        mrs.rent(32,6506); System.out.println("null");
        System.out.println(mrs.search(8501));
        System.out.println(mrs.search(6275));
        System.out.println(mrs.report());
        mrs.rent(30,8723);System.out.println("null");
        mrs.rent(8,8723);System.out.println("null");

        System.out.println(mrs.report());
        System.out.println("[[30,8723],[32,6506],[8,8723]]"); // [30,8723],[32,6506],[8,8723]
    }
}
