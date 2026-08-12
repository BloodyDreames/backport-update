package dev.BloodyDreamsWork.backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.BloodyDreamsWork.backport.content.ShelfMushroomBlock;
import dev.BloodyDreamsWork.backport.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ShelfMushroomDecorator extends TreeDecorator {

    public static final MapCodec<ShelfMushroomDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(ShelfMushroomDecorator::new, decorator -> decorator.probability);

    private final float probability;

    public ShelfMushroomDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModWorldgenTypes.SHELF_MUSHROOM_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();

        for (BlockPos logPos : context.logs()) {
            if (random.nextFloat() >= this.probability) {
                continue;
            }

            Direction facing = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos target = logPos.relative(facing);
            if (!context.isAir(target)) {
                continue;
            }

            context.setBlock(target, ModBlocks.SHELF_MUSHROOM.get().defaultBlockState()
                    .setValue(ShelfMushroomBlock.FACING, facing)
                    .setValue(ShelfMushroomBlock.AGE, random.nextInt(2)));
        }
    }
}
