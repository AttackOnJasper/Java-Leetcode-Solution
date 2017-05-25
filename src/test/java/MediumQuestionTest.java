package test.java;

import static junit.framework.TestCase.assertEquals;

import java.util.*;
import main.MediumQuestion;
import org.junit.Test;

public class MediumQuestionTest {
    @Test
    public void testTopKElement() {
        int[] arr = new int[]{1,1,1,2,2,3};
        List<Integer> res = MediumQuestion.topKFrequent(arr, 2);
        assertEquals(2, res.size());
        assertEquals(1, (int)res.get(0));
        assertEquals(2, (int)res.get(1));
    }
}
