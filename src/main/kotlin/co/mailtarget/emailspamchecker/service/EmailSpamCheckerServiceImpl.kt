package co.mailtarget.emailspamchecker.service

import co.mailtarget.emailspamchecker.resource.TriggerWord
import java.util.HashSet



class EmailSpamCheckerServiceImpl : EmailSpamCheckerService {

    override fun check(content: String): HashSet<String> {

        val splitWord = content.toLowerCase().split(" ")
        val separateContent = mutableListOf<String>().toHashSet()

        (0 until splitWord.size).mapTo(separateContent) { splitWord[it] }
        (0 until splitWord.size - 1).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1] }
        (0 until splitWord.size - 2).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2] }
        (0 until splitWord.size - 3).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2]+ " " + splitWord[it + 3] }
        (0 until splitWord.size - 4).mapTo(separateContent) { splitWord[it] + " " + splitWord[it + 1]+ " " + splitWord[it + 2]+ " " + splitWord[it + 3]+ " " + splitWord[it + 4] }

        print(separateContent)

        val triggeredWordList = TriggerWord.english.toHashSet()+
                                TriggerWord.bahasa.toHashSet()

        val similar = HashSet<String>(separateContent)
        similar.retainAll(triggeredWordList)

        return similar
    }
}