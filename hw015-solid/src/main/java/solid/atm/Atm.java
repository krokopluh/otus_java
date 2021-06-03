package solid.atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Atm {

    private final HashMap<EDenomination,Cell> cells = new HashMap<>();

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


    public void giveOutMoney(Integer amount) throws Exception {

        List<EDenomination> denominationsList = EDenomination.getDescendingListOfDenominations();
        int remainder = amount;
        for (EDenomination denomination : denominationsList){
            if(cells.get(denomination).getNotesAmount()>0) {
                Integer denValue = denomination.getValue();
                if (remainder >= denValue){
                    Cell currentCell = cells.get(denomination);
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

    public void addNotes(ArrayList<Note> notes){
        for(Note note : notes){
            addNote(note);
        }
    }

    private void addNote(Note note){
        EDenomination denomination = note.getDenomination();
        cells.get(denomination).addNote();
    }

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
