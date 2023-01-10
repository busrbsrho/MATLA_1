package Ex2_2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private PriorityBlockingQueue<Integer> priorityQueue=new PriorityBlockingQueue<>();
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable){
        return new CustomFutureTask<T>(callable);
    }

    public <T> Future<T> submit(Callable<T> callable,TaskType taskType){
        Task<T> task=Task.createTask(callable,taskType);
        return this.submit(task);
    }
    public <T> Future<T> submit(Callable<T> callable){
        Task<T> task = Task.createTask(callable);
        return this.submit(task);
    }
    public <T> Future<T> submit(Task<T> task){
        priorityQueue.add(task.getTaskType().getPriorityValue());
        return super.submit(task);
    }
    @Override
    protected void beforeExecute(Thread t,Runnable r){
        super.beforeExecute(t,r);
        priorityQueue.poll();
    }

    public void gracefullyTerminate() {
        try {
            super.shutdown();
            while (!super.awaitTermination(10,TimeUnit.MILLISECONDS));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getCurrentMax() {
        if (!priorityQueue.isEmpty()) {
            return priorityQueue.peek();
        }
        return 0;
    }
}

