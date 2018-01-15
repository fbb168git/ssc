package com.fbb.statistic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.fbb.bean.Lottery;
import com.fbb.bean.SerialBall;

public abstract class StatisticOpertaion {
	public String name = "未知";
	public static int DANGERCOUNT = 8;
	public static int TIPSCOUNT = 6;
	
	private ArrayList<StatisticUnit> mUnits = new ArrayList<StatisticUnit>();//0 个位 1十位 2千位 3万位
	
	public StatisticOpertaion() {
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
			} else {
				notifyStatistics(unit);//cache.get(0) 出现的开始期数
				unit.cache.clear();
			}
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
