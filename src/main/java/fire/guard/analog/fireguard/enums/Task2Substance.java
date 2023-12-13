package fire.guard.analog.fireguard.enums;

import java.util.Arrays;
import java.util.List;

public enum Task2Substance {
    GEKSAN("гексан", "C6 H10 O", 98),
    CHIKLO_GEKSAN("циклогексан", "С6 H14", 86),
    BUTILACHETAT("бутилацетат", "C2 H12 O2", 116),
    ACETON("ацетон", "C3 H6 O", 58),
    PENTAN("пентан", "С5 H12", 72),
    SPIRT("этиловый спирт", "С2 H6 O", 46),
    BENZOL("бензол", "C6 H6", 78);
    private String name;
    private String formula;
    private Integer molarMass;

    Task2Substance(String name, String formula, Integer molarMass) {
        this.name = name;
        this.formula = formula;
        this.molarMass = molarMass;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public Integer getMolarMass() {
        return molarMass;
    }

    public static List<String> getNames(){
        return Arrays.stream(Task2Substance.values())
                .map(Task2Substance::getName)
                .toList();
    }

    public static Task2Substance getByName(String name){
        return Arrays.stream(Task2Substance.values())
                .filter(val -> val.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
