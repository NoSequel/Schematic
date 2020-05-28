package io.github.nosequel.schematic.block;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
@Setter
public class SchematicBlock {

    private int positionX, positionY, positionZ;

    private Material type;
    private byte data;

    /**
     * Constructor for creating a new BasicSchematicBlock
     *
     * @param positionX the X axis of the block
     * @param positionY the Y axis of the block
     * @param positionZ the Z axis of the block
     * @param type      the type of the block {@link Material}
     * @param data      the data of the block
     */
    public SchematicBlock(int positionX, int positionY, int positionZ, Material type, byte data) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.type = type;
        this.data = data;
    }

    /**
     * Read a SchematicBlock from a string
     *
     * @param string the string
     */
    public SchematicBlock(String string) {
        final String[] strings = string.split(",");

        this.positionX = Integer.parseInt(strings[0]);
        this.positionY = Integer.parseInt(strings[1]);
        this.positionZ = Integer.parseInt(strings[2]);
        this.type = Material.valueOf(strings[3]);
        this.data = Byte.parseByte(strings[4]);
    }

    /**
     * Get the relative location to a location
     *
     * @param location the location
     * @return the relative location
     */
    public Location getRelativeLocation(Location location) {
        return new Location(
                location.getWorld(),
                location.getBlockX() + positionX,
                location.getBlockY() + positionY,
                location.getBlockZ() + positionZ
        );
    }

    /**
     * Change the Object to a String
     *
     * @return the string
     */
    public String toString() {
        return String.join(",",
                new String[]{
                        String.valueOf(positionX),
                        String.valueOf(positionY),
                        String.valueOf(positionZ),
                        type.name(),
                        String.valueOf(data)
                });
    }
}