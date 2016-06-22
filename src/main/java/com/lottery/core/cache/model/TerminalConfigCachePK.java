package com.lottery.core.cache.model;

import java.io.Serializable;

public class TerminalConfigCachePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2810768592401525345L;

	private Integer lotteryType;	//彩种编号
	
	private Long terminalId;			//终端编号

	private Integer terminalType;	//终端类型 TerminalType
	
	private Integer playType;

	public TerminalConfigCachePK(Integer lotteryType,Long terminalId,Integer playType){
		this.lotteryType=lotteryType;
		this.terminalId=terminalId;
		this.playType=playType;
	}
	
	public TerminalConfigCachePK(){}
	
	
	public Integer getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(Integer lotteryType) {
		this.lotteryType = lotteryType;
	}

	public Long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	public Integer getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(Integer terminalType) {
		this.terminalType = terminalType;
	}

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lotteryType == null) ? 0 : lotteryType.hashCode());
		result = prime * result + ((playType == null) ? 0 : playType.hashCode());
		result = prime * result + ((terminalId == null) ? 0 : terminalId.hashCode());
		result = prime * result + ((terminalType == null) ? 0 : terminalType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TerminalConfigCachePK other = (TerminalConfigCachePK) obj;
		if (lotteryType == null) {
			if (other.lotteryType != null)
				return false;
		} else if (!lotteryType.equals(other.lotteryType))
			return false;
		if (playType == null) {
			if (other.playType != null)
				return false;
		} else if (!playType.equals(other.playType))
			return false;
		if (terminalId == null) {
			if (other.terminalId != null)
				return false;
		} else if (!terminalId.equals(other.terminalId))
			return false;
		if (terminalType == null) {
			if (other.terminalType != null)
				return false;
		} else if (!terminalType.equals(other.terminalType))
			return false;
		return true;
	} 
	
	public String toString(){
		return this.lotteryType+"_"+this.terminalType+"_"+this.terminalId+"_"+this.playType;
	}
	
	
}
