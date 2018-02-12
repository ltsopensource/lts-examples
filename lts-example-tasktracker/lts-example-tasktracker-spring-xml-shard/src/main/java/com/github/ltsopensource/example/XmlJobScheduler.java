package com.github.ltsopensource.example;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robert HG (254963746@qq.com)on 12/21/15.
 */
public class XmlJobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlJobScheduler.class);

    @Autowired
    SpringBean springBean;

    public Result runJob1(JobContext jobContext) throws Throwable {
        try {
            Thread.sleep(1000L);

            springBean.hello();

            LOGGER.info("runJob1 我要执行：" + jobContext.getJob());
            BizLogger bizLogger = jobContext.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志啊啊啊啊啊");

        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }

    public Result runJob2(JobContext jobContext) throws Throwable {
        try {
            springBean.hello();

            LOGGER.info("runJob2 我要执行");
            BizLogger bizLogger = jobContext.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志啊啊啊啊啊");
        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }

}
