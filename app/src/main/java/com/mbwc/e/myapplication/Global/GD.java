package com.mbwc.e.myapplication.Global;


import com.mbwc.e.myapplication.Models.CategoryModel;
import com.mbwc.e.myapplication.Models.RootCategory;

import java.util.ArrayList;

/**
 * Created by E on 10/16/2016.
 */

public class GD {
    public static String choose ;
    public static ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<CategoryModel>();
    public static CategoryModel categoryModel = new CategoryModel();
    public static ArrayList<RootCategory> rootCategoryArrayList = new ArrayList<RootCategory>();
    public static String before_activity ;
    public static boolean play_flag = false ;
    public static int hour ;
    public static int minute ;
    public static String md_filename ;
}
