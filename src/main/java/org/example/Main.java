package org.example;

import org.example.atmcommand.ATMCommand;
import org.example.atmcommand.ATMCommandAdd;
import org.example.atmcommand.ATMCommandGet;
import org.example.atmcommand.ATMCommandPrint;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, ATMCommand> commandMap = new HashMap<>();
        commandMap.put("+", new ATMCommandAdd());
        commandMap.put("-", new ATMCommandGet());
        commandMap.put("?", new ATMCommandPrint());

        ATM atm = new ATM(commandMap);

        atm.execute("?").forEach(System.out::println);
        System.out.println();

        atm.execute("+ USD 100 30").forEach(System.out::println);
        System.out.println();

        atm.execute("+ RUR 250 10").forEach(System.out::println);
        System.out.println();

        atm.execute("+ CHF 100 5").forEach(System.out::println);
        System.out.println();

        atm.execute("+ USD 10 50").forEach(System.out::println);
        System.out.println();

        atm.execute("?").forEach(System.out::println);
        System.out.println();

        atm.execute("- USD 120").forEach(System.out::println);
        System.out.println();

        atm.execute("- RUR 500").forEach(System.out::println);
        System.out.println();

        atm.execute("- CHF 250").forEach(System.out::println);
        System.out.println();

        atm.execute("?").forEach(System.out::println);
        System.out.println();

        atm.execute("+ eur 100 5").forEach(System.out::println);
        System.out.println();

        atm.execute("- CHF 400").forEach(System.out::println);
        System.out.println();

        atm.execute("+ CHF 10 50").forEach(System.out::println);
        System.out.println();

        atm.execute("?").forEach(System.out::println);
        System.out.println();

    }
}