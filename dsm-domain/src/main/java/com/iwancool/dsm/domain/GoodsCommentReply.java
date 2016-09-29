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
 * 商品品论回复实体
 * @Description	TODO
 * @ClassName	GoodsCommentReply
 * @Date		2016年8月20日 下午6:57:43
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_comment_reply")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsCommentReply extends AbstractBaseModel{

	private static final long serialVersionUID = 282503106697912543L;

	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idx;
	
	//商品评论id
	@Column(name = "comment_id")
	private long commentId;
	
	//回复用户
	@Column(name = "user_id")
	private long userId;
	
	//回复内容
	@Column(name = "content", length = 1024)
	private String content;
	
	//回复时间utc，单位ms
	@Column(name = "utc")
	private long utc;
	
	//回复发表IP
	@Column(name = "ip")
	private int ip;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
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
	
	
}
