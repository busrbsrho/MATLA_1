package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class MyThreadspool implements Callable {
    private String filename;
    public MyThreadspool(String filename){
        this.filename=filename;
    }

    @Override
    public Object call() throws Exception {
        int count=0;
        try{
            String filename = this.filename;
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
        return count;
    }

}
