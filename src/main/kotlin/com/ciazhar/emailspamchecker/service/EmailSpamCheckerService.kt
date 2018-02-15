package com.ciazhar.emailspamchecker.service

import java.util.HashSet

/**
 * Created by ciazhar on 15/02/18.
 * [ Documentatiion Here ]
 */
interface EmailSpamCheckerService {
    fun check(content : String): HashSet<String>
}