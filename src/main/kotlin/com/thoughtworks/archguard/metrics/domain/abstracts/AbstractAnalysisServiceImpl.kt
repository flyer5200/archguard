package com.thoughtworks.archguard.metrics.domain.abstracts

import com.thoughtworks.archguard.clazz.domain.JClassRepository
import com.thoughtworks.archguard.module.domain.LogicModuleRepository
import com.thoughtworks.archguard.module.domain.getModule
import com.thoughtworks.archguard.module.domain.model.LogicModule
import com.thoughtworks.archguard.module.domain.model.PackageVO
import org.springframework.stereotype.Service

@Service
class AbstractAnalysisServiceImpl(val jClassRepository: JClassRepository, val logicModuleRepository: LogicModuleRepository) : AbstractAnalysisService {
    override fun calculatePackageAbstractRatio(systemId: Long, packageVO: PackageVO): PackageAbstractRatio {
        val classes = jClassRepository.getAllBysystemId(systemId)
        val classesBelongToPackage = classes.filter { packageVO.containClass(it.toVO()) }
        val abstractClassesBelongToPackage = classesBelongToPackage.filter { it.isAbstractClass() || it.isInterface() }
        return PackageAbstractRatio(abstractClassesBelongToPackage.size.toDouble() / classesBelongToPackage.size, packageVO)
    }

    override fun calculateModuleAbstractRatio(systemId: Long, logicModule: LogicModule): ModuleAbstractRatio {
        val classes = jClassRepository.getAllBysystemId(systemId)
        val logicModules = logicModuleRepository.getAllBysystemId(systemId)
        val classesBelongToModule = classes.filter { getModule(logicModules, it.toVO()).contains(logicModule) }
        val abstractClassesBelongToModule = classesBelongToModule.filter { it.isAbstractClass() || it.isInterface() }
        return ModuleAbstractRatio(abstractClassesBelongToModule.size.toDouble() / classesBelongToModule.size, logicModule)
    }
}
