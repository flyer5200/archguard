# ArchGuard backend

[![CI](https://github.com/archguard/archguard/actions/workflows/ci.yaml/badge.svg)](https://github.com/archguard/archguard/actions/workflows/ci.yaml)
[![codecov](https://codecov.io/gh/archguard/archguard/branch/master/graph/badge.svg?token=QS5H866CWH)](https://codecov.io/gh/archguard/archguard)
[![GitHub release](https://img.shields.io/github/v/release/archguard/archguard?logo=git&logoColor=white)](https://github.com/archguard/archguard/releases)
[![languages](https://img.shields.io/badge/language-kotlin-blueviolet?logo=kotlin&logoColor=white)](https://www.kotlincn.net/)
[![Java support](https://img.shields.io/badge/Java-12+-green?logo=java&logoColor=white)](https://openjdk.java.net/)
[![License](https://img.shields.io/github/license/archguard/archguard?color=4D7A97&logo=opensourceinitiative&logoColor=white)](https://opensource.org/licenses/MIT)
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-green?label=gitpod&logo=gitpod&logoColor=white)](https://gitpod.io/#https://github.com/archguard/archguard)

> ArchGuard is an architecture governance tool which can analysis architecture in container, component, code level, database, create architecture fitness functions, and test for architecture rules. 

Chinese: ArchGuard 是一个架构治理工具，用于管理和分析组织级别的软件架构。 结合 [C4 模型](https://c4model.com)，进行依赖分析，含容器级别（服务级别）、组件级别（/模块级别）、代码级别、数据库级别等。 
同时，可以创建系统的架构适应度函数，度量系统的各项指标。

- Document: [https://archguard.org/](https://archguard.org/)
- Roadmap: [Roadmap](https://github.com/archguard/archguard/discussions/5)
- Contribute: [Contribute to Archguard](https://archguard.org/contribution)
- SubProjects:
    - [ArchGuard Frontend](https://github.com/archguard/archguard-frontend)
    - [Chapi](https://github.com/modernizing/chapi) source code analysis

Dir:

Screenshots:

<table>
  <tr>
    <td><img src="https://archguard.org/assets/screenshots/archguard-20-overview.png"  alt="1" width = 480px /></td>
    <td><img src="https://archguard.org/assets/screenshots/archguard-20-apilist.png" alt="2" width = 480px /></td>
   </tr> 
   <tr>
      <td><img src="https://archguard.org/assets/screenshots/archguard-20-class.png" alt="3" width = 480px /></td>
      <td><img src="https://archguard.org/assets/screenshots/archguard-20-servicesmap.png" alt="4" width = 480px  /></td>
  </tr>
</table>

Languages parse by [Chapi](https://github.com/modernizing/chapi)

| Features/Languages  | Java | Python | Go  | Kotlin | TypeScript | C   | C#  | Scala | C++ |
|---------------------|------|--------|-----|--------|------------|-----|-----|-------|-----|
| http api decl       | ✅    | 🆕     | 🆕  | ✅      | ✅          | 🆕  | ✅   | 🆕    | 🆕  |
| syntax parse        | ✅    | ✅      | ✅   | ✅      | ✅          | 🆕  | ✅   | ✅     | 🆕  |
| function call       | ✅    | 🆕     |     | ✅      | ✅          |     |     |       |     |
| arch/package        | ✅    |        |     | ✅      | ✅          |     | ✅   | ✅     |     |
| real world validate | ✅    |        |     |        | ✅          |     |     |       |     |

### Chat

关注我们：

<img src="https://archguard.org/wechat.jpg" width="380" height="380" alt="wechat">

欢迎加入我们：

<img src="https://archguard.org/qrcode.jpg" width="380" height="480" alt="wechat">

（PS：如果群满，请添加微信 `phodal02`，并注明 ArchGuard）

License
---

This code is distributed under the MIT license. See `LICENSE` in this directory.
