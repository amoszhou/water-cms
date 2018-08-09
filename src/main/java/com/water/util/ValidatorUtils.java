package com.water.util;


import javax.validation.Validation;
import javax.validation.Validator;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @date 2017-03-15 10:50
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }



    /*
     * 验证excel文件格式
     * xls
     * created by wangzh 2018/04/24
     * */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /*
     * 验证excel文件格式
     * xlsx
     * created by wangzh 2018/04/24
     * */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /*
     * 验证EXCEL文件
     * created by wangzh 2018/04/24
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }
}
