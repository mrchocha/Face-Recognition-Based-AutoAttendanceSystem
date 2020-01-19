package Main2;


import java.io.File;
import java.io.FilenameFilter; 
import java.nio.IntBuffer; 
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_face.*; 
import static org.bytedeco.javacpp.opencv_core.*; 
import org.bytedeco.javacpp.opencv_imgcodecs;
import static org.bytedeco.javacpp.opencv_imgcodecs.*; 
import org.bytedeco.javacpp.opencv_imgproc;
import static org.bytedeco.javacpp.opencv_imgproc.resize;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import org.opencv.imgproc.Imgproc;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahul
 */

public class Train {
    public static void main() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String trainingDir = "D:\\Samples\\";
//        Mat testImage = imread("", CV_LOAD_IMAGE_GRAYSCALE); 
        File root = new File(trainingDir);
        FilenameFilter imgFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".png");
            }
        };
        File[] imageFiles = root.listFiles(imgFilter);
        MatVector images = new MatVector(imageFiles.length);
        
        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelBuff = labels.createBuffer();
        
        int counter = 0;
        for(File image : imageFiles){
              Mat img = imread(image.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
              resize(img, img, new Size(101, 101));
              int label = Integer.parseInt(image.getName().split("\\.")[1]);
              images.put(counter, img);
              labelBuff.put(counter, label);
              counter++;
        }
        FaceRecognizer faceRecognizer = null;
        faceRecognizer = LBPHFaceRecognizer.create();
        faceRecognizer.train(images, labels);
        faceRecognizer.save(trainingDir+"classifierLBPH.yml");
        System.out.println("Training Done!!!");
        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        opencv_core.Mat face = imread("C:\\Users\\rahul\\Downloads\\PhotoVishalChocha.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        faceRecognizer.predict(face, label, confidence);
        int prediction = label.get(0);
        System.out.println("ID : " + prediction);
        System.out.println(confidence.get(0));
        JOptionPane.showMessageDialog(null, "Images Are Trained!!!", "Message : " + "Message Box", JOptionPane.INFORMATION_MESSAGE);
//        new Recognizer.Recognizer().rec(prediction);
    }
}
