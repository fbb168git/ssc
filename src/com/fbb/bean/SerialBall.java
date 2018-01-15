package com.fbb.bean;

public class SerialBall {
	private Lottery begin;
	private Lottery end;
	private int serialCount;

	
	public SerialBall(Lottery begin, Lottery end, int serialCount) {
		super();
		this.begin = begin;
		this.end = end;
		this.serialCount = serialCount;
	}

	public Lottery getBegin() {
		return begin;
	}

	public void setBegin(Lottery begin) {
		this.begin = begin;
	}

	public Lottery getEnd() {
		return end;
	}

	public void setEnd(Lottery end) {
		this.end = end;
	}

	public int getSerialCount() {
		return serialCount;
	}

	public void setSerialCount(int serialCount) {
		this.serialCount = serialCount;
	}

}
