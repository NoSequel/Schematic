package io.github.nosequel.schematic.saving;

import io.github.nosequel.schematic.Schematic;

import java.io.File;

public interface SavingType {

    /**
     * Save a schematic in the file
     *
     * @param schematic the schematic
     */
    void save(Schematic schematic) throws Exception;

    /**
     * Load a schematic from the file
     *
     * @return the list of schematics
     */
    Schematic load(File file) throws Exception;

}
