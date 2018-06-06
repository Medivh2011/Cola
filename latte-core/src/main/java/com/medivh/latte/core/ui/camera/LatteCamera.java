package com.medivh.latte.core.ui.camera;

import android.net.Uri;

import com.medivh.latte.core.delegates.PermissionCheckerDelegate;
import com.medivh.latte.core.utils.FileUtil;


/**
 * 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
