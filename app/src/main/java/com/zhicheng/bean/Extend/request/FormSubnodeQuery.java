package com.zhicheng.bean.Extend.request;

/**
 * Created by Donson on 2017/2/20.
 */

public class FormSubnodeQuery {

    /**
     * iq : {"namespace":"FormSubnodeRequest","query":{"requestType":0,"id":"1031","type":3,"tableName":"","tableID":"","wfInfoID":"AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF","grid":""}}
     */

    /**
     * requestType : 0
     * id : 1031
     * type : 3
     * tableName :
     * tableID :
     * wfInfoID : AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF
     * grid :
     */

    private int requestType;
    private String id;
    private int type;
    private String tableName;
    private String tableID;
    private String wfInfoID;
    private String grid;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getWfInfoID() {
        return wfInfoID;
    }

    public void setWfInfoID(String wfInfoID) {
        this.wfInfoID = wfInfoID;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
