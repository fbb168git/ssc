package com.fbb.trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.fbb.bean.Lottery;
import com.fbb.dao.LotteryDao;
import com.fbb.util.LogUtil;

public class TradeTask {
	TradeOpertaionBig mBigModel = new TradeOpertaionBig();
	TradeOpertaionSmall mSmallModel = new TradeOpertaionSmall();
	TradeOpertaionDan mDanModel = new TradeOpertaionDan();
	TradeOpertaionShuang mShuangModel = new TradeOpertaionShuang();
	
	public void start() {
		LotteryDao lotteryDao = new LotteryDao();
		ArrayList<Lottery> lotterys = lotteryDao.getByStartDate("2017-03-21", "2017-03-22");
		for (int i = 0; i < lotterys.size(); i++) {
			Lottery lottery = lotterys.get(i);
			operate(lottery);
		}
		LogUtil.d("--------------------");
		float totalCount = 0f;
		float totalGift = 0f;
		float zhisunCOunt = 0;
		for(Prop prop : giftList){
			LogUtil.d(prop.key+" "+prop.value);
			if(prop.value >= 0){
				totalCount++;
				totalGift += prop.value;
			} else if(prop.value < 0) {
				zhisunCOunt ++;
			}
		}
		LogUtil.d("平均收益："+(totalGift/totalCount));
		LogUtil.d("止损次数：" + zhisunCOunt);
		LogUtil.d("当前余额：" + MoneyPool.getCurrentBalance());
	}
	
	ArrayList<Prop> giftList = new ArrayList<TradeTask.Prop>();
	
	private float currentBlance = MoneyPool.getCurrentBalance();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private void operate(Lottery lottery) {
		if(lottery.getLottery_code().substring(8).equalsIgnoreCase("024")) {
			mBigModel.init();
			mSmallModel.init();
			mDanModel.init();
			mShuangModel.init();
			
			float blance = MoneyPool.getCurrentBalance() - currentBlance;
			try {
				Date today = format.parse(lottery.getLottery_date());
				Calendar instance = Calendar.getInstance();
				instance.setTime(today);
				instance.set(Calendar.DAY_OF_YEAR, instance.get(Calendar.DAY_OF_YEAR) - 1);
				String key = format.format(instance.getTime());
				Prop prop = new Prop(key, blance);
				giftList.add(prop);
				LogUtil.d(key + "收益"+blance);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			currentBlance = MoneyPool.getCurrentBalance();
		}
		mBigModel.onNewLottery(lottery);
		mSmallModel.onNewLottery(lottery);
		mDanModel.onNewLottery(lottery);
		mShuangModel.onNewLottery(lottery);
	}
	
	public class Prop {
		public String key;
		public float value;
		public Prop(String key, float value) {
			super();
			this.key = key;
			this.value = value;
		}
		
	}

}
