package org.juheinz.utility;

/**
 * Output of the application as logs. Formats messages from different classes for better readability.
 */
public class Logger {

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
        System.out.println(output);
    }

}
