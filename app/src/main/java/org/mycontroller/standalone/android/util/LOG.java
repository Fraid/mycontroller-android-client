package org.mycontroller.standalone.android.util;

import android.util.Log;

import org.mycontroller.standalone.android.Config;

/**
 * Created by tibi on 13/05/16.
 */
public class LOG {

    public static void debug(String msg)
    {
        if (Config.ISDEBUG) {
            if (msg!=null) {
                Log.d(Config.LOG_TAG, msg);
            }
        }
    }

}
