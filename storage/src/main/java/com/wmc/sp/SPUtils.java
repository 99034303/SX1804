package com.wmc.sp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * @author 魏铭池
 */
public class SPUtils {
    static {
        System.loadLibrary("native-lib");
    }

    private static SPUtils spUtils;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor edit;
    private Context context;

    /**
     * sp文件名
     */
    public static final String FILE_GISIM="gisim";

    public static SPUtils getInstance(String name, Context context){
        if (spUtils == null){
            spUtils = new SPUtils(name,context);
            return spUtils;
        }
        return spUtils;
    }
    @SuppressLint("CommitPrefEdits")
    private SPUtils(String name, Context context) {
        if (context!=null){
            this.context=context.getApplicationContext();
        }
        sharedPreferences =  this.context.getSharedPreferences(name, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }


    /**
     * 存入数据
     * @param key 键
     * @param value 值
     */
    public void put(String key,Object value){
        if (value instanceof String){
            edit.putString(key,String.valueOf(value));
        }else if (value instanceof Integer){
            edit.putInt(key, (Integer) value);
        }else if (value instanceof Boolean){
            edit.putBoolean(key, (Boolean) value);
        }else if (value instanceof Float){
            edit.putFloat(key, (Float) value);
        }else if (value instanceof Long){
            edit.putLong(key, (Long) value);
        }else {
            edit.putString(key,value.toString());
        }
        //保存
        edit.apply();
    }


    /**
     * @param key 键
     * @param defValue 值
     * @return 返回获取到的数据
     */
    public Object get(String key,Object defValue){
        if (defValue instanceof String){
            return sharedPreferences.getString(key, (String) defValue);
        }else if (defValue instanceof Integer){
            return sharedPreferences.getInt(key, (Integer) defValue);
        }else if (defValue instanceof Boolean){
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        }else if (defValue instanceof Float){
            return sharedPreferences.getFloat(key, (Float) defValue);
        }else if (defValue instanceof Long){
            return sharedPreferences.getLong(key, (Long) defValue);
        }else {
            return "";
        }
    }

    /**
     * @return 返回获取到的所有数据
     */
    public Map<String, ?> getAll(){
        return sharedPreferences.getAll();
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(){
        edit.clear().apply();
    }

    /**
     * 删除
     * @param key 指定键
     */
    public void delete(String key){
        edit.remove(key).apply();
    }


}
