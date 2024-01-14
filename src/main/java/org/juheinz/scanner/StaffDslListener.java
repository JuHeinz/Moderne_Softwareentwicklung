package org.juheinz.scanner;

import org.juheinz.utility.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Simulates deliveryperson typing in message into their scanner.
 */
public class StaffDslListener {
    public void listenToStaff(String staffName){
        Logger logger = new Logger("staffInput");
        List<String> staffMessages = getStaffMessages();

        for (String message: staffMessages){
            logger.log(staffName+ ": >>" + message + "<<","staffListener");
            StaffDslTranslator staffDslTranslator = new StaffDslTranslator(staffName);
            staffDslTranslator.validateMessage(message);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static List<String> getStaffMessages() {
        List<String> staffMessages = new ArrayList<>();
        staffMessages.add("Stau 10 min");
        staffMessages.add("Stau.10.min");
        staffMessages.add("Stau.1.stunde");
        staffMessages.add("Stau.2.STUNDEN");
        staffMessages.add("tank.10");
        staffMessages.add("tank.0");
        staffMessages.add("");
        staffMessages.add("help");
        staffMessages.add("tank.99");
        staffMessages.add("tank.100");
        staffMessages.add("tank.!o");
        staffMessages.add("sonstiges.ich will nach hause");
        staffMessages.add("sonstiges");
        staffMessages.add("krankheit.für immer");
        staffMessages.add("krankheit.für immer.und ewig");
        staffMessages.add("krankheit.12.monate");
        return staffMessages;
    }


}
