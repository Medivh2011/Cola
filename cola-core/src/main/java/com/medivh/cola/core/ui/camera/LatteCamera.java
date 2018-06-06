package com.medivh.cola.core.ui.camera;

import android.net.Uri;

import com.medivh.cola.core.delegates.PermissionCheckerDelegate;
import com.medivh.cola.core.utils.FileUtil;


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
