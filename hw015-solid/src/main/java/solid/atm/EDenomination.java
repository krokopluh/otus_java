package solid.atm;

import java.util.Arrays;
import java.util.List;

public enum EDenomination{

    FIVE(5),
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final Integer value;

    EDenomination(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static List<EDenomination> getDescendingListOfDenominations(){

        List<EDenomination> denominationsList = Arrays.asList(EDenomination.values());
        denominationsList.sort(
                (EDenomination den1, EDenomination den2) -> den2.getValue().compareTo(den1.getValue())
        );
        return denominationsList;
    }

}
