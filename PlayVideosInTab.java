
//https://www.youtube.com/all_comments?v=bWl98dhvf8Q
// this program plays videos in tabs

import objectdetectioninframes.DecodeAndCaptureFrames;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import static com.xuggle.xuggler.Global.DEFAULT_TIME_UNIT;
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
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import objectdetectioninframes.CreateFrames;
import javafx.event.*;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 * Developed By Qazi Zaahirah
 */
public class MoviePlayerInTabs extends Application {

    JFileChooser fc = new JFileChooser("C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\Video");
    int imageCount;
    static String path;
    static String filepath;
    static String filename;
    static String sourcepath;

//    public static void main(String[] args) throws IOException {
//        MoviePlayerInTabs video = new MoviePlayerInTabs();
//        video.menubar();
//    }

    // this method is used to form the video

    public void VideoFormation(String FolderName1, String FolderName2,String FolderName3,int noOfFrames) throws IOException {

        final long duration = DEFAULT_TIME_UNIT.convert(6, SECONDS);

        final int videoStreamIndex = 0;
        final int videoStreamId = 0;
        final long frameRate = DEFAULT_TIME_UNIT.convert(30, MILLISECONDS);
        final int width = 900;
        final int height = 700;

        long nextFrameTime = 0;
        String outputvideo1 = "output\\MaskedFrame"+filename+".mp4";
        String outputvideo2 = "output\\Mask"+filename+".mp4";
        String outputvideo3 = "output\\Edges"+filename+".mp4";
        final IMediaWriter writer1 = ToolFactory.makeWriter(outputvideo1);
        writer1.addVideoStream(videoStreamIndex, videoStreamId, width, height);
        final IMediaWriter writer2 = ToolFactory.makeWriter(outputvideo2);
        writer2.addVideoStream(videoStreamIndex, videoStreamId, width, height);
        final IMediaWriter writer3 = ToolFactory.makeWriter(outputvideo3);
        writer3.addVideoStream(videoStreamIndex, videoStreamId, width, height);
        String filePath1 = null;
        String filePath2 = null;
        String filePath3 = null;

        while (nextFrameTime < duration) {
            for (int i = 1; i < (noOfFrames - 1); i++) {
                filePath1 = FolderName1 + i + ".bmp";
                filePath2 = FolderName2 + i + ".bmp";
                filePath3 = FolderName3 + i + ".bmp";
                System.out.println(filePath1);
                BufferedImage frame1 = ImageIO.read(new File(filePath1));
                BufferedImage bgrFrame1 = convertToType(frame1, BufferedImage.TYPE_3BYTE_BGR);
                BufferedImage frame2 = ImageIO.read(new File(filePath2));
                BufferedImage bgrFrame2 = convertToType(frame2, BufferedImage.TYPE_3BYTE_BGR);
                BufferedImage frame3 = ImageIO.read(new File(filePath3));
                BufferedImage bgrFrame3 = convertToType(frame3, BufferedImage.TYPE_3BYTE_BGR);
                writer1.encodeVideo(videoStreamIndex, bgrFrame1, nextFrameTime,
                        DEFAULT_TIME_UNIT);
                writer2.encodeVideo(videoStreamIndex, bgrFrame2, nextFrameTime,
                        DEFAULT_TIME_UNIT);
                writer3.encodeVideo(videoStreamIndex, bgrFrame3, nextFrameTime,
                        DEFAULT_TIME_UNIT);
                nextFrameTime += frameRate;
            }
        }

        writer1.close();
        writer2.close();
        writer3.close();

    }

    public static BufferedImage convertToType(BufferedImage sourceImage,
            int targetType) {
        BufferedImage image;

        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        } else {
            image = new BufferedImage(sourceImage.getWidth(),
                    sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
    }

    // this method is used to display the videos on th JFrame

    public void start(Stage stage) throws Exception {
        JFrame frame = new JFrame("Videos");
        frame.setSize(500, 500);
        frame.setPreferredSize(new Dimension(1000, 1000));
        // this makes sure that the components do not overlap each other
        //frame.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(0, 1));
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(0, 1));
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(0, 1));
        JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout(0, 1));
        JButton playButton = new JButton("Pause");
        Group root = new Group();
        Group root2 = new Group();
        Group root3 = new Group();
        Group root4 = new Group();
        String path = "output\\MaskedFrame"+filename+".mp4";
        Media media = new Media(new File(path).toURI().toString());
        final MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view = new MediaView(player);
        root.getChildren().add(view);
        Scene scene = new Scene(root, 900, 700, Color.BLACK);
        String path2 = "output\\Mask"+filename+".mp4";
        Media media2 = new Media(new File(path2).toURI().toString());
        final MediaPlayer player2 = new MediaPlayer(media2);
        player2.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view2 = new MediaView(player2);
        root2.getChildren().add(view2);
        Scene scene2 = new Scene(root2, 900, 700, Color.BLACK);
        String path3 = "output\\original.mp4";
        Media media3 = new Media(new File(path3).toURI().toString());
        final MediaPlayer player3 = new MediaPlayer(media3);
        player3.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view3 = new MediaView(player3);
        root3.getChildren().add(view3);
        Scene scene3 = new Scene(root3, 900, 700, Color.BLACK);
        String path4 = "output\\Edges"+filename+".mp4";
        Media media4 = new Media(new File(path4).toURI().toString());
        final MediaPlayer player4 = new MediaPlayer(media4);
        player4.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view4 = new MediaView(player4);
        root4.getChildren().add(view4);
        Scene scene4 = new Scene(root4, 900, 700, Color.BLACK);
        player.play();
        player2.play();
        player3.play();
        player4.play();
        System.out.println("the player is playing");
     // allow the user to play or pause a track.

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Pause".equals(playButton.getActionCommand())) {
                    view.getMediaPlayer().pause();
                    view2.getMediaPlayer().pause();
                    view3.getMediaPlayer().pause();
                    view4.getMediaPlayer().pause();
                    playButton.setActionCommand("Play");
                } else {
                    view.getMediaPlayer().play();
                    view2.getMediaPlayer().play();
                    view3.getMediaPlayer().play();
                    view4.getMediaPlayer().play();
                    playButton.setActionCommand("Pause");
                }
            }
        });
        JFXPanel javafxPanel = new JFXPanel();
        JFXPanel javafxPanel2 = new JFXPanel();
        JFXPanel javafxPanel3 = new JFXPanel();
        JFXPanel javafxPanel4 = new JFXPanel();
        javafxPanel.setScene(scene);
        javafxPanel2.setScene(scene2);
        javafxPanel3.setScene(scene3);
        javafxPanel4.setScene(scene4);
        JTabbedPane jtp = new JTabbedPane();

        p.add(javafxPanel);
        // frame.add(p);
        p2.add(javafxPanel2);
        //frame.add(p2);
        p3.add(javafxPanel3);
        //frame.add(p3);
        p4.add(javafxPanel4);
        p5.add(playButton);
        jtp.add(p, "MaskedFrame");
        jtp.add(p2, "Masked");// this is adding the tabs to the bar
        jtp.add(p3, "Original");
        jtp.add(p4, "Edges");
        frame.add(jtp, BorderLayout.CENTER);
        frame.add(p5, BorderLayout.SOUTH);
        // frame.getContentPane().add(javafxPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void menubar() {
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
//        this.setJMenuBar(mbar);
//        this.setJMenuBar(mbar);
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
                    System.out.println(filepath);
                    new DecodeAndCaptureFrames(filepath);
                    
                    MoviePlayerInTabs app = new MoviePlayerInTabs();
                    CreateFrames app2 = new CreateFrames();

                    String FolderName1 = "C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\MaskWithFrame " + filename +"\\";
                    String FolderName2 = "C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\Mask " + filename +"\\";
                    String original = "Input\\";
                    String edgesPath = "C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\Edges " + filename+"\\";
                    // Create all the folders
                    File dir1 = new File("C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\MaskWithFrame " + filename);
                    dir1.mkdir();
                    File dir2 = new File("C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\Mask " + filename);
                    dir2.mkdir();
                    File dir3 = new File("C:\\Users\\dell\\Desktop\\Neuramatix\\Neuramatix Codes\\ObjectDetectionInFrames\\Edges " + filename);
                    dir3.mkdir();
                    
                    int noOfFrames = new File(original).listFiles().length;
                    System.out.println("no of frames: " + noOfFrames);
                    int counter = 1;
                    int Threshold = 50;
                    for (int i = 1; i < (noOfFrames - 1); i++) {
                        int loop = 1;
                        String filePath1 = original + i + ".bmp";// Static Image
                        String filePath2 = original + (i - 1) + ".bmp";// moving image
                        try {
                            app2.OverlayTheFrames(filePath1, filePath2, loop, Threshold);
                        } catch (IOException ex) {
                            Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        loop++;
                        filePath1 = original + (i + 1) + ".bmp";
                        filePath2 = original + i + ".bmp";
                        try {
                            app2.OverlayTheFrames(filePath1, filePath2, loop, Threshold);
                        } catch (IOException ex) {
                            Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String filePath3 = original + i + ".bmp";
                        try {
                            app2.ExtractObject(filePath3, FolderName1, FolderName2, counter, Threshold);
                        } catch (IOException ex) {
                            Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        counter++;
                    }
                    int countImage=1;
                    for (int i = 1; i < (noOfFrames-1); i++) {
                      String  input = FolderName1 + i + ".bmp";
                      System.out.println(input);
                      
                        app2.ResizeImageCalLumin(input,edgesPath, countImage);
                        countImage++;
                    }
                    try {
                        app.VideoFormation(FolderName1,FolderName2,edgesPath, noOfFrames);
                    } catch (IOException ex) {
                        Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        app.start(null);
                    } catch (Exception ex) {
                        Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(" the awesome video is being made");
                }//if condition
            }// action performed
        });//action listener
    }// end of method menubar

}
