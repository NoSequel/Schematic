package io.github.nosequel.schematic.impl;

import io.github.nosequel.schematic.Schematic;
import io.github.nosequel.schematic.block.SchematicBlock;
import io.github.nosequel.schematic.util.ThreadUtil;
import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AsyncSchematic implements Schematic {

    private final List<SchematicBlock> blocks = new ArrayList<>();
    private final String name;

    /**
     * Constructor for creating a new AsyncSchematic
     *
     * @param name the name of the schematic
     */
    public AsyncSchematic(String name) {
        this.name = name;
    }

    @Override
    public void build(Location location) {
        ThreadUtil.execute(() -> blocks.forEach((block -> {
            final Location relativeLocation = block.getRelativeLocation(location);
            final org.bukkit.block.Block block1 = relativeLocation.getBlock();

            block1.setType(block.getType());
            block1.setData(block.getData());
        })));
    }
}