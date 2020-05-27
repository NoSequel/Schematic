package io.github.nosequel.schematic.impl;

import io.github.nosequel.schematic.Schematic;
import io.github.nosequel.schematic.block.SchematicBlock;
import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BasicSchematic implements Schematic {

    private final List<SchematicBlock> blocks = new ArrayList<>();
    private final String name;

    /**
     * Constructor for creating a new BasicSchematic
     *
     * @param name the name of the schematic
     */
    public BasicSchematic(String name) {
        this.name = name;
    }

    @Override
    public void build(Location location) {
        blocks.forEach(block -> {
            final Location relativeLocation = block.getRelativeLocation(location);
            final org.bukkit.block.Block block1 = relativeLocation.getBlock();

            block1.setType(block.getType());
            block1.setData(block.getData());
        });
    }
}