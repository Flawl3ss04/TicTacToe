import fr.hugopabich.tictactoe.Images;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageTests {

    @Test
    public void testImagePresent(){
        Images.init();
        assertNotNull(Images.getImage("test"));
    }

    @Test
    public void testImageAbsent(){
        Images.init();
        assertNull(Images.getImage("404"));
    }

}
