package io.github.nosequel.schematic;

import io.github.nosequel.schematic.saving.SavingType;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Getter
public class SchematicController {

    private static SchematicController instance;

    private final Class<? extends Schematic> schematicImplementation;
    private final List<Schematic> schematics = new ArrayList<>();
    private final SavingType<?> savingType;

    @Setter
    private Function<String, Schematic> createMethod;


    /**
     * Constructor for creating a new SchematicController instance
     *
     * @param type                    the type of the saving interface
     * @param schematicImplementation the implementation of the schematic
     */
    public SchematicController(SavingType<?> type, Class<? extends Schematic> schematicImplementation) {
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
     */
    public Schematic createSchematic(String name) {
        return this.getCreateMethod().apply(name);
    }

    /**
     * Find a schematic by a name
     *
     * @param name the name of the schematic
     * @return the found schematic | or null.
     */
    public Schematic findSchematic(String name){
        return schematics.stream()
                .filter(schematic -> schematic.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    /**
     * Get the create Function
     *
     * @return the function
     */
    private Function<String, Schematic> getCreateMethod() {
        if(this.createMethod == null) {
            this.createMethod = name -> {
                Schematic schematic = null;
                Constructor<?> constructor;

                try {
                    constructor = this.schematicImplementation.getDeclaredConstructor(String.class);
                    constructor.setAccessible(true);

                    schematic = (Schematic) constructor.newInstance(name);

                    schematics.add(schematic);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException reflectiveOperationException) {
                    reflectiveOperationException.printStackTrace();
                }


                return schematic;
            };
        }

        return this.createMethod;
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