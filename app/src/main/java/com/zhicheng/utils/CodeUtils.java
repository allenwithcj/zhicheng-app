package com.zhicheng.utils;

import com.zhicheng.R;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by lwl on 2017/4/12.
 */

public class CodeUtils {
    private static final String[] relation = UIUtils.getContext().getResources().getStringArray(R.array.relation);
    private static final String[] sex = UIUtils.getContext().getResources().getStringArray(R.array.sex);
    private static final String[] married = UIUtils.getContext().getResources().getStringArray(R.array.married);
    private static final String[] degree_of_educatio = UIUtils.getContext().getResources().getStringArray(R.array.degree_of_educatio);

    public static String relationCode(String str) {
        if(str != null){
            for(int i = 0 ;i<relation.length;i++){
                if(str.equals(relation[i])){
                    return String.valueOf(i);
                }
            }
        }
        return str;
    }


    public static String getRelationString(String str) {
        if (str != null) {
            for(int i = 0;i<relation.length;i++){
                if(str.equals(String.valueOf(i))){
                    return relation[i];
                }
            }
        }
        return null;
    }


    public static String sexCode(String str) {
        if(str != null){
            for(int i = 0 ;i<sex.length;i++){
                if(str.equals(sex[i])){
                    return String.valueOf(i);
                }
            }

        }
        return str;
    }

    public static String getSexString(String str) {
        if (str != null) {
            for(int i = 0;i<sex.length;i++){
                if(str.equals(String.valueOf(i))){
                    return sex[i];
                }
            }
        }
        return null;
    }

    public static String marriedCode(String str) {
        if(str != null) {
            for (int i = 0; i < married.length; i++) {
                if (str.equals(married[i])) {
                    return String.valueOf(i);
                }
            }
        }
        return str;
    }

    public static String getMarriedString(String str) {
        if (str != null) {
            for(int i = 0;i<married.length;i++){
                if(str.equals(String.valueOf(i))){
                    return married[i];
                }
            }
        }
        return null;
    }


    public static String degreeCode(String str){
        if(str != null) {
            for (int i = 0; i < degree_of_educatio.length; i++) {
                if (str.equals(degree_of_educatio[i])) {
                    return String.valueOf(i);
                }
            }
        }
        return str;
    }

    public static String getDegreeString(String str){
        if (str != null) {
            for(int i = 0;i<degree_of_educatio.length;i++){
                if(str.equals(String.valueOf(i))){
                    return degree_of_educatio[i];
                }
            }
        }
        return  null;
    }
}
