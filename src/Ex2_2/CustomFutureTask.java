package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CustomFutureTask<T> extends FutureTask<T> implements Comparable<CustomFutureTask<T>>{
    private final int priority;

    public CustomFutureTask(Callable<T> callable) {
        super(callable);
        Task<T> task = (Task<T>) callable;
        this.priority = task.getTaskType().getPriorityValue();
    }

    public int getPriority(){
        return this.priority;
    }

    @Override
    public int compareTo(CustomFutureTask<T> o) {
        return Integer.compare(this.priority, o.getPriority());
    }
}
