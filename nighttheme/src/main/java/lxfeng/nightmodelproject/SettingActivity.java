package lxfeng.nightmodelproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

public class SettingActivity extends BaseActivity {

    private SwitchCompat mNightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void updateTheme(){

    }

    private void initView(){
        mNightSwitch = (SwitchCompat) findViewById(R.id.sc_night_model);
        mNightSwitch.setChecked(AppConfig.isNightMode);
        mNightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppConfig.isNightMode = isChecked;
                setNightMode(isChecked, R.layout.activity_setting);
                initView();
                LocalBroadcastManager.getInstance(SettingActivity.this).sendBroadcast(new Intent(AppConfig.NIGHT_MODE));
            }
        });
    }

}
