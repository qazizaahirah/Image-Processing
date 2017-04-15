
//Author: Qazi Zaahirah
// This class creates a small menu bar where you can choose the files as an input
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException; 
  public class menubar{
     JFileChooser fc = new JFileChooser(JFileChooserPath);
    String JFileChooserPath = ""
   public void menubar(String FolderName1, String FolderName2,String FolderName3,String original) {
        JFrame main = new JFrame("File Path");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setResizable(false);
        main.setLayout(null);
        main.setPreferredSize(new Dimension(10, 100));
        main.setLocation(600, 600);
        main.getContentPane();
        Container pane = main.getContentPane();
        pane.setLayout(new GridLayout(4, 10));
        JMenu menu = new JMenu("File");
        JMenuItem open = new JMenuItem("Load Image");
        menu.add(open);// this creates the items in the menu
        JMenuBar mbar = new JMenuBar();
        mbar.add(menu);
        main.add(mbar, BorderLayout.NORTH);
        pane.add(mbar);
        main.pack();
        main.setVisible(true);

        // now we create an action listener for the open menu
        open.addActionListener(new ActionListener() {
            public Graphics Graphics;
            private PropertyChangeEvent PropertyChangeEvent;

            @Override
            public void actionPerformed(ActionEvent e) {

                int val = fc.showOpenDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    filename = file.getName();
                    filepath = file.getAbsolutePath();
       
                    System.out.println(" filepath");
                }//if condition
            }// action performed
        });//action listener
    }// end of method menubar
    }
