import org.junit.jupiter.api.Test;
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
        assertEquals(errorMsg, "Too many parameters. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is too large for this command. Line:" + p.currentLine);
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
        assertEquals(errorMsg, "Invalid command. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered command is not recognised. Line:" + p.currentLine);
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
        assertEquals(errorMsg, "Not enough parameters. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is not enough for this command. Line:" + p.currentLine);
    }

    /**
     * Test to check if parser detects that there is invalid parameter
     */
    @Test
    void TestInvalidParameter() {
        try {
            p.parseCommand("circle yellow");

        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Invalid parameter detected. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: Entered parameter is not recognised. Line:" + p.currentLine);
    }
    @Test
    void TestNoParameters(){
        try{
            p.parseCommand("circle");

        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }
        assertEquals(errorMsg, "Not enough parameters. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is not enough for this command. Line:" + p.currentLine);
    }
}

//PART TWO

class TestPartTwo{

    GUI gui = new GUI();
    MyCanvas canvas = new MyCanvas(gui.canvasView, gui.canvas.getGraphics());
    Parser p = new Parser(canvas, gui);
    String errorMsg = "";
    /**
     * Test to check if created variable is valid number
     * @throws ApplicationException
     */
    @Test
    void TestVarInt() throws ApplicationException {
        p.parseCommand("var number = 20");

        int val = Integer.parseInt(p.vars.get(0).getValue());

        assertEquals(20, val);
    }

    /**
     * Test to check if created variable is valid String
     * @throws ApplicationException
     */
    @Test
    void TestVarString() throws ApplicationException{
        p.parseCommand("var string = yellow");
        String val = p.vars.get(0).getValue();

        assertEquals("yellow", val);
    }

    /**
     * Test to check if only one variable with given name can exist.
     */
    @Test
    void TestExistedVariable() {

        try{
            p.parseCommand("var string = yellow");
            p.parseCommand("var string = green");
        }catch(ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals("Variable already exists. Line:" + p.currentLine, errorMsg);
        assertEquals("Error detected: This variable already exists. Line:" + p.currentLine, gui.errorMessage.getText());

    }

    /**
     * Test to check if variable can be updated.
     * @throws ApplicationException
     */
    @Test
    void TestUpdatingVariable() throws ApplicationException {
        p.parseCommand("var string = yellow");
        p.parseCommand("string = green");
        String val  = p.vars.get(0).getValue();

        p.parseCommand("var pos = 100");
        p.parseCommand("pos + 10");
        p.parseCommand("moveto pos pos");

        assertEquals("green", val);
        assertEquals(110, canvas.xPos);
        assertEquals(110, canvas.yPos);

    }

    /**
     * Test to check if the program will show error,
     * one command to create variable is incorrectly typed.
     */
    @Test
    void TestEmptyVariable() {

        try{
            p.parseCommand("var string =");
            p.parseCommand("var int = ");
            p.parseCommand("var int ");
            p.parseCommand("var int");
            p.parseCommand("var ");
            p.parseCommand("var");
        }catch (ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals(errorMsg, "Not enough parameters. Line:" + p.currentLine);
        assertEquals(gui.errorMessage.getText(), "Error detected: The number of entered parameters is not enough for this command. Line:" + p.currentLine);

    }

    /**
     * Test to check if the variable has a correct value for given command.
     */
    @Test
    void TestInvalidVariable() {

        try{
            p.parseCommand("var size = true");
            p.parseCommand("moveto size size");
        }catch(ApplicationException e){
            errorMsg = e.getMessage();
        }

        assertEquals("Invalid parameter detected. Line:" + p.currentLine, errorMsg);
        assertEquals("Error detected: Entered parameter is not recognised. Line:" + p.currentLine, gui.errorMessage.getText());
    }

    /**
     * Another test to check if the variable has a correct value for given command.
     */
    @Test
    void TestInvalidVariable2() {
        try {
            p.parseCommand("var g = 10");
            p.parseCommand("pen g");
        } catch (ApplicationException e) {
            errorMsg = e.getMessage();
        }

        assertEquals("Invalid parameter detected. Line:" + p.currentLine, errorMsg);
        assertEquals("Error detected: Entered parameter is not recognised. Line:" + p.currentLine, gui.errorMessage.getText());
    }


    /**
     * Test to check if created loop successfully loops given tasks,
     * by adding 1 to <strong>var number</strong> everytime it loops
     * @throws ApplicationException
     */
    @Test
    void TestLoopCommand() throws ApplicationException{
        p.parseMultiCommands("var number = 0\n" +
                "var count = 0\n" +
                "while count < 9\n" +
                "number + 1\n" +
                "count + 1\n" +
                "endloop\n" +
                "var test = test");

        String val = p.vars.get(0).value; //var number
        String count = p.vars.get(1).value; //var count
        String test = p.vars.get(2).value; //var test

       assertEquals("9", val);
       assertEquals("9", count);
       assertEquals("test", test);

    }

    /**
     * Test to check if IFStatement can be created successfully
     * @throws ApplicationException
     */

    @Test
    void TestIfStatement() throws ApplicationException {
        p.parseMultiCommands(
                "if 1==1\n"+
                "var number1 = 1\n"+
                "endif\n"+

                "if 1<2\n"+
                "var number2 = 1\n"+
                "endif\n"+

                "if 2>1\n"+
                "var number3 = 1\n"+
                "endif\n"+

                "if 3>=number3\n"+
                "var number4 = 1\n"+
                "endif\n"+

                "if 1<=4\n"+
                "var number5 = 1\n"+
                "endif"
        );

        String var1 = p.vars.get(0).value;
        String var2 = p.vars.get(1).value;
        String var3 = p.vars.get(2).value;
        String var4 = p.vars.get(3).value;
        String var5 = p.vars.get(4).value;

        assertEquals("1", var1);
        assertEquals("1", var2);
        assertEquals("1", var3);
        assertEquals("1", var4);
        assertEquals("1", var5);

    }

    /**
     * Test to check if method can be created and executred.
     * @throws ApplicationException
     */
    @Test
    void testMethod() throws ApplicationException {
        p.parseMultiCommands(
                "method myMethod\n"+
                "var text = text\n"+
                "endmethod\n"+
                "myMethod"
        );

        assertEquals("text", p.vars.get(0).value);
    }
}

