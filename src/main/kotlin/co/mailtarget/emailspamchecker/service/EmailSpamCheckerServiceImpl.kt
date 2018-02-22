package co.mailtarget.emailspamchecker.service

import co.mailtarget.emailspamchecker.resource.TriggerWord
import kotlin.collections.HashSet


class EmailSpamCheckerServiceImpl : EmailSpamCheckerService {

    override fun check(content: String): HashSet<String> {
        val separateContent = splitContent(content)
        val triggeredWordList = TriggerWord.english.toHashSet()+
                                TriggerWord.bahasa.toHashSet()

        val similar = HashSet<String>(separateContent)
        similar.retainAll(triggeredWordList)

        return similar
    }

    override fun check(content: String, additionalTriggerWord: HashSet<String>): HashSet<String> {
        val separateContent = splitContent(content)
        val triggeredWordList = TriggerWord.english.toHashSet()+
                                TriggerWord.bahasa.toHashSet()+
                                additionalTriggerWord

        val similar = HashSet<String>(separateContent)
        similar.retainAll(triggeredWordList)

        return similar
    }

    private fun splitContent(content: String) : HashSet<String>{
        val splitWord = content.toLowerCase().split(" ")
        val separateContent = mutableListOf<String>().toHashSet()

        (0 until splitWord.size).mapTo(separateContent) { splitWord[it] }
        (0 until splitWord.size - 1).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1] }
        (0 until splitWord.size - 2).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2] }
        (0 until splitWord.size - 3).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2]+ " " + splitWord[it + 3] }
        (0 until splitWord.size - 4).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2]+ " " + splitWord[it + 3]+ " " + splitWord[it + 4] }

        return separateContent
    }
}