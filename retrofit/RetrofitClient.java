package charko.tester01.com.imageviewertop10.retrofit;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final RetrofitClient ourInstance = new RetrofitClient();

    public static RetrofitClient getInstance() {
        return ourInstance;
    }

    private RetrofitClient() {
    }

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(Long.parseLong(json.getAsString()));
                }
            }).registerTypeAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
            .create();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://220.230.124.87:8080/") // 10.100.22.56, 220.230.124.87
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    // Using Android's base64 libraries. This can be replaced with any base64 library.
    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.decode(json.getAsString(), Base64.NO_WRAP);
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }

    private RetrofitService service = retrofit.create(RetrofitService.class);
//    private FileUploadService fileService = retrofit.create(FileUploadService.class);

    public RetrofitService getService() {
        return service;
    }

//    public FileUploadService getFileService() {
//        return fileService;
//    }
}
