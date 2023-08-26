import org.example.atmcommand.ATMCommand;
import org.example.atmcommand.ATMCommandPrint;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMCommandPrintTest {

    @Test
    public void testExecuteWhenCommandArgumentsLengthIsNotZeroThenReturnError() {
        ATMCommand commandPrint = new ATMCommandPrint();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"arg1"};

        List<String> result = commandPrint.execute(cashStorage, commandArguments);

        assertEquals(1, result.size());
        assertEquals(ATMCommand.ERROR, result.get(0));
    }

    @Test
    public void testExecuteWhenCommandArgumentsLengthIsZeroAndCashStorageIsEmptyThenReturnOk() {
        ATMCommand commandPrint = new ATMCommandPrint();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {};

        List<String> result = commandPrint.execute(cashStorage, commandArguments);

        assertEquals(1, result.size());
        assertEquals(ATMCommand.OK, result.get(0));
    }

    @Test
    public void testExecuteWhenCommandArgumentsLengthIsZeroAndCashStorageHasOneCurrencyThenReturnCurrencyAndOk() {
        ATMCommand commandPrint = new ATMCommandPrint();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(10, 5);
        cashStorage.put("USD", map);
        String[] commandArguments = {};

        List<String> result = commandPrint.execute(cashStorage, commandArguments);

        assertEquals(2, result.size());
        assertEquals("USD 10 5", result.get(0));
        assertEquals(ATMCommand.OK, result.get(1));
    }
}