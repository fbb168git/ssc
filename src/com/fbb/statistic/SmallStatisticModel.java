package com.fbb.statistic;

import com.fbb.bean.Lottery;

public class SmallStatisticModel extends StatisticOpertaion {
	
	public SmallStatisticModel() {
		this.name = "连小";
	}

	@Override
	public boolean computeStatus(Lottery lottery, StatisticUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball < 5) {
			return true;
		} else {
			return false;
		}
	}

}
