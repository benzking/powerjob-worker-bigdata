package com.modyy.kettle.init;

import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import org.springframework.core.annotation.Order;


/**
 * kettle初始化
 *
 * @author lyf
 */
@Component
@Slf4j
@Order(-1)
@DependsOn("kettleConfig")
public class KettleEnvironmentInit implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // 自定义环境初始化
        environmentInit();
        // kettle环境初始化
        KettleEnvironment.init();
    }

    private void environmentInit() {
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
}
