package org.example.atmcommand;

import java.util.List;
import java.util.Map;

public interface ATMCommand {
    String ERROR = "ERROR";
    String OK = "OK";

    List<Integer> powerOf10List = List.of(1,10, 100, 1000);
    List<Integer> fiveMulPowerOf10List = List.of(5, 50, 500, 5000);
    List<String> availableCurrencyList = List.of("USD", "CHF", "RUR", "EUR");

    List<String> execute(Map<String, Map<Integer, Integer>> cashStorage, String... commandArguments);
}
