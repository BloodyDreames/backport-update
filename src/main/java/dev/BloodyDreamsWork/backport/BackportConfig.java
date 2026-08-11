package dev.BloodyDreamsWork.backport;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class BackportConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger("backport");
    private static final List<Flag> FLAGS = new ArrayList<>();
    private static final Properties VALUES = new Properties();
    private static boolean loaded;

    public static final Flag GENERATE_DAPPLED_FOREST = flag("worldgen.dappledForest", true,
            "Generate the Dappled Forest biome in new chunks");

    public static final Flag GENERATE_ABANDONED_CAMP = flag("worldgen.abandonedCamp", true,
            "Generate the Abandoned Camp structure");

    public static final Flag WANDERING_TRADER_OFFERS = flag("gameplay.wanderingTraderOffers", true,
            "Add Poplar saplings and Shelf Mushroom to wandering trader offers");

    public static final Flag SHELF_MUSHROOM_BOUNCE = flag("gameplay.shelfMushroomBounce", true,
            "Shelf Mushroom bounciness when stepped on");

    public static final Flag ANY_MUSHROOM_STEW = flag("gameplay.anyMushroomStew", true,
            "Allow crafting Mushroom Stew and Suspicious Stew from any two mushrooms");

    public static final Flag VANILLA_BUGFIXES = flag("fixes.vanillaBugfixes", true,
            "Apply the vanilla bug fixes introduced in 26.3");

    private static Flag flag(String key, boolean fallback, String comment) {
        Flag value = new Flag(key, fallback, comment);
        FLAGS.add(value);
        return value;
    }

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("backport.properties");
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                VALUES.load(reader);
            } catch (IOException e) {
                LOGGER.error("Failed to read {}, falling back to defaults", path, e);
            }
        }
        loaded = true;
        write(path);
    }

    private static void write(Path path) {
        StringBuilder text = new StringBuilder("# Backport feature flags\n");
        for (Flag flag : FLAGS) {
            text.append("\n# ").append(flag.comment).append('\n')
                    .append(flag.key).append(" = ").append(flag.get()).append('\n');
        }
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                writer.write(text.toString());
            }
        } catch (IOException e) {
            LOGGER.error("Failed to write {}", path, e);
        }
    }

    public static boolean generateDappledForest() {
        return GENERATE_DAPPLED_FOREST.get();
    }

    public static boolean generateAbandonedCamp() {
        return GENERATE_ABANDONED_CAMP.get();
    }

    public static boolean vanillaBugfixes() {
        return VANILLA_BUGFIXES.get();
    }

    public static boolean wanderingTraderOffers() {
        return WANDERING_TRADER_OFFERS.get();
    }

    public static final class Flag {

        private final String key;
        private final boolean fallback;
        private final String comment;

        private Flag(String key, boolean fallback, String comment) {
            this.key = key;
            this.fallback = fallback;
            this.comment = comment;
        }

        public boolean get() {
            if (!loaded) {
                return fallback;
            }
            String raw = VALUES.getProperty(key);
            if (raw == null) {
                return fallback;
            }
            return Boolean.parseBoolean(raw.trim());
        }
    }

    private BackportConfig() {
    }
}
