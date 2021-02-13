package com.vdcom;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File("out.txt");
        int max = 10000;
	    ModifyNumberThread modifyNumberThread1 = new ModifyNumberThread(file, "1 THREAD", max);
        ModifyNumberThread modifyNumberThread2 = new ModifyNumberThread(file, "2 THREAD", max);
        modifyNumberThread1.start();
        modifyNumberThread2.start();
    }
}
