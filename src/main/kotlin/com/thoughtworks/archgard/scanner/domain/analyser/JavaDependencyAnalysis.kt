package com.thoughtworks.archgard.scanner.domain.analyser

import com.thoughtworks.archgard.scanner.domain.tools.JavaByteCodeTool
import com.thoughtworks.archgard.scanner.domain.tools.TableUsedTool
import com.thoughtworks.archgard.scanner.infrastructure.client.AnalysisModuleClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JavaDependencyAnalysis(@Value("\${spring.datasource.url}") val dbUrl: String,
                             @Value("\${spring.datasource.username}") val username: String,
                             @Value("\${spring.datasource.password}") val password: String,
                             @Autowired val analysisService: AnalysisService,
                             @Autowired val analysisModuleClient: AnalysisModuleClient) {
    private val log = LoggerFactory.getLogger(JavaDependencyAnalysis::class.java)

    fun analyse(id: Long) {
        log.info("start scan java analysis")
        val projectOperator = analysisService.getProjectOperator(id)
        val url = dbUrl.replace("://", "://" + username + ":" + password + "@")

        projectOperator.cloneAndBuildAllRepo()
        val javaByteCodeTool = JavaByteCodeTool(projectOperator.workspace, url)
        javaByteCodeTool.analyse()
        val tableUsedTool = TableUsedTool(projectOperator.workspace, projectOperator.sql)
        tableUsedTool.analyse()
        analysisModuleClient.autoDefine()
        log.info("finished scan java analysis")
    }
}
