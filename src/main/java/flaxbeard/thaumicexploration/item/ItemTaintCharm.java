package flaxbeard.thaumicexploration.item;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.Collection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.potions.PotionWarpWard;

public class ItemTaintCharm extends Item {

  public ItemTaintCharm() { this.maxStackSize = 1; }

  @Override
  public void onUpdate(ItemStack par1ItemStack, World par2World,
                       Entity par3Entity, int par4, boolean par5) {
    if (!par2World.isRemote) {
      if (par3Entity.ticksExisted % 20 == 0) {
        if (par3Entity instanceof EntityPlayer) {
          EntityPlayer player = (EntityPlayer)par3Entity;
          boolean removed = false;

          player.removePotionEffect(PotionFluxTaint.instance.id);
          /*
          Collection<PotionEffect> potions = player.getActivePotionEffects();

for (PotionEffect potion : potions) {
int id = potion.getPotionID();
//boolean badEffect;
//badEffect = ReflectionHelper.getPrivateValue(Potion.class,
Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K"}); if
(Potion.potionTypes[id] instanceof PotionFluxTaint) {
//badEffect = true;
player.removePotionEffect(id);
removed = true;
}

}

          if(removed) {
                  par2World.playSoundAtEntity(player, "thaumcraft:wand", 0.3F,
0.1F);
          }
          */
        }
      }
    }
  }
}
