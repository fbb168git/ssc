package com.fbb.trade;

import com.fbb.bean.Lottery;

public class TradeOpertaionDan extends TradeOpertaion{
	
	public TradeOpertaionDan() {
		this.name = "单";
	}

	@Override
	public boolean isFillTradeCondition(Lottery lottery, TradeUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball % 2  == 1) {
			return true;
		} else {
			return false;
		}
	}
}
