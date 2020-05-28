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
public class ChunkedSchematic implements Schematic {

    private final List<SchematicBlock> blocks = new ArrayList<>();
    private final String name;

    /**
     * Constructor for creating a new ChunkedSchematic
     *
     * @param name the name of the schematic
     */
    public ChunkedSchematic(String name) {
        this.name = name;
    }

    @Override
    public void build(Location location) {
        ThreadUtil.execute(() -> {
            final List<SchematicBlock> blocks = this.getBlocks();

            JavaUtils.splitList(blocks, Math.min(350, blocks.size() / 10))
                    .forEach(current -> {
                        current.forEach((block -> {
                            final Location relativeLocation = block.getRelativeLocation(location);
                            final org.bukkit.block.Block black = relativeLocation.getBlock();

                            black.setType(block.getType());
                            black.setData(block.getData());
                        }));

                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }
}