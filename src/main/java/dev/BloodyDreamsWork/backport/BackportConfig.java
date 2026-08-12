package dev.BloodyDreamsWork.backport;

import net.minecraftforge.common.ForgeConfigSpec;

public final class BackportConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue GENERATE_DAPPLED_FOREST = BUILDER
            .comment("Generate the Dappled Forest biome in new chunks")
            .define("worldgen.dappledForest", true);

    public static final ForgeConfigSpec.BooleanValue GENERATE_ABANDONED_CAMP = BUILDER
            .comment("Generate the Abandoned Camp structure")
            .define("worldgen.abandonedCamp", true);

    public static final ForgeConfigSpec.BooleanValue WANDERING_TRADER_OFFERS = BUILDER
            .comment("Add Poplar saplings and Shelf Mushroom to wandering trader offers")
            .define("gameplay.wanderingTraderOffers", true);

    public static final ForgeConfigSpec.BooleanValue SHELF_MUSHROOM_BOUNCE = BUILDER
            .comment("Shelf Mushroom bounciness when stepped on")
            .define("gameplay.shelfMushroomBounce", true);

    public static final ForgeConfigSpec.BooleanValue ANY_MUSHROOM_STEW = BUILDER
            .comment("Allow crafting Mushroom Stew and Suspicious Stew from any two mushrooms")
            .define("gameplay.anyMushroomStew", true);

    public static final ForgeConfigSpec.BooleanValue VANILLA_BUGFIXES = BUILDER
            .comment("Apply the vanilla bug fixes introduced in 26.3")
            .comment("The full list is documented on the mod page")
            .define("fixes.vanillaBugfixes", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean generateDappledForest() {
        return !SPEC.isLoaded() || GENERATE_DAPPLED_FOREST.get();
    }

    public static boolean generateAbandonedCamp() {
        return !SPEC.isLoaded() || GENERATE_ABANDONED_CAMP.get();
    }

    public static boolean vanillaBugfixes() {
        return !SPEC.isLoaded() || VANILLA_BUGFIXES.get();
    }

    public static boolean wanderingTraderOffers() {
        return !SPEC.isLoaded() || WANDERING_TRADER_OFFERS.get();
    }

    private BackportConfig() {
    }
}
