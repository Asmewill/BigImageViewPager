package cc.shinichi.bigimageviewpager.permission;

import android.content.Context;
import android.os.Build;

import com.hjq.permissions.Permission;


import java.util.ArrayList;
import java.util.List;

import cc.shinichi.bigimageviewpager.R;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/XXPermissions
 *    time   : 2022/06/11
 *    desc   : 权限名称转换器
 */
public final class PermissionNameConvert {

    /**
     * 获取权限名称
     */
   public static String getPermissionString(Context context, List<String> permissions) {
      return listToString(context, permissionsToNames(context, permissions));
   }

   /**
    * String 列表拼接成一个字符串
    */
   public static String listToString(Context context, List<String> hints) {
      if (hints == null || hints.isEmpty()) {
         return context.getString(R.string.common_permission_unknown);
      }

      StringBuilder builder = new StringBuilder();
      for (String text : hints) {
         if (builder.length() == 0) {
            builder.append(text);
         } else {
            builder.append("、")
                    .append(text);
         }
      }
      return builder.toString();
   }

   /**
    * 将权限列表转换成对应名称列表
    */
   public static List<String> permissionsToNames(Context context, List<String> permissions) {
       List<String> permissionNames = new ArrayList<>();
       if (context == null) {
           return permissionNames;
       }
       if (permissions == null) {
           return permissionNames;
       }
       for (String permission : permissions) {
           switch (permission) {
               case Permission.READ_EXTERNAL_STORAGE:
               case Permission.WRITE_EXTERNAL_STORAGE: {
                   String hint = context.getString(R.string.common_permission_storage);
                   if (!permissionNames.contains(hint)) {
                       permissionNames.add(hint);
                   }
                   break;
               }
               case Permission.READ_MEDIA_IMAGES:
               case Permission.READ_MEDIA_VIDEO:
               case Permission.READ_MEDIA_VISUAL_USER_SELECTED:{
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                       String hint = context.getString(R.string.common_permission_image_and_video);
                       if (!permissionNames.contains(hint)) {
                           permissionNames.add(hint);
                       }
                   }
                   break;
               }
               case Permission.CAMERA: {
                   String hint = context.getString(R.string.common_permission_camera);
                   if (!permissionNames.contains(hint)) {
                       permissionNames.add(hint);
                   }
                   break;
               }
               default:
                   break;
           }
       }

       return permissionNames;
   }
}