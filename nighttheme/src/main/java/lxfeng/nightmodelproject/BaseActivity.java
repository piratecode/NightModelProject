package lxfeng.nightmodelproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private boolean isNightMode;
    private NightModeReceiver mNightModeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isNightMode = AppConfig.isNightMode;
        setNightMode();
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppConfig.NIGHT_MODE);
        mNightModeReceiver = new NightModeReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mNightModeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mNightModeReceiver) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mNightModeReceiver);
        }
    }

    protected void changeTheme(int styleId,int layoutResID){
        setTheme(styleId);
        setContentView(layoutResID);
    }

    protected void setNightMode(boolean isNightMode,int layoutResID){
        if (isNightMode){
            changeTheme(R.style.NightTheme,layoutResID);         
        }else {
            changeTheme(R.style.DefaultTheme, layoutResID);
        }
    }

    private void setNightMode(){
        if(isNightMode){
            setTheme(R.style.NightTheme);
        }else {
            setTheme(R.style.DefaultTheme);
        }
    }

    protected void reset(){

    }

    private class NightModeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConfig.NIGHT_MODE)){
                reset();
            }
        }
    }

}
