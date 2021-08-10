/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_secondproject;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
 
public class DrawShapesExample {
 
  public static void main(String[] args) {
 
// Create a frame
 
Frame frame = new Frame();
 
// Add a component with a custom paint method
 
frame.add(new CustomPaintComponent());
 
// Display the frame
 
int frameWidth = 300;
 
int frameHeight = 300;
 
frame.setSize(frameWidth, frameHeight);
 
frame.setVisible(true);
 
  }
  static class CustomPaintComponent extends Component {
 
public void paint(Graphics g) {
 
  // Retrieve the graphics context; this object is used to paint shapes
 
  Graphics2D g2d = (Graphics2D)g;
 
  // Draw an oval that fills the window
 
  int x = 4;
 
  int y = 4;
 
  int w = getSize().width-+31;
 
  int h = 4;
 
  /**
    * The coordinate system of a graphics context is such that the origin is at the 
    * northwest corner and x-axis increases toward the right while the y-axis increases 
    * toward the bottom.
    */
 
  g2d.drawLine(x, y, h, w);
 
  g2d.drawOval(2,2 , 4, 4);
 
}
 
  }
 
}