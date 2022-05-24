package com.thoughtworks.archguard.report.domain.container

interface ContainerServiceRepo {
    fun findDemandBySystemId(systemId: Long): List<ContainerDemand>
    fun findResourceBySystemId(systemId: Long): List<ContainerResource>
    fun findSystems(ids: List<String>): List<ContainerServiceDO>
}
