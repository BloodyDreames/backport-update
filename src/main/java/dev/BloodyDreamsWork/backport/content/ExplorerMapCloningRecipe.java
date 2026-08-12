package dev.BloodyDreamsWork.backport.content;

import dev.BloodyDreamsWork.backport.registry.ModRecipes;
import dev.BloodyDreamsWork.backport.registry.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ExplorerMapCloningRecipe extends CustomRecipe {

    public ExplorerMapCloningRecipe() {
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return countIngredients(input) > 0;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        int copies = countIngredients(input);
        if (copies <= 0) {
            return ItemStack.EMPTY;
        }
        return findMap(input).copyWithCount(copies + 1);
    }

    private static int countIngredients(CraftingInput input) {
        int blanks = 0;
        boolean foundMap = false;

        for (int slot = 0; slot < input.size(); slot++) {
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

    private static ItemStack findMap(CraftingInput input) {
        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(ModTags.Items.CLONEABLE_MAPS)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipes.EXPLORER_MAP_CLONING.get();
    }
}
