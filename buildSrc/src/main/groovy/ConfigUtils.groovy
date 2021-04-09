import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

class ConfigUtils {


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
    static addBuildListener(Gradle g) {
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
    private static includeModule(Settings settings) {
        settings.include ':lib:base', ':lib:common',
                ':feature:feature0:export', ':feature:feature1:export',
                ':feature:feature0:pkg', ':feature:feature1:pkg',
                ':feature:feature0:app', ':feature:feature1:app', ':feature:launcher:app'
    }

}