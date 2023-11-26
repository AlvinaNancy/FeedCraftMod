package com.chatnouille.feedcraftmod.common.init;

import com.chatnouille.feedcraftmod.common.FeedCraftMod;
import com.chatnouille.feedcraftmod.common.recipe.StuffRecipe;
import com.chatnouille.feedcraftmod.common.recipe.TestRecipe;
import net.minecraft.item.Items;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes
{
    public static SpecialRecipeSerializer<StuffRecipe> STUFF_TNT;
    public static SpecialRecipeSerializer<TestRecipe> TEST;

    public static <T extends SpecialCraftingRecipe> SpecialRecipeSerializer<T> registerStuff(String name, SpecialRecipeSerializer.Factory<T> factory) {
        System.out.println("REGISTERING");
        return Registry.register(Registries.RECIPE_SERIALIZER, FeedCraftMod.id(name), new SpecialRecipeSerializer<>(factory));
    }

    public static void register() {
        STUFF_TNT = registerStuff("stuff_tnt", (id, c) -> new StuffRecipe(id, c, Items.TNT, STUFF_TNT));
        TEST = registerStuff("test", TestRecipe::new);
    }
}
