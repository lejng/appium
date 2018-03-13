package framework.utils;

public class SmartWait {

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

    public enum Time {
        ONE_MINUTE(60000),
        ONE_SECONDS(1000),
        TEN_SECONDS(10000),
        FIVE_SECONDS(5000);

        private int time;
        Time(int time){
            this.time = time;
        }

        public int getTime(){
            return time;
        }
    }
}
