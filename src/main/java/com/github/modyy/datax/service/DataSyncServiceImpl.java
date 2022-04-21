package com.github.modyy.datax.service;

import com.alibaba.datax.core.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class DataSyncServiceImpl implements DataSyncService {
    private static final Logger log = LoggerFactory.getLogger(DataSyncServiceImpl.class);

    @Value("${datax.home}")
    private String dataxHome;
    /**
     * 日志文件保存目录
     */
    @Value("${app.etlLogDir}")
    private String etlLogDir;


    @Override
    public void execute(String jobConfig) {
        System.setProperty("datax.home", dataxHome);
        System.setProperty("now", LocalTime.now().toString());
        String[] dataxArgs = {"-job", dataxHome + "/job/" + jobConfig, "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(dataxArgs);
        } catch (Throwable e) {
            e.printStackTrace();
        }



    }
//
//    public String startJobByJsonStr(String jobJson) {
//
//
//
//            final String tmpFilePath = "/xcloud/gpDataTest/temp_json/jobTmp-" + System.currentTimeMillis() + ".json";
//            // 根据json写入到临时本地文件
//            PrintWriter writer = null;
//            try {
//                writer = new PrintWriter(tmpFilePath, "UTF-8");
//                writer.println(jobJson);
//
//            } catch (FileNotFoundException | UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } finally {
//                if (writer != null) {
//                    writer.close();
//                }
//            }
//
//            try {
//                // 使用临时本地文件启动datax作业
//                Engine.entry(tmpFilePath);
//                //  删除临时文件
////                FileUtil.del(new File(tmpFilePath));
//            } catch (Throwable e) {
////                log.error("\n\n经DataX智能分析,该任务最可能的错误原因是:\n" + ExceptionTracker.trace(e));
//
//                if (e instanceof DataXException) {
//                    DataXException tempException = (DataXException) e;
//                    ErrorCode errorCode = tempException.getErrorCode();
//                    if (errorCode instanceof FrameworkErrorCode) {
//                        FrameworkErrorCode tempErrorCode = (FrameworkErrorCode) errorCode;
//                    }
//                }
//
//            }
//
//
//        return "success";
//    }
//
//
//
//    public String startJobLog(RunJobDto runJobDto) {
//        //取出 jobJson，并转为json对象
//        JSONObject json = JSONObject.parseObject(runJobDto.getJobJson());
//        //根据jobId和当前时间戳生成日志文件名
//        String logFileName = runJobDto.getJobConfigId().toString().concat("_").concat(StrUtil.toString(System.currentTimeMillis()).concat(".log"));
//        String logFilePath = etlLogDir.concat(logFileName);
//        //记录日志
//        JobLog jobLog = new JobLog();
//        jobLog.setJobId(runJobDto.getJobConfigId());
//        jobLog.setLogFilePath(logFilePath);
//        jobLogService.save(jobLog);
//        //将路径放进去
//        json.put(CoreConstant.LOG_FILE_PATH, logFilePath);
//
//        //启动任务
//        return startJobByJsonStr(JSON.toJSONString(json));
//    }

//    @Override
//    public LogResult viewJogLog(Long id, int fromLineNum) {
//        QueryWrapper<JobLog> queryWrapper = new QueryWrapper<>();
//        //根据id获取最新的日志文件路径
//        queryWrapper.lambda().eq(JobLog::getJobId, id).orderByDesc(JobLog::getCreateDate);
//        List<JobLog> list = jobLogService.list(queryWrapper);
//        //取最新的一条记录
//        if (list.isEmpty()) {
//            return new LogResult(1, 1, "没有找到对应的日志文件！", true);
//        } else {
//            //取出路径，读取文件
//            return EtlJobLogger.readLog(list.get(0).getLogFilePath(), fromLineNum);
//        }
//    }
}
