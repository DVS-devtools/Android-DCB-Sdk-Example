package com.example.dcbexternalexample;


import com.docomodigital.sdk.Dcb;
import com.docomodigital.sdk.DcbExternal;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //todo current kidjo conf    Dcb.with(this, "556a42415047494483", "fr-kidjoadv", "external", "fr", "http://acq.kidjo.tv", null,false);

        //DcbExternal.dcbWith(this, "556a42415047494483", "xx-gameasy", "games", "xx", "http://www.gameasy.com", null, false);
        DcbExternal.dcbWith(this, "556a42415047494483", "fr-kidjoadv", "external", "fr", "http://acq.kidjo.tv", null, false);
        //Dcb.with(this, "556a42415047494483", "ru-kidjo", "lite", "ru", "http://www.kidjotv.ru/", null, false);
        //Dcb.with(this, "556a42415047494483", "ww-kidzinmind", "news", "ru", "http://www.kidzinmind.com/", null, false);
       /* Dcb.with(this, "556a42415047494483", "fr-kidjoadv", "external", "fr", "http://www2.kidjotv.ru", "http://api2.motime.com/", new Dcb.DcbListener() {
            @Override
            public void trackCall(Context context, String s, String s1, String s2, HashMap<String, String> hashMap, String s3, String s4, String s5) {

            }

            @Override
            public void trackLogic(Context context, String s, String s1) {

            }

            @Override
            public void setTrack(String s) {

            }
        },false, false);*/

        if (BuildConfig.BUILD_VARIANT.equals("newtonqa")) {
            Dcb.getInstance().setQa(true);
            Dcb.getInstance().setQaBridge(true);
        }

    }
}

