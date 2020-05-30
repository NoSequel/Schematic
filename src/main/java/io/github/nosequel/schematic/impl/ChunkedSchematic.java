package io.github.nosequel.schematic.impl;

import io.github.nosequel.schematic.Schematic;
import io.github.nosequel.schematic.block.SchematicBlock;
import io.github.nosequel.schematic.util.JavaUtils;
import io.github.nosequel.schematic.util.ThreadUtil;
import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChunkedSchematic extends Schematic {

    private final List<SchematicBlock> blocks = new ArrayList<>();

    /**
     * Constructor for creating a new ChunkedSchematic
     *
     * @param name the name of the schematic
     */
    public ChunkedSchematic(String name) {
        super(name);
    }

    @Override
    public void build(Location location) {
        ThreadUtil.execute(() -> {
            final List<SchematicBlock> blocks = this.getBlocks();

            JavaUtils.splitList(blocks, Math.min(350, blocks.size() / 10)).forEach(current -> new Thread(() -> {
                current.forEach((block -> {
                    final Location relativeLocation = block.getRelativeLocation(location);
                    final org.bukkit.block.Block black = relativeLocation.getBlock();

                    black.setType(block.getType());
                    black.setData(block.getData());
                }));

                Thread.currentThread().destroy();
            }).run());
        });
    }
}