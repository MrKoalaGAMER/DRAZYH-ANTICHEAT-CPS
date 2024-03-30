@CheckInfo(
   type = CheckType.AUTO_CLICKER,
   friendlyName = "CPS",
   version = CheckVersion.RELEASE,
   logData = true,
   
)
public class CPS extends PacketCheck {
   private int ticks;
   private int cps;

   public void handle(VPacket packet, long p1) {
      if (!this.playerData.isDigging() && !this.playerData.isDiggingNew() && this.player.getGameMode() != GameMode.CREATIVE) {
         if (packet instanceof VPacketPlayInFlying) {
            if (++this.ticks >= 20) {
               this.debug("cps=" + this.cps);
               if ((double)this.cps > 16.0D && !this.playerData.isDiggingNew() && !this.playerData.isDigging()) {
                  this.handleViolation("cps=" + this.cps);
               }

               this.cps = 0;
               this.ticks = 0;
            }
         } else if (packet instanceof VPacketPlayInUseEntity && ((VPacketPlayInUseEntity)packet).getAction() == VPacketPlayInUseEntity.EntityUseAction.ATTACK) {
            ++this.cps;
         }

      }
   }
}