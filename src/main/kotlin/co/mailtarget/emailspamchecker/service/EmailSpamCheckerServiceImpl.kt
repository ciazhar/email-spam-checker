package co.mailtarget.emailspamchecker.service

import co.mailtarget.emailspamchecker.resource.TriggerWord
import java.util.HashSet



class EmailSpamCheckerServiceImpl : EmailSpamCheckerService {

    override fun check(content: String): HashSet<String> {

        val separateContent = content.split("\\s".toRegex()).toHashSet()
        val triggeredWordList = TriggerWord.english.toHashSet()+
                                TriggerWord.bahasa.toHashSet()

        val similar = HashSet<String>(separateContent)
        similar.retainAll(triggeredWordList)

        return similar
    }
}