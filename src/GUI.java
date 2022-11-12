import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI extends JPanel{

    private final int HEIGHT = 600;
    private final int WIDTH = 600;

    public JTextField singleCommandField = new JTextField("Type command:");
    public JTextArea multipleCommandsField = new JTextArea("Type commands:");
    public JPanel outputArea = new JPanel(new BorderLayout());

    public GUI(){
        //setting layout and border gaps for main panel
        setBorder(new EmptyBorder(35,100,35,100));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30 ,0));

        //creating another panel for textboxes
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension((WIDTH/2),HEIGHT));

        //making bigger textbox scrollable
        JPanel textArea = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(multipleCommandsField);
        multipleCommandsField.setPreferredSize(new Dimension((WIDTH/2), (HEIGHT-50)));
        textArea.add(scroll, BorderLayout.CENTER);

        //adding textboxes to side panel
        sidePanel.add(singleCommandField, BorderLayout.NORTH);
        sidePanel.add(multipleCommandsField, BorderLayout.SOUTH);

        //configuring size of output area
        outputArea.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        outputArea.setBackground(Color.gray);

        //adding both panels to main panel
        add(sidePanel);
        add(outputArea);

    }

}