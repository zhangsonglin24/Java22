package com.kaishengit.shiro;

import com.kaishengit.pojo.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserName() {
        return getCurrentUser().getUserName();
    }

    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * 判断当前登录对象是否为市场部员工
     */
    public static boolean isMar() {
        return SecurityUtils.getSubject().hasRole("role_sales");
    }

    /**
     * 判断当前登录对象是否为开发部员工
     */
    public static boolean isDev() {
        return SecurityUtils.getSubject().hasRole("role_dev");
    }

    /**
     * 判断当前登录对象是否为财务部员工
     */
    public static boolean isFin() {
        return SecurityUtils.getSubject().hasRole("role_fin");
    }

    /**
     * 判断当前登录对象是否为管理员
     */
    public static boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole("role_admin");
    }
}
