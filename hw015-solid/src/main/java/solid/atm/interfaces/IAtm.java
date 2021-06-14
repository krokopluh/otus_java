package solid.atm.interfaces;

import solid.atm.Note;

import java.util.ArrayList;

public interface IAtm {

    public void giveOutMoney(Integer amount) throws Exception;

    public Integer getOverallMoneyAmount();

    public void addNotes(ArrayList<? extends INote> notes);

}
