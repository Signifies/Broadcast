package Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Signifies on 10/3/2018 - 13:23.
 */
public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    private final int time;
    public CooldownManager(int defaultTime){
        this.time = defaultTime;
    }


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


    /**
     * If true, allow function to proceed, then add user to cooldown.
     * @param player
     * @return
     */
    public boolean processing(UUID player) {
        long timeLeft = System.currentTimeMillis() - getCooldown(player);
        return TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= time;
    }


}
