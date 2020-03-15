public class Driver {
    public static void main(String[] args) {
        //Generating the data to be tested on separately, so that it wont detract from the algorithm's performance.
        DataStorage.generateArrays();
        System.out.println("Loading Data...");
        //loading the data structures with the values from the array:

        //N²:
        vEBtreePriorityQueue vEB2 = new vEBtreePriorityQueue(101);
        BinaryHeapPriorityQueue BHPQ2 = new BinaryHeapPriorityQueue(101);
        DataStorage.storeData(vEB2, BHPQ2, DataStorage.tenSquared);

        //N³:
        vEBtreePriorityQueue vEB3 = new vEBtreePriorityQueue(1001);
        BinaryHeapPriorityQueue BHPQ3 = new BinaryHeapPriorityQueue(1001);
        DataStorage.storeData(vEB3, BHPQ3, DataStorage.tenCubed);

        //N⁴:
        vEBtreePriorityQueue vEB4 = new vEBtreePriorityQueue(10001);
        BinaryHeapPriorityQueue BHPQ4 = new BinaryHeapPriorityQueue(10001);
        DataStorage.storeData(vEB4, BHPQ4, DataStorage.tenFourthDegree);

        //N⁵:
        vEBtreePriorityQueue vEB5 = new vEBtreePriorityQueue(100001);
        BinaryHeapPriorityQueue BHPQ5 = new BinaryHeapPriorityQueue(100001);
        DataStorage.storeData(vEB5, BHPQ5, DataStorage.tenFifthDegree);

        //N⁶:
        vEBtreePriorityQueue vEB6 = new vEBtreePriorityQueue(1000001);
        BinaryHeapPriorityQueue BHPQ6 = new BinaryHeapPriorityQueue(1000001);
        DataStorage.storeData(vEB6, BHPQ6, DataStorage.tenSixthDegree);

        //Executing the ExtractMaximum until the data structures are empty, and entering the results into a table:
        System.out.println();
        System.out.println("Running performance tests...");

        long [] N2Times = PerformanceTest.runPerformanceTest(100, vEB2, BHPQ2);
        long [] N3Times = PerformanceTest.runPerformanceTest(1000, vEB3, BHPQ3);
        long [] N4Times = PerformanceTest.runPerformanceTest(10000, vEB4, BHPQ4);
        long [] N5Times = PerformanceTest.runPerformanceTest(100000, vEB5, BHPQ5);
        long [] N6Times = PerformanceTest.runPerformanceTest(1000000, vEB6, BHPQ6);

        System.out.println();
        System.out.println("Performance results (nanoseconds):");
        System.out.println("                 ____________________________________________________________");
        System.out.println("                | N²         | N³        | N⁴        | N⁵        | N⁶        |");
        System.out.println(" _______________|____________|___________|___________|___________|___________|");
        System.out.println("|Binary Heap:"+"   |" + N2Times[0] +"       |" + N3Times[0] + "     |" + N4Times[0] +"    |" + N5Times[0] + "    |"+ N6Times[0] +"   |");
        System.out.println("|_______________|____________|___________|___________|___________|___________|");
        System.out.println("|vEB Tree: " +"     |" + N2Times[1] + "       |" + N3Times[1] + "     |" + N4Times[1] +"     |" + N5Times[1] + "    |"+ N6Times[1]+"    |");
        System.out.println("|_______________|____________|___________|___________|___________|___________|");
    }
}