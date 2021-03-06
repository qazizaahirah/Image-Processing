   
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
public VideoFormation {  
    int imageCount;
    static String path;
    static String filepath;
    static String filename;
    static String sourcepath;
   public void VideoFormation(String FolderName1, String FolderName2,String FolderName3,int noOfFrames) throws IOException {

        final long duration = DEFAULT_TIME_UNIT.convert(6, SECONDS);

        final int videoStreamIndex = 0;
        final int videoStreamId = 0;
        final long frameRate = DEFAULT_TIME_UNIT.convert(30, MILLISECONDS);
        final int width = 900;
        final int height = 700;

        long nextFrameTime = 0;
        String outputvideo1 = "output\\Video1"+filename+".mp4";
        String outputvideo2 = "output\\Video2"+filename+".mp4";
        String outputvideo3 = "output\\Video3"+filename+".mp4";
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
    }
