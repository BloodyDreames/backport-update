package dev.BloodyDreamsWork.backport.data;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Backport.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        axisBlock(ModBlocks.POPLAR_LOG.get(), blockTex("poplar_log"), blockTex("poplar_log_top"));
        axisBlock(ModBlocks.STRIPPED_POPLAR_LOG.get(),
                blockTex("stripped_poplar_log"), blockTex("stripped_poplar_log_top"));
        axisBlock(ModBlocks.POPLAR_WOOD.get(), blockTex("poplar_log"), blockTex("poplar_log"));
        axisBlock(ModBlocks.STRIPPED_POPLAR_WOOD.get(),
                blockTex("stripped_poplar_log"), blockTex("stripped_poplar_log"));

        simpleBlock(ModBlocks.POPLAR_PLANKS.get());
        stairsBlock(ModBlocks.POPLAR_STAIRS.get(), blockTex("poplar_planks"));
        slabBlock(ModBlocks.POPLAR_SLAB.get(), blockTex("poplar_planks"), blockTex("poplar_planks"));
        fenceBlock(ModBlocks.POPLAR_FENCE.get(), blockTex("poplar_planks"));
        fenceGateBlock(ModBlocks.POPLAR_FENCE_GATE.get(), blockTex("poplar_planks"));
        pressurePlateBlock(ModBlocks.POPLAR_PRESSURE_PLATE.get(), blockTex("poplar_planks"));
        buttonBlock(ModBlocks.POPLAR_BUTTON.get(), blockTex("poplar_planks"));

        models().fenceInventory("poplar_fence_inventory", blockTex("poplar_planks"));
        models().buttonInventory("poplar_button_inventory", blockTex("poplar_planks"));

        doorBlockWithRenderType(ModBlocks.POPLAR_DOOR.get(),
                blockTex("poplar_door_bottom"), blockTex("poplar_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.POPLAR_TRAPDOOR.get(),
                blockTex("poplar_trapdoor"), true, "cutout");

        leavesBlock(ModBlocks.RED_POPLAR_LEAVES.get(), "red_poplar_leaves");
        leavesBlock(ModBlocks.ORANGE_POPLAR_LEAVES.get(), "orange_poplar_leaves");
        leavesBlock(ModBlocks.YELLOW_POPLAR_LEAVES.get(), "yellow_poplar_leaves");

        simpleBlock(ModBlocks.POPLAR_SAPLING.get(),
                models().cross("poplar_sapling", blockTex("poplar_sapling")).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_POPLAR_SAPLING.get(),
                models().singleTexture("potted_poplar_sapling", mcLoc("block/flower_pot_cross"),
                        "plant", blockTex("poplar_sapling")).renderType("cutout"));

        signBlock(ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get(),
                blockTex("poplar_planks"));
        hangingSignBlock(ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get(),
                blockTex("stripped_poplar_log"));

        shelfMushroom();

        simpleBlock(ModBlocks.RED_SHRUB.get(),
                models().cross("red_shrub", blockTex("red_shrub")).renderType("cutout"));

        strawBed();

        for (DyeColor color : DyeColor.values()) {
            colouredStairsAndSlabs(color, "wool",
                    ModBlocks.WOOL_STAIRS.get(color).get(), ModBlocks.WOOL_SLABS.get(color).get());
            colouredStairsAndSlabs(color, "concrete",
                    ModBlocks.CONCRETE_STAIRS.get(color).get(), ModBlocks.CONCRETE_SLABS.get(color).get());
        }
    }

    private void colouredStairsAndSlabs(DyeColor color, String base, StairBlock stairs, SlabBlock slab) {
        ResourceLocation texture = mcLoc("block/" + color.getName() + "_" + base);
        stairsBlock(stairs, texture);
        slabBlock(slab, mcLoc("block/" + color.getName() + "_" + base), texture);
    }

    private void shelfMushroom() {
        ModelFile small = models().getExistingFile(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/shelf_mushroom_stage0"));
        ModelFile large = models().getExistingFile(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/shelf_mushroom_stage1"));

        getVariantBuilder(ModBlocks.SHELF_MUSHROOM.get()).forAllStatesExcept(state -> {
            ModelFile model = state.getValue(ShelfMushroomBlock.AGE) == 0 ? small : large;
            int rotation = ((int) state.getValue(ShelfMushroomBlock.FACING).toYRot() + 180) % 360;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(rotation)
                    .build();
        }, ShelfMushroomBlock.WATERLOGGED);
    }

    private void strawBed() {
        ModelFile foot = models().getExistingFile(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/straw_bed_foot"));
        ModelFile head = models().getExistingFile(
                ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/straw_bed_head"));

        getVariantBuilder(ModBlocks.STRAW_BED.get()).forAllStatesExcept(state -> {
            ModelFile model = state.getValue(BedBlock.PART) == BedPart.HEAD ? head : foot;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY((int) state.getValue(BedBlock.FACING).toYRot())
                    .build();
        }, BedBlock.OCCUPIED);
    }

    private void leavesBlock(LeavesBlock block, String texture) {
        getVariantBuilder(block).partialState().setModels(ConfiguredModel.builder()
                .modelFile(models()
                        .singleTexture(texture, mcLoc("block/leaves"), "all", blockTex(texture))
                        .renderType("cutout_mipped"))
                .build());
    }

    private ResourceLocation blockTex(String name) {
        return ResourceLocation.fromNamespaceAndPath(Backport.MODID, "block/" + name);
    }
}
