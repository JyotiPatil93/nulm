
package org.egov.nulm.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.common.contract.request.Role;
import org.egov.nulm.common.CommonConstants;
import org.egov.nulm.config.NULMConfiguration;
import org.egov.nulm.model.NULMSEPRequest;
import org.egov.nulm.model.SEPApplication;
import org.egov.nulm.model.SEPApplicationDocument;
import org.egov.nulm.producer.Producer;
import org.egov.nulm.repository.builder.SEPQueryBuilder;
import org.egov.nulm.repository.rowmapper.SEPRowMapper;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SEPRepository {

	private JdbcTemplate jdbcTemplate;

	private Producer producer;

	private NULMConfiguration config;

	private SEPRowMapper seprowMapper;
	
	@Autowired
	public SEPRepository(JdbcTemplate jdbcTemplate, Producer producer, NULMConfiguration config,
			SEPRowMapper seprowMapper
			) {
		this.jdbcTemplate = jdbcTemplate;
		this.producer = producer;
		this.config = config;
		this.seprowMapper = seprowMapper;
	}
	
	public void createSEPApplication(SEPApplication sepApplication) {
		NULMSEPRequest infoWrapper = NULMSEPRequest.builder().nulmSepRequest(sepApplication).build();
	    producer.push(config.getSEPApplicationSaveTopic(), infoWrapper);
	}

	public List<SEPApplication> getSEPApplication(SEPApplication sepApplication,List<Role> role,Long userId) {
		List<SEPApplication> sep=new ArrayList<>();
		try {
			for (Role roleobj : role) {
				if((roleobj.getCode()).equalsIgnoreCase(config.getRoleCitizenUser())||(roleobj.getCode()).equalsIgnoreCase(config.getRoleNgoUser()))
				{
				return	sep= jdbcTemplate.query(
							SEPQueryBuilder.GET_SEP_APPLICATION_QUERY, new Object[] { sepApplication.getApplicationId(),
									sepApplication.getApplicationId(),userId.toString(),userId.toString(),sepApplication.getTenantId(),
									sepApplication.getApplicationStatus() == null ? "" :sepApplication.getApplicationStatus().toString(),
									sepApplication.getApplicationStatus() == null ? "" :sepApplication.getApplicationStatus().toString()},
							seprowMapper);
			     }
			}
				
					return	sep= jdbcTemplate.query(
							SEPQueryBuilder.GET_SEP_APPLICATION_QUERY, new Object[] { sepApplication.getApplicationId(),sepApplication.getApplicationId(),"","",sepApplication.getTenantId(),
									sepApplication.getApplicationStatus() == null ? "" :sepApplication.getApplicationStatus().toString(),
											sepApplication.getApplicationStatus() == null ? "" :sepApplication.getApplicationStatus().toString()},
							seprowMapper);		
		}
		 catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(CommonConstants.ROLE, e.getMessage());
			}
		
	
	}

	public void updateSEPApplication(SEPApplication sepapplication) {
		NULMSEPRequest infoWrapper = NULMSEPRequest.builder().nulmSepRequest(sepapplication).build();
	    producer.push(config.getSEPApplicationUpdateTopic(), infoWrapper);	}
	
	
	
	public int checkDocExist(SEPApplicationDocument sepApplication,String appId,String tenantId)
	{
	
	return  jdbcTemplate.queryForObject(
			SEPQueryBuilder.GET_SEP_DOCUMENT_QUERY, new Object[] {appId,tenantId,sepApplication.getFilestoreId(),sepApplication.getDocumentType() },
			Integer.class);
	}
	
	public void updateSEPApplicationStatus(SEPApplication sepapplication) {
		NULMSEPRequest infoWrapper = NULMSEPRequest.builder().nulmSepRequest(sepapplication).build();
	    producer.push(config.getSEPApplicationUpdateStatusTopic(), infoWrapper);	}

}
