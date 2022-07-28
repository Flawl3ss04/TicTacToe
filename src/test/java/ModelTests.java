import fr.hugopabich.tictactoe.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {

    @Test
    public void checkNotTie(){
        Model model = new Model(null, new byte[]{0,0,0,1,0,2,0,0,0});
        assertFalse(model.checkTie());
    }

    @Test
    public void checkTie(){
        Model model = new Model(null, new byte[]{1,1,2,2,1,1,1,2,2});
        assertTrue(model.checkTie());
    }

    @Test
    public void checkNotWin(){
        Model model = new Model(null, new byte[]{1,1,2,2,1,1,1,2,2});
        assertFalse(model.checkWin());
    }

    @Test
    public void checkWin(){
        Model model = new Model(null, new byte[]{2,1,2,2,1,0,0,1,1});
        assertTrue(model.checkWin());
    }

    @Test
    public void cannotPlaceMark(){
        Model model = new Model(null, new byte[]{1,0,0,0,0,0,0,0,0});
        assertFalse(model.canPlaceMark(0));
    }

    @Test
    public void canPlaceMark(){
        Model model = new Model(null, new byte[]{1,0,0,0,0,0,0,0,0});
        assertTrue(model.canPlaceMark(5));
    }

    @Test
    public void placdeMark(){
        Model model = new Model(null, new byte[]{1,0,0,0,0,0,0,0,0});
        assertTrue(model.placeMark(4, Model.USER_MARK));
        assertArrayEquals(model.getBoard(), new byte[]{1,0,0,0,2,0,0,0,0});
    }

    @Test
    public void didnotPlaceMark(){
        Model model = new Model(null, new byte[]{1,0,0,0,0,0,0,0,0});
        assertFalse(model.placeMark(0, Model.USER_MARK));
        assertArrayEquals(model.getBoard(), new byte[]{1,0,0,0,0,0,0,0,0});
    }

}
