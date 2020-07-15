package com.thoughtworks.archguard.module.domain.springcloud.feignclient

import com.thoughtworks.archguard.module.domain.JAnnotationRepository
import com.thoughtworks.archguard.module.domain.model.JAnnotation
import com.thoughtworks.archguard.module.domain.springcloud.SpringCloudServiceRepository
import com.thoughtworks.archguard.module.domain.springcloud.httprequest.HttpRequest
import com.thoughtworks.archguard.module.domain.springcloud.httprequest.HttpRequestArg
import com.thoughtworks.archguard.module.domain.springcloud.httprequest.HttpRequestService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.annotation.ElementType
import kotlin.test.assertEquals

class FeignClientServiceTest {
    @MockK
    lateinit var jAnnotationRepository: JAnnotationRepository

    @MockK
    lateinit var springCloudServiceRepository: SpringCloudServiceRepository

    @MockK
    lateinit var httpRequestService: HttpRequestService

    private lateinit var service: FeignClientService

    private val log = LoggerFactory.getLogger(FeignClientServiceTest::class.java)

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        service = FeignClientService(jAnnotationRepository,springCloudServiceRepository, httpRequestService)
    }

    @Test
    internal fun should_get_FeignClients() {

        // given
        val jAnnotation = JAnnotation("ida1", ElementType.TYPE.name, "id1", "org.springframework.cloud.netflix.feign.FeignClient")
        jAnnotation.values = mapOf("name" to "\"serviceName\"", "path" to "\"/path\"")

        every { jAnnotationRepository.getJAnnotationWithValueByName("feign.FeignClient") } returns listOf(jAnnotation)

        // when
        val feignClients = service.getFeignClients()

        log.info(feignClients[0].arg.path)

        // then
        assertEquals(1, feignClients.size)
        assertEquals("serviceName", feignClients[0].arg.name)
        assertEquals("/path", feignClients[0].arg.path)
        assertEquals("id1", feignClients[0].targetId)
    }

    @Test
    internal fun should_get_feignClient_method_dependencies() {

        // given
        val feignClientAnnotation = JAnnotation("ida1", ElementType.TYPE.name, "idc1", "org.springframework.cloud.netflix.feign.FeignClient")
        feignClientAnnotation.values = mapOf("name" to "\"producer\"")

        val httpRequestMethod1 = HttpRequest("idm1", HttpRequestArg(listOf("/hello"), listOf("GET")))
        val httpRequestMethod2 = HttpRequest("idm2", HttpRequestArg(listOf("/hello"), listOf("GET")))

        every { jAnnotationRepository.getJAnnotationWithValueByName("feign.FeignClient") } returns listOf(feignClientAnnotation)
        every { httpRequestService.getHttpRequests() } returns listOf(httpRequestMethod1, httpRequestMethod2)
        every { springCloudServiceRepository.getMethodIdsByClassId("idc1") } returns listOf("idm1")
        every { springCloudServiceRepository.getServiceNameByMethodId("idm1") } returns "consumer"
        every { springCloudServiceRepository.getServiceNameByMethodId("idm2") } returns "producer"


        // when
        val dependencies = service.getFeignClientMethodDependencies()

        // then
        assertEquals(1, dependencies.size)
        assertEquals("idm1", dependencies[0].caller.targetId)
        assertEquals("idm2", dependencies[0].callee.targetId)

    }

    @Test
    internal fun should_get_feignClient_method_dependencies_with_feignClient_path() {

        // given
        val feignClientAnnotation = JAnnotation("ida1", ElementType.TYPE.name, "idc1", "org.springframework.cloud.netflix.feign.FeignClient")
        feignClientAnnotation.values = mapOf("name" to "\"producer\"", "path" to "\"/path\"")

        val httpRequestMethod1 = HttpRequest("idm1", HttpRequestArg(listOf("/hello"), listOf("GET")))
        val httpRequestMethod2 = HttpRequest("idm2", HttpRequestArg(listOf("/path/hello"), listOf("GET")))

        every { jAnnotationRepository.getJAnnotationWithValueByName("feign.FeignClient") } returns listOf(feignClientAnnotation)
        every { httpRequestService.getHttpRequests() } returns listOf(httpRequestMethod1, httpRequestMethod2)
        every { springCloudServiceRepository.getMethodIdsByClassId("idc1") } returns listOf("idm1")
        every { springCloudServiceRepository.getServiceNameByMethodId("idm1") } returns "consumer"
        every { springCloudServiceRepository.getServiceNameByMethodId("idm2") } returns "producer"


        // when
        val dependencies = service.getFeignClientMethodDependencies()

        // then
        assertEquals(1, dependencies.size)
        assertEquals("idm1", dependencies[0].caller.targetId)
        assertEquals("idm2", dependencies[0].callee.targetId)

    }

    @Test
    internal fun should_get_feignClient_method_dependencies_with_different_PathVariable() {

        // given
        val feignClientAnnotation = JAnnotation("ida1", ElementType.TYPE.name, "idc1", "org.springframework.cloud.netflix.feign.FeignClient")
        feignClientAnnotation.values = mapOf("name" to "\"producer\"")

        val httpRequestMethod1 = HttpRequest("idm1", HttpRequestArg(listOf("/hello/{n}/{a}"), listOf("GET")))
        val httpRequestMethod2 = HttpRequest("idm2", HttpRequestArg(listOf("/hello/{m}/{b}"), listOf("GET")))

        every { jAnnotationRepository.getJAnnotationWithValueByName("feign.FeignClient") } returns listOf(feignClientAnnotation)
        every { httpRequestService.getHttpRequests() } returns listOf(httpRequestMethod1, httpRequestMethod2)
        every { springCloudServiceRepository.getMethodIdsByClassId("idc1") } returns listOf("idm1")
        every { springCloudServiceRepository.getServiceNameByMethodId("idm1") } returns "consumer"
        every { springCloudServiceRepository.getServiceNameByMethodId("idm2") } returns "producer"


        // when
        val dependencies = service.getFeignClientMethodDependencies()

        // then
        assertEquals(1, dependencies.size)
        assertEquals("idm1", dependencies[0].caller.targetId)
        assertEquals("idm2", dependencies[0].callee.targetId)

    }


}
