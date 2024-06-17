package com.jsp.warehousemanagementsystem.responsedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
	private long clientId;
	private String businessName;
	private long contactNumber;
	private String email;
}
