package com.ciazhar.emailspamchecker

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
        val str = "100% Vitamin C"
        val result = EmailSpamChecker.checkSpamAndReturnHashSet(str)
        Assert.assertTrue(!result.isEmpty())
    }


    @Test
    fun checkSpamAndMustReturnTrue(){
        val str = "100% Vitamin C"
        val result = EmailSpamChecker.isSpam(str)
        Assert.assertEquals(true,result)
    }

    @Test
    fun checkSpamAndMustReturnFalse() {
        val str = "Kotlination.com = Be Kotlineer - Be Simple - Be Connective"
        val result = EmailSpamChecker.isSpam(str)
        Assert.assertEquals(false,result)
    }
}