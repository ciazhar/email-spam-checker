package com.ciazhar.emailspamchecker

import com.ciazhar.emailspamchecker.service.EmailSpamCheckerServiceImpl

/**
 * Created by ciazhar on 15/02/18.
 * [ Documentatiion Here ]
 */
object EmailSpamChecker {
    private val service by lazy { EmailSpamCheckerServiceImpl() }

    @JvmStatic
    fun checkSpamAndReturnHashSet(content : String) : HashSet<String>{
        return service.check(content)
    }

    @JvmStatic
    fun isSpam(content : String) : Boolean{
        return service.check(content).size != 0
    }


}