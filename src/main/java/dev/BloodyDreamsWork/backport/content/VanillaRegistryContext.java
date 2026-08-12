package dev.BloodyDreamsWork.backport.content;

public final class VanillaRegistryContext {

    private static final ThreadLocal<Boolean> BUILDING = ThreadLocal.withInitial(() -> false);

    public static void enter() {
        BUILDING.set(true);
    }

    public static void leave() {
        BUILDING.set(false);
    }

    public static boolean isBuilding() {
        return BUILDING.get();
    }

    private VanillaRegistryContext() {
    }
}
