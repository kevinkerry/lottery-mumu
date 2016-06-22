package com.lottery.core.domain.terminal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberAccountPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8171459214161024241L;
	@Column(name = "terminal_type")
	private Integer terminalType;
	@Column(name = "agent_code")
	private String agentCode;//账号
	
	public MemberAccountPK(){}
	public MemberAccountPK(int terminalType,String agentCode){
		this.agentCode=agentCode;
		this.terminalType=terminalType;
	}
	public Integer getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(Integer terminalType) {
		this.terminalType = terminalType;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((agentCode == null) ? 0 : agentCode.hashCode());
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
	    MemberAccountPK other = (MemberAccountPK) obj;
	    if (agentCode == null) {
		    if (other.agentCode != null)
			    return false;
	    } else if (!agentCode.equals(other.agentCode))
		    return false;
	    if (terminalType == null) {
		    if (other.terminalType != null)
			    return false;
	    } else if (!terminalType.equals(other.terminalType))
		    return false;
	    return true;
    }
	
	
}
