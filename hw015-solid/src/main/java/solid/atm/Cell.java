package solid.atm;

public class Cell {

    private final EDenomination denomination;

    private Integer notesAmount = 0;
    private Integer reservedNotesAmount = 0;

    public Cell(EDenomination denomination) {
        this.denomination = denomination;
    }

    public Integer getNotesAmount() {
        return notesAmount;
    }

    public Integer getMoneyAmount() {
        return notesAmount*denomination.getValue();
    }

    public void addNote() {
        notesAmount += 1;
    }

    public Integer reserveMoneyAndReturnRemainder(Integer moneyAmount){

        Integer currentNotesAmount = moneyAmount / denomination.getValue();
        Integer remainder = moneyAmount % denomination.getValue();

        if(notesAmount<currentNotesAmount){
            remainder = currentNotesAmount - notesAmount*denomination.getValue();
            currentNotesAmount = notesAmount;
        }

        reservedNotesAmount = currentNotesAmount;
        notesAmount -= currentNotesAmount;
        return remainder;
    }

    public void giveOutReservedNotes(){
        reservedNotesAmount = 0;
    }

    public void releaseReservedNotes(){
        notesAmount = notesAmount + reservedNotesAmount;
        reservedNotesAmount = 0;
    }

}
