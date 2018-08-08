package com.example.permissionapplication;

import android.Manifest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.permissionapplication.Permission.PermissionHelp;
import com.example.permissionapplication.Permission.PermissionReturn;

public class MainActivity extends AppCompatActivity {


    public final static int CALL_PHONE_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();

            }
        });
    }

    private void callPhone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PermissionHelp.with(this)
                    .permissionCode(CALL_PHONE_CODE)
                    .permissions(new String[]{Manifest.permission.CALL_PHONE})
                    .request(new PermissionReturn() {
                        @Override
                        public void success() {
                            //回调成功
                        }

                        @Override
                        public void fail() {
                            //回调失败
                        }
                    });
        }
    }
}
