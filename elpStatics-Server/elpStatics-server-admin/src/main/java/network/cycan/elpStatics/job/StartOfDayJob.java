package network.cycan.elpStatics.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class StartOfDayJob extends QuartzJobBean {
//    private StudentService studentService;

//    @Autowired
//    public StartOfDayJob(StudentService studentService) {
//        this.studentService = studentService;
//    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        // 任务的具体逻辑
    }
}
