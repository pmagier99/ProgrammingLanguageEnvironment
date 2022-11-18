import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * A public class that is used to <strong>Load file</strong>
 * The file is then read and split according the new lines,
 * and finally concatenated to <strong>output</strong> string,
 * along with inserting a new line when needed.
 */
public class LoadFile {
    String output = "";
    public LoadFile(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to load");
            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File myFile = new File(fileChooser.getSelectedFile().getAbsoluteFile().toURI());
                Scanner myReader = new Scanner(myFile);
                while(myReader.hasNextLine()){
                   output += myReader.nextLine()+"\n";
                }
                myReader.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

/**
 * Returns the <strong>output</strong> string
 * @return the String of whatever is in the <strong>Loaded file</strong>
 */

public String printText(){
        return output;
    }
}
