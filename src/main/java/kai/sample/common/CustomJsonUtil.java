package kai.sample.common;

import com.google.gson.Gson;

public class CustomJsonUtil {

    private static Gson getGson() {
        // 可調整初始化的方式
        return new Gson();
    }

    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

}
