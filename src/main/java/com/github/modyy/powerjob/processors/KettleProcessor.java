package com.github.modyy.powerjob.processors;

import com.github.modyy.kettle.server.JobServer;
import com.github.modyy.kettle.server.TranServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

import java.util.Collections;

/**
 * 
 */
@Slf4j
@Component
public class KettleProcessor implements BasicProcessor {
    @Autowired
    private JobServer JobServer;
    @Autowired
    private TranServer  TranServer;
    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        OmsLogger omsLogger = context.getOmsLogger();
        omsLogger.info("StandaloneProcessorDemo start process,context is {}.", context);
        omsLogger.info("Notice! If you want this job process failed, your jobParams need to be 'failed'");



        omsLogger.info("Let's test the exception~");
        // 测试异常日志
        try {
            Collections.emptyList().add("277");
        }catch (Exception e) {
            omsLogger.error("oh~it seems that we have an exception~", e);
        }

        System.out.println("================ StandaloneProcessorDemo#process ================");
        System.out.println(context.getJobParams());
        // 根据控制台参数判断是否成功
        boolean success = !"failed".equals(context.getJobParams());
        omsLogger.info("StandaloneProcessorDemo finished process,success: .", success);

        omsLogger.info("anyway, we finished the job successfully~Congratulations!");
        return new ProcessResult(success, context + ": " + success);
    }
}
