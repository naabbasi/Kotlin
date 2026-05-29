
import edu.learn.kotlin.basic.Variables
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class VariablesTest {
    companion object {
        private var variables : Variables? = null

        @JvmStatic
        @BeforeAll fun initClass() {
            variables = Variables()
        }

        @JvmStatic
        @AfterAll fun closeClass() {
            variables = null
        }
    }

    @Test
    fun checkObject(){
        Assertions.assertNotNull(variables)
    }

    @Test
    fun getMessage(){
        Assertions.assertEquals("Hello Noman", variables!!.message)
    }
}