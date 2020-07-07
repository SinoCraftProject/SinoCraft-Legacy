package cx.rain.mc.forgemod.culturecraft.enumerate;

public enum PepperType {
    Chili("chili"),
    Green("green");

    private String name;

    private PepperType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
}