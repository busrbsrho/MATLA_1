package Ex2_2;

import java.util.concurrent.TimeUnit;

public class Ex2_2 {

    public static void main(String[] args) {
        CustomExecutor customExecutor = new CustomExecutor();
        Task<Integer> task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 1; i++) {
                sum += i;
                System.out.println("hello 10");
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Task<Integer> task1 = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 1; i++) {
                sum += i;
                System.out.println("hello 1");
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        customExecutor.submit(task1);
        customExecutor.submit(task);
        customExecutor.submit(task1);
        customExecutor.submit(task);
        customExecutor.submit(task1);
        customExecutor.submit(task);
        customExecutor.submit(task1);
        customExecutor.submit(task);
        customExecutor.submit(task1);
        customExecutor.submit(task);
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        customExecutor.shutdown();
    }
}
