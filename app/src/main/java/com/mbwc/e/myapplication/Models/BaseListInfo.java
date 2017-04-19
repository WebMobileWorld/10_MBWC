package com.mbwc.e.myapplication.Models;


public class BaseListInfo {

    protected int m_nDataType;

    public BaseListInfo()
    {
        m_nDataType = 0;
    }

    public void setDataType(int dataType)
    {
        m_nDataType = dataType;
    }

    public int getDataType()
    {
        return m_nDataType;
    }
}
