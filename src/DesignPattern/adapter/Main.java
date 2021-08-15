package DesignPattern.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//通常向windowAdapter就是将所有interface里面的abstract方法用空方法实现
// 这样我们就不用每次把所有方法都实现一边，而是只需要将需要的而方法实现就行

public class Main {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("c:/test.text");
        //将inputstream装换成reader这样就能给bufferedReader做参数
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            System.out.println(line);
        }
        br.close();


    }
}
