package com.example.eric.tutorversity;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class OurSingleton {
    private static OurSingleton mInstance;
    private RequestQueue mRequestQueue;

    private OurSingleton(Context context) {
        mRequestQueue = getRequestQueue(context);
    }

    public static synchronized OurSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new OurSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Context context, Request<T> req) {
        getRequestQueue(context).add(req);
    }
}