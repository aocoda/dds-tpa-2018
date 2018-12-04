package web;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.JobDispositivos;

public class Cron {

	public static void main(String[] args) throws Exception {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		scheduler.start();

		JobDetail jobDetail = JobBuilder.newJob(JobDispositivos.class).build();

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
	}
}