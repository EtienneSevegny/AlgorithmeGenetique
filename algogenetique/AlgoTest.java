package ca.cegepvicto.techinfo.a2019.p3.da1737508.algogenetique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgoTest {
    @Test
    void correct()  {
        Algo algo = new Algo(50,10);
        algo.Run();
    }
}