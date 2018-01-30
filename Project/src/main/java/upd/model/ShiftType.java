package upd.model;

public enum ShiftType {

    PREMIERE("Premiéra"),
    GENERAL("Veřejná generálka"),
    TOUR("Zájezd"),
    REHEARSAL("Zkouška"),
    DEFAULT("Žádný");

    private String name;

    public String getName() {
        return name;
    }

    private ShiftType(String name) {
        this.name = name;
    }
}
