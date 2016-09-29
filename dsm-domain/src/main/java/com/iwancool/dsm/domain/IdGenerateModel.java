package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * ID生成实体类
 * @Description	TODO
 * @ClassName	IdGenerateModel
 * @Date		2016年8月20日 下午2:42:39
 * @Author		huchanghuan
 */
@Entity
@Table(name = "id_generate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IdGenerateModel extends AbstractBaseModel{

	private static final long serialVersionUID = 8458593410245084714L;

	//用户
	public static final String USER_ID_GENERATOR_KEY = "user";
		//商品
	public static final String GOODS_ID_GENERATOR_KEY = "goods";
		//订单
	public static final String ORDER_ID_GENERATOR_KEY = "order";
		//快递单
	public static final String USER_EXPRESS_ID_GENERATOR_KEY = "user_express_addr";
		//私信
	public static final String PRIMSG_SESSION_ID_GENERATOR_KEY = "primsg_session";

	
	//tid table id, 需要生成唯一主键表名， 
	@Id
	@Column(length = 32, columnDefinition = "char")
	private String tid;
	//uid 当前uid，统一无符号长整形
	
	@Column(name = "uid")
	private long uid;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	
}
