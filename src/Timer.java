public class Timer {
   public static long startTimer(){
       return System.nanoTime();
   }
   public static long endTimer(){
       return System.nanoTime();
   }
   public static long calculateTotalTime(long start, long finish){
       return finish - start;
   }

}
