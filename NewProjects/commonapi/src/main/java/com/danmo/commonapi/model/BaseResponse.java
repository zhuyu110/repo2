package com.danmo.commonapi.model;
import java.io.Serializable;

/**
 * retrofit网络请求基类
 * @author zhuyu
 *
 */
public class BaseResponse<T> implements Serializable {
	String msg;
	int code ;
	T data;
	String uuid;
	public BaseResponse( String msg, int status,String uid) {
		this.msg = msg;
		this.code = status;
		this.uuid=uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
