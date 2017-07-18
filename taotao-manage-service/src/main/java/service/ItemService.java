package com.taotao.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.service.ApiService;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParamItem;

@Service
public class ItemService extends BaseService<Item>
{

  @Autowired
  private ItemDescService itemDescService;

//  @Autowired
//  private ItemParamItemService itemParamItemService;

  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  private ApiService apiService;

//  @Autowired
//  private RabbitTemplate rabbitTemplate;

//  @Value("${TAOTAO_WEB_URL}")
//  private String TAOTAO_WEB_URL;
//  private static final ObjectMapper MAPPER = new ObjectMapper();

  public void saveItem(Item item, String desc, String itemParams)
  {
    item.setStatus(Integer.valueOf(1));

    item.setId(null);

    super.save(item);

    ItemDesc itemDesc = new ItemDesc();
    itemDesc.setItemId(item.getId());
    itemDesc.setItemDesc(desc);

    this.itemDescService.save(itemDesc);

    ItemParamItem itemParamItem = new ItemParamItem();
    itemParamItem.setItemId(item.getId());
    itemParamItem.setParamData(itemParams);
//    this.itemParamItemService.save(itemParamItem);

    sendMsg(item.getId(), "insert");
  }

  public PageInfo<Item> queryPageList(Integer page, Integer rows) {
    Example example = new Example(Item.class);
    example.setOrderByClause("updated DESC");

    PageHelper.startPage(page.intValue(), rows.intValue());

    List list = this.itemMapper.selectByExample(example);
    return new PageInfo(list);
  }

  public void updateItem(Item item, String desc, String itemParams)
  {
    item.setStatus(null);
    item.setCreated(null);
    super.updateSelective(item);

    ItemDesc itemDesc = new ItemDesc();
    itemDesc.setItemId(item.getId());
    itemDesc.setItemDesc(desc);
    this.itemDescService.updateSelective(itemDesc);
    
	try {
		//修改商品成功后需要通知前台系统 数据同步（从缓存中删除修改的数据）
		String notifyUrl = "http://127.0.0.1:9083/item/cache/"+item.getId()+".html";
		apiService.doPost(notifyUrl, null);
	} catch (Exception e) {
		e.printStackTrace();
	}
//    this.itemParamItemService.updateItemParamItem(item.getId(), itemParams);

//    sendMsg(item.getId(), "update");
  }

  private void sendMsg(Long itemId, String type)
  {
    try
    {
      Map msg = new HashMap();
      msg.put("itemId", itemId);
      msg.put("type", type);
      msg.put("date", Long.valueOf(System.currentTimeMillis()));
//      this.rabbitTemplate.convertAndSend("item." + type, MAPPER.writeValueAsString(msg));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}