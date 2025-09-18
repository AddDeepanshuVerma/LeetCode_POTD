package designing;

import java.util.*;

// TreeSet + custom class + comparator === here custom comparator is slower than implemented compareTo
class TaskManager_1 {
    private final TreeSet<Task2> set;                       // {userId, taskId, priority}
    private final HashMap<Integer, Integer> taskToUser;     // {taskId, userId}
    private final HashMap<Integer, Integer> taskToPriority; // {taskId, priority}

    public TaskManager_1(List<List<Integer>> tasks) {
        Comparator<Task2> comp = Comparator.comparing((Task2 t) -> t.priority, Comparator.reverseOrder())
                                           .thenComparing(t -> t.taskId, Comparator.reverseOrder());
        set = new TreeSet<>(comp);
        taskToUser = new HashMap<>();
        taskToPriority = new HashMap<>();
        for (List<Integer> task : tasks) {
            Integer userId = task.get(0), taskId = task.get(1), priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        set.add(new Task2(userId, taskId, priority));
        taskToUser.put(taskId, userId);
        taskToPriority.put(taskId, priority);
    }

    public void edit(int taskId, int newPriority) {
        int userId = taskToUser.get(taskId);
        int prevPriority = taskToPriority.get(taskId);

        taskToPriority.put(taskId, newPriority);
        set.remove(new Task2(userId, taskId, prevPriority));
        set.add(new Task2(userId, taskId, newPriority));
    }

    public void rmv(int taskId) {
        int userId = taskToUser.get(taskId);
        int priority = taskToPriority.get(taskId);

        taskToPriority.remove(taskId);
        taskToUser.remove(taskId);
        set.remove(new Task2(userId, taskId, priority));
    }

    public int execTop() {
        return set.isEmpty() ? -1 : set.removeFirst().userId;
    }

    static class Task2 {
        Integer userId;
        Integer taskId;
        Integer priority;

        public Task2(Integer userId, Integer taskId, Integer priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */