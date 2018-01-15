package com.fbb.trade;

import java.util.ArrayList;

import com.fbb.bean.Lottery;
import com.fbb.util.LogUtil;

public abstract class TradeOpertaion {
	public String name = "未知";//大小单双
	public static int DANGERCOUNT = 7;
	
	private ArrayList<TradeUnit> mUnits = new ArrayList<TradeUnit>();//0 个位 1十位 2千位 3万位
	
	public TradeOpertaion() {
		super();
		mUnits.add(new TradeUnit("万位",0));
		mUnits.add(new TradeUnit("千位",1));
		mUnits.add(new TradeUnit("百位",2));
		mUnits.add(new TradeUnit("十位",3));
		mUnits.add(new TradeUnit("个位",4));
	}

	public void init() {
		for(TradeUnit unit : mUnits) {
			unit.init();
		}
	}
	
	public void onNewLottery(Lottery lottery) {
		for(TradeUnit unit : mUnits) {
			
			boolean lotteryStatus = isFillTradeCondition(lottery, unit);
			
			if(unit.isOpenTrade()) {
				if(unit.tradeCountDown < 0) {
					LogUtil.d(lottery.getLottery_code() + " 交易关闭,"+unit.name +" " +name);
					unit.closeTrade();
				} else {
					unit.tradeCountDown --;
				}
			}
			
			if (lotteryStatus) {
				unit.cache.add(lottery);
				unit.onNewLottery(lottery, lotteryStatus, name);
			} else {
				unit.onNewLottery(lottery, lotteryStatus, name);
				judgeIsOpenTrade(unit,lottery);
				unit.cache.clear();
			}
		}
	}
	
	int zhisunCount = 0;
	private void judgeIsOpenTrade(TradeUnit unit,Lottery lottery) {
		if (unit.cache.size() >= DANGERCOUNT) {
			if(!unit.isOpenTrade()) {
				LogUtil.d(unit.cache.get(unit.cache.size() - 1).getLottery_code()+"  "+unit.name+"开"+unit.cache.size()+"连"+name+",交易"+20+"期内开启");
				unit.openTrade();
			} else {
				zhisunCount++;
				LogUtil.d("止损次数："+zhisunCount);
			}
		}
		
	}

	public abstract boolean isFillTradeCondition(Lottery lottery,TradeUnit unit);

	
	

	
}
