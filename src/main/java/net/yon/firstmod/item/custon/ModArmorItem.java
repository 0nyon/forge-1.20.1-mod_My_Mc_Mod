package net.yon.firstmod.item.custon;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yon.firstmod.item.ModArmorMaterials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {


    // this map maps an armor material to an effect, if you want to have multiple effects after the last .put(WHATEVER PARAMS) add .put(WHATEVER PARAMS)
    private static final Map<ArmorMaterial, MobEffectInstance[]> MATERIAL_TO_EFFECT_MAP = // like down here
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance[]>()) //keep in mind that this is and array, so between each MobEffectInstance remember tto add ","
                    .put(ModArmorMaterials.RUBY /*<-- maps this armor material*/,
                            /*to these effects*/ new MobEffectInstance[]{new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1,
                            false, false, true),
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2,
                            false, false, true)}) // <-- first put method ended
                    // if you want to add armor effects to another armor material, add ".put" and do the same thing done in the first "put" method,
                    // just put the Armor material first and then an array of whatever effects you want
                    .put(ModArmorMaterials.INVIS,
                            new MobEffectInstance[]{new MobEffectInstance(MobEffects.INVISIBILITY, 200, 1,
                                    false, false, true)})
                    .build();

    @Override // called every time that a ModArmorItem with the materials mentioned in class is inside your armor slot
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide()){ //if we on server side
            if(hasFullSuitOfArmorOn(player)){ //and player has full suit of armor
                evaluateArmorEffects(player); // if the player has the correct armor on, add the effects
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance[]> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) { //for each entry of the Material to effect map
            ArmorMaterial mapArmorMaterial = entry.getKey(); //get the "key" which is the armor material
            MobEffectInstance[] mapStatusEffect = entry.getValue(); // get the "value" which is the effect linked with the armor material

            if(hasCorrectArmorOn(mapArmorMaterial, player)) { // if we have the correct armor on
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect); // add the effects to the player
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance[] mapStatusEffects) {
        boolean hasPlayerEffects = false;
        List<MobEffectInstance> effectsPlayerDoesntHave = new ArrayList<MobEffectInstance>(mapStatusEffects.length);
        for(int i=0; i<mapStatusEffects.length; i++) {
            //hasPlayerEffects = player.hasEffect(mapStatusEffect[i].getEffect()); //check if the effect given to us in the parameters is active on the player
            if(!player.hasEffect(mapStatusEffects[i].getEffect()) || player.getEffect(mapStatusEffects[i].getEffect()).getDuration() <= 200) {
               effectsPlayerDoesntHave.add(mapStatusEffects[i]);
                //hasPlayerEffects = false;
            }
        }

        if(hasCorrectArmorOn(mapArmorMaterial, player)) { //if we have the correct armor on
            for (int i=0; i<effectsPlayerDoesntHave.size(); i++) {
                player.addEffect(new MobEffectInstance(effectsPlayerDoesntHave.get(i))); //add the effects to the player
            }
        }
    }

    //return true if all armor slots aren't empty (armor slots can have a lot of different items in them)
    public boolean hasFullSuitOfArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !breastplate.isEmpty() && !helmet.isEmpty();
    }

    public boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) { //for each armor slot,
            if(!(armorStack.getItem() instanceof ArmorItem)) {// check if it's an armor item (not an elytra)
                return false; //if it is return that the player has wrong armor on
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem()); //get the boots
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem()); //get the leggings
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem()); //get the breastplate
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem()); //get the helmet

        return helmet.getMaterial() == material && breastplate.getMaterial() == material && //if all armor that the player is wearing has the correct material (the correct mat is the one param we entered)
                leggings.getMaterial() == material && boots.getMaterial() == material; // we return true
    }


    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
}
