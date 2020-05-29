package io.github.nosequel.schematic;

import io.github.nosequel.schematic.block.SchematicBlock;
import lombok.Getter;
import org.bukkit.Location;

import java.util.List;

@Getter
public abstract class Schematic {

    private final String name;

    /**
     * Constructor for creating a new Schematic
     *
     * @param name the name of the schematic
     */
    public Schematic(String name) {
        this.name = name;
    }

    /**
     * Get the blocks of a schematic
     *
     * @return the list of blocks
     */
    public abstract List<SchematicBlock> getBlocks();

    /**
     * Build the schematic at a location
     *
     * @param location the location
     */
    public abstract void build(Location location);

}