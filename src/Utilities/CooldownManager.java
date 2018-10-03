package Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Signifies on 10/3/2018 - 13:23.
 */
public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public void setCoolDown(UUID player, long time) {
        if(time < 1){
            cooldowns.remove(player);
        }else{
            cooldowns.put(player,time);
        }
    }

    public long getCooldown(UUID player){
        return cooldowns.getOrDefault(player, System.currentTimeMillis());
    }


}
