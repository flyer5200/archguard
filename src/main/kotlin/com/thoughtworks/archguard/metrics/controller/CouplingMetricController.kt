package com.thoughtworks.archguard.metrics.controller

import com.thoughtworks.archguard.metrics.domain.coupling.ClassCoupling
import com.thoughtworks.archguard.metrics.domain.coupling.CouplingService
import com.thoughtworks.archguard.metrics.domain.coupling.ModuleCoupling
import com.thoughtworks.archguard.metrics.domain.coupling.PackageCoupling
import com.thoughtworks.archguard.module.domain.LogicModuleRepository
import com.thoughtworks.archguard.module.domain.model.JClassVO
import com.thoughtworks.archguard.module.domain.model.PackageVO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/metric/coupling")
class CouplingMetricController(val couplingService: CouplingService, val logicModuleRepository: LogicModuleRepository) {
    @GetMapping("/class")
    fun getClassCouplingMetric(@RequestParam className: String, @RequestParam moduleName: String): ClassCoupling {
        return couplingService.calculateClassCoupling(JClassVO(className, moduleName))
    }

    @GetMapping("/package")
    fun getPackageCouplingMetric(@RequestParam packageName: String, @RequestParam moduleName: String): PackageCoupling {
        return couplingService.calculatePackageCoupling(PackageVO(packageName, moduleName))
    }

    @GetMapping("/module")
    fun getModuleCouplingMetric(@RequestParam moduleName: String): ModuleCoupling {
        return couplingService.calculateModuleCoupling(logicModuleRepository.get(moduleName))
    }

    @PostMapping("/persist")
    @ResponseStatus(HttpStatus.OK)
    fun persistCouplingMetric() {
        couplingService.persistAllClassCouplingResults()
    }
}