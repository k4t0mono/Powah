package owmii.powah.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import owmii.lib.util.Recipe;
import owmii.powah.Powah;
import owmii.powah.block.IBlocks;
import owmii.powah.compat.jei.energizing.EnergizingCategory;
import owmii.powah.compat.jei.magmator.MagmatorCategory;
import owmii.powah.config.Configs;
import owmii.powah.item.IItems;
import owmii.powah.recipe.Recipes;

@JeiPlugin
public class PowahJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new MagmatorCategory(helper));
        registration.addRecipeCategories(new CoolantCategory(helper));
        registration.addRecipeCategories(new SolidCoolantCategory(helper));
        registration.addRecipeCategories(new HeatSourceCategory(helper));
        registration.addRecipeCategories(new EnergizingCategory(helper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ORB), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_STARTER), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_BASIC), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_HARDENED), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_BLAZING), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_NIOTIC), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_SPIRITED), EnergizingCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.ENERGIZING_ROD_NITRO), EnergizingCategory.ID);

        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_STARTER), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_BASIC), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_HARDENED), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_BLAZING), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_NIOTIC), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_SPIRITED), MagmatorCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.MAGMATOR_NITRO), MagmatorCategory.ID);

        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_STARTER), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_BASIC), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_HARDENED), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_BLAZING), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_NIOTIC), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_SPIRITED), HeatSourceCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_NITRO), HeatSourceCategory.ID);

        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_STARTER), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_BASIC), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_HARDENED), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_BLAZING), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_NIOTIC), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_SPIRITED), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.THERMO_NITRO), CoolantCategory.ID);

        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_STARTER), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_BASIC), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_HARDENED), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_BLAZING), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_NIOTIC), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_SPIRITED), CoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_NITRO), CoolantCategory.ID);

        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_STARTER), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_BASIC), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_HARDENED), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_BLAZING), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_NIOTIC), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_SPIRITED), SolidCoolantCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(IBlocks.REACTOR_NITRO), SolidCoolantCategory.ID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(Recipe.getAll(Minecraft.getInstance().world, Recipes.ENERGIZING), EnergizingCategory.ID);
        registration.addRecipes(MagmatorCategory.Maker.getBucketRecipes(registration.getIngredientManager()), MagmatorCategory.ID);
        registration.addRecipes(CoolantCategory.Maker.getBucketRecipes(registration.getIngredientManager()), CoolantCategory.ID);
        registration.addRecipes(SolidCoolantCategory.Maker.getBucketRecipes(registration.getIngredientManager()), SolidCoolantCategory.ID);
        registration.addRecipes(HeatSourceCategory.Maker.getBucketRecipes(registration.getIngredientManager()), HeatSourceCategory.ID);

        if (Configs.GENERAL.player_aerial_pearl.get())
            registration.addIngredientInfo(new ItemStack(IItems.PLAYER_AERIAL_PEARL), VanillaTypes.ITEM, I18n.format("wiki.powah.player_aerial_pearl"));
        if (Configs.GENERAL.binding_card_dim.get())
            registration.addIngredientInfo(new ItemStack(IItems.BINDING_CARD_DIM), VanillaTypes.ITEM, I18n.format("wiki.powah.binding_card_dim"));
        if (Configs.GENERAL.lens_of_ender.get())
            registration.addIngredientInfo(new ItemStack(IItems.LENS_OF_ENDER), VanillaTypes.ITEM, I18n.format("wiki.powah.lens_of_ender"));
    }


    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Powah.MOD_ID, "main");
    }
}
