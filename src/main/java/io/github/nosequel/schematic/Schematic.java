package io.github.nosequel.schematic;

import io.github.nosequel.schematic.block.SchematicBlock;
import org.bukkit.Location;

import java.util.List;

public interface Schematic {

    /**
     * Get the name of the schematic
     *
     * @return the name
     */
    String getName();

    /**
     * Get the blocks of a schematic
     *
     * @return the list of blocks
     */
    List<SchematicBlock> getBlocks();

    /**
     * Build the schematic at a location
     *
     * @param location the location
     */
    void build(Location location);

}