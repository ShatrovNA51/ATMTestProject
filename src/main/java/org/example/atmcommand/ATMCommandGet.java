package org.example.atmcommand;

import org.example.BanknotesQuantityPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ATMCommandGet implements ATMCommand {

    //Map<Integer, Integer> должна быть мутабельной
    @Override
    public List<String> execute(Map<String, Map<Integer, Integer>> cashStorage, String... commandArguments) {

        if (commandArguments.length != 2) {
            return List.of(ATMCommand.ERROR);
        }
        String currency = commandArguments[0];
        int amount = Integer.parseInt(commandArguments[1]);
        if (!availableCurrencyList.contains(currency)
                || amount <= 0) {
            return List.of(ATMCommand.ERROR);
        }

        int sum = 0;
        Map<Integer, Integer> cash = cashStorage.get(currency);
        if (cash != null) {
            for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
                int banknoteNominal = entry.getKey();
                int banknoteQuantity = entry.getValue();
                sum += banknoteNominal * banknoteQuantity;
            }
        }

        if (sum >= amount) {
            int remainingAmount = amount;
            List<BanknotesQuantityPair> usedBanknotes = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
                int banknoteNominal = entry.getKey();
                int banknoteQuantity = entry.getValue();

                int banknoteCount = Math.min(remainingAmount / banknoteNominal, banknoteQuantity);

                remainingAmount -= banknoteNominal * banknoteCount;

                if (banknoteCount > 0) {
                    usedBanknotes.add(new BanknotesQuantityPair(banknoteNominal, banknoteCount));
                }

                if (remainingAmount == 0) {
                    break;
                }
            }

            if (remainingAmount == 0) {
                List<String> returnList = new ArrayList<>(usedBanknotes.stream()
                        .map(banknoteUsage -> {
                            int banknoteCount = cash.get(banknoteUsage.getBanknoteNominal());
                            cash.put(banknoteUsage.getBanknoteNominal(), banknoteCount - banknoteUsage.getQuantity());
                            return String.format("%d %d", banknoteUsage.getBanknoteNominal(), banknoteUsage.getQuantity());
                        })
                        .toList());
                returnList.add(ATMCommand.OK);
                return returnList;
            } else {
                return List.of(ATMCommand.ERROR);
            }
        } else {
            return List.of(ATMCommand.ERROR);
        }

    }
}
