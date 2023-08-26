package org.example.atmcommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;

public class ATMCommandAdd implements ATMCommand {

    final IntPredicate isRightBanknoteNominal = banknoteNominal -> powerOf10List.contains(banknoteNominal) || fiveMulPowerOf10List.contains(banknoteNominal);
    @Override
    public List<String> execute(Map<String, Map<Integer, Integer>> cashStorage, String... commandArguments) {
        if (commandArguments.length != 3) {
            return List.of(ATMCommand.ERROR);
        }
        String currency = commandArguments[0];
        int value = Integer.parseInt(commandArguments[1]);
        int number = Integer.parseInt(commandArguments[2]);
        if (!availableCurrencyList.contains(currency) ||
                !isRightBanknoteNominal.test(value)
                || number < 1) {
            return List.of(ATMCommand.ERROR);
        }
        cashStorage.computeIfAbsent(currency, k -> new HashMap<>())
                .merge(value, number, Integer::sum);
        return List.of(ATMCommand.OK);
    }
}
