package org.apache.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.view.View;


public class HWASwitch extends CordovaPlugin
{
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("switchHWA")) {
            boolean enable = args.getBoolean(0);
            this.switchHWA(enable, callbackContext);
            return true;
        }
        return false;
    }

    private void switchHWA(boolean enable, CallbackContext callbackContext) {
        View wv = this.webView;
        class SetLayer implements Runnable {
            public int type;
            public View view;
            public CallbackContext cb;
            SetLayer(int t, View v, CallbackContext cb) { type=t; view=v; this.cb=cb;}
            public void run() {
                this.view.setLayerType(type, null);
                this.cb.success("Set hardware layer success, layer type = " + this.view.getLayerType());
            }
        }
        if(enable){
            this.cordova.getActivity().runOnUiThread(new SetLayer(View.LAYER_TYPE_HARDWARE, wv, callbackContext));
        }else{
            this.cordova.getActivity().runOnUiThread(new SetLayer(View.LAYER_TYPE_SOFTWARE, wv, callbackContext));
        }
    }
}