package com.taotao.manage.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.pojo.Content;
															  
public abstract interface ContentMapper extends Mapper<Content>
{
  public abstract List<Content> queryList(Long paramLong);
}	