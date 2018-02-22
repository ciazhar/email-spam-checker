package co.mailtarget.emailspamchecker

import org.junit.Assert
import org.junit.Test

/**
 * Created by ciazhar on 15/02/18.
 * [ Documentatiion Here ]
 */
class EmailSpamTest {

    @Test
    fun checkSpamAndMustReturnHashSetWithNoSpamWord(){
        val str = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"
        val result = EmailSpamChecker.checkSpamAndReturnHashSet(str)
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun checkSpamAndMustReturnHashSetWithSpamWord(){
        val str = "100% free viagra with Vitamin C $$$"
        val result = EmailSpamChecker.checkSpamAndReturnHashSet(str)
        Assert.assertTrue(!result.isEmpty())
    }


    @Test
    fun checkSpamAndMustReturnTrue(){
        val str = "100% free viagra with Vitamin C $$$ amazed amazing buy cancel cash cash bonus cashcashcash casino"
        val result = EmailSpamChecker.isSpam(str)
        Assert.assertEquals(true,result)
    }

    @Test
    fun checkSpamAndMustReturnFalse() {
        val str = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"
        val result = EmailSpamChecker.isSpam(str)
        Assert.assertEquals(false,result)
    }

    @Test
    fun checkSpamScore() {
        val str = "100% Vitamin C viagra"
        val result = EmailSpamChecker.checkSpamScore(str)
        print(result)
    }

    val additionalSpamWord = mutableListOf(
            "im",
            "mister",
            "mobaloba"
    ).toHashSet()

    @Test
    fun checkSpamWithAdditionalSpamWordListAndMustReturnHashSetWithNoSpamWord(){
        val str = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"
        val result = EmailSpamChecker.checkSpamAndReturnHashSet(str,additionalSpamWord)
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun checkSpamWithAdditionalSpamWordListAndMustReturnHashSetWithSpamWord(){
        val str = "Free Mobaloba"
        val result = EmailSpamChecker.checkSpamAndReturnHashSet(str,additionalSpamWord)
        Assert.assertTrue(!result.isEmpty())
    }


    @Test
    fun checkSpamWithAdditionalSpamWordListAndMustReturnTrue(){
        val str = "im Mister Mobaloba 100% free viagra with Vitamin C amazed amazing buy cancel"
        val result = EmailSpamChecker.isSpam(str,additionalSpamWord)
        Assert.assertEquals(true,result)
    }

    @Test
    fun checkSpamWithAdditionalSpamWordListAndMustReturnFalse() {
        val str = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"
        val result = EmailSpamChecker.isSpam(str,additionalSpamWord)
        Assert.assertEquals(false,result)
    }

    @Test
    fun checkSpamScoreWithAdditionalSpamWordList() {
        val str = "100% Vitamin C viagra Mobaloba"
        val result = EmailSpamChecker.checkSpamScore(str,additionalSpamWord)
        print(result)
    }
}