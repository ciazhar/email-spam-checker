package co.mailtarget.emailspamchecker.service

import kotlin.collections.HashSet

/**
 * Created by ciazhar on 15/02/18.
 * [ Documentatiion Here ]
 */
interface EmailSpamCheckerService {
    fun check(content : String): HashSet<String>
    fun check(content : String, additionalTriggerWord : HashSet<String>): HashSet<String>
}