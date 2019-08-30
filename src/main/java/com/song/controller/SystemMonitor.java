/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.controller 
 * @author: dongsong   
 * @date: 2019年8月12日 下午8:50:46 
 */
package com.song.controller;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import com.alibaba.fastjson.JSON;
import com.sun.management.OperatingSystemMXBean;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: SystemMonitor.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: dongsong
 * @date: 2019年8月12日 下午8:50:46
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年8月12日
 *        dongsong v1.0.0 修改原因
 */
@Controller
@RequestMapping("/SystemMonitor")
public class SystemMonitor {
	@RequestMapping(value="/init",produces = "application/json;charset=UTF-8")
	public void init() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			try {

				SystemInfo systemInfo = new SystemInfo();

				OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
				MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
				// 椎内存使用情况
				MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

				// 初始的总内存
				long initTotalMemorySize = memoryUsage.getInit();
				// 最大可用内存
				long maxMemorySize = memoryUsage.getMax();
				// 已使用的内存
				long usedMemorySize = memoryUsage.getUsed();

				// 操作系统
				String osName = System.getProperty("os.name");
				// 总的物理内存
				String totalMemorySize = new DecimalFormat("#.##")
						.format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
				// 剩余的物理内存
				String freePhysicalMemorySize = new DecimalFormat("#.##")
						.format(osmxb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
				// 已使用的物理内存
				String usedMemory = new DecimalFormat("#.##").format(
						(osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024)
						+ "G";
				// 获得线程总数
				ThreadGroup parentThread;
				for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
						.getParent() != null; parentThread = parentThread.getParent()) {

				}

				int totalThread = parentThread.activeCount();

				// 磁盘使用情况
				File[] files = File.listRoots();
				for (File file : files) {
					String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024)
							+ "G";
					String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
					String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
					String path = file.getPath();
					System.err.println(path + "总:" + total + ",可用空间:" + un + ",空闲空间:" + free);
					System.err.println("=============================================");
				}

				System.err.println("操作系统:" + osName);
				System.err.println("程序启动时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
				System.err.println("pid:" + System.getProperty("PID"));
				System.err.println("cpu核数:" + Runtime.getRuntime().availableProcessors());
				printlnCpuInfo(systemInfo);
				System.err.println("JAVA_HOME:" + System.getProperty("java.home"));
				System.err.println("JAVA_VERSION:" + System.getProperty("java.version"));
				System.err.println("USER_HOME:" + System.getProperty("user.home"));
				System.err.println("USER_NAME:" + System.getProperty("user.name"));
				System.err.println("初始的总内存(JVM):"
						+ new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M");
				System.err.println(
						"最大可用内存(JVM):" + new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M");
				System.err.println(
						"已使用的内存(JVM):" + new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M");
				System.err.println("总的物理内存:" + totalMemorySize);
				System.err
						.println("总的物理内存:"
								+ new DecimalFormat("#.##").format(
										systemInfo.getHardware().getMemory().getTotal() * 1.0 / 1024 / 1024 / 1024)
								+ "M");
				System.err.println("剩余的物理内存:" + freePhysicalMemorySize);
				System.err
						.println("剩余的物理内存:"
								+ new DecimalFormat("#.##").format(
										systemInfo.getHardware().getMemory().getAvailable() * 1.0 / 1024 / 1024 / 1024)
								+ "M");
				System.err.println("已使用的物理内存:" + usedMemory);
				System.err.println("已使用的物理内存:"
						+ new DecimalFormat("#.##").format((systemInfo.getHardware().getMemory().getTotal()
								- systemInfo.getHardware().getMemory().getAvailable()) * 1.0 / 1024 / 1024 / 1024)
						+ "M");
				System.err.println("总线程数:" + totalThread);
				System.err.println("===========================");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 0, 60, TimeUnit.SECONDS);
	}

	/**
	 * 打印 CPU 信息
	 *
	 * @param systemInfo
	 */
	@RequestMapping(value="/printcpu",produces = "application/json;charset=UTF-8")
	private void printlnCpuInfo(SystemInfo systemInfo) throws InterruptedException {
		CentralProcessor processor = systemInfo.getHardware().getProcessor();
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		// 睡眠1s
		TimeUnit.SECONDS.sleep(1);
		long[] ticks = processor.getSystemCpuLoadTicks();
		long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
				- prevTicks[CentralProcessor.TickType.NICE.getIndex()];
		long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
		long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
		long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
				- prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
		long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
				- prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
		long user = ticks[CentralProcessor.TickType.USER.getIndex()]
				- prevTicks[CentralProcessor.TickType.USER.getIndex()];
		long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
				- prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
		long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
				- prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
		long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
		System.err.println("cpu核数:" + processor.getLogicalProcessorCount());
		System.err.println("cpu系统使用率:" + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
		System.err.println("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
		System.err.println("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
		System.err.println("cpu当前空闲率:" + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
		System.err.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
		System.err.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
	}
	
	/**   
	* @Function: SystemMonitor.java
	* @Description: 返回系统CPU信息，通过首页进行展示
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年8月12日 下午10:34:34 
	*/
	@ResponseBody
	@RequestMapping(value="/SystemCpu",produces = "application/json;charset=UTF-8")
	public String SystemCpu(SystemInfo systemInfo) throws InterruptedException {
		CentralProcessor processor = systemInfo.getHardware().getProcessor();
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		// 睡眠1s
		TimeUnit.SECONDS.sleep(1);
		long[] ticks = processor.getSystemCpuLoadTicks();
		long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
				- prevTicks[CentralProcessor.TickType.NICE.getIndex()];
		long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
		long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
				- prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
		long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
				- prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
		long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
				- prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
		long user = ticks[CentralProcessor.TickType.USER.getIndex()]
				- prevTicks[CentralProcessor.TickType.USER.getIndex()];
		long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
				- prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
		long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
				- prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
		long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
		System.err.println("cpu系统使用率:" + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
		System.err.println("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
		System.err.println("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
		System.err.println("cpu当前空闲率:" + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
		System.err.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
		System.err.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
		Map<String, Object> cpudata = new HashMap<String, Object>();
		cpudata.put("cSys",new DecimalFormat("#.##").format(cSys * 1.0 / totalCpu));
		cpudata.put("user",new DecimalFormat("#.##").format(user * 1.0 / totalCpu));
		cpudata.put("iotwait",new DecimalFormat("#.##").format(iowait * 1.0 / totalCpu));
		cpudata.put("idle",new DecimalFormat("#.##").format(idle * 1.0 / totalCpu));
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strsystime = sf.format(System.currentTimeMillis());//系统当前时间
	    cpudata.put("tem",strsystime);
		return JSON.toJSONString(cpudata);
	}
	
	
	@ResponseBody
	@RequestMapping(value="SystemMemory",produces = "application/json;charset=UTF-8")
	public String systemmemory() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		// 总的物理内存
		String totalMemorySize = new DecimalFormat("#.##")
				.format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024);
		// 剩余的物理内存
		String freePhysicalMemorySize = new DecimalFormat("#.##")
				.format(osmxb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024);
		// 已使用的物理内存
		String usedMemory = new DecimalFormat("#.##").format(
				(osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024);
		Map<String,Object> MemoryInfo=new HashMap<String,Object>();
		//系统内存百分比
		DecimalFormat myformat=new DecimalFormat("#0.00");
		MemoryInfo.put("free",myformat.format(Double.parseDouble(freePhysicalMemorySize)/Double.parseDouble(totalMemorySize)));
		MemoryInfo.put("used",myformat.format(Double.parseDouble(usedMemory)/Double.parseDouble(totalMemorySize)));
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strsystime = sf.format(System.currentTimeMillis());//系统当前时间
	    MemoryInfo.put("tem",strsystime);
	    System.out.println(JSON.toJSONString(MemoryInfo));
		return JSON.toJSONString(MemoryInfo);
	}
	
	//动态系统信息  JVM 内存 物理内存
	@ResponseBody
	@RequestMapping(value="SystemInfo",produces="application/json;charset=UTF-8")
	public String systeminfo() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		// 椎内存使用情况
		MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
		// 初始的总内存
		long initTotalMemorySize = memoryUsage.getInit();
		// 最大可用内存
		long maxMemorySize = memoryUsage.getMax();
		// 已使用的内存
		long usedMemorySize = memoryUsage.getUsed();
		// 操作系统
		String osName = System.getProperty("os.name");
		// 总的物理内存		
		// 获得线程总数
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent()) {
		}
		int totalThread = parentThread.activeCount();
		System.err.println("操作系统:" + osName);
		System.err.println("程序启动时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));	
		System.err.println("cpu核数:" + Runtime.getRuntime().availableProcessors());
	
		System.err.println("初始的总内存(JVM):"
				+ new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M");
		System.err.println(
				"最大可用内存(JVM):" + new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M");
		System.err.println(
				"已使用的内存(JVM):" + new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M");
		
		System.err.println("总线程数:" + totalThread);
		Map<String,Object> systeminfo =new HashMap<String,Object>();
		systeminfo.put("osName", osName);
		systeminfo.put("cpu", Runtime.getRuntime().availableProcessors()+"核");
		systeminfo.put("initjvm",new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M");
		systeminfo.put("usedjvm", new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M");
		systeminfo.put("maxjvm", new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M");
		systeminfo.put("totalThread", totalThread+"个");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String strsystime = sf.format(System.currentTimeMillis());//系统当前时间
	    systeminfo.put("tem",strsystime);
		return JSON.toJSONString(systeminfo);
	}
	
	//动态系统信息  JVM 内存 物理内存
		@ResponseBody
		@RequestMapping(value="jvmInfo",produces="application/json;charset=UTF-8")
		public String jvminfo() {
			MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
			// 椎内存使用情况
			MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
			// 初始的总内存
			long initTotalMemorySize = memoryUsage.getInit();
			// 已使用的内存
			long usedMemorySize = memoryUsage.getUsed();	
			
			Map<String,Object> systeminfo =new HashMap<String,Object>();
			systeminfo.put("usedjvm", new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024));
			systeminfo.put("initjvm",new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024));
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String strsystime = sf.format(System.currentTimeMillis());//系统当前时间
		    systeminfo.put("tem",strsystime);
			return JSON.toJSONString(systeminfo);
		}
	
}
