package cx.rain.mc.forgemod.culturecraft.enumerate;

public enum RadishType {
    White("white"),
    Summer("summer"),
    Green("green");

    private String name;

    private RadishType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
}
