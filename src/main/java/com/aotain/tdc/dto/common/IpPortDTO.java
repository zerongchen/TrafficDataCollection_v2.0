package com.aotain.tdc.dto.common;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class IpPortDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -136622319622792198L;

	@Override
	public boolean equals(Object obj) {
		IpPortDTO dto = (IpPortDTO) obj;
		return (this.getQueryStartIp().equals(dto.getQueryStartIp()) && this.getQueryStartPort().equals(dto.getQueryStartPort())
				&& this.getQueryEndIp().equals(dto.getQueryEndIp()) && this.getQueryEndPort().equals(dto.getQueryEndPort()));
	}

	@Override
	public int hashCode() {
		return ((31 * this.getQueryStartIp().hashCode() + this.getQueryEndIp().hashCode()) * 31 + this.getQueryStartPort().hashCode())
				+ this.getQueryEndPort().hashCode();
	}

}
