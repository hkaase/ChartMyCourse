package chartmycourse.chartmycourse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.awt.event.ActionEvent;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * Tests {@link ChartMyCourseMainPage}
 * @author Harm Drenth
 *
 */
public class ChartMyCourseTester {

	protected ChartMyCourseMainPage box = new ChartMyCourseMainPage();
	
    @BeforeEach
    void init() {
    	box = new ChartMyCourseMainPage();
    }

    @Test
    @DisplayName("Recommended Professor")
    void recProf() {
        box.recommendedProfessorsButtonActionPerformed(new ActionEvent("src", 1, "command"));
    }

    @Test
    @DisplayName("Recommended Course")
    void recCourse() {
        box.recommendedCoursesButtonActionPerformed(new ActionEvent("src", 2, "command"));
    }
	
    @Test
    @DisplayName("Invalid Email")
    void userEmailVerify() {
  	    boolean caught = false;
  	    try {
			User user = new User("abc", "abcd", "12", "ab", "ab");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			caught = true;
		} 
  	    assertTrue(caught);
    }
	
    @Test
    @DisplayName("Add Reply")
    void addReplyTest() {
  	    assertThrows(NullPointerException.class, () -> box.addReplyButton.doClick());
    }
}
