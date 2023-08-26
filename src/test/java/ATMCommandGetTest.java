import org.example.atmcommand.ATMCommand;
import org.example.atmcommand.ATMCommandAdd;
import org.example.atmcommand.ATMCommandGet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMCommandGetTest {

    @Test
    public void testExecuteWhenCommandArgumentsLengthNotTwoThenReturnError() {
        ATMCommand commandGet = new ATMCommandGet();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"USD"};

        List<String> result = commandGet.execute(cashStorage, commandArguments);

        assertEquals(List.of(ATMCommand.ERROR), result);
    }

    @Test
    public void testExecuteWhenCurrencyUnavailableOrAmountNonPositiveThenReturnError() {
        ATMCommand commandGet = new ATMCommandGet();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"JPY", "0"};

        List<String> result = commandGet.execute(cashStorage, commandArguments);

        assertEquals(List.of(ATMCommand.ERROR), result);
    }

    @Test
    public void testExecuteWhenSumOfBanknotesLessThanRequestedAmountThenReturnError() {
        ATMCommand commandGet = new ATMCommandGet();
        ATMCommand commandAdd = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        commandAdd.execute(cashStorage, "USD", "10", "1");
        List<String> result = commandGet.execute(cashStorage, "USD", "20");

        assertEquals(List.of(ATMCommand.ERROR), result);
    }

    @Test
    public void testExecuteWhenSumOfBanknotesGreaterThanOrEqualToRequestedAmountThenReturnBanknoteUsageAndOk() {
        ATMCommand commandGet = new ATMCommandGet();
        ATMCommand commandAdd = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        commandAdd.execute(cashStorage, "USD", "10", "3");
        List<String> result = commandGet.execute(cashStorage, "USD", "20");

        assertEquals(List.of("10 2", ATMCommand.OK), result);
    }

}