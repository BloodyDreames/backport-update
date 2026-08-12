package dev.BloodyDreamsWork.backport.content;

import net.minecraft.world.item.MapItem;

public class ExplorerMapItem extends MapItem {

    private final ExplorerMapType type;

    public ExplorerMapItem(ExplorerMapType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public ExplorerMapType type() {
        return this.type;
    }
}
