package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService
{
	@Value("${BASE_IMAGE_URL}")
	public String BASE_IMAGE_URL;
	
	@Value("${REPOSITORY_PATH}")
	public String REPOSITORY_PATH;
}