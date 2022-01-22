package flaxbeard.thaumicexploration.item;

import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class ItemTaintSeedFood extends ItemSeedFood implements IPlantable {
  /** Block ID of the crop this seed food should place. */
  private int cropId;

  /** Block ID of the soil this seed food should be planted on. */
  private int soilId;

  public ItemTaintSeedFood(int par2, float par3, Block par4, Block par5) {
    super(par2, par3, par4, par5);
  }

  /**
   * Callback for item usage. If the item does something special on right
   * clicking, he will have one of those. Return True if something happen and
   * false if it don't. This is for ITEMS, not BLOCKS
   */
  @Override
  public boolean onItemUse(ItemStack par1ItemStack,
                           EntityPlayer par2EntityPlayer, World par3World,
                           int par4, int par5, int par6, int par7, float par8,
                           float par9, float par10) {
    if (par7 != 1) {
      return false;
    } else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
                                              par1ItemStack) &&
               par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7,
                                              par1ItemStack)) {

      Block soil = par3World.getBlock(par4, par5, par6);

      if (soil != null &&
          ((soil == Blocks.grass &&
            par3World.getBiomeGenForCoords(par4, par6) ==
                ThaumcraftWorldGenerator.biomeTaint) ||
           (soil == ConfigBlocks.blockTaint &&
            par3World.getBlockMetadata(par4, par5, par6) == 1)) &&
          par3World.isAirBlock(par4, par5 + 1, par6)) {
        par3World.setBlock(par4, par5 + 1, par6,
                           ThaumicExploration.taintBerryCrop);
        --par1ItemStack.stackSize;
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}