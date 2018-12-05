package com.example.patch;

import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.request_path)
    Button requestPath;
    @BindView(R.id.request_config)
    Button requestConfig;
    @BindView(R.id.clean_path)
    Button cleanPath;
    @BindView(R.id.kill_self)
    Button killSelf;
    @BindView(R.id.new_button)
    Button newButton;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.request_path, R.id.request_config, R.id.clean_path, R.id.kill_self,R.id.new_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.request_path:
                TinkerPatch.with().fetchPatchUpdate(true);
                break;
            case R.id.request_config:
                TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {

                    @Override
                    public void onSuccess(HashMap<String, String> configs) {
                        TinkerLog.w(TAG, "request config success, config:" + configs);
                    }

                    @Override
                    public void onFail(Exception e) {
                        TinkerLog.w(TAG, "request config failed, exception:" + e);
                    }
                }, true);
                break;
            case R.id.clean_path:
                TinkerPatch.with().cleanAll();
                break;
            case R.id.kill_self:
                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
                Process.killProcess(Process.myPid());
                break;
            case R.id.new_button:
                Toast.makeText(MainActivity.this,"新按钮点击",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
