package org.egov.nulm.model;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NULMSMIDRequest {

	@JsonProperty("RequestInfo")
	private RequestInfo requestInfo;

	@Valid
	@JsonProperty("NULMSMIDRequest")
	private SMIDApplication nulmSmidRequest;

	@JsonProperty("AuditDetails")
	AuditDetails auditDetails;


}
