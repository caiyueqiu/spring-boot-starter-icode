package com.icode.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/28 8:51
 */
@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        log.info("正在进行数据备份，备份时间：{}", formatDate);
    }
}
