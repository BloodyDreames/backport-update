package dev.BloodyDreamsWork.backport.data;

import com.mojang.math.Quadrant;
import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.ExplorerMapType;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected Stream<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Backport.MODID.equals(
                        block.builtInRegistryHolder().key().identifier().getNamespace()));
    }

    @Override
    protected Stream<Item> getKnownItems() {
        return BuiltInRegistries.ITEM.stream()
                .filter(item -> Backport.MODID.equals(
                        item.builtInRegistryHolder().key().identifier().getNamespace()));
    }

    @Override
    protected BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks,
                                                            ItemInfoCollector items,
                                                            SimpleModelCollector models) {
        return new BackportBlockModelGenerators(blocks, items, models);
    }

    @Override
    protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items,
                                                          SimpleModelCollector models) {
        return new BackportItemModelGenerators(items, models);
    }

    private static final class BackportBlockModelGenerators extends BlockModelGenerators {

        private BackportBlockModelGenerators(BlockStateGeneratorCollector blocks,
                                             ItemInfoCollector items,
                                             SimpleModelCollector models) {
            super(blocks, items, models);
        }

        @Override
        public void run() {
            woodProvider(ModBlocks.POPLAR_LOG.get())
                    .logWithHorizontal(ModBlocks.POPLAR_LOG.get())
                    .wood(ModBlocks.POPLAR_WOOD.get());
            woodProvider(ModBlocks.STRIPPED_POPLAR_LOG.get())
                    .logWithHorizontal(ModBlocks.STRIPPED_POPLAR_LOG.get())
                    .wood(ModBlocks.STRIPPED_POPLAR_WOOD.get());
            createHangingSign(ModBlocks.STRIPPED_POPLAR_LOG.get(),
                    ModBlocks.POPLAR_HANGING_SIGN.get(), ModBlocks.POPLAR_WALL_HANGING_SIGN.get());

            BlockFamily poplarFamily = new BlockFamily.Builder(ModBlocks.POPLAR_PLANKS.get())
                    .stairs(ModBlocks.POPLAR_STAIRS.get())
                    .slab(ModBlocks.POPLAR_SLAB.get())
                    .fence(ModBlocks.POPLAR_FENCE.get())
                    .fenceGate(ModBlocks.POPLAR_FENCE_GATE.get())
                    .pressurePlate(ModBlocks.POPLAR_PRESSURE_PLATE.get())
                    .button(ModBlocks.POPLAR_BUTTON.get())
                    .door(ModBlocks.POPLAR_DOOR.get())
                    .trapdoor(ModBlocks.POPLAR_TRAPDOOR.get())
                    .sign(ModBlocks.POPLAR_SIGN.get(), ModBlocks.POPLAR_WALL_SIGN.get())
                    .getFamily();
            family(ModBlocks.POPLAR_PLANKS.get()).generateFor(poplarFamily);

            registerDefaultBlockItem(ModBlocks.POPLAR_PLANKS.get());
            registerDefaultBlockItem(ModBlocks.POPLAR_FENCE_GATE.get());
            registerDefaultBlockItem(ModBlocks.POPLAR_PRESSURE_PLATE.get());

            createUntintedLeaves(ModBlocks.RED_POPLAR_LEAVES.get());
            createUntintedLeaves(ModBlocks.ORANGE_POPLAR_LEAVES.get());
            createUntintedLeaves(ModBlocks.YELLOW_POPLAR_LEAVES.get());

            createPlantWithDefaultItem(ModBlocks.POPLAR_SAPLING.get(),
                    ModBlocks.POTTED_POPLAR_SAPLING.get(), PlantType.NOT_TINTED);

            shelfMushroom();
            createCrossBlockWithDefaultItem(ModBlocks.RED_SHRUB.get(), PlantType.NOT_TINTED);
            strawBed();

            for (DyeColor color : DyeColor.values()) {
                colouredStairsAndSlabs(color, "wool",
                        ModBlocks.WOOL_STAIRS.get(color).get(), ModBlocks.WOOL_SLABS.get(color).get());
                colouredStairsAndSlabs(color, "concrete",
                        ModBlocks.CONCRETE_STAIRS.get(color).get(), ModBlocks.CONCRETE_SLABS.get(color).get());
            }
        }

        private void createUntintedLeaves(Block block) {
            createTrivialBlock(block, TexturedModel.LEAVES);
            registerDefaultBlockItem(block);
        }

        private void registerDefaultBlockItem(Block block) {
            registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
        }

        private void shelfMushroom() {
            Identifier small = Identifier.fromNamespaceAndPath(Backport.MODID, "block/shelf_mushroom_stage0");
            Identifier large = Identifier.fromNamespaceAndPath(Backport.MODID, "block/shelf_mushroom_stage1");

            blockStateOutput.accept(
                    MultiVariantGenerator.dispatch(ModBlocks.SHELF_MUSHROOM.get())
                            .with(PropertyDispatch.initial(ShelfMushroomBlock.AGE)
                                    .select(0, plainVariant(small))
                                    .select(1, plainVariant(large)))
                            .with(PropertyDispatch.modify(ShelfMushroomBlock.FACING)
                                    .select(Direction.NORTH, variant -> variant)
                                    .select(Direction.EAST, VariantMutator.Y_ROT.withValue(Quadrant.R90))
                                    .select(Direction.SOUTH, VariantMutator.Y_ROT.withValue(Quadrant.R180))
                                    .select(Direction.WEST, VariantMutator.Y_ROT.withValue(Quadrant.R270))));
        }

        private void strawBed() {
            Identifier foot = Identifier.fromNamespaceAndPath(Backport.MODID, "block/straw_bed_foot");
            Identifier head = Identifier.fromNamespaceAndPath(Backport.MODID, "block/straw_bed_head");

            blockStateOutput.accept(
                    MultiVariantGenerator.dispatch(ModBlocks.STRAW_BED.get())
                            .with(PropertyDispatch.initial(BedBlock.PART)
                                    .select(BedPart.HEAD, plainVariant(head))
                                    .select(BedPart.FOOT, plainVariant(foot)))
                            .with(PropertyDispatch.modify(BedBlock.FACING)
                                    .select(Direction.SOUTH, variant -> variant)
                                    .select(Direction.WEST, VariantMutator.Y_ROT.withValue(Quadrant.R90))
                                    .select(Direction.NORTH, VariantMutator.Y_ROT.withValue(Quadrant.R180))
                                    .select(Direction.EAST, VariantMutator.Y_ROT.withValue(Quadrant.R270))));
        }

        private void colouredStairsAndSlabs(DyeColor color, String base,
                                            StairBlock stairs, SlabBlock slab) {
            Block material = vanilla(color, base);
            TextureMapping textures = TextureMapping.cube(material);

            Identifier inner = ModelTemplates.STAIRS_INNER.create(stairs, textures, modelOutput);
            Identifier straight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, textures, modelOutput);
            Identifier outer = ModelTemplates.STAIRS_OUTER.create(stairs, textures, modelOutput);
            blockStateOutput.accept(createStairs(stairs,
                    plainVariant(inner), plainVariant(straight), plainVariant(outer)));
            registerSimpleItemModel(stairs, straight);

            Identifier bottom = ModelTemplates.SLAB_BOTTOM.create(slab, textures, modelOutput);
            Identifier top = ModelTemplates.SLAB_TOP.create(slab, textures, modelOutput);
            blockStateOutput.accept(createSlab(slab,
                    plainVariant(bottom),
                    plainVariant(top),
                    plainVariant(ModelLocationUtils.getModelLocation(material))));
            registerSimpleItemModel(slab, bottom);
        }
    }

    private static final class BackportItemModelGenerators extends ItemModelGenerators {

        private BackportItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
            super(items, models);
        }

        @Override
        public void run() {
            generateFlatItem(ModItems.POPLAR_BOAT.get(), ModelTemplates.FLAT_ITEM);
            generateFlatItem(ModItems.POPLAR_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);

            for (DyeColor color : DyeColor.values()) {
                generateFlatItem(ModItems.CUSHIONS.get(color).get(), ModelTemplates.FLAT_ITEM);
            }

            for (ExplorerMapType type : ExplorerMapType.values()) {
                flatItem(ModItems.EXPLORER_MAPS.get(type).get(), "item/" + type.textureName());
            }

            existingModelItem(ModItems.STRAW_BED.get(), "item/straw_bed_full");
            existingModelItem(ModItems.SHELF_MUSHROOM.get(), "block/shelf_mushroom_stage0");
        }

        private void flatItem(Item item, String texturePath) {
            Identifier modelId = ModelTemplates.FLAT_ITEM.create(
                    ModelLocationUtils.getModelLocation(item),
                    TextureMapping.layer0(new Material(
                            Identifier.fromNamespaceAndPath(Backport.MODID, texturePath))),
                    modelOutput);
            itemModelOutput.accept(item, ItemModelUtils.plainModel(modelId));
        }

        private void existingModelItem(Item item, String modelPath) {
            itemModelOutput.accept(item, ItemModelUtils.plainModel(
                    Identifier.fromNamespaceAndPath(Backport.MODID, modelPath)));
        }
    }

    private static Block vanilla(DyeColor color, String suffix) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_" + suffix));
    }
}
