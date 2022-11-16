import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCanvasTest {

    GUI gui = new GUI();
    Parser p  = new Parser();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    String errorMsg = "";

    @Test
    void TestParseCommandMoveToValid() {
        canvas.moveTo(100,100);
        assertEquals(canvas.xPos, 100);
        assertEquals(canvas.yPos, 100);
    }

    @Test
    void TestParseCommandFillValid(){
        canvas.setFill(true);
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
        assertEquals(errorMsg, "Too many arguments");
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
            p.parseCommand("rectangle 10");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Not enough arguments");
    }

    @Test
    void TestInvalidParameter(){
        try{
            p.parseCommand("circle 100 yellow");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals(errorMsg, "Invalid parameter detected");
    }



}