public class PerformanceTest {
    public static long [] runPerformanceTest(int n, vEBtreePriorityQueue vEB, BinaryHeapPriorityQueue BHPQ){

        long startTimevEB = Timer.startTimer();
        for(int i = 0; i < n; i++){
            vEB.ExtractMax(vEB);
        }
        long endTimevEB = Timer.endTimer();
        long vEBtotalTime = Timer.calculateTotalTime(startTimevEB, endTimevEB);

        long startTimeBHPQ = Timer.startTimer();
        for(int i = 0; i < n; i++){
            BHPQ.ExtractMax();
        }

        long endTimeBHPQ = Timer.endTimer();
        long BHPQTotalTime = Timer.calculateTotalTime(startTimeBHPQ, endTimeBHPQ);

        return new long[] {BHPQTotalTime, vEBtotalTime};
    }

}
