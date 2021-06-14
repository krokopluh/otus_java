package solid.atm;

import solid.atm.interfaces.IAtm;
import solid.atm.interfaces.ICell;
import solid.atm.interfaces.INote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Atm implements IAtm {

    private final HashMap<EDenomination,ICell> cells = new HashMap<>();

    public Atm(){
        for(EDenomination denomination: EDenomination.values()){
            addCell(denomination);
        }
    }

    private void giveOutReservedNotes(){
        for(EDenomination denomination : cells.keySet()){
            cells.get(denomination).giveOutReservedNotes();
        }
    }

    private void releaseReservedNotes(){
        for(EDenomination denomination : cells.keySet()){
            cells.get(denomination).releaseReservedNotes();
        }
    }

    @Override
    public void giveOutMoney(Integer amount) throws Exception {

        List<EDenomination> denominationsList = EDenomination.getDescendingListOfDenominations();
        int remainder = amount;
        for (EDenomination denomination : denominationsList){
            if(cells.get(denomination).getNotesAmount()>0) {
                Integer denValue = denomination.getValue();
                if (remainder >= denValue){
                    ICell currentCell = cells.get(denomination);
                    remainder = currentCell.reserveMoneyAndReturnRemainder(remainder);
                    if (remainder==0){
                        giveOutReservedNotes();
                        return;
                    }
                }
            }
        }
        releaseReservedNotes();
        throw new Exception("ATM can't give out requested amount of money.");
    }

    public void addCell(EDenomination denomination){
        this.cells.put(denomination,new Cell(denomination));
    }

    @Override
    public void addNotes(ArrayList<? extends INote> notes){
        for(INote note : notes){
            addNote(note);
        }
    }

    private void addNote(INote note){
        EDenomination denomination = note.getDenomination();
        cells.get(denomination).addNote();
    }

    @Override
    public Integer getOverallMoneyAmount(){

        Integer moneyAmount = 0;
        for (EDenomination denomination : cells.keySet()){
            moneyAmount += cells.get(denomination).getMoneyAmount();
        }
        return moneyAmount;
    }

    public String outputCellsContent(){

        StringBuilder stringBuilder = new StringBuilder();

        for (EDenomination denomination : EDenomination.getDescendingListOfDenominations()){
            stringBuilder.append(String.format("Denomination: %s, notes amount: %s\n", denomination.getValue(),cells.get(denomination).getNotesAmount()));
        }
        return stringBuilder.toString();
    }


}
