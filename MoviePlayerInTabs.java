//Author: Qazi Zaahirah
// this program plays videos in tabs. It uses other Java libraries

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



    public static void main(String[] args) throws IOException {
       MoviePlayerInTabs obj = new MoviePlayerInTabs();
		   
					VideoFormation video = new VideoFormation();
					int noOfFrames = new File(original).listFiles().length;
					try {
						video.VideoFormation(FolderName1,FolderName2,FolderName3, noOfFrames);
					} catch (IOException ex) {
						Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
					}
					try {
						obj.start(null);
					} catch (Exception ex) {
						Logger.getLogger(MoviePlayerInTabs.class.getName()).log(Level.SEVERE, null, ex);
					}
		   
        video.menubar(String FolderName1, String FolderName2,String FolderName3,String original);
    }

    // this method is used to display the videos on the JFrame

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
        String path = "output\\Video1"+filename+".mp4";
        Media media = new Media(new File(path).toURI().toString());
        final MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view = new MediaView(player);
        root.getChildren().add(view);
        Scene scene = new Scene(root, 900, 700, Color.BLACK);
        String path2 = "output\\Video2"+filename+".mp4";
        Media media2 = new Media(new File(path2).toURI().toString());
        final MediaPlayer player2 = new MediaPlayer(media2);
        player2.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view2 = new MediaView(player2);
        root2.getChildren().add(view2);
        Scene scene2 = new Scene(root2, 900, 700, Color.BLACK);
        String path3 = "output\\Video3.mp4";
        Media media3 = new Media(new File(path3).toURI().toString());
        final MediaPlayer player3 = new MediaPlayer(media3);
        player3.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view3 = new MediaView(player3);
        root3.getChildren().add(view3);
        Scene scene3 = new Scene(root3, 900, 700, Color.BLACK);
        player.play();
        player2.play();
        player3.play();
        System.out.println("the player is playing");
		
     // allow the user to play or pause a track.

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Pause".equals(playButton.getActionCommand())) {
                    view.getMediaPlayer().pause();
                    view2.getMediaPlayer().pause();
                    view3.getMediaPlayer().pause();
                    playButton.setActionCommand("Play");
                } else {
                    view.getMediaPlayer().play();
                    view2.getMediaPlayer().play();
                    view3.getMediaPlayer().play();
                    playButton.setActionCommand("Pause");
                }
            }
        });
        JFXPanel javafxPanel = new JFXPanel();
        JFXPanel javafxPanel2 = new JFXPanel();
        JFXPanel javafxPanel3 = new JFXPanel();
        javafxPanel.setScene(scene);
        javafxPanel2.setScene(scene2);
        javafxPanel3.setScene(scene3);
        JTabbedPane jtp = new JTabbedPane();

        p.add(javafxPanel);
        // frame.add(p);
        p2.add(javafxPanel2);
        //frame.add(p2);
        p3.add(javafxPanel3);
        //frame.add(p3);
        p4.add(javafxPanel4);
        p5.add(playButton);
        jtp.add(p, "Video1");
        jtp.add(p2, "Video2");// this is adding the tabs to the bar
        jtp.add(p3, "Video3");
        frame.add(jtp, BorderLayout.CENTER);
        frame.add(p5, BorderLayout.SOUTH);
        // frame.getContentPane().add(javafxPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }



}
