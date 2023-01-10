package Ex2_2;

import org.junit.Test;

import java.util.concurrent.Callable;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TaskTest {
    @Test
    public void createTask(){
        TaskType taskType=TaskType.IO;
        Callable<Integer> callable=()->{
            return 5;
        };
       Task<Integer> task=Task.createTask(callable,taskType);
       assertEquals(task.getTaskType(),TaskType.IO);
    }
    @Test
    public void equals(){
        TaskType taskType=TaskType.IO;
        Callable<Integer> callable=()->{
            return 5;
        };
        TaskType taskType1=TaskType.OTHER;
        Callable<Integer> callable1=()->{
            return 5;
        };
        Task<Integer> task=Task.createTask(callable,taskType);
        Task<Integer> task1=Task.createTask(callable1,taskType1);
        assertNotEquals(taskType, taskType1);
        assertFalse(task.equals(task1));
    }
    @Test
    public void getTaskType(){

    }

}
