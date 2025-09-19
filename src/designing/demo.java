package designing;

import java.util.List;

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

        Spreadsheet spreadsheet = new Spreadsheet(458);
        System.out.println(spreadsheet.getValue("=O126+10272"));
    }
}
