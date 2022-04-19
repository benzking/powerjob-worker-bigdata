package com.modyy.powerjob.processors;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.util.StrUtil;
import com.alibaba.datax.core.Engine;
import com.alibaba.datax.core.util.container.CoreConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Collections;

/**
 *
 */
@Slf4j
@Component
public class DataXProcessor implements BasicProcessor {
    @Value("${datax.home}")
    private String dataxHome;
    /**
     * 日志文件保存目录
     */
    @Value("${datax.logDir}")
    private String logDir;

    /**
     * 日志文件保存目录
     */
    @Value("${datax.jsonDir}")
    private String jsonDir;

    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        OmsLogger omsLogger = context.getOmsLogger();
        omsLogger.info("StandaloneProcessorDemo start process,context is {}.", context);
        System.out.println("================ StandaloneProcessorDemo#process start================");


        logDir="F:\\CODE\\dataxjson";
        jsonDir="F:\\CODE\\dataxjson";
        JSONObject jobJson=JSON.parseObject(context.getJobParams());

        //根据jobId和当前时间戳生成日志文件名
        String logFileName = context.getTaskId().toString().concat("_").concat(String.valueOf(System.currentTimeMillis())).concat(".log");
        String logFilePath = logDir.concat(logFileName);
        //将路径放进去
        jobJson.put("logFilePath", logFilePath);

        final String tmpFilePath = jsonDir+"/jobTmp-" + System.currentTimeMillis() + ".json";
        // 根据json写入到临时本地文件
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(tmpFilePath, "UTF-8");
            writer.println(jobJson);

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        if(StringUtils.isBlank(dataxHome)){
            dataxHome=getCurrentClasspath()+"/datax";
        }
        System.setProperty("datax.home", dataxHome);
        System.setProperty("now", LocalTime.now().toString());
        String[] datxArgs = {"-job",tmpFilePath , "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);
        } catch (DataXException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("================ StandaloneProcessorDemo#process end ================");
        // 根据控制台参数判断是否成功
        boolean success = !"failed".equals(context.getJobParams());
        omsLogger.info("StandaloneProcessorDemo finished process,success: .", success);

        omsLogger.info("anyway, we finished the job successfully~Congratulations!");

        return new ProcessResult(success, context + ": " + success);
    }
    public static String getCurrentClasspath() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String currentClasspath = classLoader.getResource("").getPath();
        // 当前操作系统
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            // 删除path中最前面的/
            currentClasspath = currentClasspath.substring(1);
        }
        return currentClasspath;
    }

}
