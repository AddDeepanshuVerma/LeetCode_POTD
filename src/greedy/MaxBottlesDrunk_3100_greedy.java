package greedy;

class MaxBottlesDrunk_3100_greedy {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int drunk = numBottles;
        int empty = numBottles;
        
        while (empty >= numExchange) {
            drunk++;
            empty = empty - numExchange + 1;
            numExchange++;
        }
        
        return drunk;
    }
}