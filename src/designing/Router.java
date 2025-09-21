package designing;

import java.util.*;

class Router {

    private final int memoryLimit;
    private final LinkedHashSet<Packet> que;
    private final HashMap<Integer, List<Integer>> destinationToListOfTime;
    private final HashMap<Integer, Integer> destinationLowestIndex;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;

        this.que = new LinkedHashSet<>();
        this.destinationToListOfTime = new HashMap<>();
        this.destinationLowestIndex = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (que.contains(packet)) return false; // check the duplicacy

        if (que.size() >= memoryLimit) forwardPacket(); // if reached memory limit apply FIFO
        que.add(packet); // add the new packet in queue

        destinationToListOfTime.computeIfAbsent(destination, list -> new ArrayList<>())
                               .add(timestamp); // add the new Timestamp for destination
        return true;
    }

    public int[] forwardPacket() {
        if (que.isEmpty()) return new int[]{}; // que is empty return empty array
        Packet removedPacket = que.removeFirst(); // remove as per FIFO
        // as timeStamps are in increasing order hence the removed one will be the first one in the destinationToListOfTime's list hence increase the searching index
        destinationLowestIndex.merge(removedPacket.destination, 1, Integer::sum);
        return removedPacket.getArray();
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> list = destinationToListOfTime.getOrDefault(destination, Collections.emptyList());
        int startIndex = destinationLowestIndex.getOrDefault(destination, 0);
        if (list.isEmpty() || list.size() == startIndex) return 0;
        int lower = getLowerBoundIndex(list, startIndex, startTime, list.size());
        int higher = getHigherBoundIndex(list, startIndex, endTime, list.size());

        return higher - lower + 1;
    }

    private int getLowerBoundIndex(List<Integer> list, int start, int startTime, int size) {
        int ans = size;
        int end = size - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (list.get(mid) >= startTime) {
                ans = mid;
                end = mid - 1;
            } else start = mid + 1;
        }
        return ans;
    }

    private int getHigherBoundIndex(List<Integer> list, int start, int endTime, int size) {
        int ans = start - 1;
        int end = size - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (list.get(mid) <= endTime) {
                ans = mid;
                start = mid + 1;
            } else end = mid - 1;
        }
        return ans;
    }

    record Packet(int source, int destination, int timestamp) {

        int[] getArray() {
            return new int[]{source, destination, timestamp};
        }
    }
}