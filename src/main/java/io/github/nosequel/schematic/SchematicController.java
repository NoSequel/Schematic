package io.github.nosequel.schematic;

import io.github.nosequel.schematic.saving.SavingType;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@Getter
public class SchematicController {

    private static SchematicController instance;

    private final Class<? extends Schematic> schematicImplementation;
    private final List<Schematic> schematics = new ArrayList<>();
    private final SavingType savingType;


    /**
     * Constructor for creating a new SchematicController instance
     *
     * @param type                    the type of the saving interface
     * @param schematicImplementation the implementation of the schematic
     */
    public SchematicController(SavingType type, Class<? extends Schematic> schematicImplementation) {
        instance = this;

        this.savingType = type;
        this.schematicImplementation = schematicImplementation;
    }

    /**
     * Save all the loaded/registered schematics
     */
    public void save() {
        this.schematics.forEach(schematic -> {
            try {
                this.savingType.save(schematic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create a schematic {@link Schematic}
     *
     * @param name the name of the schematic
     * @return the created schematic
     * @throws NoSuchMethodException     thrown whenever the constructor isn't found
     * @throws IllegalAccessException    thrown whenever you don't have access to the constructor
     * @throws InvocationTargetException thrown whenever there's an error while invoking the constructor
     * @throws InstantiationException    thrown whenever instantiating the object
     */
    public Schematic createSchematic(String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<?> constructor = this.schematicImplementation.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        final Schematic schematic = (Schematic) constructor.newInstance(name);

        schematics.add(schematic);
        return schematic;
    }

    /**
     * Get the current SchematicController instance
     *
     * @return the instance
     */
    public static SchematicController get() {
        return instance;
    }

}