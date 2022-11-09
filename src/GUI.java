import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    public JTextField singleCommandField = new JTextField("Type command:");
    public JTextArea multipleCommandsField = new JTextArea("Type commands:");
    public JPanel outputArea = new JPanel();

    public GUI(){

        //Settings for frame window
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Programming Language Environment");

        //Defining layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Single line command field
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridheight = 1; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(singleCommandField, gbc);

        //Multiple lines commands field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridheight = 1; gbc.gridwidth = 1;
        gbc.ipady = 500; gbc.ipadx = 300;
        gbc.insets = new Insets(30,0,0,0);
        JPanel textArea = new JPanel();
        textArea.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(multipleCommandsField);
        textArea.add(scroll, BorderLayout.CENTER);
        add(textArea, gbc);

        //Output area
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 2; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 600;
        gbc.insets = new Insets(0,50,0,0);
        outputArea.setBackground(Color.gray);
        add(outputArea, gbc);

        //End of frame
        setVisible(true);
    }
}
