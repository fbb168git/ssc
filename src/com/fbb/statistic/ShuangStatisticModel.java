package com.fbb.statistic;

import com.fbb.bean.Lottery;

public class ShuangStatisticModel extends StatisticOpertaion {
	
	public ShuangStatisticModel() {
		this.name = "连双";
	}

	@Override
	public boolean computeStatus(Lottery lottery, StatisticUnit unit) {
		int ball = lottery.getBalls().get(unit.position);
		if (ball % 2  == 0) {
			return true;
		} else {
			return false;
		}
	}

}
