package com.thoughtworks.archguard.packages.infrastructure

import com.thoughtworks.archguard.packages.domain.PackageRepository
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
class PackageRepositoryImpl : PackageRepository {

    @Autowired
    lateinit var jdbi: Jdbi

    override fun getPackageDependenceByModule(projectId: Long, module: String): List<PackageDependenceDTO> {
        return jdbi.withHandle<List<PackageDependenceDTO>, Nothing> { handle ->
            handle.registerRowMapper(ConstructorMapper.factory(PackageDependenceDTO::class.java))
            handle
                    .createQuery("select a.clzname aClz, b.clzname bClz from JMethod a, JMethod b, _MethodCallees mc " +
                            "where a.id = mc.a and b.id = mc.b and a.module='$module' and b.module='$module' and a.project_id='$projectId' and b.project_id='$projectId' and mc.project_id='$projectId'")
                    .mapTo(PackageDependenceDTO::class.java)
                    .list()
        }
    }
}