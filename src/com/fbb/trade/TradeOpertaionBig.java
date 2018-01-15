package com.fbb.trade;

import com.fbb.bean.Lottery;

public class TradeOpertaionBig extends TradeOpertaion{
	
	public TradeOpertaionBig() {
		super();
		this.name = "大";
	}

	@Override
	public boolean isFillTradeCondition(Lottery lottery, TradeUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball >= 5) {
			return true;
		} else {
			return false;
		}
	}
}
