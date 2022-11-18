import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
    public CreateFile(String commands){

        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a directory to save your file: ");
            int returnValue = fileChooser.showSaveDialog(null);

            if(returnValue==JFileChooser.APPROVE_OPTION){
                    File myFile = new File(fileChooser.getSelectedFile()+".txt");
                    if(myFile.createNewFile()){
                        FileWriter myWriter = new FileWriter(fileChooser.getSelectedFile()+".txt");
                        myWriter.write(commands);
                        myWriter.close();
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
