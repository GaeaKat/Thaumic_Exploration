package flaxbeard.thaumicexploration.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IRunicArmor;

public class ItemBauble extends Item implements IBauble, IRunicArmor {
  public BaubleType baubleType;

  public ItemBauble(BaubleType t) {
    this.baubleType = t;
    this.maxStackSize = 1;
  }

  @Override
  public BaubleType getBaubleType(ItemStack arg0) {
    return baubleType;
  }

  @Override
  public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {}

  @Override
  public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {}

  @Override
  public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {}

  @Override
  public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
    return true;
  }

  @Override
  public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
    return true;
  }

  @Override
  public EnumRarity getRarity(ItemStack itemstack) {
    return EnumRarity.uncommon; //
  }

  @Override
  public int getRunicCharge(ItemStack arg0) {
    return 0;
  }
}
