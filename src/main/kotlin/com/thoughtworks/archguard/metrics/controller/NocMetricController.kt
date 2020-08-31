package com.thoughtworks.archguard.metrics.controller

import com.thoughtworks.archguard.metrics.domain.MetricsService
import com.thoughtworks.archguard.module.domain.model.JClassVO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects/{systemId}/metric/noc")
class NocMetricController(val metricsService: MetricsService) {
    @GetMapping("/class")
    fun getClassNocMetric(@PathVariable("systemId") systemId: Long,
                          @RequestParam className: String,
                          @RequestParam moduleName: String): Int {
        return metricsService.getClassNoc(systemId, JClassVO(className, moduleName))
    }
}
