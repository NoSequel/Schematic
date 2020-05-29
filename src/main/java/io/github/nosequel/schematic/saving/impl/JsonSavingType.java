package io.github.nosequel.schematic.saving.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nosequel.schematic.Schematic;
import io.github.nosequel.schematic.SchematicController;
import io.github.nosequel.schematic.block.SchematicBlock;
import io.github.nosequel.schematic.saving.SavingType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonSavingType implements SavingType<File> {

    private final String blockSpliterator = "||";
    private final String path;

    /**
     * Constructor for creating a new JsonSavingType
     *
     * @param path the path the files will be located in
     */
    public JsonSavingType(String path) {
        this.path = path;

        final File directory = new File(path);

        if (directory.mkdir()) {
            System.out.println("Created \"" + path + "\" directory");
        }

        if (directory.listFiles() != null) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                try {
                    this.load(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void save(Schematic schematic) throws IOException {
        final File file = new File(path + File.separator + schematic.getName());

        if (file.delete()) {
            System.out.println("Deleted \"" + schematic.getName() + ".json\"");
        }

        if (file.createNewFile()) {
            System.out.println("Created \"" + schematic.getName() + ".json\"");
        }

        final FileWriter writer = new FileWriter(file);

        writer.write(schematic.getBlocks().stream().map(SchematicBlock::toString).collect(Collectors.joining(blockSpliterator)));
        writer.flush();
        writer.close();
    }

    @Override
    public Schematic load(File file) throws IOException {
        final FileReader reader = new FileReader(file);
        final StringBuilder string = new StringBuilder();

        int content;

        while ((content = reader.read()) != -1) {
            string.append((char) content);
        }

        final String[] strings = string.toString().split(blockSpliterator);
        final Schematic schematic = SchematicController.get().createSchematic(file.getName());

        Arrays.stream(strings).forEach(str -> schematic.getBlocks().add(new SchematicBlock(str)));

        return schematic;
    }
}