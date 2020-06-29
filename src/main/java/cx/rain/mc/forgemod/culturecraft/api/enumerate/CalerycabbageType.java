package cx.rain.mc.forgemod.culturecraft.api.enumerate;

public enum CalerycabbageType {
    CALERYCABBAGE("calery_cabbage");

    private String name;
    private CalerycabbageType(String nameIn) {
        name = nameIn;
    }
    public String getName() {
        return name;
    }

}
