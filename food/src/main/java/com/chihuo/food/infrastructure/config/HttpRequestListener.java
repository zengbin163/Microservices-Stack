package com.chihuo.food.infrastructure.config;

import javax.annotation.Resource;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.chihuo.food.infrastructure.common.session.SessionToolComponent;

public class HttpRequestListener implements ServletRequestListener {
	
	@Resource
	private SessionToolComponent sessionToolComponent;
	
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		this.sessionToolComponent.setSession(null);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		this.sessionToolComponent.setSession(null);
	}
	
}
