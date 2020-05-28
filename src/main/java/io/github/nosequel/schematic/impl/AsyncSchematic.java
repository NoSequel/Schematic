package io.github.nosequel.schematic.impl;

import io.github.nosequel.schematic.util.ThreadUtil;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class AsyncSchematic extends BasicSchematic {

    /**
     * Constructor for creating a new AsyncSchematic
     * This Schematic type just calls the normal build method in {@link BasicSchematic} in a different thread.
     *
     *
     * @param name the name of the schematic
     */
    public AsyncSchematic(String name) {
        super(name);
    }

    @Override
    public void build(Location location) {
        ThreadUtil.execute(() -> this.getBlocks().forEach((block -> {
            super.build(location);
        })));
    }
}