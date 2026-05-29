import edu.learn.kotlin.basic.Variables
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test


class VariablesTest {
    companion object {
        private var variables : Variables? = null

        @JvmStatic
        @BeforeClass fun initClass() {
            variables = Variables()
        }

        @JvmStatic
        @AfterClass fun closeClass() {
            variables = null
        }
    }

    @Test
    fun checkObject(){
        Assert.assertNotNull(variables)
    }

    @Test
    fun getMessage(){
        Assert.assertEquals("Hello Noman", variables!!.message)
    }
}