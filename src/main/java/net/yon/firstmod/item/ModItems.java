package net.yon.firstmod.item;


import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.item.custon.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    //ruby item
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    //cat
    public static final RegistryObject<Item> CAT = ITEMS.register("cat",
            () -> new SoundItem(Items.DIAMOND,SoundEvents.ANVIL_LAND, new Item.Properties().stacksTo(1).durability(10)));


    //ruby fragment
    public static final RegistryObject<Item> RUBY_FRAGMENT = ITEMS.register("ruby_fragment",
            () -> new Item(new Item.Properties()));

    //ruby detector
    public static final RegistryObject<Item> RUBY_DETECTOR = ITEMS.register("ruby_detector",
            () -> new RubyDetectorItem(new Item.Properties().durability(1024)));

    //cooked tropical fish
    public static final RegistryObject<Item> COOKED_TROPICAL_FISH = ITEMS.register("cooked_tropical_fish",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_TROPICAL_FISH)){
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.firstmod.cooked_tropical_fish.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });

    //amogus
    public static final RegistryObject<Item> AMOGUS = ITEMS.register("amogus",
            () -> new Item(new Item.Properties().stacksTo(1)));

    //fake pickaxe
    public static final RegistryObject<Item> FAKE_DIAMOND_PICKAXE = ITEMS.register("fake_diamond_pickaxe",
            () -> new Item(new Item.Properties().food(ModFoods.FAKE_DIAMOND_PICKAXE).stacksTo(1)));//fake pickaxe

    //coal powder
    public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_powder",
            () -> new FuelItem(new Item.Properties(), 400));

    //ruby sword
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(ModToolTiers.RUBY, 3, -1.5f, new Item.Properties()));

    //ruby pickaxe
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY, 1, -1.9f, new Item.Properties()));

    //ruby axe
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY, 5f, -2.5f, new Item.Properties()));

    //ruby shovel
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY, 1.5f, -2.2f, new Item.Properties()));

    //ruby hoe
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY, -4, 1f, new Item.Properties()));

    //ruby helmet
    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties()));

    //ruby chestplate
    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    //ruby leggings
    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    //ruby boots
    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new Item.Properties()));

    //wizard hat
    public static final RegistryObject<Item> WIZARD_HAT = ITEMS.register("wizard_hat",
            () -> new ModInvisArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.HELMET, new Item.Properties()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
