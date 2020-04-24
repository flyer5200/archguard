package com.thoughtworks.archgard

import com.thoughtworks.archgard.scanner.domain.hubexecutor.ScannerManager
import com.thoughtworks.archgard.scanner.infrastructure.db.*
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.spi.JdbiPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.stereotype.Component
import java.util.function.Consumer
import javax.sql.DataSource


@SpringBootApplication
class ScannerApplication {

    @Bean
    fun jdbi(ds: DataSource, jdbiPlugins: List<JdbiPlugin>): Jdbi {
        val proxy = TransactionAwareDataSourceProxy(ds)
        val jdbi = Jdbi.create(proxy)
        jdbiPlugins.forEach(Consumer { plugin: JdbiPlugin? -> jdbi.installPlugin(plugin) })
        return jdbi
    }

    @Bean
    fun jdbiPlugins(): List<JdbiPlugin> {
        return listOf(SqlObjectPlugin(), KotlinPlugin(), KotlinSqlObjectPlugin())
    }

    @Bean
    fun badSmellModelDao(jdbi: Jdbi): BadSmellDao {
        return jdbi.onDemand(BadSmellDao::class.java)
    }

    @Bean
    fun testBadSmellModelDao(jdbi: Jdbi): TestBadSmellDao {
        return jdbi.onDemand(TestBadSmellDao::class.java)
    }

    @Bean
    fun checkStylesDao(jdbi: Jdbi): StyleDao {
        return jdbi.onDemand(StyleDao::class.java)
    }

    @Bean
    fun statisticDao(jdbi: Jdbi): StatisticDao {
        return jdbi.onDemand(StatisticDao::class.java)
    }

    @Bean
    fun overviewDao(jdbi: Jdbi): OverviewDao {
        return jdbi.onDemand(OverviewDao::class.java)
    }

    @Bean
    fun configDao(jdbi: Jdbi): ConfigDao {
        return jdbi.onDemand(ConfigDao::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<ScannerApplication>(*args)
}

@Component
class ApplicationRunnerImpl : ApplicationRunner {
    @Autowired
    private lateinit var scannerManager: ScannerManager

    override fun run(args: ApplicationArguments?) {
        scannerManager.register()
    }

}