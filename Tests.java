import static org.junit.Assert.assertEquals;
import org.junit.Test;

  
public class Tests {  
  
    @Test  
    public void simpleTest(){  
        assertEquals(3,Syllables.SyllableChecker("banana"));  
        assertEquals(1,Syllables.SyllableChecker("sea"));  
    }  

    @Test  
    public void wordsThatEndWithE(){  
        assertEquals(2,Syllables.SyllableChecker("complete"));  
        assertEquals(1,Syllables.SyllableChecker("see"));  
        assertEquals(2,Syllables.SyllableChecker("above"));  
        assertEquals(2,Syllables.SyllableChecker("argue"));  
    }  

    @Test  
    public void wordsThatContainUOU(){  
        assertEquals(3,Syllables.SyllableChecker("virtuous"));  
        assertEquals(4,Syllables.SyllableChecker("continuous"));  
    }  

    @Test  
    public void wordsThatContainUA(){  
        assertEquals(1,Syllables.SyllableChecker("dual"));  
        assertEquals(1,Syllables.SyllableChecker("quay"));  
        assertEquals(3,Syllables.SyllableChecker("usual"));
    }

    @Test  
    public void wordsThatEndWithES(){  
        assertEquals(1,Syllables.SyllableChecker("castes"));  
        assertEquals(1,Syllables.SyllableChecker("vapes"));  
        assertEquals(2,Syllables.SyllableChecker("cages"));
        assertEquals(2,Syllables.SyllableChecker("tackles"));
    }

    @Test  
    public void wordsThatEndWithLE(){  
        assertEquals(1,Syllables.SyllableChecker("gale"));  
        assertEquals(1,Syllables.SyllableChecker("sale"));  
        assertEquals(2,Syllables.SyllableChecker("tackle"));
        assertEquals(2,Syllables.SyllableChecker("apple"));
    }

    @Test  
    public void wordsThatContainAEO(){  
        assertEquals(2,Syllables.SyllableChecker("aeon"));  
        assertEquals(3,Syllables.SyllableChecker("archaeon"));  
    }

    @Test  
    public void wordsThatContainY(){  
        assertEquals(2,Syllables.SyllableChecker("yellow"));  
        assertEquals(2,Syllables.SyllableChecker("lolly"));  
        assertEquals(1,Syllables.SyllableChecker("sky"));  
    }
}  