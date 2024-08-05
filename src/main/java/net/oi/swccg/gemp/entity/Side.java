package net.oi.swccg.gemp.entity;

public enum Side {
   D("D"),
   L("L");

   private String side;

   Side (String side) {
      this.side = side;
   }

   public String getSide() {
      return this.side;
   }
}
