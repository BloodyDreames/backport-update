package dev.BloodyDreamsWork.backport.gametest;

import dev.BloodyDreamsWork.backport.Backport;
import dev.BloodyDreamsWork.backport.content.CushionEntity;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import dev.BloodyDreamsWork.backport.registry.ModEntities;
import dev.BloodyDreamsWork.backport.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

import java.util.List;

@GameTestHolder(Backport.MODID)
@PrefixGameTestTemplate(false)
public final class CushionAndMushroomTests {

    private static final String EMPTY = "empty";

    private static final BlockPos GROUND = new BlockPos(3, 1, 3);

    private CushionAndMushroomTests() {
    }

    @GameTest(template = EMPTY, timeoutTicks = 120)
    public static void cushionStaysOnSign(GameTestHelper helper) {
        helper.setBlock(GROUND, Blocks.STONE);
        helper.setBlock(GROUND.above(), Blocks.OAK_SIGN);

        CushionEntity cushion = helper.spawn(ModEntities.CUSHION.get(), GROUND.above(2));

        helper.startSequence()
                .thenIdle(70)
                .thenExecute(() -> helper.assertTrue(cushion.isAlive(), "cushion fell off the sign"))
                .thenSucceed();
    }

    @GameTest(template = EMPTY, timeoutTicks = 120)
    public static void cushionFallsWithoutSupport(GameTestHelper helper) {
        CushionEntity cushion = helper.spawn(ModEntities.CUSHION.get(), GROUND.above(2));

        helper.startSequence()
                .thenIdle(70)
                .thenExecute(() -> helper.assertFalse(cushion.isAlive(), "cushion floats with no support"))
                .thenSucceed();
    }

    @GameTest(template = EMPTY, timeoutTicks = 120)
    public static void cushionKeepsCustomName(GameTestHelper helper) {
        helper.setBlock(GROUND, Blocks.STONE);

        Component name = Component.literal("Fluff");
        ItemStack stack = new ItemStack(ModItems.CUSHIONS.get(DyeColor.RED).get());
        stack.set(DataComponents.CUSTOM_NAME, name);

        helper.startSequence()
                .thenExecute(() -> placeOnTopFace(helper, stack, GROUND))
                .thenIdle(2)
                .thenExecute(() -> {
                    CushionEntity cushion = onlyCushion(helper);
                    helper.assertValueEqual(cushion.getCustomName(), name, "placed cushion name");
                    cushion.hurt(helper.getLevel().damageSources().source(DamageTypes.GENERIC), 1.0F);
                })
                .thenIdle(2)
                .thenExecute(() -> {
                    List<ItemEntity> drops = helper.getEntities(EntityType.ITEM, GROUND.above(), 3.0);
                    helper.assertValueEqual(drops.size(), 1, "dropped items");
                    helper.assertValueEqual(drops.get(0).getItem().get(DataComponents.CUSTOM_NAME), name,
                            "dropped item name");
                })
                .thenSucceed();
    }

    @GameTest(template = EMPTY, timeoutTicks = 200)
    public static void shelfMushroomDoesNotBounceWhileStanding(GameTestHelper helper) {
        ArmorStand stand = standDroppedOnMushroom(helper);
        double[] previous = {Double.NaN};

        helper.startSequence()
                .thenIdle(60)
                .thenExecuteFor(60, () -> {
                    double speed = stand.getDeltaMovement().y;
                    helper.assertTrue(Double.isNaN(previous[0]) || Math.abs(speed - previous[0]) < 1.0E-4,
                            "standing entity is bounced by the mushroom: speed jumped from "
                                    + previous[0] + " to " + speed);
                    previous[0] = speed;
                })
                .thenSucceed();
    }

    @GameTest(template = EMPTY, timeoutTicks = 200)
    public static void shelfMushroomBouncesOnFall(GameTestHelper helper) {
        ArmorStand stand = standDroppedOnMushroom(helper);
        boolean[] bounced = {false};

        helper.startSequence()
                .thenExecuteFor(60, () -> {
                    if (stand.getDeltaMovement().y > 0.05) {
                        bounced[0] = true;
                    }
                })
                .thenExecute(() -> helper.assertTrue(bounced[0], "falling onto the mushroom did not bounce"))
                .thenSucceed();
    }

    private static CushionEntity onlyCushion(GameTestHelper helper) {
        List<CushionEntity> cushions = helper.getEntities(ModEntities.CUSHION.get(), GROUND.above(), 3.0);
        helper.assertValueEqual(cushions.size(), 1, "cushions after placement");
        return cushions.get(0);
    }

    private static ArmorStand standDroppedOnMushroom(GameTestHelper helper) {
        BlockPos log = GROUND.above();
        BlockPos mushroom = log.north();
        helper.setBlock(GROUND, Blocks.STONE);
        helper.setBlock(log, ModBlocks.POPLAR_LOG.get());
        helper.setBlock(mushroom, ModBlocks.SHELF_MUSHROOM.get().defaultBlockState()
                .setValue(ShelfMushroomBlock.FACING, Direction.NORTH)
                .setValue(ShelfMushroomBlock.AGE, 1));

        ArmorStand stand = helper.spawn(EntityType.ARMOR_STAND, mushroom.above(3));
        stand.setNoBasePlate(true);
        return stand;
    }

    private static void placeOnTopFace(GameTestHelper helper, ItemStack stack, BlockPos pos) {
        BlockPos absolute = helper.absolutePos(pos);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);

        Vec3 hitPoint = new Vec3(absolute.getX() + 0.5, absolute.getY() + 1.0, absolute.getZ() + 0.5);
        stack.useOn(new UseOnContext(player, InteractionHand.MAIN_HAND,
                new BlockHitResult(hitPoint, Direction.UP, absolute, false)));
    }
}
