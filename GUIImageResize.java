/*
	Input: image, number of times to resize the image
	Output: GUI displaying 8 images which are resized
	Process: This program takes a source image and resizes it maximum 8 times. It then displays the resizes images in a GUI panel.
	You can use a slider to get the number of times you can resize the image. The maximum number is 8
 */
/*
 * *****************************************************************************            
 * This program is GUI representation of the Resized images
 * @author Qazi Zaahirah
 * *****************************************************************************
 */
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.border.TitledBorder;
import org.imgscalr.Scalr;

public class GUIImageResize extends JFrame {

    int imageCount;
    static String path;
    static String filepath;
    static String  filename;
    static String sourcepath;
   ImagePreview filechoose;
   JFileChooser fc = new JFileChooser("FilePath");

    public GUIImageResize() {
    }

    public void ResizeImage(String filePath, double thresh) {
        int imageCount = 1;
        BufferedImage source;


        try {
            long startTime = System.currentTimeMillis();


            for (int count = 0; count < thresh; count++) {
                int oldImageCount = imageCount - 1;
                String filepath2 = "FilePath" + oldImageCount + ".bmp";
                if (imageCount == 1) {
                    source = ImageIO.read(new File(filePath));
                } else {
                    source = ImageIO.read(new File(filepath2));
                }

                int width = source.getWidth();
                int height = source.getHeight();
                int sz = width * height;
                int swidth = (int) width / 2;
                int sheight = (int) height / 2;
                int sz2 = swidth * sheight;
                int maxsize = swidth;
                if (maxsize < sheight) {
                    maxsize = sheight;
                }

                source = Scalr.resize(source, maxsize);
                ImageIO.write(source, "BMP", new File("resizedImagePath" + imageCount + ".bmp"));
                imageCount++;
            }// for loop for multiple images  
            long endTime = System.currentTimeMillis();
            double totalTime = (double) (endTime - startTime) / 1000;
			System.out.println(totalTime);
        }//try
        catch (Exception ex) {
            ex.printStackTrace();
        }//catch
    }// method end

    public void sliderDemo(String filePath) {
              //  System.out.println("the menu bar is formed");
        
        final JSlider Thresh3;
         final int[] threshvalue = new int[4];
         
        JPanel ThreshHolder = new JPanel();
        //System.out.println("the Slider is formed");
        JPanel image1 = new JPanel();
        image1.setLayout(new GridLayout(1, 1));
        JPanel image2 = new JPanel();
        image2.setLayout(new GridLayout(1, 1));
        JPanel image3 = new JPanel();
        image3.setLayout(new GridLayout(1, 1));
        JPanel image4 = new JPanel();
        image4.setLayout(new GridLayout(1, 1));
        JPanel image5 = new JPanel();
        image5.setLayout(new GridLayout(1, 1));
        JPanel image6 = new JPanel();
        image6.setLayout(new GridLayout(1, 1));
        JPanel image7 = new JPanel();
        image7.setLayout(new GridLayout(1, 1));
        JPanel image8 = new JPanel();
        image8.setLayout(new GridLayout(1, 1));
         JPanel source = new JPanel();
        source.setLayout(new GridLayout(1, 1));
        
        final JLabel label;
        final JLabel label1;
        final JLabel label2;
        final JLabel label3;
        final JLabel label4;
        final JLabel label5;
        final JLabel label6;
        final JLabel label7;
        final JLabel label8;
        try {
             GUIImageResize app = new GUIImageResize();
               path =  filePath;
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            label = new JLabel(new ImageIcon(image));
            label1 = new JLabel(new ImageIcon(image));
            label2 = new JLabel(new ImageIcon(image));
            label3 = new JLabel(new ImageIcon(image));
            label4 = new JLabel(new ImageIcon(image));
            label5 = new JLabel(new ImageIcon(image));
            label6 = new JLabel(new ImageIcon(image));
            label7 = new JLabel(new ImageIcon(image));
            label8 = new JLabel(new ImageIcon(image));
            
            
            
            setSize(800, 800);
            setPreferredSize(new Dimension(1000, 1000));
            setTitle("Thresholdchange for line resized");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            JLabel ThreshMax = new JLabel("ResizeTimes");
            Thresh3 = new JSlider(JSlider.HORIZONTAL, 0, 8, 1);
            Thresh3.setMajorTickSpacing(8);
            Thresh3.setMinorTickSpacing(1);
            Thresh3.setPaintLabels(true);
            Thresh3.setPaintTicks(true);
            ThreshHolder.add(Thresh3);
            ThreshHolder.add(ThreshMax);
            JButton getAllValues = new JButton("Get Threshold Values");
            ThreshHolder.add(getAllValues);
            getAllValues.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    threshvalue[0] = Thresh3.getValue();
                    GUIImageResize obj1 = new GUIImageResize();
                    obj1.ResizeImage(path, threshvalue[0]);
                    try {
                        
                        BufferedImage image = ImageIO.read(new File(path));
                        label.setIcon(new ImageIcon(image));
                        label.repaint();
                        
                        BufferedImage image1 = ImageIO.read(new File("ResizeImage1Path"));
                        label1.setIcon(new ImageIcon(image1));
                        label1.repaint();
                        
                        BufferedImage image2 = ImageIO.read(new File("ResizeImage2Path"));
                        label2.setIcon(new ImageIcon(image2));
                        label2.repaint();
                        
                        BufferedImage image3 = ImageIO.read(new File("ResizeImage3Path"));
                        label3.setIcon(new ImageIcon(image3));
                        label3.repaint();
                        BufferedImage image4 = ImageIO.read(new File("ResizeImage4Path"));
                        label4.setIcon(new ImageIcon(image4));
                        label4.repaint();
                        
                        
                        BufferedImage image4 = ImageIO.read(new File("Resizeimage4Path"));
                        label5.setIcon(new ImageIcon(image4));
                        label5.repaint();
                        
                        BufferedImage image6 = ImageIO.read(new File("MResizeImage6Path"));
                        label6.setIcon(new ImageIcon(image6));
                        label6.repaint();
                        
                        BufferedImage image7 = ImageIO.read(new File("ResizeImage7Path"));
                        label7.setIcon(new ImageIcon(image7));
                        label7.repaint();

                        BufferedImage image8 = ImageIO.read(new File("ResizeImage8Path"));
                        label8.setIcon(new ImageIcon(image8));
                        label8.repaint();
                        
                        

                    } catch (Exception io) {
                        io.printStackTrace();
                    }

                }//action performed
            });// action listener
            image1.add(label1);
            image2.add(label2);
            image3.add(label3);
            image4.add(label4);
            image5.add(label5);
            image6.add(label6);
            image7.add(label7);
            image8.add(label8);
            source.add(label);
            
         JTabbedPane jtp = new JTabbedPane();
          jtp.add(source, "Source Image");
        jtp.add(image1, "Image 1");// this is adding the tabs to the bar
        jtp.add(image2, "Image 2");
        jtp.add(image3, "Image 3");
        jtp.add(image4, "Image 4");
        jtp.add(image5, "Image 5");
        jtp.add(image6, "Image 6");
        jtp.add(image7, "Image 7");
        jtp.add(image8, "Image 8");
       
        
        
        add(jtp, BorderLayout.CENTER);
        add(ThreshHolder, BorderLayout.SOUTH);
          //  add(label, BorderLayout.CENTER);
           // add(label1,BorderLayout.WEST );
            pack();
            repaint();

        } catch (Exception e) {
        }
    }//sliderDemo
public  void menubar()
{
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
        add(mbar, BorderLayout.NORTH);
        this.setJMenuBar(mbar);// this creates the menu bar that holds the items
        // this is different class that loads the image
        this.setJMenuBar(mbar);
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
                   
                     Resize app = new GUIImageResize();
                app.sliderDemo(filepath);
                }//if condition
            }// action performed
        });//action listener
        fc.setAccessory(new ImagePreview(fc));
    }// end of method menubar



    public static void main(String[] args) throws IOException {

 				GUIImageResize app = new GUIImageResize();
                app.menubar();
    }// main class 
}// class



