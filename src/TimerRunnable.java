public class TimerRunnable implements Runnable{

    static int day = 0;

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(1000);
                day++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(day);
        }


    }
}
