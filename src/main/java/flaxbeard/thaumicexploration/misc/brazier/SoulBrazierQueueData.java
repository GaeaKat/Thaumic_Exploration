package flaxbeard.thaumicexploration.misc.brazier;

import java.util.UUID;

public class SoulBrazierQueueData {

  public final UUID aOwner;
  public final int aQueuedWarpToAdd;
  public final int aTileX;
  public final int aTileY;
  public final int aTileZ;

  public SoulBrazierQueueData(UUID aPlayer, int aWarp, int aX, int aY, int aZ) {
    aOwner = aPlayer;
    aQueuedWarpToAdd = aWarp;
    aTileX = aX;
    aTileY = aY;
    aTileZ = aZ;
  }
}
