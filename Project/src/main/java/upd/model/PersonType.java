package upd.model;

public enum PersonType {
    ADMIN("Administrátor"),
    OFFICE("Kancelář"),
    ACTOR("Herec/herečka"),
    MUSICIAN("Muzikant/muzikantka"),
    USHERETTE("Uvaděčka"),
    COSTUMIER("Kostymérka"),
    TECHNICIAN("Technik"),
    LIGHTING("Osvětlovač"),
    SOUND("Zvukař");

    private String name;

    public String getName() {
        return name;
    }

    private PersonType(String name) {
        this.name = name;
    }
}
