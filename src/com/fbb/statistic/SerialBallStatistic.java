package com.fbb.statistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.fbb.bean.Lottery;
import com.fbb.bean.SerialBall;
import com.fbb.dao.LotteryDao;

public class SerialBallStatistic {
	BigStatisticModel mBigModel = new BigStatisticModel();
	SmallStatisticModel mSmallModel = new SmallStatisticModel();
	DanStatisticModel mDanModel = new DanStatisticModel();
	ShuangStatisticModel mShuangModel = new ShuangStatisticModel();
	
	public void start() {
		LotteryDao lotteryDao = new LotteryDao();
		ArrayList<Lottery> lotterys = lotteryDao.getByStartDate("2017-01-01", "2017-12-31");
		for (int i = 0; i < lotterys.size(); i++) {
			Lottery lottery = lotterys.get(i);
			operate(lottery);
		}
		printResult();
	}
	
	private void printResult() {
		ArrayList<StatisticUnit> unitsBig = mBigModel.getUnits();
		ArrayList<StatisticUnit> unitsSmall = mSmallModel.getUnits();
		ArrayList<StatisticUnit> unitsDan = mDanModel.getUnits();
		ArrayList<StatisticUnit> unitsShuang = mShuangModel.getUnits();
		
		for(int i =0;i< 5;i++) {
			if(i == 0) {
				System.out.println("---------------万位---------------");
			} else if (i == 1){
				System.out.println("---------------千位---------------");
			}else if (i == 2){
				System.out.println("---------------百位---------------");
			}else if (i == 3){
				System.out.println("---------------十位---------------");
			}else if (i == 4){
				System.out.println("---------------个位---------------");
			}
			StatisticUnit unitBig = unitsBig.get(i);
			StatisticUnit unitSmall = unitsSmall.get(i);
			StatisticUnit unitDan = unitsDan.get(i);
			StatisticUnit unitShuang = unitsShuang.get(i);
			
//			printDetailCount(unitBig, unitSmall, unitDan, unitShuang);
//			printDetail(unitsBig, unitsSmall, unitsDan, unitsShuang, i);
//			printDetail2(unitBig, unitSmall, unitDan, unitShuang);
//			printDetail3(unitBig, unitSmall, unitDan, unitShuang);
			printDetail4(unitBig, unitSmall, unitDan, unitShuang);
		}
		System.out.println("出现总次数："+totalCount);
//		mBigModel.printResult();
//		mSmallModel.printResult();
//		mDanModel.printResult();
//		mShuangModel.printResult();
		
	}

	private void printDetailCount(StatisticUnit unitBig,
			StatisticUnit unitSmall, StatisticUnit unitDan,
			StatisticUnit unitShuang) {
		HashMap<Integer,Integer> statisticsBig = unitBig.statisticsCount;
		HashMap<Integer,Integer> statisticsSmall = unitSmall.statisticsCount;
		HashMap<Integer,Integer> statisticsDan = unitDan.statisticsCount;
		HashMap<Integer,Integer> statisticsShuang = unitShuang.statisticsCount;
		
		for(int j = 2 ;j < 50; j++) {
			int  resultBig = statisticsBig.containsKey(j) ? statisticsBig.get(j) : 0;
			int  resultSmall = statisticsSmall.containsKey(j) ? statisticsSmall.get(j) : 0;
			int  resultDan = statisticsDan.containsKey(j) ? statisticsDan.get(j) : 0;
			int  resultShuang = statisticsShuang.containsKey(j) ? statisticsShuang.get(j) : 0;
			if(resultBig > 0 || resultSmall>0 || resultDan>0 || resultShuang>0){
				System.out.print(j+"连    ");
				System.out.print("大"+resultBig+"次");
				System.out.print("|小"+resultSmall+"次");
				System.out.print("|单"+resultDan+"次");
				System.out.print("|双"+resultShuang+"次");
				System.out.println();
			}
			
		}
	}

	private void printDetail(ArrayList<StatisticUnit> unitsBig,
			ArrayList<StatisticUnit> unitsSmall,
			ArrayList<StatisticUnit> unitsDan,
			ArrayList<StatisticUnit> unitsShuang, int i) {
		HashMap<Integer,ArrayList<Lottery>> statisticssBig = unitsBig.get(i).statisticsTime;
		HashMap<Integer,ArrayList<Lottery>> statisticssSmall = unitsSmall.get(i).statisticsTime;
		HashMap<Integer,ArrayList<Lottery>> statisticssDan = unitsDan.get(i).statisticsTime;
		HashMap<Integer,ArrayList<Lottery>> statisticssShuang = unitsShuang.get(i).statisticsTime;
		StringBuilder builder = new StringBuilder();
		for(int j = 8 ;j < 50; j++) {
			ArrayList<Lottery>  builderBig = statisticssBig.get(j);
			ArrayList<Lottery>  builderSmall = statisticssSmall.get(j);
			ArrayList<Lottery>  builderDan = statisticssDan.get(j);
			ArrayList<Lottery>  builderShuang = statisticssShuang.get(j);
			if(builderBig != null && builderBig.size() > 0){
				builder.delete(0, builder.length());
				for(int k = builderBig.size() -1 ; k >= 0;k--){
					builder.append(builderBig.get(k).getLottery_code() + "期");
					if(k != 0) builder.append(",");
				}
				System.out.println(j+"连大:"+builder.toString());
			}
			if(builderSmall != null && builderSmall.size() > 0){
				builder.delete(0, builder.length());
				for(int k = builderSmall.size() -1 ; k >= 0;k--){
					builder.append(builderSmall.get(k).getLottery_code() + "期");
					if(k != 0) builder.append(",");
				}
				System.out.println(j+"连小:"+builder.toString());
			}
			if(builderDan != null && builderDan.size() > 0){
				builder.delete(0, builder.length());
				for(int k = builderDan.size() -1 ; k >= 0;k--){
					builder.append(builderDan.get(k).getLottery_code() + "期");
					if(k != 0) builder.append(",");
				}
				System.out.println(j+"连单:"+builder.toString());
			}
			if(builderShuang != null && builderShuang.size() > 0){
				builder.delete(0, builder.length());
				for(int k = builderShuang.size() -1 ; k >= 0;k--){
					builder.append(builderShuang.get(k).getLottery_code() + "期");
					if(k != 0) builder.append(",");
				}
				System.out.println(j+"连双:"+builder.toString());
			}
			
		}
	}
	
	private void printDetail2(StatisticUnit unitBig,
			StatisticUnit unitSmall, StatisticUnit unitDan,
			StatisticUnit unitShuang) {
		ArrayList<Lottery> timeListBig = unitBig.mDangerList;
		ArrayList<Lottery> timeListSmall = unitSmall.mDangerList;
		
		ArrayList<Lottery> timeListDan = unitDan.mDangerList;
		ArrayList<Lottery> timeListShuang = unitShuang.mDangerList;
		
		ArrayList<Lottery> timeListBigSmall = new ArrayList<Lottery>();
		ArrayList<Lottery> timeListDanShuang = new ArrayList<Lottery>();
		
		String timeBigSmall = hebingList(timeListBig, timeListSmall, timeListBigSmall);
		String timeDanShuang = hebingList(timeListDan, timeListShuang, timeListDanShuang);
		
		
		
		System.out.println("10连以上'大小'的出现时间:");
		System.out.println(timeBigSmall);
		System.out.println("两次10连以上'大小'间隔的期数：");
		printCodeGap(timeListBigSmall);
		System.out.println("10连以上'单双'的出现时间:");
		System.out.println(timeDanShuang);
		System.out.println("两次10连以上'单双'间隔的期数：");
		printCodeGap(timeListDanShuang);
	}
	
	private void printDetail4(StatisticUnit unitBig,
			StatisticUnit unitSmall, StatisticUnit unitDan,
			StatisticUnit unitShuang) {
		ArrayList<SerialBall> timeListBig = unitBig.mSerialBallList;
		ArrayList<SerialBall> timeListSmall = unitSmall.mSerialBallList;
		ArrayList<SerialBall> timeListDan = unitDan.mSerialBallList;
		ArrayList<SerialBall> timeListShuang = unitShuang.mSerialBallList;
		

//		String bigResult = genSerialBallBeginListString(timeListBig);
//		String smallResult = genSerialBallBeginListString(timeListSmall);
//		String danResult = genSerialBallBeginListString(timeListDan);
//		String shuangResult = genSerialBallBeginListString(timeListShuang);
		
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'大':");
		printNumrGap(timeListBig);
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'小':");
		printNumrGap(timeListSmall);
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'单':");
		printNumrGap(timeListDan);
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'双':");
		printNumrGap(timeListShuang);
	}
	
	private void printDetail3(StatisticUnit unitBig,
			StatisticUnit unitSmall, StatisticUnit unitDan,
			StatisticUnit unitShuang) {
		ArrayList<Lottery> timeListBig = unitBig.mDangerList;
		ArrayList<Lottery> timeListSmall = unitSmall.mDangerList;
		
		ArrayList<Lottery> timeListDan = unitDan.mDangerList;
		ArrayList<Lottery> timeListShuang = unitShuang.mDangerList;
		

		String bigResult = genBallListString(timeListBig);
		String smallResult = genBallListString(timeListSmall);
		String danResult = genBallListString(timeListDan);
		String shuangResult = genBallListString(timeListShuang);
		
		
		
		
		System.out.println("||||||||||||||||||||");
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'大'的出现时间:");
//		System.out.println(bigResult);
		System.out.println("间隔的天数：");
		printDayGap(timeListBig);
		System.out.println("||||||||||||||||||||");
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'小'的出现时间:");
//		System.out.println(smallResult);
		System.out.println("间隔的天数：");
		printDayGap(timeListSmall);
		System.out.println("||||||||||||||||||||");
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'单'的出现时间:");
//		System.out.println(danResult);
		System.out.println("间隔的天数：");
		printDayGap(timeListDan);
		System.out.println("||||||||||||||||||||");
		System.out.println(StatisticOpertaion.DANGERCOUNT+"连以上'双'的出现时间:");
//		System.out.println(shuangResult);
		System.out.println("间隔的天数：");
		printDayGap(timeListShuang);
		
//		System.out.println("两次10连以上'大小'间隔的期数：");
//		printGap(timeListBigSmall);
//		System.out.println("10连以上'单双'的出现时间:");
//		System.out.println(timeDanShuang);
//		System.out.println("两次10连以上'单双'间隔的期数：");
//		printGap(timeListDanShuang);
	}

	private String printCodeGap(ArrayList<Lottery> ballList) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calLast = Calendar.getInstance();
		Calendar calPre = Calendar.getInstance();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int min = Integer.MAX_VALUE;
		int max = -1;
		for(int i = ballList.size() -1  ;i > 0;i-- ) {
			Lottery last = ballList.get(i);
			Lottery pre = ballList.get(i - 1);
			String last_code = last.getLottery_code();
			String pre_code = pre.getLottery_code();
//			20170429110
			try {
				Date lastDate = dateFormat.parse(last_code.substring(0,8));
				Date preDate = dateFormat.parse(pre_code.substring(0,8));
				calLast.setTime(lastDate);
				calPre.setTime(preDate);
				int dayGap = calLast.get(Calendar.DAY_OF_YEAR) - calPre.get(Calendar.DAY_OF_YEAR);
				int numberGap = new Integer(last_code.substring(8)) - new Integer(pre_code.substring(8)) - 1;
				int resultGap = dayGap * 120 + numberGap;
				if(resultGap < min) min = resultGap;
				if(resultGap > max) max = resultGap;
				result.add(resultGap);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		StringBuilder builder = new StringBuilder();
		for(Integer gap : result){
			builder.append(gap);
			builder.append(",");
		}
		System.out.println(builder.toString());
		System.out.println("最小值："+min+" 最大值："+max);
		return builder.toString();
	}
	
	private String printDayGap(ArrayList<Lottery> ballList) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calLast = Calendar.getInstance();
		Calendar calPre = Calendar.getInstance();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int min = Integer.MAX_VALUE;
		int max = -1;
		int day1Danger2 = 0;
		int day1Danger3 = 0;
		StringBuilder builder0Day = new StringBuilder();
		for(int i = ballList.size() -1  ;i > 0;i-- ) {
			Lottery last = ballList.get(i);
			Lottery pre = ballList.get(i - 1);
			String last_code = last.getLottery_code();
			String pre_code = pre.getLottery_code();
			try {
				Date lastDate = dateFormat.parse(last_code.substring(0,8));
				Date preDate = dateFormat.parse(pre_code.substring(0,8));
				calLast.setTime(lastDate);
				calPre.setTime(preDate);
				int dayGap = calLast.get(Calendar.DAY_OF_YEAR) - calPre.get(Calendar.DAY_OF_YEAR);
				if(dayGap < min) min = dayGap;
				if(dayGap > max) max = dayGap;
				if(dayGap == 0) {
					if(i<ballList.size()-1) builder0Day.append( ballList.get(i + 1).getLottery_code());
					day1Danger2++;
					builder0Day.append("(");
					builder0Day.append(last_code);
					builder0Day.append(",");
					builder0Day.append(pre_code);
					builder0Day.append(")");
				}
				
				result.add(dayGap);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		StringBuilder builder = new StringBuilder();
		for(Integer gap : result){
			builder.append(gap);
			builder.append(",");
		}
		System.out.println(builder.toString());
		System.out.println(builder0Day.toString());
		System.out.println("同一天两次"+StatisticOpertaion.DANGERCOUNT+"连出现的次数 "+day1Danger2);
		System.out.println("最小值："+min+" 最大值："+max);
		return builder.toString();
	}
	
	public static final int NumGapCount = 20;
	public static int totalCount = 0;
	private String printNumrGap(ArrayList<SerialBall> ballList) {//between pre end and this begin
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calLast = Calendar.getInstance();
		Calendar calPre = Calendar.getInstance();
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int dangerCount = 0;
		
		StringBuilder builder0Day = new StringBuilder();
		for(int i = ballList.size() -1  ;i > 0;i-- ) {
			Lottery lastBegin = ballList.get(i).getBegin();
			Lottery preEnd = ballList.get(i - 1).getEnd();
			
			String last_code = lastBegin.getLottery_code();
			String pre_code = preEnd.getLottery_code();
			
			String last_date = last_code.substring(0,8);
			String pre_date = pre_code.substring(0,8);
			String last_number = last_code.substring(8);
			String pre_number = pre_code.substring(8);
			
			if(last_date.equalsIgnoreCase(pre_date)) {
				if(Math.abs(new Integer(last_number) - new Integer(pre_number) - 1) < NumGapCount) {
					dangerCount++;
					totalCount++;
					builder0Day.append("(");
					builder0Day.append(last_code);
					builder0Day.append(",");
					builder0Day.append(pre_code);
					builder0Day.append(")");
				}
			} else {
				if(new Integer(last_number) <= 24) {
					try {
						Date lastDate = dateFormat.parse(last_date);
						Date preDate = dateFormat.parse(pre_date);
						calLast.setTime(lastDate);
						calPre.setTime(preDate);
						int dayGap = calLast.get(Calendar.DAY_OF_YEAR) - calPre.get(Calendar.DAY_OF_YEAR);
						if(dayGap == 1) {
							if(Math.abs(120 - new Integer(pre_number) + new Integer(last_number) - 1) < NumGapCount) {
								dangerCount++;
								totalCount++;
								builder0Day.append("(");
								builder0Day.append(last_code);
								builder0Day.append(",");
								builder0Day.append(pre_code);
								builder0Day.append(")");
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
//		StringBuilder builder = new StringBuilder();
//		for(Integer gap : result){
//			builder.append(gap);
//			builder.append(",");
//		}
//		System.out.println(builder.toString());
//		System.out.println(NumGapCount+"期内两次"+StatisticModel.DANGERCOUNT+"连： ");
		System.out.println(builder0Day.toString());
		System.out.println("出现次数："+dangerCount);
		
		return builder0Day.toString();
	}

	private String hebingList(ArrayList<Lottery> timeListBig,
			ArrayList<Lottery> timeListSmall,
			ArrayList<Lottery> timeListBigSmall) {
		while(timeListBig.size() > 0 || timeListSmall.size() > 0) {
			if(timeListBig.size() <= 0) {
				timeListBigSmall.addAll(timeListSmall);
				timeListSmall.clear();
				continue;
			}
			if(timeListSmall.size() <= 0) {
				timeListBigSmall.addAll(timeListBig);
				timeListBig.clear();
				continue;
			}
			Lottery lotteryBig = timeListBig.get(0);
			Lottery lotterySmall = timeListSmall.get(0);
			if(lotteryBig.getLottery_code().compareTo(lotterySmall.getLottery_code()) < 0){
				timeListBigSmall.add(lotteryBig);
				timeListBig.remove(0);
			} else {
				timeListBigSmall.add(lotterySmall);
				timeListSmall.remove(0);
			}
		}
		String result = genBallListString(timeListBigSmall);
		return result;
	}

	private String genBallListString(ArrayList<Lottery> ballList) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = ballList.size() - 1 ; i >= 0 ; i--) {
			Lottery lottery = ballList.get(i);
			builder.append(lottery.getLottery_code());
			builder.append(",");
		}
		return builder.toString();
	}
	
	private String genSerialBallBeginListString(ArrayList<SerialBall> ballList) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = ballList.size() - 1 ; i >= 0 ; i--) {
			Lottery lottery = ballList.get(i).getBegin();
			builder.append(lottery.getLottery_code());
			builder.append(",");
		}
		return builder.toString();
	}

	String currentDate = null;
	private void operate(Lottery lottery) {
		if(lottery.getLottery_code().substring(8).equalsIgnoreCase("024")) {
//		if(!lottery.getLottery_date().equalsIgnoreCase(currentDate)) {
//			System.out.println(lottery.getLottery_code());
			mBigModel.init();
			mSmallModel.init();
			mDanModel.init();
			mShuangModel.init();
			currentDate = lottery.getLottery_date();
		}
		mBigModel.onNewLottery(lottery);
		mSmallModel.onNewLottery(lottery);
		mDanModel.onNewLottery(lottery);
		mShuangModel.onNewLottery(lottery);
	}
	
	

}
