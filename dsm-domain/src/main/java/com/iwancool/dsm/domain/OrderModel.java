package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 订单实体类
 * @Description	TODO
 * @ClassName	OrderModel
 * @Date		2016年8月20日 下午8:16:12
 * @Author		huchanghuan
 */
@Entity
@Table(name = "`order`")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrderModel extends AbstractBaseModel{

	private static final long serialVersionUID = 5954961817104124413L;
	
	public static final int NOT_PAY = 0;						//待付款
	public static final int NOT_SEND = 1;						//待发货
	public static final int NOT_RECEIVE = 2;					//待收货
	public static final int RECEIVED = 3;						//确认收货交易完成
	public static final int TIME_OUT_RECEIVER = 4;				//超时自动确认收货交易完成
	public static final int REFUND = 10;						//申请退款待卖家确认
	public static final int AGREE_REFUND = 11;					//卖家同意退款，退款中
	public static final int TIME_OUT_REFUND = 12;				//卖家超时自动进入退款中状态
	public static final int REFUND_COMPLETE = 13;				//退款完成
	public static final int REFUSE_REFUND= 14;					//卖家拒绝付款,等同于待收货状态
	public static final int CANCEL = 127;						//订单取消，不能再做任何操作

	//订单ID，整型，配合 id_gererate生成
	@Id
	private long id;
	
	//订单状态 0-待付款,1-待发货,2-待收货,3-确认收货交易完成, 4-超时自动确认收货交易完成
	//10-申请退款待卖家确认,11-卖家同意退款，退款中,12-卖家超时自动进入退款中状态，13-退款完成，14-卖家拒绝付款,等同于待收货状态，127-订单取消，不能再做任何操作
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;
	
	//订单生成(拍下)时间
	@Column(name = "create_utc")
	private long createUtc;
	
	//买家付款时间
	@Column(name = "pay_utc")
	private long payUtc;
	
	//卖家发货时间
	@Column(name = "deliver_utc")
	private long deliverUtc;
	
	//确认收货时间
	@Column(name = "receipt_utc")
	private long receiptUtc;
	
	//成交价
	@Column(name = "deal_price")
	private int dealPrice;
	
	//卖家id
	@Column(name = "seller_id")
	private long sellerId;
	
	//买家id
	@Column(name = "buyer_id")
	private long buyerId;
	
	//宝贝id
	@Column(name = "goods_id")
	private long goodsId;
	
	//支付方式 0-未知（未支付）1-支付宝支付 2-微信支付 3-信用卡支付 4-Apple-pay
	@Column(name = "pay_type", columnDefinition = "tinyint")
	private int payType;
	
	//支付标识id，支付宝订单号或微信支付订单号等
	@Column(name = "trade_no", length = 64, columnDefinition = "char")
	private String tradeNo;
	
	//收货地址
	@Column(name = "express_addr", length = 512)
	private String expressAddr;
	
	//收货人姓名
	@Column(name = "receiver", length = 32)
	private String receiver;
	
	//收货联系人电话
	@Column(name = "phone", length = 32)
	private String phone;
	
	//邮编
	@Column(name = "postcode", length = 10)
	private String postcode;
	
	//快递公司
	@Column(name = "express", length = 16)
	private String express;
	
	//运单号
	@Column(name = "tracking_no", length = 16)
	private String trackingNo;
	
	//备注
	@Column(name = "bookmark", length = 32)
	private String bookmark;
	
	//退款金额
	@Column(name = "return_amount")
	private int return_amount;
	
	//申请退款时间
	@Column(name = "apply_return_utc")
	private long applyReturnUtc;
	
	//同意退款时间
	@Column(name = "agree_return_utc")
	private long agreeReturnUtc;
	
	//退货理由
	@Column(name = "return_excuse", length = 2048)
	private String returnExcuse;
	
	//拒绝退货理由
	@Column(name = "refuse_return_excuse", length = 2048)
	private String refuse_return_excuse;
	
	//
	@Formula("(select g.name from goods g where g.id = goods_id)")
	private String goodsName;

	@Formula("(select g.images from goods g where g.id = goods_id)")
	private String images;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateUtc() {
		return createUtc;
	}

	public void setCreateUtc(long createUtc) {
		this.createUtc = createUtc;
	}

	public long getPayUtc() {
		return payUtc;
	}

	public void setPayUtc(long payUtc) {
		this.payUtc = payUtc;
	}

	public long getDeliverUtc() {
		return deliverUtc;
	}

	public void setDeliverUtc(long deliverUtc) {
		this.deliverUtc = deliverUtc;
	}

	public long getReceiptUtc() {
		return receiptUtc;
	}

	public void setReceiptUtc(long receiptUtc) {
		this.receiptUtc = receiptUtc;
	}

	public int getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(int dealPrice) {
		this.dealPrice = dealPrice;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getExpressAddr() {
		return expressAddr;
	}

	public void setExpressAddr(String expressAddr) {
		this.expressAddr = expressAddr;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getBookmark() {
		return bookmark;
	}

	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}

	public int getReturn_amount() {
		return return_amount;
	}

	public void setReturn_amount(int return_amount) {
		this.return_amount = return_amount;
	}

	public long getApplyReturnUtc() {
		return applyReturnUtc;
	}

	public void setApplyReturnUtc(long applyReturnUtc) {
		this.applyReturnUtc = applyReturnUtc;
	}

	public long getAgreeReturnUtc() {
		return agreeReturnUtc;
	}

	public void setAgreeReturnUtc(long agreeReturnUtc) {
		this.agreeReturnUtc = agreeReturnUtc;
	}

	public String getReturnExcuse() {
		return returnExcuse;
	}

	public void setReturnExcuse(String returnExcuse) {
		this.returnExcuse = returnExcuse;
	}

	public String getRefuse_return_excuse() {
		return refuse_return_excuse;
	}

	public void setRefuse_return_excuse(String refuse_return_excuse) {
		this.refuse_return_excuse = refuse_return_excuse;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
}
