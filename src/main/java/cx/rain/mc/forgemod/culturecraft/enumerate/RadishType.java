package cx.rain.mc.forgemod.culturecraft.enumerate;

public enum RadishType {
    WHITE("radish_white"),
    SUMMER("radish_summer"),
    GREEN("radish_green");

    private String name;

    private RadishType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
}
