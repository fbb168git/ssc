package com.fbb.trade;

import java.util.ArrayList;

import com.fbb.bean.Lottery;
import com.fbb.util.LogUtil;

public class TradeUnit {
	public String name = "个位";
	public int position = -1;
	
	public ArrayList<Lottery> cache = new ArrayList<Lottery>();
	
	
	private boolean openTrade = false;
	public static int startTradeCountIfOpen = 2;
	public static int endTradeCountIfOpen = 8;
	
	public TradeDetail tradeMode = new TradeDetail();
	
	public int tradeCountDown = 20;
	
	public TradeUnit(String name, int position) {
		super();
		this.name = name;
		this.position = position;
	}

	public void init() {
		cache.clear();
		if(tradeMode.hasTrade()) {
			throw new RuntimeException("TradeUnit init error");
		}
	}
	
	public void openTrade() {
		openTrade = true;
		tradeCountDown = 20;
	}
	
	public void closeTrade() {
		openTrade = false;
	}
	
	public boolean isOpenTrade(){
		return openTrade;
	}

	public void onNewLottery(Lottery lottery, boolean lotteryStatus, String operationName) {
		if(isOpenTrade()) {
			if(lotteryStatus) {
				if(tradeMode.hasTrade()) {
					LogUtil.d(lottery.getLottery_code()+"  "+ name+"开"+cache.size()+"连 "+operationName);
					tradeMode.continuteTrade(lottery,name + operationName);
				} else {
					if(cache.size() >= startTradeCountIfOpen) {
						
						int num = new Integer(lottery.getLottery_code().substring(8));
						if(num >=0 && num <=23 && (23 - (TradeUnit.endTradeCountIfOpen - TradeUnit.startTradeCountIfOpen) < num)) {
							//无法完成整个倍投流程   不开始投注
							LogUtil.d("无法完成整个倍投流程，不交易");
						} else {
							LogUtil.d(lottery.getLottery_code()+"  "+ name+"开"+cache.size()+"连 "+operationName);
							tradeMode.beginTrade(lottery,name+operationName);//开始投注
						}
					} else {
						//未满足投注条件
					}
				}
			} else {
				if(tradeMode.hasTrade()) {
					tradeMode.endTrade(lottery,name+operationName);
				} else {
					//未开始交易
				}
			}
		} else {
			if(lotteryStatus) {
				if(tradeMode.hasTrade()) {
					LogUtil.d(lottery.getLottery_code()+"  "+ name+"开"+cache.size()+"连 "+operationName);
					tradeMode.continuteTrade(lottery,name+operationName);
				}  else {
					// 交易已关闭，即使条件满足也不再开始投注
				}
			} else {
				if(tradeMode.hasTrade()) {
					tradeMode.endTrade(lottery,name+operationName);
				}  else {
					// 交易已关闭，且未满足投注提交
				}
			}
		}
		
	}
	
}
