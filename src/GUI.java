import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A public class that extends JPanel.
 * It is used to create a JPanel that then further is added
 * to JFrame in Main class.
 */
public class GUI extends JPanel{

    public final int HEIGHT = 600;
    public final int WIDTH = 600;

    public JTextField singleCommandField = new JTextField("Type command:");
    public JTextArea multipleCommandsField = new JTextArea("Type commands:");
    public JButton runButton = new JButton("Run");
    public JPanel outputArea = new JPanel(new BorderLayout());
    public BufferedImage canvas = new BufferedImage((HEIGHT-50), (WIDTH-50), BufferedImage.TYPE_INT_RGB);
    public JLabel canvasView = new JLabel(new ImageIcon(canvas));
    public JLabel errorMessage = new JLabel("");

    /**
     * GUI class constructor that render entire User Interface
     */
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
        sidePanel.add(multipleCommandsField, BorderLayout.CENTER);
        sidePanel.add(runButton,BorderLayout.SOUTH);

        //configuring size of output area
        outputArea.setBackground(Color.BLACK);
        outputArea.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        outputArea.add(canvasView, BorderLayout.CENTER);


        //configuring error messages
        errorMessage.setForeground(Color.red);
        outputArea.add(errorMessage, BorderLayout.SOUTH);



        //adding both panels to main panel
        add(sidePanel);
        add(outputArea);

    }

}