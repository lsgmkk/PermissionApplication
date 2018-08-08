package com.example.permissionapplication.Permission;

/**
 * Created by liangshiguan on 2018/8/6.
 * 权限请求接口返回处理
 */
public interface PermissionReturn {
    //权限请求成功
    void success();

    //权限请求失败
    void fail();
}
