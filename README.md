# Schematic API
An easy to use Schematic API for the Bukkit API.

# Usage
This Schematic API uses a main SchematicController to handle all things.
To use this API you must create a new SchematicController instance.

* **SchematicController instantiating example**:
    ```java
    final SchematicController controller = new SchematicController(new JsonSavingType(), ChunkedSchematic.class);
    ```

* SavingType implementations
  * Default SavingType implementations
    * JsonSavingType
  * Implementing your own SavingType
    ```java
    public class ExampleSavingType implements SavingType {
    
        public Schematic load(File file) {
            Schematic schematic;
    
            // schematic instantiating    
    
            return schematic;
        }
    
        public void save(Schematic schematic) {
            // saving code
        }   
    
    }
    ```

# Binaries
Binaries and dependency information for the available dependency managers.

* **Maven**:
    * ```maven
        <dependency>
            <groupId>io.github.nosequel</groupId>
            <artifactId>Schematic</artifactId>
            <version>x.y.z</version>
        </dependency>
      ```