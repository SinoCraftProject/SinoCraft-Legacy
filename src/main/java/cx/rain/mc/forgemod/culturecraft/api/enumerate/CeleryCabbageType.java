package cx.rain.mc.forgemod.culturecraft.api.enumerate;

public enum CeleryCabbageType {
    CELERY_CABBAGE("celery_cabbage");

    private String name;
    private CeleryCabbageType(String nameIn) {
        name = nameIn;
    }
    public String getName() {
        return name;
    }

}
