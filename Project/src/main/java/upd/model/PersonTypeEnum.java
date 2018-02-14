package upd.model;

public enum PersonTypeEnum {

    ADMIN("Administrátor"),
    OFFICE("Kancelář"),
    ACTOR("Herec/herečka"),
    MUSICIAN("Muzikant/muzikantka"),
    USHERETTE("Uvaděčka"),
    COSTUMIER("Kostymérka"),
    TECHNICIAN("Technik"),
    LIGHTING("Osvětlovač"),
    SOUND("Zvukař"),
    PRINCIPAL("Jiří Suchý"),
    MAIN_USHERETTE("Hlavní uvaděčka");

    private String name;

    public String getName() {
        return name;
    }

    private PersonTypeEnum(String name) {
        this.name = name;
    }


}
