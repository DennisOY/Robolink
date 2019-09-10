package com.fii.robolink.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;


public class LocaleUtils {

    public static final Locale LOCALE_CHINESE =Locale.CHINESE;
    public static final int CHINESE_KEY = 0;
    public static final Locale LOCALE_TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE;
    public static final int TRADITIONAL_CHINESE_KEY = 1;
    public static final Locale LOCALE_ENGLISH = Locale.ENGLISH;
    public static final int ENGLISH_KEY = 2;

    private static final String LOCALE_FILE = "LOCALE_FILE";
    private static final String LOCALE_KEY = "LOCALE_KEY";



    public static int getUserLocale(Context context){
        int userLocale_key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCALE_FILE,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(LOCALE_KEY,0);
    }

    public static void setUserLocale(Context context, int userLocale_key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCALE_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LOCALE_KEY,userLocale_key);
        editor.apply();
    }

    public static Locale getCurrentLocale(Context context){
        Locale locale;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            locale = context.getResources().getConfiguration().getLocales().get(0);
        }else{
            locale = context.getResources().getConfiguration().locale;
        }
        return locale;
    }

    public static void updateLocale(Context context, int userLocale_key){
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Locale locale;
        //根据版本获得初始化应用语言
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            locale = configuration.getLocales().get(0);
        }else{
            locale = configuration.locale;
        }
        //根据编号获取用户选择的语言
        switch (userLocale_key) {
            case 0:
                locale = LOCALE_CHINESE;
                break;
            case 1:
                locale = LOCALE_TRADITIONAL_CHINESE;
                break;
            case 2:
                locale = LOCALE_ENGLISH;
                break;
            default:
                break;
        }
        //根据版本设置应用语言为用户选择的语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, displayMetrics);
        setUserLocale(context, userLocale_key);
    }
}
