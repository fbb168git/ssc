package com.fbb.trade;

import com.fbb.bean.Lottery;

public class TradeOpertaionSmall extends TradeOpertaion{
	
	public TradeOpertaionSmall() {
		this.name = "小";
	}

	@Override
	public boolean isFillTradeCondition(Lottery lottery, TradeUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball < 5) {
			return true;
		} else {
			return false;
		}
	}
}
