package iw;

import arc.*;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.mod.*;

public class IWMod extends Mod {

    public IWMod() {
        Events.on(EventType.BuildDamageEvent.class, e -> {
            if (e.build.team == Vars.player.team() && !e.build.wasRecentlyDamaged()) {
                Vars.ui.hudfrag.showToast(Icon.warning, Core.bundle.format("iw.buildingdamaged", e.build.block.localizedName, e.build.tileX(), e.build.tileY()));
            }
        });
        Events.on(EventType.BlockDestroyEvent.class, e -> {
            Vars.ui.hudfrag.showToast(Icon.cancel, Core.bundle.format("iw.buildingdestroyed", e.tile.build.block.localizedName, e.tile.build.tileX(), e.tile.build.tileY()));
        });
    }
}
