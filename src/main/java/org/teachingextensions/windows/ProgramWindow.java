package org.teachingextensions.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.teachingextensions.approvals.lite.util.FrameCloser;
import org.teachingextensions.approvals.lite.util.WindowUtils;
import org.teachingextensions.logo.ImageBackground;
import org.teachingextensions.logo.Paintable;
import org.teachingextensions.logo.PenColors;
import org.teachingextensions.virtualproctor.VirtualProctorWeb;

/**
 * <img src="http://ftpmirror.your.org/pub/wikimedia/images/wikibooks/de/2/2c/JPanel_Add_JButton_PAGE_END.JPG" style="text-align: left" alt="A window image" height="50" width="75" > Program Window
 * allows you to change the color of the background and more...
 */
@SuppressWarnings({"serial"})
public class ProgramWindow extends JPanel
{
  public ArrayList<Paintable> additional = new ArrayList<>();
  public ProgramWindow(String title)
  {
    this();
    JFrame frame = new JFrame(title);
    frame.getContentPane().add(this);
    ProgramWindow.createStandardFrame(frame);
  }
  public ProgramWindow()
  {
    setPreferredSize(new Dimension(627, 442));
    setColor(PenColors.Whites.White);
  }
  public static void createStandardFrame(JFrame frame)
  {
    WindowUtils.testFrame(frame, new VirtualProctorWeb(), new FrameCloser());
  }
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = configureGraphics2D(g);
    for (Paintable p : additional)
    {
      p.paint(g2d, this);
    }
    g2d.dispose();
  }
  public void setColor(Color backgroundColor)
  {
    setBackground(backgroundColor);
  }
  public void addPaintable(Paintable additional)
  {
    this.additional.add(additional);
    repaint();
  }
  public void removePaintable()
  {
    additional.clear();
    repaint();
  }
  public void addMouseRightClickListener(MouseRightClickListener listener)
  {
    addMouseListener(new RightClickMouseAdapter(listener));
  }
  public void addMouseLeftClickListener(MouseLeftClickListener listener)
  {
    addMouseListener(new LeftClickMouseAdapter(listener));
  }
  public void setBackgroundImage(String url)
  {
    addPaintable(new ImageBackground(url));
  }
  public static Graphics2D configureGraphics2D(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    return g2d;
  }
  public void removePaintable(Paintable item)
  {
    this.additional.remove(item);
    repaint();
  }
}
