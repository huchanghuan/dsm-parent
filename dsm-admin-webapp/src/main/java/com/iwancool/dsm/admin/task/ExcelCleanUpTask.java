package com.iwancool.dsm.admin.task;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ExcelCleanUpTask {
	
	private final Logger log = Logger.getLogger(ExcelCleanUpTask.class);
	
	@Resource(name = "config")
	private Properties config;

	/**
	 * 定时器入口
	 */
	public void execute () {
		long startUtc = System.currentTimeMillis();
		log.debug("start ExcelCleanUpTask...");
		
		try{
			startTask();
		} catch (Exception e) {
			log.error("fail to execute ExcelCleanUpTask...");
			e.printStackTrace();
		}
		log.debug("complete ExcelCleanUpTask...");
		long endUtc = System.currentTimeMillis();
		log.debug("耗时：" + (endUtc - startUtc) + "ms");
	}

	private void startTask() {
		File f = new File(ExcelCleanUpTask.class.getResource("/").toString()).getParentFile().getParentFile();
		String folderName = config.getProperty("excel_folder");
		File file = new File(f, folderName);
		try {
			FileUtils.cleanDirectory(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(FileUtils.getUserDirectoryPath());
	}
}
