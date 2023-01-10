package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread {
    String filename;
    int counter;
    public MyThread(String filename){
          this.filename=filename;
    }
    public int getCounter() {
        return this.counter;}

    @Override
    public void run() {
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
        this.counter=count;
        }
    }
    /*public Ex2_1.MyThread(String filename){
        super(filename);
    }
    private int counter;
    public void run(){
        int count=0;
        try{
            String filename = this.getName();
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            String str = br.readLine();
            while (str!= null){
                count++;
                str=br.readLine();
            }
            this.counter=count;

        }
        catch (IOException ex){
            System.out.println("error reading the file");
        }
        }
        public  int getCounter()
        {
            return counter;
        }*/


