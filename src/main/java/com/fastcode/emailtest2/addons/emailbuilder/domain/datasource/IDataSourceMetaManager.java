package com.fastcode.emailtest2.addons.emailbuilder.domain.datasource;

import org.json.JSONException;

import com.fastcode.emailtest2.addons.emailbuilder.application.datasource.dto.PreviewDataSourceOutput;

public interface IDataSourceMetaManager {

	public PreviewDataSourceOutput getData(String query) throws JSONException;

}

