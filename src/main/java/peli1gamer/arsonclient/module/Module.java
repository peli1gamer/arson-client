package peli1gamer.arsonclient.module;

public interface Module {
    String id();
    String name();
    Category category();
    boolean enabled();
    void setEnabled(boolean enabled);
    default void onEnable() {}
    default void onDisable() {}
    default void onTick() {}
}
