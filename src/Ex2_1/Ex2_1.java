package Ex2_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {
    /*public static String[] createTextFiles(int n, int seed, int bound){
       String[] filenames = new String[n];
       String filepath= "C:\\ADI";
       Random rand = new Random(seed);
       for(int i=0;i<n;i++){
           int nextnumber = rand.nextInt(bound);
           File M = new File(filepath+"file_" + i+ ".txt");
           linefiller(nextnumber,M);
           filenames[i]=M.getName();
       }
    return filenames; }
    public static void  linefiller(int lines, File file) {
        try {
            FileWriter fw = new FileWriter(file.getName());
            PrintWriter outs = new PrintWriter(fw);
            for (int i=0; i<lines; i++)
            {
                outs.println("i love dogs and cats");
            }
            outs.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("error");
        }
        ExecutorService pl = Executors.newFixedThreadPool(fileNames.length);
        for (int i=0; i<fileNames.length;i++){
            Ex2_1.MyThreadspool mp = new Ex2_1.MyThreadspool(fileNames[i]);
            Future<Integer> future= pl.submit(mp);
            sumoflines+= future.get();
        }
        pl.shutdown()
    }*/
    public static String[] createTextFiles(int n, int seed, int bound){
        String[] filenames = new String[n];
        Random rand = new Random(seed);
        for(int i=0;i<n;i++){
            filenames[i] = "src\\files\\file_" + i+ ".txt";
            int nextnumber = rand.nextInt(bound);
            creatfile(filenames[i]);
            writefile(filenames[i],nextnumber );
        }
        return filenames; }
    public static void creatfile(String name){
        try {
            File myObj = new File(name);
            if (!myObj.createNewFile()) {
                // System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writefile(String name, int n){
        try {
            FileWriter myWriter = new FileWriter(name);
            for(int i=0;i<n-1; i++){
                myWriter.write("i love dogs and cats\n");
            }
            myWriter.write("i love dogs and cats");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static int getNumOfLines(String[] fileNames){
        int sumOfLines=0;
        for(int i=0; i<fileNames.length;i++){
            sumOfLines+=linescounter(fileNames[i]);
        }
        return sumOfLines;}
    public static int linescounter(String filename){
        int count=0;
        try{
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            String str = br.readLine();
            while (str!= null){
                count++;
                str=br.readLine();
            }
        }
        catch (IOException ex){
            System.out.println("error reading the file");
        }
        return count;}
    public int getNumOfLinesThreads(String[] fileNames){
        int sumoflines=0;
        MyThread[] myThreads = new MyThread[fileNames.length];
        for(int i=0; i<fileNames.length; i++){
            MyThread f=new MyThread(fileNames[i]);
            myThreads[i]=f;
            f.start();
        }
        for(int i=0;i<fileNames.length;i++){
            try {
                myThreads[i].join();
                sumoflines+=myThreads[i].getCounter();
            }
            catch (InterruptedException exception){
                System.out.println("eror");
            }
        }
        return sumoflines;}
    public int getNumOfLinesThreadPool(String[] fileNames) {
        int sumoflines=0;
        List<Future<Integer>> list=new ArrayList<Future<Integer>>();
        ExecutorService pool = Executors.newFixedThreadPool(fileNames.length);
        for(int i=0;i< fileNames.length;i++){
            Callable<Integer> callable=new MyThreadspool(fileNames[i]);
            Future<Integer> f= pool.submit(callable);
            list.add(f);
        }
        for(Future<Integer> i: list){
            try{
                sumoflines+=i.get();
            }
            catch (InterruptedException|ExecutionException e){
                e.printStackTrace();
            }
        }
        pool.shutdown();
        return sumoflines;}


    public static void main(String[] args) {
        Ex2_1 test=new Ex2_1();
        String []s= createTextFiles(5,4,100000);
        long startTime = System.currentTimeMillis();
        System.out.println("num of files: 5,000");
        System.out.print("num of lines: ");
        System.out.println(getNumOfLines(s));
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("getNumOfLines: "+totalTime/(1000F));
        startTime = System.currentTimeMillis();
        test.getNumOfLinesThreads(s);
        endTime=System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("getNumOfLinesThreads: "+totalTime/(1000F));
        startTime = System.currentTimeMillis();
        test.getNumOfLinesThreadPool(s);
        endTime=System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("getNumOfLinesThreadPool: "+totalTime/(1000F));


    }

}