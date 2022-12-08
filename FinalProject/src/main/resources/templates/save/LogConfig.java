package templates.save;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;


public class LogConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RollingFileAppender RollingFileAppender() {
	    LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

	    PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
	    logEncoder.setContext(logCtx);
	    logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
	    logEncoder.start();

	    ConsoleAppender logConsoleAppender = new ConsoleAppender();
	    logConsoleAppender.setContext(logCtx);
	    logConsoleAppender.setName("console");
	    logConsoleAppender.setEncoder(logEncoder);
	    logConsoleAppender.start();

	    logEncoder = new PatternLayoutEncoder();
	    logEncoder.setContext(logCtx);
	    logEncoder.setPattern("%-12date{YYYY-MM-dd HH:mm:ss.SSS} %-5level - %msg%n");
	    logEncoder.start();

	    RollingFileAppender logFileAppender = new RollingFileAppender();
	    logFileAppender.setContext(logCtx);
	    logFileAppender.setName("logFile");
	    logFileAppender.setEncoder(logEncoder);
	    logFileAppender.setAppend(true);
	    logFileAppender.setFile("logs/logfile.log");

	    TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
	    logFilePolicy.setContext(logCtx);
	    logFilePolicy.setParent(logFileAppender);
	    logFilePolicy.setFileNamePattern("logs/logfile-%d{yyyy-MM-dd_HH}.log");
	    logFilePolicy.setMaxHistory(7);
	    logFilePolicy.start();

	    logFileAppender.setRollingPolicy(logFilePolicy);
	    logFileAppender.start();
	    
		return logFileAppender;
	  }
	
	
}
