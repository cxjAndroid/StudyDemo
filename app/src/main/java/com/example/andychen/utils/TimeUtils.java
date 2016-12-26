package com.example.andychen.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * @author weicheng
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    /**
     */
    private final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private final static String dateFormat2 = "yyyy-MM-dd HH:mm";

    public static SimpleDateFormat defaultDateFormat = new SimpleDateFormat(dateFormat);
    public static SimpleDateFormat defaultDateFormat2 = new SimpleDateFormat(dateFormat2);
    static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private static Map<String, String> monthMap;


    static {
        monthMap = new HashMap<>();
        monthMap.put("01", "一月");
        monthMap.put("02", "二月");
        monthMap.put("03", "三月");
        monthMap.put("04", "四月");
        monthMap.put("05", "五月");
        monthMap.put("06", "六月");
        monthMap.put("07", "七月");
        monthMap.put("08", "八月");
        monthMap.put("09", "九月");
        monthMap.put("10", "十月");
        monthMap.put("11", "十一月");
        monthMap.put("12", "十二月");
    }

    /**
     * 根据日期字符串得到Calendar对象
     *
     * @param dateStr yyyy-MM-dd格式字符串
     * @return
     */
    public static Calendar getCommonDateFromStr(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public Date getDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date((System.currentTimeMillis()));
    }


    /**
     * 得到目标日期的毫秒数 如果格式不正确 则返回当前毫秒数
     *
     * @param targetDateStr
     * @return
     */
    public static long getTargetTimeMillis(String targetDateStr) {
        try {
            Date targetDate = defaultDateFormat.parse(targetDateStr);
            return targetDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * @return HH:mm
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDate(double timeOffset) {
        Date curDate = new Date((long) (System.currentTimeMillis() + timeOffset * 1000));
        String str = defaultDateFormat.format(curDate);
        return str;
    }

    public static String getLongDate(long currentTimeMillis) {
        Date curDate = new Date(currentTimeMillis);
        String str = defaultDateFormat.format(curDate);
        return str;
    }

    /**
     * @return the current time with format yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateStr() {
        return defaultDateFormat.format(new Date());
    }

    /**
     * @return
     */
    public static String getUTCDate(double timeOffset) {
        // �õ��������α�׼ʱ����Ҫ��ȥ TimeZone.getDefault().getRawOffset()
        Date curDate = new Date((long) (System.currentTimeMillis() + timeOffset * 1000 - TimeZone.getDefault().getRawOffset()));
        String str = defaultDateFormat.format(curDate);
        return str;
    }


    public static String getPushHistoryDate(Date date) {
        return defaultDateFormat2.format(date);
    }

    /**
     */
    public static String UTCTime2Now(String ntpTime) {

        /**
         try{
         SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
         Date date = new Date();
         date = sdf.parse(ntpTime);
         Date date2 = new Date(date.getTime() + TimeZone.getDefault().getRawOffset());
         String    str    =    sdf.format(date2);
         return str;
         }catch(Exception e) {
         e.printStackTrace();
         }
         return null;
         */

        return ntpTime;
    }


    /**
     * 将时间字符串 yyyy-MM-dd HH:mm:ss 转成long
     */
    public static long getTimeLong(String timeStr) {
        long result = 0;
        try {
            result = defaultDateFormat.parse(timeStr).getTime();
        } catch (Exception e) {
        }
        return result;
    }


    /**
     * 聊天页面时间显示
     *
     * @param time       标准格式时间（本地）
     * @param textView
     * @param timeOffset 时间偏差
     * @return
     */
    public static void displayTime(String time, TextView textView, double timeOffset) {
        if (TextUtils.isEmpty(time)) {
            return;
        }

        textView.setVisibility(View.VISIBLE);
        if (textView.getTag() == null || !textView.getTag().toString().equals(time)) {
            try {

                // 显示日期
                Date date_taget = defaultDateFormat.parse(time);
                // 12小时制
                Date date_taget_h = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(time);

                // 显示日期时间零点，用来计算偏差天数
                Date date_taget_zero = defaultDateFormat.parse(time);
                date_taget_zero.setHours(0);
                date_taget_zero.setMinutes(0);
                date_taget_zero.setSeconds(0);

                // 当前日期零点，用来计算偏差天数
                Date date_taday_zero = new Date((long) (System.currentTimeMillis() + timeOffset * 1000));
                date_taday_zero.setHours(0);
                date_taday_zero.setMinutes(0);
                date_taday_zero.setSeconds(0);

                // 时区：早上、中午、下午。。。
                String timeBlock = "";
                // 偏差天数，用来计算目标日期是否是今天、昨天
                int dayOffset = (int) ((date_taday_zero.getTime() - date_taget_zero.getTime()) / (24 * 60 * 60 * 1000));

                if (date_taget.getYear() == date_taday_zero.getYear()) {    // 同年
                    switch (dayOffset) {
                        case 0:    // 今天
                            String hours = date_taget.getHours() == 12 ? "12" : String.valueOf(date_taget_h.getHours());    // 如果是中午12点的话 taget_h.getHours() 会是0，需要做一下判断。
                            textView.setText(timeBlock + " " + hours + ":" + addZero(date_taget_h.getMinutes()));
                            break;
                        case 1:    // 昨天
                            textView.setText("昨天 " + date_taget.getHours() + ":" + addZero(date_taget.getMinutes()));
                            break;
                        default:    // 不是今天、昨天
                            if ((date_taday_zero.getDay() != 0 && dayOffset < (date_taday_zero.getDay())) || (date_taday_zero.getDay() == 0 && dayOffset <= 6)) {    // 同星期
                                textView.setText(getWeek(date_taget.getDay()) + " " + date_taget.getHours() + ":" + addZero(date_taget.getMinutes()));
                            } else {    // 不同星期
                                textView.setText(addZero(date_taget.getMonth() + 1) + "-" + addZero(date_taget.getDate()) + " " + addZero(date_taget.getHours()) + ":" + addZero(date_taget.getMinutes()));
                            }
                    }
                } else {    // 不同年
                    int adYear = 1900 + date_taget.getYear();
                    textView.setText(adYear + "-" + addZero(date_taget.getMonth() + 1) + "-" + addZero(date_taget.getDate()));
                }
                textView.setTag(time);

            } catch (ParseException e) {
                e.printStackTrace();
                textView.setText(time);
                textView.setTag(null);
            }
        }
    }

    private static String getWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 0:
                return "星期天";
            default:
                return "";
        }
    }

    /**
     * 获取时间区间   早上00-11  中午12  下午13-18  晚上19-24
     *
     * @param date "yyyy-MM-dd HH:mm:ss"
     * @return 时间区间字符串 "早上""中午""下午""晚上"
     */
    private static String getTimeblock(Date date) {
        int i = date.getHours();
        String block = "";
        if (i >= 0 && i <= 11) {
            block = "早上";
        } else if (i == 12) {
            block = "中午";
        } else if (i >= 13 && i <= 18) {
            block = "下午";
        } else if (i >= 19 && i <= 24) {
            block = "晚上";
        }
        return block;
    }

    /**
     * 如果传进来的是个位数，则在前面加个"0"再返回
     *
     * @param d
     * @return
     */
    public static String addZero(int d) {
        String result = String.valueOf(d);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * 获取两个 Calendar 对象之间相差天数
     *
     * @param from
     * @param to
     * @return
     */
    public static int getDateRange(Calendar from, Calendar to) {
        long during = to.getTimeInMillis() - from.getTimeInMillis();
        return (int) (during / DateUtils.DAY_IN_MILLIS);
    }

    /**
     * 获取新的添加过相应属性的 Calendar，避免修改原有的 Calendar
     *
     * @param origin 基底 Calendar
     * @param field  需要修改的属性
     * @param value  修改值
     * @return 新的 Calendar
     */
    public static Calendar addToNewCalendar(Calendar origin, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origin.getTime());
        calendar.add(field, value);
        return calendar;
    }

    /**
     * 查询当前日期前(后)x天的日期
     *
     * @param date 当前日期
     * @param day  天数（如果day数为负数,说明是此日期前的天数）
     * @return yyyy-MM-dd
     */
    public String beforeNumDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 查询当前日期前(后)x天的日期
     *
     * @param date 当前日期
     * @param day  天数（如果day数为负数,说明是此日期前的天数）
     * @return yyyyMMdd
     */
    public String beforeNumberDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 查询当前日期前(后)x天的日期
     *
     * @param millis 当前日期毫秒数
     * @param day    天数（如果day数为负数,说明是此日期前的天数）
     * @return long 毫秒数只显示到天，时间全为0
     * @throws ParseException
     */
    public long beforeDateNum(long millis, int day) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(c.getTimeInMillis());
        Date newDate = sdf.parse(sdf.format(date));
        return newDate.getTime();
    }

    /**
     * 查询当前日期前(后)x天的日期
     *
     * @param millis 当前日期毫秒数
     * @param day    天数（如果day数为负数,说明是此日期前的天数）
     * @return yyyy-MM-dd
     */
    public String beforeLongDate(long millis, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(c.getTimeInMillis());
        return sdf.format(date);
    }

    public static void main(String[] args) {
        try {
            TimeUtils timeUtils = new TimeUtils();
            long nowDate = System.currentTimeMillis();
            System.out.println("nowDate = " + nowDate);
            long beforeDate = timeUtils.beforeDateNum(nowDate, 3);
            System.out.println("beforDate = " + beforeDate);
            Date date = new Date(beforeDate);
            System.out.println("毫秒值结果日期 = " + date.toLocaleString());
            System.out.println("yyyyMMdd结果日期  = " + timeUtils.beforeNumberDay(new Date(nowDate), 3));
            System.out.println("yyyy-MM-dd结果日期  = " + timeUtils.beforeNumDay(new Date(nowDate), 3));
            System.out.println("毫秒值获取日期结果 = " + timeUtils.beforeLongDate(nowDate, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public String getWeekOfDate(String strDate) {
        Date date = getDate(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public int getWeekOfDateNumber(String strDate) {
        Date date = getDate(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    public static String getCapitalizationMonth(String numberKey) {
        return monthMap.get(numberKey);
    }

}
