package org.example.atmcommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ATMCommandPrint implements ATMCommand {
    @Override
    public List<String> execute(Map<String, Map<Integer, Integer>> cashStorage, String... commandArguments) {
        if (commandArguments.length != 0) {
            return List.of(ATMCommand.ERROR);
        }
        List<String> returnList = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, Integer>> entry : cashStorage.entrySet()) {
            String currency = entry.getKey();
            for (Map.Entry<Integer, Integer> pair : entry.getValue().entrySet()) {
                returnList.add(String.format("%s %d %d", currency, pair.getKey(), pair.getValue()));
            }
        }
        returnList.add(ATMCommand.OK);
        return returnList;
    }
}
