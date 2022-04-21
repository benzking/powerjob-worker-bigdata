package tech.powerjob.samples;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * kettle初始化
 *
 * @author lyf
 */
@Component
//@DependsOn("kettleConfig")
public class KettleEnvironmentInit implements  ApplicationRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(KettleEnvironmentInit.class);
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("KETTLE_PLUGIN_PACKAGES");
//        System.out.println("KettleEnvironmentInit 我是启动时加载...");
//
//        // 自定义环境初始化
//        environmentInit();
//        // kettle环境初始化
//        KettleEnvironment.init();
//        System.out.println("KETTLE_PLUGIN_BASE_FOLDERS");
//    }

    private void environmentInit() {
        System.out.println("KettleEnvironmentInit 我是1111111111启动时加载...");
//        log.info("KETTLE HOME:" + KettleConfig.kettleHome);
//        System.getProperties().put("KETTLE_HOME", KettleConfig.kettleHome);
//        if (StringUtil.hasText(KettleConfig.kettlePluginPackages)) {
//            log.info("KETTLE_PLUGIN_PACKAGES:" + KettleConfig.kettlePluginPackages);
//            System.getProperties().put("KETTLE_PLUGIN_PACKAGES", FileUtil.replaceSeparator(KettleConfig.kettlePluginPackages));
//            System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS",  FileUtil.replaceSeparator(KettleConfig.kettlePluginPackages));
//            // 加载Kettle插件
//            StepPluginType.getInstance().getPluginFolders().add(new PluginFolder(KettleConfig.kettlePluginPackages, false, true));
//        }
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("KettleEnvironmentInit Application started with option names : {}");
        System.out.println("KettleEnvironmentInit 我是启动时加载...");
        LOG.info("KettleEnvironmentInit Increment counter");

    }
}
