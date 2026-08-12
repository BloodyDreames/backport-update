package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModRegister<T> {

    private static final List<ModRegister<?>> ALL = new ArrayList<>();
    private static final ThreadLocal<ResourceKey<Block>> CURRENT_BLOCK_KEY = new ThreadLocal<>();
    private static final ThreadLocal<ResourceKey<Item>> CURRENT_ITEM_KEY = new ThreadLocal<>();

    private final Registry<T> registry;
    private final List<Runnable> pending = new ArrayList<>();

    private ModRegister(Registry<T> registry) {
        this.registry = registry;
    }

    public static <T> ModRegister<T> create(Registry<T> registry) {
        ModRegister<T> register = new ModRegister<>(registry);
        ALL.add(register);
        return register;
    }

    public <R extends T> Entry<R> register(String name, Supplier<R> factory) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport.MODID, name);
        ResourceKey<T> key = ResourceKey.create(this.registry.key(), id);
        Entry<R> entry = new Entry<>(id);
        pending.add(() -> {
            @SuppressWarnings("unchecked")
            Holder.Reference<R> holder;
            if (this.registry == BuiltInRegistries.BLOCK) {
                @SuppressWarnings("unchecked")
                ResourceKey<Block> blockKey = (ResourceKey<Block>) (ResourceKey<?>) key;
                CURRENT_BLOCK_KEY.set(blockKey);
                try {
                    holder = (Holder.Reference<R>) Registry.registerForHolder(this.registry, key, factory.get());
                } finally {
                    CURRENT_BLOCK_KEY.remove();
                }
            } else if (this.registry == BuiltInRegistries.ITEM) {
                @SuppressWarnings("unchecked")
                ResourceKey<Item> itemKey = (ResourceKey<Item>) (ResourceKey<?>) key;
                CURRENT_ITEM_KEY.set(itemKey);
                try {
                    holder = (Holder.Reference<R>) Registry.registerForHolder(this.registry, key, factory.get());
                } finally {
                    CURRENT_ITEM_KEY.remove();
                }
            } else {
                holder = (Holder.Reference<R>) Registry.registerForHolder(this.registry, key, factory.get());
            }
            entry.bind(holder);
        });
        return entry;
    }

    public static void registerAll() {
        for (ModRegister<?> register : ALL) {
            for (Runnable action : register.pending) {
                action.run();
            }
            register.pending.clear();
        }
    }

    public static ResourceKey<Block> currentBlockKey() {
        return CURRENT_BLOCK_KEY.get();
    }

    public static ResourceKey<Item> currentItemKey() {
        return CURRENT_ITEM_KEY.get();
    }

    public static final class Entry<T> implements Supplier<T> {

        private final Identifier id;
        private Holder.Reference<T> holder;

        private Entry(Identifier id) {
            this.id = id;
        }

        private void bind(Holder.Reference<T> holder) {
            this.holder = holder;
        }

        @Override
        public T get() {
            return delegate().value();
        }

        public Holder<T> getDelegate() {
            return delegate();
        }

        private Holder.Reference<T> delegate() {
            if (holder == null) {
                throw new IllegalStateException("Object " + id + " is not registered yet");
            }
            return holder;
        }

        public Identifier getId() {
            return id;
        }
    }
}
