package com.fbb.trade;

import java.util.ArrayList;

import com.fbb.bean.Lottery;
import com.fbb.bean.Trade;
import com.fbb.util.LogUtil;


public class TradeDetail {
	private float rate = 1.94f;
	private boolean getGift = true;
	
	private ArrayList<Trade> mTradeList = new ArrayList<Trade>();
	
	public void beginTrade(Lottery lottery, String operationName) {
		if (mTradeList.size() > 0) throw new RuntimeException("遗留交易数据");
		float money = MoneyPool.getMoney(0);
		mTradeList.add(new Trade(lottery,money));
		LogUtil.d("------"+operationName+"开始交易"+"------");
		LogUtil.d("投注 "+operationName +  " " + money);
	}
	
	public static int zhishunCount = 0;
	public void continuteTrade(Lottery lottery, String operationName) {//跟号  直到开出8连
		if(mTradeList.size() >= (TradeUnit.endTradeCountIfOpen - TradeUnit.startTradeCountIfOpen)) {
			LogUtil.d("eeeeeee"+operationName+"止损eeeeeeee");
			zhishunCount++;
			System.out.println(lottery.getLottery_code()+"-1期止损 "+operationName);
			getGift = false;
			endTrade(lottery,operationName);
		} else {
			float money = MoneyPool.getMoney(mTradeList.size());
			mTradeList.add(new Trade(lottery,money));
			LogUtil.d("跟注 "+operationName +  " " + money);
		}
	}
	
	public void endTrade(Lottery lottery, String operationName) {
		if(getGift) {
			float getMoney = mTradeList.get(mTradeList.size() - 1).getTradeAmount() * rate;
			MoneyPool.putMoney(getMoney);
			LogUtil.d("盈利 "+operationName);
		} else {
			getGift = true;
		}
		mTradeList.clear();
		LogUtil.d("------"+operationName+"结束交易"+"------");
		MoneyPool.printCurrentAmount();
		LogUtil.d("------"+"-------"+"------");
	}
	
	public boolean hasTrade() {
		return mTradeList.size() > 0;
	}
}
