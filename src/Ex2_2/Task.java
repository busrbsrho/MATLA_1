package Ex2_2;

import java.util.concurrent.Callable;


public class Task<T> implements Callable<T>{
    private final TaskType taskType;
    private final Callable<T> callable;

    private Task(Callable<T> callable){
        this.callable=callable;
        this.taskType=TaskType.OTHER;
    }
    private Task(Callable<T> callable, TaskType taskType){
        this.callable=callable;
        this.taskType=taskType;
    }
    //TODO: returns callable or Task
    public static Task createTask(Callable callable,TaskType taskType) {
        return new Task(callable,taskType);
    }
    public static Task createTask(Callable callable) {
        return new Task(callable);
    }


    @Override
    public T call() throws Exception{
        return this.callable.call();
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if(!(object instanceof Task)){
            return false;
        }
        //TODO: if we need Task<t> or not
        if(((Task) object).taskType==this.taskType){
            return true;
        }
        return false;
    }

    public TaskType getTaskType() {
        return taskType;
    }
}
