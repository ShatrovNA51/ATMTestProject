import org.example.atmcommand.ATMCommand;
import org.example.atmcommand.ATMCommandAdd;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATMCommandAddTest {

    @Test
    public void testExecuteWhenValidArgumentsThenUpdateCashStorage() {
        ATMCommand command = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"USD", "10", "5"};

        List<String> result = command.execute(cashStorage, commandArguments);

        assertEquals(ATMCommand.OK, result.get(0));
        assertTrue(cashStorage.containsKey("USD"));
        assertTrue(cashStorage.get("USD").containsKey(10));
        assertEquals(5, cashStorage.get("USD").get(10));
    }

    @Test
    public void testExecuteWhenInvalidArgumentsThenReturnError() {
        ATMCommand command = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"USD", "3", "5"};

        List<String> result = command.execute(cashStorage, commandArguments);

        assertEquals(ATMCommand.ERROR, result.get(0));
    }

    @Test
    public void testExecuteWhenCurrencyNotInCashStorageThenAddCurrency() {
        ATMCommand command = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"CHF", "10", "5"};

        List<String> result = command.execute(cashStorage, commandArguments);

        assertEquals(ATMCommand.OK, result.get(0));
        assertTrue(cashStorage.containsKey("CHF"));
        assertTrue(cashStorage.get("CHF").containsKey(10));
        assertEquals(5, cashStorage.get("CHF").get(10));
    }

    @Test
    public void testExecuteWhenNumberLessThanOneThenReturnError() {
        ATMCommand command = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();
        String[] commandArguments = {"USD", "10", "0"};

        List<String> result = command.execute(cashStorage, commandArguments);

        assertEquals(ATMCommand.ERROR, result.get(0));
    }

    @Test
    public void testExecuteAddCurrencyDifferentCurrency() {
        ATMCommand command = new ATMCommandAdd();
        Map<String, Map<Integer, Integer>> cashStorage = new HashMap<>();

        command.execute(cashStorage, "CHF", "10", "5");
        command.execute(cashStorage, "CHF", "10", "5");

        command.execute(cashStorage, "USD", "10", "5");
        command.execute(cashStorage, "USD", "100", "5");

        assertTrue(cashStorage.containsKey("CHF"));
        assertTrue(cashStorage.get("CHF").containsKey(10));
        assertEquals(10, cashStorage.get("CHF").get(10));

        assertTrue(cashStorage.containsKey("USD"));
        assertTrue(cashStorage.get("USD").containsKey(10));
        assertEquals(5, cashStorage.get("USD").get(10));

        assertTrue(cashStorage.containsKey("USD"));
        assertTrue(cashStorage.get("USD").containsKey(100));
        assertEquals(5, cashStorage.get("USD").get(100));
    }

}