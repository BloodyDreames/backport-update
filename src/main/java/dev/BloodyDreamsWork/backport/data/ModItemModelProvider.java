package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Backport.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.POPLAR_LOG.get());
        blockItem(ModBlocks.STRIPPED_POPLAR_LOG.get());
        blockItem(ModBlocks.POPLAR_WOOD.get());
        blockItem(ModBlocks.STRIPPED_POPLAR_WOOD.get());
        blockItem(ModBlocks.POPLAR_PLANKS.get());
        blockItem(ModBlocks.POPLAR_STAIRS.get());
        blockItem(ModBlocks.RED_POPLAR_LEAVES.get());
        blockItem(ModBlocks.ORANGE_POPLAR_LEAVES.get());
        blockItem(ModBlocks.YELLOW_POPLAR_LEAVES.get());

        blockItem(ModBlocks.POPLAR_SLAB.get());
        blockItem(ModBlocks.POPLAR_FENCE_GATE.get());
        blockItem(ModBlocks.POPLAR_PRESSURE_PLATE.get());

        blockItemWithSuffix(ModBlocks.POPLAR_FENCE.get(), "_inventory");
        blockItemWithSuffix(ModBlocks.POPLAR_BUTTON.get(), "_inventory");

        blockItemWithSuffix(ModBlocks.POPLAR_TRAPDOOR.get(), "_bottom");

        basicItem(ModItems.POPLAR_DOOR.get());
        flatItem("poplar_sapling", "block/poplar_sapling");

        basicItem(ModItems.POPLAR_SIGN.get());
        basicItem(ModItems.POPLAR_HANGING_SIGN.get());
        basicItem(ModItems.POPLAR_BOAT.get());
        basicItem(ModItems.POPLAR_CHEST_BOAT.get());

        withExistingParent("shelf_mushroom",
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/shelf_mushroom_stage0"));
        flatItem("red_shrub", "block/red_shrub");

        withExistingParent("straw_bed",
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "item/straw_bed_full"));

        for (ExplorerMapType type : ExplorerMapType.values()) {
            flatItem(type.itemName(), "item/" + type.textureName());
        }

        for (DyeColor color : DyeColor.values()) {
            basicItem(ModItems.CUSHIONS.get(color).get());

            blockItem(ModBlocks.WOOL_STAIRS.get(color).get());
            blockItem(ModBlocks.WOOL_SLABS.get(color).get());
            blockItem(ModBlocks.CONCRETE_STAIRS.get(color).get());
            blockItem(ModBlocks.CONCRETE_SLABS.get(color).get());
        }
    }

    private void flatItem(String name, String texturePath) {
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(Backport.MODID, texturePath));
    }

    private void blockItem(Block block) {
        blockItemWithSuffix(block, "");
    }

    private void blockItemWithSuffix(Block block, String suffix) {
        String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
        withExistingParent(path,
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/" + path + suffix));
    }
}
