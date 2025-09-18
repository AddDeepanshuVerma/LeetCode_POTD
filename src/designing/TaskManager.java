package designing;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

// TreeSet + record class + implemented Comparable === it is faster than custom comparator
class TaskManager {
    private final TreeSet<Task> set;                        // {userId, taskId, priority}
    private final HashMap<Integer, Integer> taskToUser;     // {taskId, userId}
    private final HashMap<Integer, Integer> taskToPriority; // {taskId, priority}

    public TaskManager(List<List<Integer>> tasks) {
        set = new TreeSet<>();
        taskToUser = new HashMap<>();
        taskToPriority = new HashMap<>();
        for (List<Integer> task : tasks) {
            Integer userId = task.get(0), taskId = task.get(1), priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        set.add(new Task(userId, taskId, priority));
        taskToUser.put(taskId, userId);
        taskToPriority.put(taskId, priority);
    }

    public void edit(int taskId, int newPriority) {
        int userId = taskToUser.get(taskId);
        int prevPriority = taskToPriority.get(taskId);

        taskToPriority.put(taskId, newPriority);
        set.remove(new Task(userId, taskId, prevPriority));
        set.add(new Task(userId, taskId, newPriority));
    }

    public void rmv(int taskId) {
        int userId = taskToUser.get(taskId);
        int priority = taskToPriority.get(taskId);

        taskToPriority.remove(taskId);
        taskToUser.remove(taskId);
        set.remove(new Task(userId, taskId, priority));
    }

    public int execTop() {
        return set.isEmpty() ? -1 : set.removeFirst().userId;
    }

    static record Task(Integer userId, Integer taskId, Integer priority) implements Comparable<Task> {

        @Override
        public int compareTo(Task o) {
            if (o == null) return 0;
            return Objects.equals(this.priority, o.priority) ? o.taskId - this.taskId : o.priority - this.priority;
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