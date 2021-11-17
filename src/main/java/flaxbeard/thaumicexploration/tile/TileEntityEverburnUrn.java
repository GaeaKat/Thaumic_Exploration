package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.ThaumicExploration;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileVisRelay;
// import flaxbeard.thaumicexploration.integration.BotaniaIntegration;

public class TileEntityEverburnUrn
    extends TileVisNode implements IFluidTank, IFluidHandler {

  private int ticks = 0;
  private int drainTicks = 0;
  public float ignisVis;
  private int dX;
  private int dY;
  private int dZ;
  private int excessTicks = 0;
  private int drainType = 0;
  private float distance = 0;
  private int range = 3;
  private int yRange = 2;
  private EntityPlayer burningPlayer;
  public static int CONVERSION_FACTOR = 250;

  @Override
  public void readCustomNBT(NBTTagCompound nbttagcompound) {
    super.readCustomNBT(nbttagcompound);
    ignisVis = nbttagcompound.getFloat("ignisVis");
  }

  @Override
  public void writeCustomNBT(NBTTagCompound nbttagcompound) {
    super.writeCustomNBT(nbttagcompound);
    nbttagcompound.setFloat("ignisVis", ignisVis);
  }

  @Override
  public FluidStack getFluid() {
    // TODO Auto-generated method stub
    return new FluidStack(FluidRegistry.LAVA,
                          (int)Math.floor(ignisVis * CONVERSION_FACTOR));
  }

  @Override
  public int getFluidAmount() {
    // TODO Auto-generated method stub
    return (int)Math.floor(ignisVis * CONVERSION_FACTOR);
  }

  @Override
  public int getCapacity() {
    // TODO Auto-generated method stub
    return 4 * CONVERSION_FACTOR;
  }

  @Override
  public FluidTankInfo getInfo() {
    // TODO Auto-generated method stub
    return new FluidTankInfo(this);
  }

  @Override
  public int fill(FluidStack resource, boolean doFill) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public FluidStack drain(int maxDrain, boolean doDrain) {
    // TODO Auto-generated method stub
    float drained = maxDrain;
    if (getFluidAmount() < drained) {
      drained = getFluidAmount();
    }
    if (doDrain) {
      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      ignisVis = ignisVis - (drained / 250);
    }
    FluidStack stack = new FluidStack(FluidRegistry.LAVA, (int)drained);
    return stack;
  }

  @Override
  public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
    // TODO Auto-generated method stub
    return this.fill(resource, doFill);
  }

  @Override
  public FluidStack drain(ForgeDirection from, FluidStack resource,
                          boolean doDrain) {
    // TODO Auto-generated method stub
    if (!resource.isFluidEqual(new FluidStack(FluidRegistry.LAVA, 1)) ||
        !(from == ForgeDirection.UP)) {
      return null;
    }
    if (doDrain)
      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    return this.drain(resource.amount, doDrain);
  }

  @Override
  public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
    // TODO Auto-generated method stub
    if (from == ForgeDirection.UP) {
      if (doDrain)
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      return this.drain(maxDrain, doDrain);
    } else {
      FluidStack stack = new FluidStack(FluidRegistry.LAVA, 0);
      return stack;
    }
  }

  @Override
  public boolean canFill(ForgeDirection from, Fluid fluid) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean canDrain(ForgeDirection from, Fluid fluid) {
    // TODO Auto-generated method stub
    return (from == ForgeDirection.UP);
  }

  @Override
  public FluidTankInfo[] getTankInfo(ForgeDirection from) {
    // TODO Auto-generated method stub
    return new FluidTankInfo[] {this.getInfo()};
  }

  @Override
  public void updateEntity() {
    super.updateEntity();
    this.ticks++;
    if (this.ticks == 10) {
      if (this.ignisVis < 16) {
        ignisVis +=
            VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord,
                                   this.zCoord, Aspect.FIRE, 1);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      }
      ticks = 0;
    }
  }

  @Override
  public int getRange() {
    return 0;
  }

  @Override
  public boolean isSource() {
    return false;
  }
}
