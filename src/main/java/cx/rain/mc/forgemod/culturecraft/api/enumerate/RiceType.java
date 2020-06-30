package cx.rain.mc.forgemod.culturecraft.api.enumerate;

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