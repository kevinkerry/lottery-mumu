package com.lottery.core.domain.ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 彩票批次表.
 * 
 */
@Entity
@Table(name="ticket_batch")
public class TicketBatch implements Serializable{

	private static final long serialVersionUID = -2552552743754980391L;
	@Id
	@Column(name="id")
	private String id;			//批次号
	@Column(name="terminal_id",nullable=false)
	private Long terminalId;	//终端号，本次送票采用的终端号
	@Column(name="ticket_batch_status")
	 
	private int status;	//批次状态 TicketBatchStatus
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;	//批次创建时间
	@Column(name="send_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;		//送票成功时间
	@Column(name="terminal_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date terminateTime;	//截止送票时间，冗余字段，实际上就是当前彩期出票截止时间

    @Column(name="phase")
	private String phase;		//期数
	@Column(name="lottery_type",nullable=false)
	private Integer lotteryType;
	@Column(name="play_type")
	private Integer playType; //PlayType
	@Column(name="terminal_type_id")
	private Integer terminalTypeId;//终端类型id,TeminalType 的value值
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getTerminateTime() {
		return terminateTime;
	}
	public void setTerminateTime(Date terminateTime) {
		this.terminateTime = terminateTime;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public Integer getLotteryType() {
		return lotteryType;
	}
	public void setLotteryType(Integer lotteryType) {
		this.lotteryType = lotteryType;
	}
	public Integer getPlayType() {
		return playType;
	}
	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

    public Integer getTerminalTypeId() {
        return terminalTypeId;
    }

    public void setTerminalTypeId(Integer terminalTypeId) {
        this.terminalTypeId = terminalTypeId;
    }

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("id=").append(id);
    	sb.append(",termianlId=").append(terminalId);
    	sb.append(",terminalTypeId=").append(terminalTypeId);
    	return sb.toString();
    }

	
}
