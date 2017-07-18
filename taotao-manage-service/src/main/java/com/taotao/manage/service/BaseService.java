package com.taotao.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;

public abstract class BaseService<T extends BasePojo>
{

  @Autowired
  private Mapper<T> mapper;

  public T queryById(Long id)
  {
	return this.mapper.selectByPrimaryKey(id);
  }

  public List<T> queryAll()
  {
    return this.mapper.select(null);
  }

  public T queryOne(T record)
  {
    return this.mapper.selectOne(record);
  }

  public List<T> queryListByWhere(T record)
  {
    return this.mapper.select(record);
  }

  public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record)
  {
    PageHelper.startPage(page.intValue(), rows.intValue());
    List list = this.mapper.select(record);
    return new PageInfo(list);
  }

  public Integer save(T t)
  {
    t.setCreated(new Date());
    t.setUpdated(t.getCreated());
    return Integer.valueOf(this.mapper.insert(t));
  }

  public Integer saveSelective(T t)
  {
    t.setCreated(new Date());
    t.setUpdated(t.getCreated());
    return Integer.valueOf(this.mapper.insertSelective(t));
  }

  public Integer update(T t)
  {
    t.setUpdated(new Date());
    return Integer.valueOf(this.mapper.updateByPrimaryKey(t));
  }

  public Integer updateSelective(T t)
  {
    t.setUpdated(new Date());
    return Integer.valueOf(this.mapper.updateByPrimaryKeySelective(t));
  }

  public Integer deleteById(Long id)
  {
    return Integer.valueOf(this.mapper.deleteByPrimaryKey(id));
  }

  public Integer deleteByIds(Class<T> clazz, String property, List<Object> values)
  {
    Example example = new Example(clazz);
    example.createCriteria().andIn(property, values);
    return Integer.valueOf(this.mapper.deleteByExample(example));
  }

  public Integer deleteByWhere(T record)
  {
    return Integer.valueOf(this.mapper.delete(record));
  }
}