import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

public String printText(){
        return output;
    }
}
