package org.juheinz.scanner;

import java.util.ArrayList;
import java.util.List;

public class StaffDslTranslator {

    List<String> inputs;

    /**
     * Simulates inputs a delivery driver can give to his scanner device in an external DSL to communicate problems
     */
    private void giveInputs(){
        inputs = new ArrayList<>();
        inputs.add("Adressfehler 1");
        inputs.add("Stau 6 2");
        inputs.add("Panne 7 4");
    }

}
