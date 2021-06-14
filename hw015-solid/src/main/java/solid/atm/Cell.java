package solid.atm;

import solid.atm.interfaces.ICell;

public class Cell implements ICell {

    private final EDenomination denomination;

    private Integer notesAmount = 0;
    private Integer reservedNotesAmount = 0;

    public Cell(EDenomination denomination) {
        this.denomination = denomination;
    }

    @Override
    public Integer getNotesAmount() {
        return notesAmount;
    }

    @Override
    public Integer getMoneyAmount() {
        return notesAmount*denomination.getValue();
    }

    @Override
    public void addNote() {
        notesAmount += 1;
    }

    @Override
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

    @Override
    public void giveOutReservedNotes(){
        reservedNotesAmount = 0;
    }

    @Override
    public void releaseReservedNotes(){
        notesAmount = notesAmount + reservedNotesAmount;
        reservedNotesAmount = 0;
    }

}
