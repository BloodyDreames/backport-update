package dev.BloodyDreamsWork.backport.registry;

import dev.BloodyDreamsWork.backport.Backport;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModRegister<T> {

    private static final List<ModRegister<?>> ALL = new ArrayList<>();

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
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Backport.MODID, name);
        ResourceKey<T> key = ResourceKey.create(this.registry.key(), id);
        Entry<R> entry = new Entry<>(id);
        pending.add(() -> {
            @SuppressWarnings("unchecked")
            Holder.Reference<R> holder =
                    (Holder.Reference<R>) Registry.registerForHolder(this.registry, key, factory.get());
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

    public static final class Entry<T> implements Supplier<T> {

        private final ResourceLocation id;
        private Holder.Reference<T> holder;

        private Entry(ResourceLocation id) {
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

        public ResourceLocation getId() {
            return id;
        }
    }
}
