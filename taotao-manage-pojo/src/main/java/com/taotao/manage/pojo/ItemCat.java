package com.taotao.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_item_cat")
public class ItemCat extends BasePojo
{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private Long parentId;
  private String name;
  private Integer status;
  private Integer sortOrder;
  private Boolean isParent;

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Boolean getIsParent() {
    return this.isParent;
  }

  public void setIsParent(Boolean isParent) {
    this.isParent = isParent;
  }

  public String getText()
  {
    return getName();
  }

  public String getState() {
    return getIsParent().booleanValue() ? "closed" : "open";
  }
}