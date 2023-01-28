package iw;

import arc.*;
import arc.struct.ObjectFloatMap;
import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.mod.*;

import static mindustry.game.EventType.*;

public class IWMod extends Mod {
    static ObjectFloatMap<Building> toastTimers = new ObjectFloatMap<>();
    static {
        Events.on(BuildDamageEvent.class, e -> {
            Log.info("building damaged");
            if (e.build.team == Vars.player.team() && toastTimers.get(e.build, Time.time) <= Time.time) {
                Vars.ui.hudfrag.showToast(Icon.warning, Core.bundle.format("iw.buildingdamaged", e.build.block.localizedName, e.build.tileX(), e.build.tileY()));
                //20 seconds message cooldown per building
                toastTimers.put(e.build, Time.time + 1200f);
            }
        });
    }

    public IWMod() {
        Events.on(BlockDestroyEvent.class, e -> {
            if (e.tile.build.team == Vars.player.team()) {
                Vars.ui.hudfrag.showToast(Icon.cancel, Core.bundle.format("iw.buildingdestroyed", e.tile.build.block.localizedName, e.tile.build.tileX(), e.tile.build.tileY()));
            }
        });
    }
}
