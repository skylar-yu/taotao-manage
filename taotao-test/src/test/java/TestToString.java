import com.taotao.manage.pojo.ItemCat;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
public class TestToString {

    public static void main(String [] args){

        ItemCat itemCat = new ItemCat();
        itemCat.setName("");
        itemCat.setSortOrder(null);
        itemCat.setCreated(new Date());
//        itemcat.set
        System.out.println(itemCat);
    }
}
