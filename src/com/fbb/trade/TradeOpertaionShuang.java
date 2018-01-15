package com.fbb.trade;

import com.fbb.bean.Lottery;

public class TradeOpertaionShuang extends TradeOpertaion{
	
	public TradeOpertaionShuang() {
		this.name = "双";
	}

	@Override
	public boolean isFillTradeCondition(Lottery lottery, TradeUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball % 2  == 0) {
			return true;
		} else {
			return false;
		}
	}
}
