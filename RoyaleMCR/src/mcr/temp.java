/*
package mcr;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class temp extends JFrame {

   public Macro() {
      // 제목 설정
      JButton start = new JButton("Start");
      JButton stop = new JButton("Stop");
      JButton pause = new JButton("Pause");

      // 레이아웃 설정
      this.setLayout(new FlowLayout());

      start.addActionListener(new BtnListener());
      stop.addActionListener(new BtnListener());
      pause.addActionListener(new BtnListener());

      // 버튼 추가
      this.add(start);
      this.add(stop);
      this.add(pause);

      // 프레임 크기 지정
      this.setSize(300, 400);

      // 프레임 보이도록 설정
      this.setVisible(true);

      // X버튼 눌렀을 때 닫히도록 설정

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public static void main(String args[]) throws Exception {

      // MacroForIMG m4IMG = new MacroForIMG();
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      Macro mcr = new Macro();

      //
      //

   }

}

class BtnListener implements ActionListener {
   BtnListener() {
   }
   public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), BufferedImage.TYPE_INT_RGB);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
      }
   public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("Start")) {
         try {
            Robot robot;

            robot = new Robot();

            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit()
                  .getScreenSize());

            BufferedImage bufImage = null;
            bufImage = robot.createScreenCapture(area);
            
            
            ImageIO.write(bufImage, "png", new File("./resource/realtime/test.png"));   
            
            Mat srcimg= Highgui.imread("./resource/realtime/test.png");   
            final Mat templateimg =Highgui.imread("./resource/realtime/srcimg.png");   
            
            
            int result_cols = srcimg.cols() - templateimg.cols() + 1;
             int result_rows = srcimg.rows() - templateimg.rows() + 1;
             Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
             // 스크린 캡쳐.
            //         
            
            Imgproc.matchTemplate(srcimg, templateimg, result, Imgproc.TM_SQDIFF);//method=CV_TM_SQDIFF);
            Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
              //Highgui.imwrite("./resource/realtime/out2.png", result);
             MinMaxLocResult mmr = Core.minMaxLoc(result);

                 final Point matchLoc;
//                 if (match_method == Imgproc.TM_SQDIFF
//                         || match_method == Imgproc.TM_SQDIFF_NORMED) {
//                     matchLoc = mmr.minLoc;
//                     System.out.println(mmr.minVal);
//                 } else {
                 matchLoc = mmr.minLoc;
                 //matchLoc = mmr.maxLoc;
//                     System.out.println(mmr.maxVal);
//                 }

                 //
               Window w=new Window(null)
               {
                 @Override
                 public void paint(Graphics g)
                 {
                  // final Font font = getFont().deriveFont(48f);
                   //g.setFont(font);
                   g.setColor(Color.RED);
                   //final String message = "Hello";
                   FontMetrics metrics = g.getFontMetrics();
                   //g.drawString(message,(getWidth()-metrics.stringWidth(message))/2,(getHeight()-metrics.getHeight())/2);
                  g.drawRoundRect((int)(matchLoc.x + templateimg.cols()), (int)(matchLoc.y + templateimg.rows()), templateimg.width(),templateimg.height(), 2, 2);
                  // g.drawRect();
                   //g.drawImage(img, matchLoc.x + templateimg.cols(), y, observer)
                 }
                 @Override
                 public void update(Graphics g)
                 {
                   paint(g);
                 }
               };
               w.setAlwaysOnTop(true);
               w.setBounds(w.getGraphicsConfiguration().getBounds());
               w.setBackground(new Color(0, true));
               w.setVisible(true);
               //
                 // / Show me what you got
                 Core.rectangle(srcimg, matchLoc, new Point(matchLoc.x + templateimg.cols(),
                         matchLoc.y + templateimg.rows()), new Scalar(0, 255, 0));

                 // Save the visualized detection.
                 //System.out.println("Writing " + outFile);
                 Highgui.imwrite("./resource/realtime/out2.png", srcimg);

            

         } catch (Exception e1) {
            e1.printStackTrace();
         }

      } else if (e.getActionCommand().equals("Stop")) {
         Runtime.getRuntime().exit(0);
      }
   }
}
*/