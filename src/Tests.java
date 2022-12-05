import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    GUI gui = new GUI();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    Parser p  = new Parser(canvas, gui);
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
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is too large for this command");
    }

    @Test
    void TestInvalidCommand(){
        try{
            p.parseCommand("invalid");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Invalid command");
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered command is not recognised");
    }

    @Test
    void TestNotEnoughArgs(){
        try{
            p.parseCommand("rect 10");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Not enough parameters");
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is not enough for this command");
    }

    @Test
    void TestInvalidParameter(){
        try{
            p.parseCommand("circle 50 yellow");

        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals(errorMsg, "Invalid parameter detected");
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered parameter is not recognised");
    }

}

//PART TWO

//class TestPartTwo{
//
//    GUI gui = new GUI();
//    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
//    Parser p = new Parser(canvas);
//    String errorMsg = "";
//    /**
//     * Test to check if created variable is valid number
//     * @throws ApplicationException
//     */
//    @Test
//    void TestVarInt() throws ApplicationException {
//        p.parseCommand("var number = 20");
//        Variable var = new Variable();
//        int val = var.getValue();
//
//        assertEquals(20, val);
//    }
//
//    /**
//     * Test to check if created variable is valid String
//     * @throws ApplicationException
//     */
//    @Test
//    void TestVarString() throws ApplicationException{
//        p.parseCommand("var string = yellow");
//        Variable var = new Variable();
//        String val = var.getValue();
//
//        assertEquals("yellow", val);
//    }
//
//
//    /**
//     * Test to check if created loop successfully loops given tasks,
//     * by adding 1 to <strong>var number</strong> everytime it loops
//     * @throws ApplicationException
//     */
//    @Test
//    void TestLoopCommand() throws ApplicationException{
//        p.parseMultiCommands("var number = 0" +
//                "var count = 1" +
//                "While Count < 10" +
//                "number = number + 1" +
//                "count = count + 1" +
//                "Endloop");
//
//        Variable var = new Variable();
//        int val = var.getValue();
//
//        assertEquals(9, val);
//    }
//}