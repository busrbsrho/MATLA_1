# oop_Assignment_2

 #### This is the third assignment of oop , in this assignment we have implemented and used few of the design patterns that we have learnd    through the semester, such as the: Observer , Adapter , Factorial. very important design patters which are used world-wide.



## First part of the assignment.
in this part we were requierd to make a class named Ex2_1 which conatins total of 4 methods: 
createTextFiles , getNumOfLines , getNumOfLineThread and getNumOfLinesThreadPool.

1. **createTextFile (int n, int seed, int bound)** - the mehod creats n text files and generates a random number of lines for each and every one of them , then the method return an array size of n wich contains the names of the files.

2. **getNumOfLines(String [] fileNames)** - the method recive an array which contains names of files , the method will return the total amount of the lines of all the files.

3. **getNumOfLinesThread(String [] fileNames)** - the method receivs an array which contains names of files , the method will return the total amount of the lines of all the files using Threads.

4. **getNumOfLinesThreadPool(String [] fileNames)** - the method receivs an array which contains names of files, 
the method will return the total amount of the lines of all the files using ThreadPool.

 


### uml diagram of the class :
![WhatsApp Image 2023-01-10 at 20 53 23](https://user-images.githubusercontent.com/118810462/211641778-a463f5a1-e4ce-4d54-83c2-de62cfe1a156.jpeg)

#### MyThread 
a class that inheriate from the Thread class , implemented the run method as a method to count all the lines in a specified file.
used for the third mehod.   

#### MyThreadPool 
a class that implements the Callable class , modified the call() method so that it will return the amount of lines in a specified file. 
used for the forth method.


## Conclusions 
![WhatsApp Image 2023-01-10 at 20 21 19](https://user-images.githubusercontent.com/118810462/211872980-5a935c05-b58a-4180-9b7a-97200b602edf.jpeg)

we have tested both the ThreadPool and Threads methods and checked which is faster , as shown above in large scale of files the Threads method won the race , however they were super close. To our knowledge the reason that the Threads method won is because in this specific implementation the Threads didn't have to do deal with all the managment and the threads creation like the ThreadPool .



## Second part of the assignment
In this part we were requierd to give priority to tasks , unlike java which enables developers to set the priority of a thread, but not the Runnable operation it executes.
Tightly coupling the operation with the execution path that runs it creates major drawback when
using an executor such as a ThreadPoolExecutor: the collection of threads in an executor is defined by
a ThreadFactory. By default, it creates all threads with the same priority and non-daemon status.

Moreover, if we wish to execute a returning value operation, for example using the Callable<V>
interface, there are no constructors in the Thread class that get a Callable<V> as parameter and we
ought to use an Executor of some type, such as a ThreadPoolExecutor.
 

### uml diagram:

![package_2](https://user-images.githubusercontent.com/118810462/212095986-e5bb0427-0042-4e7c-b0fe-fde41da5e8f2.png)

#### TaskType 
an enum class made to define the type of the task 

#### Task 
 Represents a task object with a TaskType and a Callable operation.
 this class uses the Factory method desgin pattern. so that the only way to create an object of this type is to use
 the createtask method.
 
#### CustomExecuter 
inherites from the ThreadPool class. this class is used to desribe our own ThreadPool.Our ThreadPool contains threads with task priotity , moreover this class also always hold the task with the most priority at any given moment.
 
 #### CustomFutureTask
  an adapter desigend class . since the ThreadPool only accepts objects of the Runnable type we had to create another class that will allow us to submit Callable type object , therefore the class inheriate from the FutureTask class which on itself implemented the RunnableFutureTask class . moreover the class implimants the Compareable class so that we could compare between two tasks priority.
 
