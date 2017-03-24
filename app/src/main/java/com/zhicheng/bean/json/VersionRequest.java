package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/24.
 */

public class VersionRequest {
    private String aKey;
    private String uKey;
    private String _api_key;
    private String aId;

    public String getaKey() {
        return aKey;
    }

    public void setaKey(String aKey) {
        this.aKey = aKey;
    }

    public String get_api_key() {
        return _api_key;
    }

    public void set_api_key(String _api_key) {
        this._api_key = _api_key;
    }

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }
}
