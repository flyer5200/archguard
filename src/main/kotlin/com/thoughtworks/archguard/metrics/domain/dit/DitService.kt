package com.thoughtworks.archguard.metrics.domain.dit

import com.thoughtworks.archguard.clazz.domain.JClass

interface DitService {
    fun getDepthOfInheritance(systemId:Long, target: JClass): Int
}
