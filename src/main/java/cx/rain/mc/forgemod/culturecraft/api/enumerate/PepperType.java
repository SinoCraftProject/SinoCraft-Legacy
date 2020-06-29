package cx.rain.mc.forgemod.culturecraft.api.enumerate;

public enum PepperType {
    CHILI("pepper_chili");

    private String name;

    private PepperType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
}