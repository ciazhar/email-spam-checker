package co.mailtarget.emailspamchecker.service

import java.nio.file.Files
import java.nio.file.Paths
import java.util.HashSet



class EmailSpamCheckerServiceImpl : EmailSpamCheckerService {

    private var englishSpamTriggerWordPathFile = "/home/ciazhar/Documents/project/service/email-scam-checker/dataset/trigger-word/english.txt"

    override fun check(content: String): HashSet<String> {

        val separateContent = content.split("\\s".toRegex()).toHashSet()
        val triggeredWordList = mutableListOf<String>().toHashSet()

        Files.lines(Paths.get(englishSpamTriggerWordPathFile)).use {
            stream -> stream.forEach{
                triggeredWordList.add(it)
            }
        }

        val similar = HashSet<String>(separateContent)
        similar.retainAll(triggeredWordList)

        return similar
    }
}