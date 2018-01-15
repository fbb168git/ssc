package com.fbb.bean;

public class Trade {
	private Lottery currentLottery;
	private float tradeAmount;
	private boolean getProfit;
	
	public Trade() {
		super();
	}
	
	public Trade(Lottery currentLottery, float tradeAmount) {
		super();
		this.currentLottery = currentLottery;
		this.tradeAmount = tradeAmount;
	}
	
	public Lottery getCurrentLottery() {
		return currentLottery;
	}
	public void setCurrentLottery(Lottery currentLottery) {
		this.currentLottery = currentLottery;
	}
	public float getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(float tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public boolean isGetProfit() {
		return getProfit;
	}
	public void setGetProfit(boolean getProfit) {
		this.getProfit = getProfit;
	}
	
	
}
