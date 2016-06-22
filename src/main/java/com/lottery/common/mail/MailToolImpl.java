package com.lottery.common.mail;

import com.lottery.common.contains.MailType;

public class MailToolImpl extends AbstractMailTool {

	@Override
	public void sendEmail(String msg) {
		send(MailType.email, msg);

	}

	@Override
	public void sendPhoneMsg(String msg) {
		send(MailType.phone, msg);

	}

}
