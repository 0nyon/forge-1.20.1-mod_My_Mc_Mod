package net.yon.firstmod.item.custon;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ModInvisArmorItem extends ModArmorItem{
    public ModInvisArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public boolean hasFullSuitOfArmorOn(Player player){

        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty();
    }

    @Override
    public boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
            if(!(player.getInventory().armor.get(3).getItem() instanceof ModInvisArmorItem)) {// check if it's an armor item (not an elytra)
                return false; //if it is return that the player has wrong armor on
            }

        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem()); //get the helmet

        return helmet.getMaterial() == material; // we return true
    }

}
