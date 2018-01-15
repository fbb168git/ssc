package com.fbb.statistic;

import com.fbb.bean.Lottery;

public class BigStatisticModel extends StatisticOpertaion {
	
	public BigStatisticModel() {
		this.name = "连大";
	}

	@Override
	public boolean computeStatus(Lottery lottery, StatisticUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball >= 5) {
			return true;
		} else {
			return false;
		}
	}

}
