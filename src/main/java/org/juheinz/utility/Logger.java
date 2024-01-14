package org.juheinz.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Output of the application as logs. Formats messages from different classes for better readability.
 */
public class Logger {
    final String filePath = "./output/log-";

    private final String sender;

    public Logger(String tag) {
        this.sender = tag;
    }

    public void log(String message, String receiver) {
        String to = receiver;
        String from = sender;
        String wrapStart = "";
        String wrapEnd = "";

        switch (receiver) {
            case "meta":
                wrapStart = "====== ";
                wrapEnd = " ======";
                message = message.toUpperCase();
                to = "";
                from = "";
                receiver = "admin";
                break;
            case "user":
                from = "";
                to = "";
                wrapStart = "[USER NOTIFICATION]: ";
                break;
            case "zusteller":
                to = "";
                wrapStart = "[STAFF NOTIFICATION]: ";
                break;

        }

        String adressBlock;
        if (from.isEmpty() && to.isEmpty()) {
            adressBlock = "";
        } else {
            adressBlock = "[" + from + " > " + to + "]: ";
        }

        String output = wrapStart + adressBlock + message + wrapEnd;
        if (Objects.equals(this.sender, "staffDSL") || Objects.equals(this.sender, "staffInput")) {
            System.out.println(output);
        } else {
            writeToFile(output, receiver);
        }

    }

    /**
     * Write a log of all activities. See output folder.
     */
    public void writeToFile(String input, String receiver) {

        try (FileWriter fileWriter = new FileWriter(filePath + receiver + ".txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(LocalTime.now() + ": " + input);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
