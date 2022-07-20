package ru.romazanov.data.retrofit;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.romazanov.data.room.dao.MyInterceptorDao;
import ru.romazanov.data.room.entities.MyInterceptorEntity;

public class MyInterceptor implements Interceptor {

    MyInterceptorDao dao;

    @Inject
    public MyInterceptor(MyInterceptorDao dao) {
        this.dao = dao;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        addToLog(request, response);

        return response;
    }

    private void addToLog(Request request, Response response) {
        String addRequest = "REQUEST: URL " + request.url();
        String addResponse = "RESPONSE: Headers " + response.headers();
        new MyAsyncTask().execute(new MyInterceptorEntity(addRequest, addResponse));
    }

    class MyAsyncTask extends AsyncTask<MyInterceptorEntity, Void, Void> {
        @Override
        protected Void doInBackground(MyInterceptorEntity... myInterceptorEntities) {
            dao.addLogg(myInterceptorEntities[0]);
            return null;
        }
    }


}
