package com.example.jonchen.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 半段字符串是否为空
     *
     * @param content
     * @return
     */
    public static boolean isEmpty(String content) {
        if (content == null || content.equals("") || content.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param content
     * @return
     */
    public static boolean isNotEmpty(String content) {
        if (content == null || content.equals("") || content.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 得到非空字符串
     * @param content
     * @return
     */
    public static String getNotNullStr(String content){
        if(isEmpty(content)){
            return "";
        }
        return content;
    }

    /**
     * 文本自动换行过滤
     *
     * @param content
     */
    public static String nextLineFliter(String content) {
        String str = content.replaceAll("[,，]", " ,").replaceAll("[、]", " 、").replaceAll("[;；]", " ;");
        return str;
    }

    /**
     * 邮箱格式
     *
     * @param emailAddr
     * @return
     */
    public static boolean isEmailEmpty(String emailAddr) {

        if (TextUtils.isEmpty(emailAddr)) {
            return false;
        }
        return emailAddr.matches("(\\S)+[@]{1}(\\S)+[.]{1}(\\w)+");
        // return
        // emailAddr.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]{2,3}){1,2}$");
    }

    public static boolean isEmail(String emailAddr) {
        return emailAddr.matches("(\\S)+[@]{1}(\\S)+[.]{1}(\\w)+");
        // return
        // emailAddr.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]{2,3}){1,2}$");
    }

    /**
     * 邮政编码
     *
     * @param post
     * @return
     */
    public static boolean isPost(String post) {
        if (TextUtils.isEmpty(post)) {
            return false;
        }
        return post.matches("^\\d{1,6}$");
    }

    public static boolean isQq(String post) {
        if (TextUtils.isEmpty(post)) {
            return false;
        }
        return post.matches("[1-9][0-9]{4,}");
    }

    public static boolean isStringOrEmail(String post) {

        if (post.matches("^\\w+$")
                || post.matches("(\\S)+[@]{1}(\\S)+[.]{1}(\\w)+")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isInvitationCode(String invitationCode) {
        if (TextUtils.isEmpty(invitationCode)) {
            return false;
        }
        return invitationCode.matches("[0-9]{4}");
    }

    // 判断真实姓名（英文中文）
    public static boolean isCharname(String post) {

        if (post.matches("^[a-zA-Z\u4e00-\u9fa5]{1,}") && post.length() <= 10) {
            return true;
        } else {
            return false;
        }

    }

    // 不能是中文
    static String regEx = "[\u4e00-\u9fa5]";
    static Pattern pat = Pattern.compile(regEx);

    public static boolean containsChinese(String str) {
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }


    public static boolean isLength(String str, int startLength, int endLength) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.length() < startLength || str.length() > endLength;

    }


    /**
     * 通用判断是否为手机号码
     *
     * @param telNum
     * @return
     */
    public static boolean isMobilePhoneNum(String telNum) {
        String regex = "1[0-9]{10}";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }


    /**
     * 国家区号
     *
     * @param countryNumber
     * @return
     */
    public static boolean isCountryNumber(String countryNumber) {
        if (TextUtils.isEmpty(countryNumber)) {
            return false;
        }
        return countryNumber.matches("^\\d{1,5}$");
    }

    /**
     * 是否固定电话（不含区号：83311070 or 8331107）
     *
     * @param number
     * @return
     */
    public static boolean isTelephoneNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("^\\d{7,8}$");
    }


    public static boolean outOfLength(String str, int startLength, int endLenght) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.length() < startLength || str.length() > endLenght;

    }


    /**
     * 是否电话号码或者手机号码(含国家代码，区号：+86-15806030060 或者+86-0591-83311070)
     *
     * @param number
     * @return
     */
    public static boolean isCountryCityPhoneNumber(String number) {
        String regHome = "^\\++\\d{1,5}(\\-)+\\d{3,4}(\\-)+\\d{7,8}$";
        String regPhone = "^\\++\\d{1,5}(\\-)+\\d{7,11}$";
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return (number.matches(regHome) || number.matches(regPhone));
    }

    /**
     * 身份证号验证
     *
     * @param number
     *             要判断的号码
     * @return
     */
    public static boolean isIdCardNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("(\\d{15})|(\\d{17}[0-9Xx])");
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 编码验证
     *
     * @param number
     * @return
     */
    public static boolean CertificateNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("[0-9]{15}");
    }

    /**
     * 判断首字符是否是字母
     *
     * @param fstrData
     * @return
     */
    public static boolean CheckfstrData(String fstrData) {
        String s = fstrData.substring(0, 1);
        if (TextUtils.isEmpty(fstrData)) {
            return false;
        }
        return s.matches("^[A-Za-z]+$");
    }





    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 高亮某段字体
     * @param context
     * @param fullText 一段字体全部内容
     * @param HighLightText 需要高亮的某段字体
     * @param color 高亮的颜色
     * @return
     */
    public static CharSequence getHighLightText(Context context,int fullText,String HighLightText,int color) {
        String hintText = String.format(
                context.getResources().getString(fullText),
                HighLightText);
        int index = hintText.indexOf(HighLightText);
        CharSequence text = getHighLightText(hintText,
                context.getResources().getColor(color), index, index
                        + HighLightText.length());
        return text;
    }



    /**
     * 显示部分可点击文字
     * @param text	显示全部内容
     * @param clickSpan	点击监听回调
     * @param mTextView	TextView
     * @param start	开始位
     * @param end	结束位
     * @param textColor 设置点击文字颜色
     */
    public static void setClickableText(String text,ClickableSpan clickSpan,TextView mTextView,int start,int end,int textColor){
        if(clickSpan != null && mTextView != null){
            SpannableString spanableInfo = new SpannableString(text);
            spanableInfo.setSpan(clickSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextView.setText(spanableInfo);
            mTextView.setLinkTextColor(textColor);
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }

    /**
     * 设置拨打电话  电话号高亮的 Span
     * @param a
     * @param tv_des      目标textView
     * @param description 电话号前描述文字
     */
   /* public static void setClickCallSpan(final Activity a,TextView tv_des,String description){

        final String serverPhone = a.getString(R.string.real_server_number);
        String tips = description+serverPhone+" ";
        ClickableSpan clickSpan = new WithoutLineClickableSpan() {
            @Override
            public void onClick(View widget) {
                *//**拨打客服*//*
                IntentUtils.call(a, serverPhone);
            }
        };

        //设置点击监听
        StringUtils.setClickableText(tips, clickSpan, tv_des, serverPhone
                , a.getResources().getColor(R.color.text_blue));


    }*/


    /**
     * 显示部分可点击文字
     * @param text	显示全部内容
     * @param clickSpan	点击监听回调
     * @param mTextView	TextView
     * @param desStr	可以点击的文字
     * @param textColor 设置点击文字颜色
     */
    public static void setClickableText(String text,ClickableSpan clickSpan,TextView mTextView,String desStr,int textColor){
        if(!StringUtils.isEmpty(text)&& !StringUtils.isEmpty(desStr)) {
            int start = text.indexOf(desStr);
            int end = start + desStr.length();
            setClickableText(text, clickSpan, mTextView, start, end, textColor);
        }
    }



    /**
     * 转换时间格式
     * @param millisUntilFinished 毫秒数
     * @return
     */
    public static String getDateStr(long millisUntilFinished) {

        long hours = 0;
        long minutes = 0;
        long seconds = 0;
        String dateStr="";

        if(millisUntilFinished / DateUtils.HOUR_IN_MILLIS > 0){
            hours = millisUntilFinished / DateUtils.HOUR_IN_MILLIS;
            dateStr = hours + "小时";
        }
        millisUntilFinished -= hours * DateUtils.HOUR_IN_MILLIS;
        if(millisUntilFinished / DateUtils.MINUTE_IN_MILLIS > 0){
            minutes = millisUntilFinished / DateUtils.MINUTE_IN_MILLIS;
            dateStr += minutes + "分";
        }
        millisUntilFinished -= minutes * DateUtils.MINUTE_IN_MILLIS;
        if (millisUntilFinished / DateUtils.SECOND_IN_MILLIS >0 ){
            seconds = millisUntilFinished / DateUtils.SECOND_IN_MILLIS;
            dateStr += seconds + "秒";
        }

        return dateStr;
    }

    /**
     *	获取掩藏特定字符的邮箱
     * @return
     */
    public static String getMaskEmail(String email){
        try{
            if(email.contains("@")){
                return (email.subSequence(0, 3) + email.substring(3, email.indexOf("@")).replaceAll("\\w", "*") + email.substring(email.indexOf("@")));
            }else{
                return (email.subSequence(0, 3) + email.substring(3).replaceAll("\\w", "*"));
            }
        }catch (Exception e){
            return StringUtils.isEmpty(email)?"":email;
        }
    }


    /**
     * 获取掩盖银行卡号数字，最后4位数字除外
     * */
    public static String getMaskBankAccount(String bankaccount){
        String result = "";
        try{
            result = bankaccount.substring(0,bankaccount.length()-4).replaceAll("\\w", "*") + bankaccount.substring(bankaccount.length()-4);
        }catch (Exception e){}
        return StringUtils.isEmpty(result)?"":result;
    }

    /**
     * 获取掩盖手机号数字，首3位后4位数字除外
     * */
    public static String getMaskMobileNum(String mobileNum){
        String result = "";
        try{
            result = mobileNum.substring(0, 3) + mobileNum.substring(3,mobileNum.length()-4).replaceAll("\\w", "*") + mobileNum.substring(mobileNum.length()-4);
        }catch (Exception e){}
        return StringUtils.isEmpty(result)?"":result;
    }





    //把日期转为字符串
    public static String ConverToString(Date date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(date);
    }
    //把字符串转为日期
    public static Date ConverToDate(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }


    /**
     * 得到省略的字符串后面拼接...
     * @param sourceStr     源字符
     * @param limitLength   允许的长度
     * @return  源字符长度小于等于允许长度返回源字符，否则截取允许长度拼接...
     */
    public static String getEllipticalText(@NonNull String sourceStr,int limitLength){
        return sourceStr.length()>limitLength?sourceStr.substring(0, limitLength)+"...":sourceStr;
    }

}
