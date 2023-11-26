package com.chatnouille.feedcraftmod.common.recipe;

import com.chatnouille.feedcraftmod.common.FeedCraftMod;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class StuffRecipe extends SpecialCraftingRecipe
{
    public final Item item;
    private final SpecialRecipeSerializer<StuffRecipe> serializer;

    public StuffRecipe(Identifier id, CraftingRecipeCategory category, Item item, SpecialRecipeSerializer<StuffRecipe> serializer) {
        super(id, category);
        this.item = item;
        this.serializer = serializer;
        System.out.println("CREATED");
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        Item food = null;
        boolean hasItem = false;
        for (ItemStack stack : inventory.getInputStacks()) {
            if(food == null && stack.isFood() && stack.getOrCreateNbt().getCompound(FeedCraftMod.MODID).contains("scuffedItem")) {
                food = stack.getItem();
            } else if (!hasItem && stack.isOf(item)) {
                hasItem = true;
            }else {
                return true;
            }
        }
        return food != null && hasItem;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        for (ItemStack stack : inventory.getInputStacks()) {
            if(stack.isFood()) {
                ItemStack output = new ItemStack(stack.getItem());
                output.getOrCreateNbt().getCompound(FeedCraftMod.MODID).putString("scuffedItem", Registries.ITEM.getId(item).toString());
                return output;
            }
        }
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return serializer;
    }
}
