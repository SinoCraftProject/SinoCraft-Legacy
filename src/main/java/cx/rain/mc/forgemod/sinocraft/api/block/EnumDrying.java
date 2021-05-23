package cx.rain.mc.forgemod.sinocraft.api.block;

public enum EnumDrying {

    NO_PAPER(0, false, false, 0.00f),
    PROGRESS1(1, false, true, 0.25f),
    PROGRESS2(2, false, true, 0.50f),
    PROGRESS3(3, false, true, 0.75f),
    FINISHED(4, true, false, 1.00f)
    ;

    public final int progress;
    public final boolean isFinished, isDrying;
    public final float dryingProgress;

    EnumDrying(int progress, boolean isFinished, boolean isDrying, float dryingProgress) {
        this.progress = progress;
        this.isFinished = isFinished;
        this.isDrying = isDrying;
        this.dryingProgress = dryingProgress;
    }

    public static EnumDrying valueOf(int progress) {
        switch (progress) {
            case 1: return PROGRESS1;
            case 2: return PROGRESS2;
            case 3: return PROGRESS3;
            case 4: return FINISHED;
            default: return NO_PAPER;
        }
    }
}
