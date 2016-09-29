package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品（宝贝）实体
 * @Description	TODO
 * @ClassName	GoodsModel
 * @Date		2016年8月20日 下午3:06:47
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsModel extends AbstractBaseModel{

	private static final long serialVersionUID = 7415954313433949332L;
	
	public static final int ON_SALE = 1;       			//出售中
	public static final int SOLD_OUT = 2;				//已售
	

	//商品ID，主键，配合id_generate生成
	@Id
	private long id;
	
	//商品名称
	@Column(name = "name", length = 128)
	private String name;
	
	//所属栏目（一级分类）
	@Column(name = "column_id")
	private int columnId;
	
	//所属分类（二级分类）
	@Column(name = "category_id")
	private int categoryId;
	
	//图片列表, json列表格式，形如：{["http://www.duoshoucat.com/1.jpeg","http://www.duoshoucat.com/2.jpeg"]}默认第一张即为封面图，最多允许10张图
	@Column(name = "images", length = 4096)
	private String images;
	
	//发布者
	@Column(name = "user_id")
	private long userId;
	
	//生成时间
	@Column(name = "create_utc")
	private long createUtc;
	
	//发布时间
	@Column(name = "pub_utc")
	private long pubUtc;
	
	//品牌
	@Column(name = "brand", length = 64)
	private String brand;
	
	//标签，如全新、保真，新款等，最多允许选择三个标签
	@Column(name = "tag", length = 128)
	private String tag;
	
	//原价
	@Column(name = "ori_price")
	private int oriPrice;
	
	//现价
	@Column(name = "cur_price")
	private int curPrice;
	
	//新旧程度
	@Column(name = "degree", columnDefinition = "tinyint")
	private int degree;
	
	//尺寸
	@Column(name = "size")
	private int size;
	
	//描述
	@Column(name = "desp", length = 1024)
	private String desp;
	
	//视频"http://www.duoshoucat.com/1.mp4"
	@Column(name = "video_url", length = 128)
	private String videoUrl;
	
	//视频缩略图url
	@Column(name = "video_cover", length = 128)
	private String videoCover;
	
	//视频时长
	@Column(name = "video_dur")
	private int videoDur;
	
	//状态 1-出售中, 2-已售出
	@Column(name = "status", columnDefinition = "tinyint default 1")
	private int status;
	
	//留言量
	@Column(name = "comment_count")
	private int commentCount;
	
	//喜欢量
	@Column(name = "fav_count")
	private int favCount;
	
	//推荐级别，后台运维人员调整维护，用于排序
	@Column(name = "rcm_level", columnDefinition = "tinyint")
	private int rcmLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCreateUtc() {
		return createUtc;
	}

	public void setCreateUtc(long createUtc) {
		this.createUtc = createUtc;
	}

	public long getPubUtc() {
		return pubUtc;
	}

	public void setPubUtc(long pubUtc) {
		this.pubUtc = pubUtc;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(int oriPrice) {
		this.oriPrice = oriPrice;
	}

	public int getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(int curPrice) {
		this.curPrice = curPrice;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoCover() {
		return videoCover;
	}

	public void setVideoCover(String videoCover) {
		this.videoCover = videoCover;
	}

	public int getVideoDur() {
		return videoDur;
	}

	public void setVideoDur(int videoDur) {
		this.videoDur = videoDur;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public int getRcmLevel() {
		return rcmLevel;
	}

	public void setRcmLevel(int rcmLevel) {
		this.rcmLevel = rcmLevel;
	}
	
	
}
