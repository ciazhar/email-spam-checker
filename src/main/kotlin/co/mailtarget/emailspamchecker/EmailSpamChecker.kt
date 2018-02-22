package co.mailtarget.emailspamchecker

import co.mailtarget.emailspamchecker.service.EmailSpamCheckerServiceImpl

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
    fun checkSpamAndReturnHashSet(content : String, additionalTriggerWord : HashSet<String>) : HashSet<String>{
        return service.check(content,additionalTriggerWord)
    }

    @JvmStatic
    fun isSpam(content : String) : Boolean{
        return service.check(content).size > 10
    }

    @JvmStatic
    fun isSpam(content : String, additionalTriggerWord : HashSet<String>) : Boolean{
        return service.check(content,additionalTriggerWord).size > 10
    }

    @JvmStatic
    fun checkSpamScore(content : String) : Int{
        return service.check(content).size
    }

    @JvmStatic
    fun checkSpamScore(content : String, additionalTriggerWord : HashSet<String>) : Int{
        return service.check(content,additionalTriggerWord).size
    }
}