public class RandomNumberGenerator {
    public static int generateRandomNumber(int low, int high){
        return (int)Math.abs((Math.random()*(low-high+1)+low));
    }
}
