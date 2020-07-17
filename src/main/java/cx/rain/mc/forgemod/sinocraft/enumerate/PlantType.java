package cx.rain.mc.forgemod.sinocraft.enumerate;

public enum PlantType {
    WHITE_RADISH("white_radish"),
    SUMMER_RADISH("summer_radish"),
    GREEN_RADISH("green_radish"),
    CHILI_PEPPER("chili_pepper"),
    GREEN_PEPPER("green_pepper"),
    EGGPLANT("eggplant"),
    CABBAGE("cabbage"),
    RICE("rice"),
    MILLET("millet"),
    SOYBEAN("soybean"),
    SORGHUM("sorghum");

    private String name;

    private PlantType(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }
}
