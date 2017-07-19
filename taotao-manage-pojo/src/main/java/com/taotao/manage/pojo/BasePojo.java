package com.taotao.manage.pojo;

import com.taotao.common.NoNullStyle;
//import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
//import org.apache.commons.lang3.builder.StandardToStringStyle;

import java.util.Date;

public abstract class BasePojo {
    
    private Date created;
    private Date updated;
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    @Override
    public String toString() {
//        return ToStringBuilder.reflectionToString(this, new StandardToStringStyle());
        return ToStringBuilder.reflectionToString(this, new NoNullStyle());
    }
}
