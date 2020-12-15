package com.example.net.converter;

import com.example.net.BaseEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private Type type;

    public CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        BaseEntity baseEntity=gson.fromJson(string,BaseEntity.class);
        if (baseEntity.getCode() == 0){
            return gson.fromJson(string, type);
        } else if (baseEntity.getCode() == -1){
//            throw new ClassCastException("类型转换异常或操作失败");
            return (T) baseEntity;
        }

        return (T) baseEntity;
    }
}

