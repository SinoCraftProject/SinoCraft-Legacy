package cx.rain.mc.forgemod.culturecraft.api.interfaces;

/**
 * Cooking tool interface
 * All cooking tool must implements it
 */
public interface ICookingTool {
    /**
     * Get the type of cooking tool
     * @return the type of cooking tool
     */
    String getCookingToolType();

    /**
     * Set the type of cooking tool
     * @param type the type of cooking tool
     */
    void setCookingToolType(String type);

    /**
     * Get the level of cooking tool
     * @return The level of cooking tool
     */
    int getCookingToolLevel();

    /**
     * Set the level of cooking tool
     * @param level The level of cooking tool
     */
    void setCookingToolLevel(int level);
}
