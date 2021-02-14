package com.vdcom;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        File file = createAndFillFile();
        int max = Integer.parseInt(args[0]);
	    ModifyNumberThread modifyNumberThread1 = new ModifyNumberThread(file, "1 THREAD", max);
        ModifyNumberThread modifyNumberThread2 = new ModifyNumberThread(file, "2 THREAD", max);
        modifyNumberThread1.start();
        modifyNumberThread2.start();
    }

    private static File createAndFillFile(){
        File file = new File("out.txt");
        if(file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(0);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
