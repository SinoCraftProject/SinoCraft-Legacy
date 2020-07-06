package cx.rain.mc.forgemod.culturecraft.enumerate;

public enum PepperType {
    CHILI("pepper_chili"),
    GREEN("pepper_green");

    private String name;
    private PepperType(String nameIn) {
        name = nameIn;
    }
    public String getName() {
        return name;
    }

}