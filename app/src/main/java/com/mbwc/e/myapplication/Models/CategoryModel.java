package com.mbwc.e.myapplication.Models;

import java.util.ArrayList;

/**
 * Created by E on 10/16/2016.
 */

public class CategoryModel {
    public String category_name ;
    public String success_flag ;
    public ArrayList<DataModel> dataModelArrayList = new ArrayList<DataModel>();

    public String getCategory_name(){
        return category_name;
    }
    public ArrayList<DataModel> getDataModelArrayList(){
        return dataModelArrayList;
    }
    public void setDataModelArrayList(ArrayList<DataModel> dataModelArrayList){
        this.dataModelArrayList = dataModelArrayList ;
    }
    public void setCategory_name(String category_name){
        this.category_name = category_name ;
    }
}
