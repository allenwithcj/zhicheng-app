package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/6.
 */

public class OfficialBaseGridResponse extends BaseResponse {
    //社区
    private String communicate;
    private String fourAddressCommunity;
    private String AcreageCommunity;
    private int householdCommunity;
    //网格
    private String grid;
    private String fourAddressGrid;
    private String AcreageGrid;
    private int householdGrid;
    //楼栋
    private String build;
    private String typeBuild;
    private String layerBuild;
    private String oneLayerBuild;
    private String unitNum;

    public OfficialBaseGridResponse(){}

    public OfficialBaseGridResponse(int code,String msg){
        super(code,msg);
    }

    public String getFourAddressCommunity() {
        return fourAddressCommunity;
    }

    public void setFourAddressCommunity(String fourAddressCommunity) {
        this.fourAddressCommunity = fourAddressCommunity;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getOneLayerBuild() {
        return oneLayerBuild;
    }

    public void setOneLayerBuild(String oneLayerBuild) {
        this.oneLayerBuild = oneLayerBuild;
    }

    public String getLayerBuild() {
        return layerBuild;
    }

    public void setLayerBuild(String layerBuild) {
        this.layerBuild = layerBuild;
    }

    public String getTypeBuild() {
        return typeBuild;
    }

    public void setTypeBuild(String typeBuild) {
        this.typeBuild = typeBuild;
    }

    public int getHouseholdGrid() {
        return householdGrid;
    }

    public void setHouseholdGrid(int householdGrid) {
        this.householdGrid = householdGrid;
    }

    public String getAcreageGrid() {
        return AcreageGrid;
    }

    public void setAcreageGrid(String acreageGrid) {
        AcreageGrid = acreageGrid;
    }

    public String getFourAddressGrid() {
        return fourAddressGrid;
    }

    public void setFourAddressGrid(String fourAddressGrid) {
        this.fourAddressGrid = fourAddressGrid;
    }

    public int getHouseholdCommunity() {
        return householdCommunity;
    }

    public void setHouseholdCommunity(int householdCommunity) {
        this.householdCommunity = householdCommunity;
    }

    public String getAcreageCommunity() {
        return AcreageCommunity;
    }

    public void setAcreageCommunity(String acreageCommunity) {
        AcreageCommunity = acreageCommunity;
    }

    public String getCommunicate() {
        return communicate;
    }

    public void setCommunicate(String communicate) {
        this.communicate = communicate;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }
}
