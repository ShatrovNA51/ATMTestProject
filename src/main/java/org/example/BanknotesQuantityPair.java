package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BanknotesQuantityPair {
    private int banknoteNominal;

    private int quantity;
}
