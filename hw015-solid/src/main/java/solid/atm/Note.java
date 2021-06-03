package solid.atm;

public class Note {

    private final EDenomination denomination;

    public Note(EDenomination denomination) {
        this.denomination = denomination;
    }

    public Integer getValue(){
        return denomination.getValue();
    }

    public EDenomination getDenomination() {
        return denomination;
    }
}
