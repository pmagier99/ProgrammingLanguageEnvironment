import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    GUI gui = new GUI();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    Parser p  = new Parser(canvas, gui);
    String errorMsg = "";

    /**
     * Test to check if <strong>moveto 100 100</strong> command moves the cursor to 100,100
     *
     * @throws ApplicationException
     */

    @Test
    void TestParseCommandMoveToValid() throws ApplicationException {
        p.parseCommand("moveto 100 100");
        assertEquals(100, canvas.xPos);
        assertEquals(100, canvas.yPos);
    }

    /**
     * Test to check if <strong>fill on</strong> command sets the fill on
     *
     * @throws ApplicationException
     */
    @Test
    void TestParseCommandFillValid() throws ApplicationException {
        p.parseCommand("fill on");
        assertTrue(canvas.fill);
    }

    /**
     * Test to check if <strong>reset</strong> command reset position of cursor
     */
    @Test
    void TestResetPosition() {
        canvas.reset();
        assertEquals(0, canvas.xPos);
        assertEquals(0, canvas.yPos);
    }

    /**
     * Test to check if parser detects that there are too many parameters
     */
    @Test
    void TestTooManyArgs() {
        try {
            p.parseCommand("circle 100 2");
        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Too many parameters");
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is too large for this command");
    }

    /**
     * Test to check if parser detects that there is invalid command
     */
    @Test
    void TestInvalidCommand() {
        try {
            p.parseCommand("invalid");
        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Invalid command");
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered command is not recognised");
    }

    /**
     * Test to check if parser detects that there are not enough parameters
     */
    @Test
    void TestNotEnoughArgs() {
        try {
            p.parseCommand("rect 10");
        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Not enough parameters");
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is not enough for this command");
    }

    /**
     * Test to check if parser detects that there is invalid parameter
     */
    @Test
    void TestInvalidParameter() {
        try {
            p.parseCommand("circle 50 yellow");

        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Invalid parameter detected");
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered parameter is not recognised");
    }
}
    //PART TWO

class TestPartTwo{

    GUI gui = new GUI();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    Parser p = new Parser(canvas);
    String errorMsg = "";
        /**
         * Test to check if created variable is valid number
         * @throws ApplicationException
         */
        @Test
        void TestVarInt() throws ApplicationException {
            p.parseCommand("var number = 20");
            Variable var = new Variable();
            int val = var.getValue();

            assertEquals(20, val);
        }

        /**
         * Test to check if created variable is valid String
         * @throws ApplicationException
         */
        @Test
        void TestVarString() throws ApplicationException{
            p.parseCommand("var string = yellow");
            Variable var = new Variable();
            String val = var.getValue();

            assertEquals("yellow", val);
        }


        /**
         * Test to check if created loop successfully loops given tasks,
         * by adding 1 to <strong>var number</strong> everytime it loops
         * @throws ApplicationException
         */
        @Test
        void TestLoopCommand() throws ApplicationException{
            p.parseMultiCommands("var number = 0" +
                    "var count = 1" +
                    "While Count < 10" +
                    "number = number + 1" +
                    "count = count + 1" +
                    "Endloop");

            Variable var = new Variable();
            int val = var.getValue();

            assertEquals(9, val);
        }
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

