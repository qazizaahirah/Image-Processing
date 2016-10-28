import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import javax.swing.*;


public class DisplayPanel extends JPanel{
    
    
    BufferedImage image;
    String filename;
    
    
    /**
		Author: Qazi Zaahirah
		This Program displays image in a panel
		
     */
	
    public DisplayPanel(String path, String name)
    {
        try
        {
            filename = name;
            image = ImageIO.read(new File(path));
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
