package org.example;

import org.example.atmcommand.ATMCommand;

import java.util.*;

public class ATM {

    private final Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();

    private final Map<String, ATMCommand> atmCommands;

    public ATM(Map<String, ATMCommand> atmCommands) {
        this.atmCommands = atmCommands;
    }

    public List<String> execute(String commandArgs) {
        String[] commandArray = commandArgs.split(" ");
        if (commandArray.length < 1) {
            return Collections.singletonList(ATMCommand.ERROR);
        }
        if (atmCommands.containsKey(commandArray[0])) {
            if (commandArray.length == 1) {
                return atmCommands.get(commandArray[0]).execute(cashStorage);
            } else {
                return atmCommands.get(commandArray[0]).execute(cashStorage, Arrays.copyOfRange(commandArray, 1, commandArray.length));
            }
        } else {
            return Collections.singletonList(ATMCommand.ERROR);
        }
    }


}
