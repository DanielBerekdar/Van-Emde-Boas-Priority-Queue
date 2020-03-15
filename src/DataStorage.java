public class DataStorage {
    static int [] tenSquared = new int[100];
    static int [] tenCubed = new int[1000];
    static int [] tenFourthDegree = new int[10000];
    static int [] tenFifthDegree = new int[100000];
    static int [] tenSixthDegree = new int[1000000];

    public static void generateArrays(){

        for(int i = 0; i < 100; i++){
            tenSquared[i] = RandomNumberGenerator.generateRandomNumber(0, 100);
        }
        for(int i = 0; i < 1000; i++){
            tenCubed[i] = RandomNumberGenerator.generateRandomNumber(0, 100);
        }
        for(int i = 0; i < 10000; i++){
            tenFourthDegree[i] = RandomNumberGenerator.generateRandomNumber(0, 100);
        }

        for(int i = 0; i < 100000; i++){
            tenFifthDegree[i] = RandomNumberGenerator.generateRandomNumber(0, 100);
        }
        for(int i = 0; i < 1000000; i++){
            tenSixthDegree[i] = RandomNumberGenerator.generateRandomNumber(0, 100);
        }

    }
    public static void storeData(vEBtreePriorityQueue vEB, BinaryHeapPriorityQueue BHPQ, int [] arr){
        for(int integer : arr){
            vEB.Insert(vEB, integer);
            BHPQ.Insert(integer);
        }
    }
}
