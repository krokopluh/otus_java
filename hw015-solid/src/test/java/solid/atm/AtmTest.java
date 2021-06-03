package solid.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtmTest {

    ArrayList<Note> notes;

    @BeforeEach
    public void populateNotesArray(){

        notes = new ArrayList<>();

        for(int i = 1; i <=3; i++){
            notes.add(new Note(EDenomination.FIFTY));
        }

        for(int i = 1; i <=5; i++){
            notes.add(new Note(EDenomination.THOUSAND));
        }

        for(int i = 1; i <=10; i++){
            notes.add(new Note(EDenomination.TWO_HUNDRED));
        }

        notes.add(new Note(EDenomination.FIVE_THOUSAND));

    }

    @Test
    public void testAddMoneyIntoAtm(){

        Atm atm = new Atm();
        atm.addNotes(notes);

        String expectedResult = "Denomination: 5000, notes amount: 1\n" +
                "Denomination: 2000, notes amount: 0\n" +
                "Denomination: 1000, notes amount: 5\n" +
                "Denomination: 500, notes amount: 0\n" +
                "Denomination: 200, notes amount: 10\n" +
                "Denomination: 100, notes amount: 0\n" +
                "Denomination: 50, notes amount: 3\n" +
                "Denomination: 10, notes amount: 0\n" +
                "Denomination: 5, notes amount: 0\n";

        assertThat(atm.outputCellsContent()).isEqualTo(expectedResult);
    }

    @Test
    public void testCurrentMoneyAmountInAtm(){

        Atm atm = new Atm();
        atm.addNotes(notes);

        assertThat(atm.getOverallMoneyAmount()).isEqualTo(12150);
    }

    @Test
    public void testGiveOutMoney() throws Exception{

        Atm atm = new Atm();
        atm.addNotes(notes);

        atm.giveOutMoney(5550);

        String expectedResult = "Denomination: 5000, notes amount: 0\n" +
                "Denomination: 2000, notes amount: 0\n" +
                "Denomination: 1000, notes amount: 5\n" +
                "Denomination: 500, notes amount: 0\n" +
                "Denomination: 200, notes amount: 8\n" +
                "Denomination: 100, notes amount: 0\n" +
                "Denomination: 50, notes amount: 0\n" +
                "Denomination: 10, notes amount: 0\n" +
                "Denomination: 5, notes amount: 0";

        assertThat(atm.outputCellsContent()).isEqualTo(expectedResult);
    }

    @Test
    public void testInabilityToGiveOutMoney() throws Exception{

        Atm atm = new Atm();
        atm.addNotes(notes);
        Exception exception = assertThrows(Exception.class, () -> {
            atm.giveOutMoney(123);
        });

        String expectedMessage = "ATM can't give out requested amount of money.";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void testInabilityToGiveOutMoney2() throws Exception{

        Atm atm = new Atm();
        atm.addNotes(notes);
        Exception exception = assertThrows(Exception.class, () -> {
            atm.giveOutMoney(100000);
        });

        String expectedMessage = "ATM can't give out requested amount of money.";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }


}
