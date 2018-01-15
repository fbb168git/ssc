package com.fbb.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.fbb.bean.Lottery;
import com.fbb.bean.SerialBall;
import com.fbb.statistic.StatisticUnit;
import com.fbb.util.LogUtil;
import com.fbb.util.SMSUtil;

public abstract class ListenerOpertaion {
	public String name = "未知";
	public static int DANGERCOUNT = 8;
	public static int TIPSCOUNT = 6;
	
	private ArrayList<StatisticUnit> mUnits = new ArrayList<StatisticUnit>();//0 个位 1十位 2千位 3万位
	
	public ListenerOpertaion() {
		super();
		mUnits.add(new StatisticUnit("万位",0));
		mUnits.add(new StatisticUnit("千位",1));
		mUnits.add(new StatisticUnit("百位",2));
		mUnits.add(new StatisticUnit("十位",3));
		mUnits.add(new StatisticUnit("个位",4));
	}

	public void init() {
		for(StatisticUnit unit : mUnits) {
			unit.init();
		}
	}
	
	public void onNewLottery(Lottery lottery) {
		for(StatisticUnit unit : mUnits) {
			boolean lotteryStatus = computeStatus(lottery, unit);
			if (lotteryStatus) {
				unit.cache.add(lottery);
//				notifyListener(unit);
			} else {
				notifyStatistics(unit);//cache.get(0) 出现的开始期数
				judgeIsOpenTrade(unit);
				unit.cache.clear();
			}
		}
	}

	private void judgeIsOpenTrade(StatisticUnit unit) {
		if (unit.cache.size() >= DANGERCOUNT) {
			
		}
		
	}

	public abstract boolean computeStatus(Lottery lottery,StatisticUnit unit);

	// 统计
	private void notifyStatistics(StatisticUnit unit) {
		if (unit.cache.size() >= DANGERCOUNT) {
			if (unit.statisticsCount.get(unit.cache.size()) == null) {
				unit.statisticsCount.put(unit.cache.size(), 1);
				
				ArrayList<Lottery> arrayList = new ArrayList<Lottery>();
				arrayList.add(unit.cache.get(0));
				unit.statisticsTime.put(unit.cache.size(), arrayList);
			} else {
				unit.statisticsCount.put(unit.cache.size(), unit.statisticsCount.get(unit.cache.size()) + 1);
				
				unit.statisticsTime.get(unit.cache.size()).add(unit.cache.get(0));
			}
			
			unit.mDangerList.add(unit.cache.get(0));
			
			unit.mSerialBallList.add(new SerialBall(unit.cache.get(0),unit.cache.get(unit.cache.size() - 1),unit.cache.size()));
		}
	}
	
	private void notifyListener(StatisticUnit unit) {
		if(unit.cache.size() >= TIPSCOUNT) {
			Lottery lottery = unit.cache.get(unit.cache.size() - 1);
			String smsMsg = lottery.getLottery_code().substring(2) + unit.name+"出"+unit.cache.size()+"连"+name;
			String logMsg = smsMsg + " " + lottery.getLottery_time();
			LogUtil.d(logMsg);
			SMSUtil.sendSms(smsMsg, lottery.getLottery_nums(), lottery.getLottery_time(),"15838948865");
			if(unit.cache.size() >= DANGERCOUNT) {
				SMSUtil.sendSms(smsMsg, lottery.getLottery_nums(), lottery.getLottery_time(),"18701501627");
			}
		}
	}
	
	public void printResult() {
		for(StatisticUnit unit : mUnits) {
			System.out.println("-------------"+unit.name+" | "+name+"-------------");
			Set<Entry<Integer,Integer>> entrySet = unit.statisticsCount.entrySet();
			Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
			while(iterator.hasNext()) {
				Entry<Integer, Integer> next = iterator.next();
				Integer key = next.getKey();
				Integer value = next.getValue();
				System.out.println(key+"连出现"+value+"次");
			}
		}
	}

	public ArrayList<StatisticUnit> getUnits() {
		return mUnits;
	}

	public void setUnits(ArrayList<StatisticUnit> mUnits) {
		this.mUnits = mUnits;
	}
	
	

	
}
