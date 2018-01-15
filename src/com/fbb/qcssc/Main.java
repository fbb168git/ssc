package com.fbb.qcssc;

import com.fbb.statistic.SerialBallStatistic;
import com.fbb.trade.TradeTask;
import com.fbb.util.FileUtil;

public class Main {
	private final static int TaskStartTime = 10;

	public static final String historyFilePath = FileUtil.getProjectResPath()
			+ "/history.txt";

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
//				 new GetHistoryDataTask().start();
//				 new SerialBallStatistic().start();
//				new SerialBallListener().start();
				new TradeTask().start();
			}
		}).start();
		
		
//		SerialBallListener listenerTask = new SerialBallListener();
//		
//		Calendar cal = Calendar.getInstance();
//		int DAY_OF_MONTH = cal.get(Calendar.DAY_OF_MONTH);
//		int HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
//		int MINUTE = cal.get(Calendar.MINUTE);
//		LogUtil.d("currentTime:" + DAY_OF_MONTH + "号 " + HOUR_OF_DAY + ":"
//				+ MINUTE);
//		if(HOUR_OF_DAY >= 0 && HOUR_OF_DAY <= 1) {
//			listenerTask.run();
//		}
//		cal.set(Calendar.HOUR_OF_DAY, TaskStartTime);
//		cal.set(Calendar.MINUTE, 5);
//		cal.set(Calendar.SECOND, 0);
//
//		Timer mainTimer = new Timer();
//		
//		Date time = cal.getTime();
//		mainTimer.scheduleAtFixedRate(listenerTask, time,
//				1000 * 60 * 60 * 24);

	}
}
