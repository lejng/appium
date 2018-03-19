package framework.utils;

public class SmartWait {
    public static final int DEFAULT_DURATION = 1000;

    public static boolean waitFor(Condition condition, int timeout, int duration) {
        for(int time = 0; time < timeout; time += duration){
            if(condition.isSuccess()){
                return true;
            }
            sleep(duration);
        }
        return false;
    }

    public static void sleep(int duration){
        try{
            Thread.sleep(duration);
        }catch (Exception e){
            return;
        }
    }

    public interface Condition {
        boolean isSuccess();
    }
}
