import javax.swing.*;
import java.awt.*;

public interface Commands {
    void setFill(boolean fill);
    void setColour(Color c);
    void clear(JLabel view, Graphics g);
    void reset();
    void moveTo(int x, int y);

}
