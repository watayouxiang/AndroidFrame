import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Project
import org.gradle.api.ProjectEvaluationListener
import org.gradle.api.ProjectState
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

class ConfigUtils {


    static addBuildListener(Gradle g) {
        /*
        setting.gradle
        // 设置配置完成
        settingsEvaluated
        // 项目加载完成
        projectsLoaded
        // 根目录的 build.gradle
        root project 'AucFrameTemplate' -> root
        // 子工程的 build.gradle
        project ':lib:base' -> lib
        project ':lib:common' -> lib
        project ':feature:feature0:app' -> app
        project ':feature:feature0:export' -> lib
        project ':feature:feature0:pkg' -> lib
        project ':feature:feature1:app' -> app
        project ':feature:feature1:export' -> lib
        project ':feature:feature1:pkg' -> lib
        project ':feature:launcher:app' -> app
        // 项目评估完成
        projectsEvaluated
        // 构建结束
        buildFinished
         */
        g.addBuildListener(new BuildListener() {
            /**
             * 构建开始
             * @param gradle
             */
            @Override
            void buildStarted(Gradle gradle) {
                GLog.d("buildStarted")
            }

            /**
             * 设置评估完成
             * @param settings
             */
            @Override
            void settingsEvaluated(Settings settings) {
                GLog.d("settingsEvaluated")
                includeModule(settings)
            }

            /**
             * 项目加载完成
             * @param gradle
             */
            @Override
            void projectsLoaded(Gradle gradle) {
                GLog.d("projectsLoaded")

                generateDep(gradle)

                gradle.addProjectEvaluationListener(new ProjectEvaluationListener() {
                    /**
                     * 执行在各 module 的 build.gradle 之前
                     * @param project
                     */
                    @Override
                    void beforeEvaluate(Project project) {
                        //GLog.d("beforeEvaluate")
                        if (project.subprojects.isEmpty()) {// 定位到具体 project
                            if (project.name == "app") {
                                GLog.l(project.toString() + " applies buildApp.gradle")
                                project.apply {
                                    from "${project.rootDir.path}/buildApp.gradle"
                                }
                            } else {
                                GLog.l(project.toString() + " applies buildLib.gradle")
                                project.apply {
                                    from "${project.rootDir.path}/buildLib.gradle"
                                }
                            }
                        }
                    }

                    /**
                     * 执行在各 module 的 build.gradle 之后
                     * @param project
                     * @param state
                     */
                    @Override
                    void afterEvaluate(Project project, ProjectState state) {
                        //GLog.d("afterEvaluate")
                    }
                })
            }

            /**
             * 项目评估完成
             * @param gradle
             */
            @Override
            void projectsEvaluated(Gradle gradle) {
                GLog.d("projectsEvaluated")
            }

            /**
             * 构建结束
             * @param buildResult
             */
            @Override
            void buildFinished(BuildResult buildResult) {
                GLog.d("buildFinished")
            }
        })
    }

    /**
     * 在 settings.gradle 中 根据 appConfig 和 pkgConfig 来 include 本地模块
     */
    private static includeModule(Settings settings) {
        /*
        include ':feature:launcher:app'
        include ':feature:feature1:app'
        include ':feature:feature0:app'
        include ':feature:feature1:pkg'
        include ':feature:feature0:pkg'
        include ':feature:feature1:export'
        include ':feature:feature0:export'
        include ':lib:common'
        include ':lib:base'
         */

        /*settings.include ':lib:base', ':lib:common',
                ':feature:feature0:export', ':feature:feature1:export',
                ':feature:feature0:pkg', ':feature:feature1:pkg',
                ':feature:feature0:app', ':feature:feature1:app', ':feature:launcher:app'*/

        def config = getDepConfigByFilter(new DepConfigFilter() {
            @Override
            boolean accept(String name, DepConfig config) {
                // 如果最终是 app 的话
                if (name.endsWith('.app')) {
                    // 获取 app 模块的名字
                    def appName = name.substring('feature.'.length(), name.length() - 4)
                    // 如果 Config.appConfig 中不存在，那就不让它进依赖
                    if (!Config.appConfig.contains(appName)) {
                        config.isApply = false
                    }
                }
                // 如果 Config.pkgConfig 不为空，说明是 pkg 调试模式
                if (!Config.pkgConfig.isEmpty()) {
                    // 如果是 pkg 的话
                    if (name.endsWith('.pkg')) {
                        // 获取 pkg 模块的名字
                        def pkgName = name.substring('feature.'.length(), name.length() - 4)
                        // 如果 Config.pkgConfig 中不存在，那就不让它进依赖
                        if (!Config.pkgConfig.contains(pkgName)) {
                            config.isApply = false
                        }
                    }
                }
                // 过滤出本地并且 apply 的模块
                if (!config.isApply) return false
                if (!config.useLocal) return false
                if (config.localPath == "") return false
                return true
            }
        }).each { _, cfg ->
            // 把本地模块 include 进去
            settings.include cfg.localPath
        }
        GLog.l("includeModule = ${GLog.object2String(config)}")
    }

    /**
     * 根据 depConfig 生成 dep
     */
    private static generateDep(Gradle gradle) {
        def config = getDepConfigByFilter(new DepConfigFilter() {
            @Override
            boolean accept(String name, DepConfig config) {
                if (config.useLocal) {
                    // 如果使用的是本地模块，那么把它转化为 project
                    config.dep = gradle.rootProject.findProject(config.localPath)
                } else {
                    // 如果是远端依赖，那就直接使用远端依赖即可
                    config.dep = config.remotePath
                }
                return true
            }
        })
        GLog.l("generateDep = ${GLog.object2String(config)}")
    }

    // =============================================================================================
    // 获取 DepConfig
    // =============================================================================================

    /**
     * 根据过滤器来获取 DepConfig
     */
    static Map<String, DepConfig> getDepConfigByFilter(DepConfigFilter filter) {
        return _getDepConfigByFilter("", Config.depConfig, filter)
    }

    private static _getDepConfigByFilter(String namePrefix, Map map, DepConfigFilter filter) {
        // 结果 Map
        def depConfigList = [:]
        for (Map.Entry entry : map.entrySet()) {
            def (name, value) = [entry.getKey(), entry.getValue()]
            if (value instanceof Map) {
                // 如果值是 Map 类型就加到结果 Map 中
                namePrefix += (name + '.')
                depConfigList.putAll(_getDepConfigByFilter(namePrefix, value, filter))
                namePrefix -= (name + '.')
                continue
            }
            def config = value as DepConfig
            if (filter == null || filter.accept(namePrefix + name, config)) {
                // 符合过滤条件就加到结果 Map 中
                depConfigList.put(namePrefix + name, config)
            }
        }
        return depConfigList
    }

    interface DepConfigFilter {
        boolean accept(String name, DepConfig config);
    }

    // =============================================================================================
    // 获取可用的 pkg 和 export
    // =============================================================================================

    static getApplyPkgs() {
        def applyPkgs = getDepConfigByFilter(new DepConfigFilter() {
            @Override
            boolean accept(String name, DepConfig config) {
                if (!config.isApply) return false
                return name.endsWith(".pkg")
            }
        })
        GLog.d("getApplyPkgs = ${GLog.object2String(applyPkgs)}")
        return applyPkgs
    }

    static getApplyExports() {
        def applyExports = getDepConfigByFilter(new DepConfigFilter() {
            @Override
            boolean accept(String name, DepConfig config) {
                if (!config.isApply) return false
                return name.endsWith(".export")
            }
        })
        GLog.d("getApplyExports = ${GLog.object2String(applyExports)}")
        return applyExports
    }

}