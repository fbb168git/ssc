package com.fbb.statistic;

import java.util.ArrayList;
import java.util.HashMap;

import com.fbb.bean.Lottery;
import com.fbb.bean.SerialBall;

public class StatisticUnit {
	public String name = "未知";
	public int position = -1;
	
	public ArrayList<Lottery> cache = new ArrayList<Lottery>();
	
	public HashMap<Integer, Integer> statisticsCount = new HashMap<Integer, Integer>();//n连出现的次数
	public HashMap<Integer, ArrayList<Lottery>> statisticsTime = new HashMap<Integer, ArrayList<Lottery>>();//n连出现的具体期号序列
	
	public ArrayList<Lottery> mDangerList = new ArrayList<Lottery>();//DANGERCOUNT连以上出现的具体序列
	
	public ArrayList<SerialBall> mSerialBallList = new ArrayList<SerialBall>();//DANGERCOUNT连以上出现的具体序列
	
	public StatisticUnit(String tag, int position) {
		super();
		this.name = tag;
		this.position = position;
	}

	public void init() {
		cache.clear();
	}
	
}