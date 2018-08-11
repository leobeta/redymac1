package com.gci.siarp.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gci.siarp.api.annotation.SiarpService;
import com.gci.siarp.webapp.dao.WebappDao;

@SiarpService
public class WebappService {

	@Autowired
	private WebappDao webAppDao;
}
