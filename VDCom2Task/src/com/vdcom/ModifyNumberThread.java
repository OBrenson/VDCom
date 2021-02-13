package com.vdcom;

import java.io.*;

public class ModifyNumberThread extends Thread {

    private File file;
    private Integer number = -1;
    private int maxNumber;
    private String threadName;

    public ModifyNumberThread(File file, String threadName, int maxNumber) {
        super(threadName);
        this.threadName = threadName;
        this.file = file;
        this.maxNumber = maxNumber;
    }

    @Override
    public void run() {
        try {
                while (number < maxNumber) {
                    synchronized (file) {
                        readNumber();
                        if (number < maxNumber) {
                            number++;
                            writeToConsole();
                            writeToFile();
                        }
                    }
                }
            System.out.println(threadName + " STOPED");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readNumber() throws IOException {
        try (BufferedReader buffer =
                     new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            number = Integer.parseInt(buffer.readLine());
        } catch (NumberFormatException e) {
            int a = 0;
        }
    }

    private void writeToConsole() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Thread: " + threadName + "\n");
        stringBuffer.append("Old value:" + number + "\n");
        stringBuffer.append("New value: " + number + "\n\n");
        System.out.println(stringBuffer);
    }

    private void writeToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(Integer.toString(number));
        }
    }
}
