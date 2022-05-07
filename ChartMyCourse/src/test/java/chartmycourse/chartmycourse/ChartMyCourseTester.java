package chartmycourse.chartmycourse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    /* example tests from lab 9
    @Test
    void feed() {
    	box.insertCoin(Coin.cent);
    	assertEquals(.01f, box.balance(), "Cent expected");
    }

    @Test
    //@Disabled("TODO: need to fix :)... yes you!")
    
    //Fixed by not changing balance if coin is null.
    void feedEmpty() {
    	//box.insertCoin(null);
    	assertThrows(RuntimeException.class, () -> box.insertCoin(null), "Tried to insert null coin, didn't throw");

    }
    
    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => coins={0}, total={1}")
    @MethodSource("coins")
    void feedParams(Coin[] coins, float total) {
    	for (Coin coin : coins) {
    		box.insertCoin(coin);
		}
    	assertEquals(total, box.balance(), "Different total expected");
    
    }
    */
    
}