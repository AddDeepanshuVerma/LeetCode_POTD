package designing;

import java.util.*;

class TaskManager_2 {
    final PriorityQueue<TaskDetails> pq;
    final HashMap<Integer, Integer> latestPriority;
    final HashMap<Integer, Integer> taskIdToUser;
    final HashSet<String> removedTaskID;

    public TaskManager_2(List<List<Integer>> tasks) {
        Comparator<TaskDetails> comp2 = Comparator.comparing((TaskDetails t) -> t.taskId, Comparator.reverseOrder())
                                                  .thenComparing(t -> t.taskId, Comparator.reverseOrder());
        pq = new PriorityQueue<TaskDetails>(comp2);
        latestPriority = new HashMap<>();
        taskIdToUser = new HashMap<>();
        removedTaskID = new HashSet<>();

        for (List<Integer> task : tasks) {
            Integer userId = task.get(0);
            Integer taskId = task.get(1);
            Integer priority = task.get(2);

            pq.offer(new TaskDetails(userId, taskId, priority));
            latestPriority.put(taskId, priority);
            taskIdToUser.put(taskId, userId);
        }

    }

    public void add(int userId, int taskId, int priority) {
        pq.offer(new TaskDetails(userId, taskId, priority));
        latestPriority.put(taskId, priority);
    }

    public void edit(int taskId, int newPriority) {
        latestPriority.put(taskId, newPriority);
        pq.offer(new TaskDetails(taskIdToUser.get(taskId), taskId, newPriority));
    }

    public void rmv(int taskId) {
        removedTaskID.add(String.valueOf(taskId + "_" + taskIdToUser.get(taskId)));
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            TaskDetails poll = pq.poll();
            Integer userId = poll.userId;
            Integer taskId = poll.taskId;
            Integer priority = poll.priority;

            if (removedTaskID.contains(String.valueOf(taskId + "_" + userId))) continue;
            if (!Objects.equals(latestPriority.get(taskId), priority)) continue;

            removedTaskID.remove(String.valueOf(taskId + "_" + userId));
            return userId;
        }
        return -1;
    }

    static record TaskDetails(Integer userId, Integer taskId, Integer priority) {

    }
}