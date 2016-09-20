package cn.com.blueline.dto;

import java.io.Serializable;
/*作者:Dillon
 *日期:2016年6月1日
 **/
import java.util.List;

public class ResultWithJson<T> implements Serializable{
	
	private static final long serialVersionUID = -1177233229034746660L;
	
	
	private String errorCode;
	private String msg;
	private List<T> data;
	private Object datas;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public ResultWithJson() {
		super();
	}
	
	public ResultWithJson(String errorCode,String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}
	
	public ResultWithJson(String errorCode,String msg,List<T> data) {
		this.errorCode = errorCode;
		this.msg = msg;
		this.data = data;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Object getDatas() {
		return datas;
	}
	public void setDatas(Object datas) {
		this.datas = datas;
	}
	public ResultWithJson(String errorCode, String msg, Object datas) {
		super();
		this.errorCode = errorCode;
		this.msg = msg;
		this.datas = datas;
	}
	
}
