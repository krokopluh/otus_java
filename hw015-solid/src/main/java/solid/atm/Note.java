package solid.atm;

import solid.atm.interfaces.INote;

public class Note implements INote {

    private final EDenomination denomination;

    public Note(EDenomination denomination) {
        this.denomination = denomination;
    }

    public EDenomination getDenomination() {
        return denomination;
    }
}
