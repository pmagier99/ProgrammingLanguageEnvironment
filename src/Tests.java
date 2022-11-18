import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    GUI gui = new GUI();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    Parser p  = new Parser(canvas);
    String errorMsg = "";

    @Test
    void TestParseCommandMoveToValid() throws ApplicationException {
        p.parseCommand("moveto 100 100");
        assertEquals(canvas.xPos, 100);
        assertEquals(canvas.yPos, 100);
    }

    @Test
    void TestParseCommandFillValid() throws ApplicationException {
        p.parseCommand("fill on");
        assertTrue(canvas.fill);
    }

    @Test
    void TestResetPosition(){
        canvas.reset();
        assertEquals(canvas.xPos, 0);
        assertEquals(canvas.yPos, 0);
    }

    @Test
    void TestTooManyArgs(){
        try{
            p.parseCommand("circle 100 2");
        }catch(ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Too many parameters");
    }

    @Test
    void TestInvalidCommand(){
        try{
            p.parseCommand("invalid");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Invalid command");
    }

    @Test
    void TestNotEnoughArgs(){
        try{
            p.parseCommand("rect 10");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Not enough parameters");
    }

    @Test
    void TestInvalidParameter(){
        try{
            p.parseCommand("circle 50 yellow");

        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals(errorMsg, "Invalid parameter detected");
    }

}