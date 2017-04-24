package com.zhicheng.api.presenter;

import java.util.List;

/**
 * Created by Donson on 2017/2/15.
 */

public interface ExperiencePresenter {
    void UpThings(String GUID,String jFile, List<String> imgs);

    void UpExperience(int requestType,  String json);

    void loadExp(String json,int start);

    void loadExpDetails(String json);
}
