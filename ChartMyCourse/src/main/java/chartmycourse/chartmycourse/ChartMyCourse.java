package chartmycourse.chartmycourse;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * This is the initializer for ChartMyCourse. It is important to initialize here so that 
 * the LookAndFeel is set! 
 * 
 * @author Harm Drenth
 * @version 1.0
 * @since 1.0
 */
public class ChartMyCourse {

    public static void main(String[] args) {
        ChartMyCourseMainPage uiFrame = new ChartMyCourseMainPage();
        

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChartMyCourse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ChartMyCourse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChartMyCourse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChartMyCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
}
