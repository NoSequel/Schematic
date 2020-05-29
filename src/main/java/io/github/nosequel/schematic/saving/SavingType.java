package io.github.nosequel.schematic.saving;

import io.github.nosequel.schematic.Schematic;

public interface SavingType<T> {

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
    Schematic load(T file) throws Exception;

}
