package com.fbb.trade;

import com.fbb.util.LogUtil;

public class MoneyPool {

	private static final float mInitialBalance = 100f;
	private static float mCurrentBalance = mInitialBalance;
	private static float[] mLadderPrice = { 1, 3, 7, 15, 31, 63 };// total 1200

	public static float getMoney(int tradeNum) {
		if (tradeNum < mLadderPrice.length) {
			mCurrentBalance -= mLadderPrice[tradeNum];
			return mLadderPrice[tradeNum];
		}
		throw new RuntimeException("借钱超标");
	}
	
	public static void putMoney(float money) {
		if (money <= 0) {
			throw new RuntimeException("putMoney error");
		}
		mCurrentBalance += money;
	}

	public static void printCurrentAmount() {
		LogUtil.d("当前余额：" + mCurrentBalance);
	}

	public static float getCurrentBalance() {
		return mCurrentBalance;
	}


}
