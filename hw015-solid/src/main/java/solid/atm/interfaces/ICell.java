package solid.atm.interfaces;

public interface ICell {

    public Integer getNotesAmount();

    public Integer getMoneyAmount();

    public void addNote();

    public Integer reserveMoneyAndReturnRemainder(Integer moneyAmount);

    public void giveOutReservedNotes();

    public void releaseReservedNotes();



    }
