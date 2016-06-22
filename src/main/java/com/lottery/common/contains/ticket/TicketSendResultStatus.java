package com.lottery.common.contains.ticket;

public enum TicketSendResultStatus {
	printed(1,"出票成功"),
	success(2,"送票成功"),
	duplicate(3,"订单已存在"),
	failed(4,"送票失败"),
	sys_busy(5,"系统繁忙"),
	timeout(6,"系统超时"),
	http_502(7,"502错误"),
	http_504(8,"504错误"),
	unkown(9,"未知异常"),
	all(0,"全部");
	public int value;
	public String name;
	
	TicketSendResultStatus(int value,String name){
		this.value=value;
		this.name=name;
	}

	
	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String toString(){
		return "["+value+":"+name+"]";
	}
	
	
}
