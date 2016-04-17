package lxfeng.nightuimode;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
//    private FrameLayout mRootView;
//    private ImageView mLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d(TAG, "---onCreate()---");
    }

    private void initView() {
//        mRootView = (FrameLayout) findViewById(R.id.rootView);
//        mLayer = (ImageView) findViewById(R.id.img_layer);
        findViewById(R.id.btn_night).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConfig.isNightMode = !AppConfig.isNightMode;
                updateNightMode(AppConfig.isNightMode);
                recreate();
            }
        });
    }

    private void updateNightMode(boolean isNightMode) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration config = getResources().getConfiguration();
        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        config.uiMode |=
                isNightMode ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
        getResources().updateConfiguration(config, dm);
    }

//    private Bitmap createBitmap() {
//        View view = getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();
//        return bitmap;
//    }
//
//    private void createLayer() {
//        ImageView imageView = new ImageView(this);
//        imageView.setLayoutParams(
//                new FrameLayout.LayoutParams(mRootView.getWidth(), mRootView.getHeight()));
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setImageBitmap(createBitmap());
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "---onDestroy()---");
    }

    @Deprecated
    private void setNightMode() {
        //开启行车模式，可进入夜间效果
        AppConfig.isNightMode = !AppConfig.isNightMode;
        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        if (AppConfig.isNightMode) {
            uiModeManager.enableCarMode(0);
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        } else {
            uiModeManager.enableCarMode(0);
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        }
    }

}
