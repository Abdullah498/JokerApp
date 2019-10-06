package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import javax.annotation.Nullable;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context,String>,Void,String> {
    private static MyApi sMyApi= null;
    private final String TAG = EndpointsAsyncTask.class.getSimpleName();
    private JokeRecciveInterface mJokeReceiveInterface;


    public interface JokeRecciveInterface{
        void onJokeReceive(String data);
    }

    public EndpointsAsyncTask(JokeRecciveInterface jokeReceiveInterface) {
        this.mJokeReceiveInterface = jokeReceiveInterface;
    }
    //@Nullable
    //EndpointsAsyncTask(MainActivity mainActivity){



    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        if(sMyApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://buiditbigger-android.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            sMyApi = builder.build();
        }

        try {
            return sMyApi.getAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
         mJokeReceiveInterface.onJokeReceive(result);
    }
}
