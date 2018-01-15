package com.fbb.task;

import java.util.Calendar;
import java.util.TimerTask;

import com.fbb.bean.Lottery;
import com.fbb.dao.SettingDao;
import com.fbb.statistic.BigStatisticModel;
import com.fbb.statistic.DanStatisticModel;
import com.fbb.statistic.ShuangStatisticModel;
import com.fbb.statistic.SmallStatisticModel;
import com.fbb.statistic.StatisticOpertaion;
import com.fbb.util.DataUtil;
import com.fbb.util.LogUtil;

public class SerialBallListener extends TimerTask{
	BigStatisticModel mBigModel = null;
	SmallStatisticModel mSmallModel = null;
	DanStatisticModel mDanModel = null;
	ShuangStatisticModel mShuangModel = null;
	
	SettingDao mSettingdao = null;

	@Override
	public void run() {
		start();
	}
	
	private void init() {
		mSettingdao = new SettingDao();
		reSetParams();
		mBigModel = new BigStatisticModel();
		mSmallModel = new SmallStatisticModel();
		mDanModel = new DanStatisticModel();
		mShuangModel = new ShuangStatisticModel();
		LogUtil.d("task init success...");
	}

	public void start() {
		init();
		LogUtil.d("task start...");
//		//test------------------------------------------------
//		LotteryDao lotteryDao = new LotteryDao();
//		ArrayList<Lottery> lotterys = lotteryDao.getByStartDate("2017-04-29", "2017-04-29");
//		//test------------------------------------------------
		
		boolean tag = true;
		Lottery preLottery = null;
		while(tag) {
//			//test------------------------------------------------
//			if(lotterys.size()<=0) break;
//			Lottery latestLottery = lotterys.get(0);
//			lotterys.remove(0);
//			//test------------------------------------------------
			Lottery latestLottery = DataUtil.requestLatestData();
			if(preLottery == null) {
				preLottery = latestLottery;
				LogUtil.d(latestLottery.getLottery_time()+" | 第"+latestLottery.getLottery_code()+"期开："+latestLottery.getLottery_nums());
			}
			if(latestLottery != null) {
				if(!latestLottery.getLottery_code().equalsIgnoreCase(preLottery.getLottery_code())) {
					System.out.println();
					LogUtil.d(latestLottery.getLottery_time()+" | 第"+latestLottery.getLottery_code()+"期开："+latestLottery.getLottery_nums());
					operate(latestLottery);
					preLottery = latestLottery;
				}
			}
			System.out.print(".");
			int HOUR_OF_DAY = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(HOUR_OF_DAY >= 2 && HOUR_OF_DAY <= 9) {
				tag = false;
				LogUtil.d("task stop...");
				continue;
			}
			reSetParams();
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				LogUtil.e(e.getMessage());
			}
		}
	}
	
	private void operate(Lottery lottery) {
		if(lottery.getLottery_code().substring(8).equalsIgnoreCase("025")) {
//		if(!lottery.getLottery_date().equalsIgnoreCase(currentDate)) {
//			System.out.println(lottery.getLottery_code());
			mBigModel.init();
			mSmallModel.init();
			mDanModel.init();
			mShuangModel.init();
		}
		mBigModel.onNewLottery(lottery);
		mSmallModel.onNewLottery(lottery);
		mDanModel.onNewLottery(lottery);
		mShuangModel.onNewLottery(lottery);
	}
	
	private void reSetParams() {
		String danger = mSettingdao.getByName("danger_count");
		String tip = mSettingdao.getByName("tip_count");
		if(danger != null && !danger.equalsIgnoreCase("")){
			int newDangerCount = new Integer(danger);
			if(StatisticOpertaion.DANGERCOUNT != newDangerCount){
				StatisticOpertaion.DANGERCOUNT = newDangerCount;
				LogUtil.d("DangerCount changed, new value is "+newDangerCount);
			}
		}
		if(tip != null && !tip.equalsIgnoreCase("")){
			int newTipCount = new Integer(tip);
			if(StatisticOpertaion.TIPSCOUNT != newTipCount){
				StatisticOpertaion.TIPSCOUNT = newTipCount;
				LogUtil.d("TipCount changed, new value is "+newTipCount);
			}
		}
	}
	
	

}
