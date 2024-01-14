package org.juheinz.scanner;

import org.juheinz.utility.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Example for an external DSL.
 */
public class StaffDslTranslator {

    final String messageHeader;

    public StaffDslTranslator(String staffName) {
        messageHeader = "Zusteller " + staffName + " meldet: ";
    }

    /**
     * Simulates inputs a delivery driver can give to his scanner device in an external DSL to communicate problems
     */
    static final String errorInvalidFormat = "Wir konnten ihre Nachricht nicht verstehen: Gefordertes Format: Kategorie.Wert.(Einheit)";
    static final String errorInvalidCategory = "Ihre Nachricht muss eine gültige Kategorie beinhalten.";

    final String errorInvalidValue = "Die eingebene Zeiteinheit oder Zeitspanne ist nicht valide.";
    static final Logger logger = new Logger("staffDSL");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");


    /**
     * Check if the message includes one of the accepted categories
     *
     * @param input staff input
     */
    public void validateMessage(String input) {
        input = input.toLowerCase();
        boolean isValid = false;
        for (StatusCategory category : StatusCategory.values()) {
            isValid = input.contains(category.toString().toLowerCase());
            if (isValid) {
                parseMessage(input, category);
                break;
            }
        }
        if (!isValid) {
            logger.log(errorInvalidCategory, "staff");
            showHelp();
        }
    }

    /**
     * Sort message according to its category
     *
     * @param input    full user input
     * @param category what category the message is concerned with
     */
    private void parseMessage(String input, StatusCategory category) {
        logger.log("Sende...", "staff");

        switch (category) {
            case StatusCategory.STAU:
                handleTrafficJamMessage(input);
                break;
            case StatusCategory.KRANKHEIT:
                handleSickMessage(input);
                break;
            case StatusCategory.SONSTIGES:
                handleMiscMessage(input);
                break;
            case StatusCategory.TANK:
                handleFuelMessage(input);
                break;
            case StatusCategory.HELP:
                showHelp();
                break;
            default:
                logger.log(errorInvalidFormat, "staff");
        }
    }

    /**
     * Split the string into its category,time unit and value
     */
    private String[] getValueAndUnit(String input) {
        String[] splitString;
        splitString = input.split("\\.");
        if (splitString.length != 3) {
            logger.log(errorInvalidFormat, "staff");
            return new String[]{"", "", ""};
        } else {
            return splitString;
        }

    }

    /**
     * Valid categories
     */
    enum StatusCategory {
        STAU, KRANKHEIT, TANK, SONSTIGES, HELP
    }

    /**
     * time units
     */
    enum TimeUnit {
        MINUTE, STUNDE, TAG, WOCHE, MONAT, INVALID,
    }

    /**
     * Pass staff message directly through to admin
     *
     * @param input message staff wants to send
     */
    private void handleMiscMessage(String input) {
        String[] split = input.split("sonstiges.");
        try {
            String message = split[1];
            logger.log("Dies wird an die Zentrale übermittelt: " + message, "staff");
            logger.log(messageHeader + message, "admin");

        } catch (IndexOutOfBoundsException e) {
            logger.log("Sie können keine leere Nachricht senden", "staff");

        }

    }


    /**
     * Calculate sick days and notify admin.
     */
    private void handleSickMessage(String input) {
        String[] message = getValueAndUnit(input);
        String inputtedAmount = message[1];
        String inputtedUnit = message[2];
        int value = getAmount(inputtedAmount);
        TimeUnit unit = getUnit(inputtedUnit);
        if (value < 1 || unit == TimeUnit.INVALID) {
            logger.log(errorInvalidValue, "staff");
        } else {
            logger.log("Gute Besserung! Die Krankmeldung wurde entgegengenommen.", "staff");
            logger.log(messageHeader + "Krankheit bis " + formatter.format(getDueDate(value, unit)), "admin");
        }


    }

    /**
     * Calculate delay and notify admin
     */
    private void handleTrafficJamMessage(String input) {
        String[] message = getValueAndUnit(input);
        String inputtedAmount = message[1];
        String inputtedUnit = message[2];
        int value = getAmount(inputtedAmount);
        TimeUnit unit = getUnit(inputtedUnit);

        if (value < 1 || unit == TimeUnit.INVALID) {
            logger.log(errorInvalidValue, "staff");
        } else {
            logger.log("Vielen dank, die Staumeldung wurde entgegengenommen!", "staff");
            logger.log(messageHeader + " Stau bis vrsl. " + formatter.format(getDueDate(value, unit)) + ".", "admin");
        }
    }

    private void handleFuelMessage(String input) {
        String[] splitInput = input.split("\\.");
        int percentageFuelLeft = getAmount(splitInput[1]);
        if (percentageFuelLeft >= 0 && percentageFuelLeft < 100) {
            int amount = (100 - percentageFuelLeft);
            logger.log(messageHeader + "Nur noch " + percentageFuelLeft + "% Tankstand vorhanden", "admin");
            logger.log("Wir sind mit " + amount + " l Benzin unterwegs zu Ihnen.", "staff");
        } else {
            logger.log("Der angegebene Füllstand ist nich valide (0 - 99)", "staff");
        }

    }

    private static TimeUnit getUnit(String input) {
        return switch (input) {
            case "min", "minute", "minuten" -> TimeUnit.MINUTE;
            case "stunde", "stunden", "h" -> TimeUnit.STUNDE;
            case "tage", "tag" -> TimeUnit.TAG;
            case "woche", "wochen" -> TimeUnit.WOCHE;
            case "monate", "mon", "monat" -> TimeUnit.MONAT;
            default -> TimeUnit.INVALID;
        };
    }

    private static LocalDateTime getDueDate(int amount, TimeUnit unit) {

        return switch (unit) {
            case TimeUnit.MINUTE -> LocalDateTime.now().plusMinutes(amount);
            case TimeUnit.STUNDE -> LocalDateTime.now().plusHours(amount);
            case TimeUnit.TAG -> LocalDateTime.now().plusDays(amount);
            case TimeUnit.WOCHE -> LocalDateTime.now().plusWeeks(amount);
            case TimeUnit.MONAT -> LocalDateTime.now().plusMonths(amount);
            case INVALID -> null;
        };
    }

    /**
     * get the integer from a string, if not possible return -1
     *
     * @return integer if in string, if not in string, -1
     */
    private static int getAmount(String amountAsString) {
        int amount;
        try {
            amount = Integer.parseInt(amountAsString);
        } catch (NumberFormatException e) {
            amount = -1;
        }
        return amount;
    }

    /**
     * Show help for this DSL tool
     */
    static void showHelp() {
        logger.log("Die folgenden Optionen sind verfügbar:", "staff");
        for (StatusCategory category : StatusCategory.values()) {
            logger.log(category.toString(), "staff");
        }
    }
}
