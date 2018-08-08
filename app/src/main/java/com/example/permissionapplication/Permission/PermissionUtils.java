package com.example.permissionapplication.Permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangshiguan on 2018/7/25.
 */
public class PermissionUtils {
    private PermissionUtils() {
        throw new IllegalThreadStateException("该对象不能实例化");
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        }
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }

    public static List<String> checkPermission(Object object, String[] permissions) {
        List<String> noPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(object), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                noPermissions.add(permission);
            }
        }
        return noPermissions;
    }

    public static void request(Object object, List<String> noPermission, int permissionCode) {
        ActivityCompat.requestPermissions(getActivity(object),
                noPermission.toArray(new String[noPermission.size()]), permissionCode);
    }

    public static void requestPermission(Object object, List<String> noPermission, int permissionCode) {
        PermissionFragment permissionFragment = new PermissionFragment();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(noPermission);

        Bundle bundle = new Bundle();
        bundle.putSerializable("permission", list);
        bundle.putInt("code", permissionCode);
        permissionFragment.setArguments(bundle);
        getActivity(object).getFragmentManager().beginTransaction().add(permissionFragment, getActivity(object).getClass().getName()).commit();
    }


}
