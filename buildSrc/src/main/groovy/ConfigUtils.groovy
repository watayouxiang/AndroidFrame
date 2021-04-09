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
                _projectsLoaded(gradle)
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

    private static _projectsLoaded(Gradle gradle) {
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
        settings.include ':lib:base', ':lib:common',
                ':feature:feature0:export', ':feature:feature1:export',
                ':feature:feature0:pkg', ':feature:feature1:pkg',
                ':feature:feature0:app', ':feature:feature1:app', ':feature:launcher:app'
    }

}