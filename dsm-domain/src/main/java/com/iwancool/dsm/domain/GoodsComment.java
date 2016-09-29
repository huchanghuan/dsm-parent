package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品评论
 * @Description	TODO
 * @ClassName	GoodsComment
 * @Date		2016年8月20日 下午6:09:12
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_comment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsComment extends AbstractBaseModel{

	private static final long serialVersionUID = -2627309332912228050L;
	
	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idx;
	
	//商品id
	@Column(name = "goods_id")
	private long goodsId;
	
	//用户id
	@Column(name = "user_id")
	private long userId;
	
	//评论内容
	@Column(name = "content", length = 1024)
	private String content;
	
	//评论时间utc， 单位ms
	@Column(name = "utc")
	private long utc;
	
	//评论发表IP
	@Column(name = "ip")
	private int ip;
	
	//回复数(另有一张goods_comment_reply表，以支持评论的嵌套回复）
	@Column(name = "reply_count")
	private int replyCount;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUtc() {
		return utc;
	}

	public void setUtc(long utc) {
		this.utc = utc;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	
}
