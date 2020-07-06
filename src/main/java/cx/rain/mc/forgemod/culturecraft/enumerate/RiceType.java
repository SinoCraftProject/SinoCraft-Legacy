package cx.rain.mc.forgemod.culturecraft.enumerate;

public enum RiceType {
    RICE("rice");
    private String name;

    private RiceType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
    }