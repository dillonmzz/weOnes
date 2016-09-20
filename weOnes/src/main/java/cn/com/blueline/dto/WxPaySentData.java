package cn.com.blueline.dto;

/**
 *  统一下单API 绑定的数据 参考文档:http://www.jb51.net/article/70537.htm
 * 
 * @author Dillon
 *
 */
public class WxPaySentData {

	private String appid;
	private String mch_id;// 商户号
	private String device_info;// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String nonce_str;// 随机字符串，不长于32位
	private String sign;// 签名
	private String body;// 商品或支付单简要描述
	private String detail;// 商品详情
	private String out_trade_no;// 商户系统内部的订单号,32个字符内、可包含字母
	private int total_fee;// 订单总金额，单位为分,必须为整数，不能有小数点
	private String spbill_create_ip;// 用户端ip
	private String notify_url;// Notify_url
	private String trade_type;// 交易类型,取值如下：JSAPI，NATIVE，APP
	private String openid;// trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	private String attach;// 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据

	public WxPaySentData() {
		super();
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "WxPaySentData [appid=" + appid + ", mch_id=" + mch_id
				+ ", device_info=" + device_info + ", nonce_str=" + nonce_str
				+ ", sign=" + sign + ", body=" + body + ", detail=" + detail
				+ ", out_trade_no=" + out_trade_no + ", total_fee=" + total_fee
				+ ", spbill_create_ip=" + spbill_create_ip + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", openid="
				+ openid + ", attach=" + attach + "]";
	}

}
