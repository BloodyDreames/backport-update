package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModRecipes;
import dev.BloodyDreamsWork.backport.registry.ModTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ExplorerMapCloningRecipe extends CustomRecipe {

    public ExplorerMapCloningRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingContainer input, Level level) {
        return countIngredients(input) > 0;
    }

    @Override
    public ItemStack assemble(CraftingContainer input, net.minecraft.core.RegistryAccess registries) {
        int copies = countIngredients(input);
        if (copies <= 0) {
            return ItemStack.EMPTY;
        }
        return findMap(input).copyWithCount(copies + 1);
    }

    private static int countIngredients(CraftingContainer input) {
        int blanks = 0;
        boolean foundMap = false;

        for (int slot = 0; slot < input.getContainerSize(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.is(ModTags.Items.CLONEABLE_MAPS)) {
                if (foundMap) {
                    return 0;
                }
                foundMap = true;
            } else if (stack.is(Items.MAP)) {
                blanks++;
            } else {
                return 0;
            }
        }

        return foundMap ? blanks : 0;
    }

    private static ItemStack findMap(CraftingContainer input) {
        for (int slot = 0; slot < input.getContainerSize(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(ModTags.Items.CLONEABLE_MAPS)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.EXPLORER_MAP_CLONING.get();
    }
}
