package cn.wqy.SwingUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class MySwingUtils {

    private MySwingUtils(){}

    public static final Font DEFAULT_FONT = new Font("等线" , Font.PLAIN , 14);

    public static void add(Container container , Component... components){
        for (Component component : components) container.add(component);
    }

    public static void add(ButtonGroup buttonGroup , AbstractButton... abstractButtons){
        for (AbstractButton abstractButton : abstractButtons) buttonGroup.add(abstractButton);
    }

    public static TitledBorder setTitledBorderFont(TitledBorder titledBorder){
        titledBorder.setTitleFont(DEFAULT_FONT);
        return titledBorder;
    }

    public static void setDefaultFonts(Container container){
        Component[] components = container.getComponents();
        for (Component component : components){
            if (component instanceof Container)
                setDefaultFonts((Container) component);
            component.setFont(DEFAULT_FONT);
        }
    }

    public static void setDefaultFont(Component component){
        component.setFont(DEFAULT_FONT);
    }

    public static void setFont(Component component , int style , int size){
        component.setFont(new Font("等线" , style , size));
    }

    public static void setDefaultLookAndFeel(RootPaneContainer rootPaneContainer) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(rootPaneContainer.getContentPane());
    }

    public static void setDefaultLookAndFeel(JMenuBar jMenuBar) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(jMenuBar);
    }

    public static void setDefaultLookAndFeel(JPopupMenu jPopupMenu) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(jPopupMenu);
    }

    public static void defaultJFrameSetting(JFrame jFrame) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        setDefaultFonts(jFrame);
        setDefaultLookAndFeel(jFrame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(new Dimension(740 , 500));
    }

    public static void defaultJDialogSetting(JDialog jDialog) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        setDefaultFonts(jDialog);
        setDefaultLookAndFeel(jDialog);
        jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jDialog.setSize(new Dimension(740 , 500));
    }

    public static void setCenterAndVisible(JFrame jFrame){
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void setCenterAndVisible(JDialog jDialog){
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }

    public static int getStringWidth(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getWidth());
    }

    public static int getStringHeight(Font font, String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(str, frc).getHeight());
    }
}
