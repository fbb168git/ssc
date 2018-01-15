package com.fbb.statistic;

import com.fbb.bean.Lottery;

public class DanStatisticModel extends StatisticOpertaion {
	
	public DanStatisticModel() {
		this.name = "连单";
	}

	@Override
	public boolean computeStatus(Lottery lottery, StatisticUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball % 2  == 1) {
			return true;
		} else {
			return false;
		}
	}

}
