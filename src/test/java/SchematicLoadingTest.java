import io.github.nosequel.schematic.SchematicController;
import io.github.nosequel.schematic.impl.BasicSchematic;
import io.github.nosequel.schematic.saving.impl.BasicSavingType;
import org.junit.Test;

public class SchematicLoadingTest {

    @Test
    public void test() {
        final SchematicController controller = new SchematicController(new BasicSavingType("empty"), BasicSchematic.class);
    }

}
