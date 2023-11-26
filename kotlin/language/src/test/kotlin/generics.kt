import kotlin.test.Test
import kotlin.test.assertEquals

class GenericsTest  {
    fun <T : Comparable<T>> compareTo(compare : T, to : T) : Int {
        return compare.compareTo(to)
    }
    
    @Test fun `generic function`() {
        val res = compareTo("short", "long text")
        assertEquals(7, res) 
    }
    
    // TODO in and out
}