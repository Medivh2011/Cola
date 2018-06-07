package com.medivh.cola.core.delegates;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.medivh.cola.core.utils.OpenPermissionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class PermissionCheckerDelegate extends BaseDelegate implements  ActivityCompat.OnRequestPermissionsResultCallback {


    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA
    };

    private static final int PERMISSON_REQUESTCODE = 0x113;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    /**
     * @since 2.5.0
     */
    protected void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(_mActivity,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(_mActivity,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    _mActivity, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }
    /**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    protected boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }
    /**
     * 显示提示信息
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限"+"\n"+"\n请打开所需权限");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                (dialog, which) -> _mActivity.finish());

        builder.setPositiveButton("设置",
                (dialog, which) -> startAppSettings());

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        OpenPermissionUtils.getInstance(_mActivity.getApplicationContext()).jumpPermissionPage();
    }
}
