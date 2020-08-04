
package org.egov.nulm.repository;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.Role;
import org.egov.nulm.common.CommonConstants;
import org.egov.nulm.config.NULMConfiguration;
import org.egov.nulm.model.NulmShgRequest;
import org.egov.nulm.model.NulmSmidRequest;
import org.egov.nulm.model.SmidApplication;
import org.egov.nulm.model.SmidShgGroup;
import org.egov.nulm.producer.Producer;
import org.egov.nulm.repository.builder.NULMQueryBuilder;
import org.egov.nulm.repository.rowmapper.SMIDRowMapper;
import org.egov.nulm.repository.rowmapper.ShgRowMapper;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SmidShgRepository {

	private JdbcTemplate jdbcTemplate;

	private Producer producer;

	private NULMConfiguration config;

	private ShgRowMapper shgrowMapper;
	
	@Autowired
	public SmidShgRepository(JdbcTemplate jdbcTemplate, Producer producer, NULMConfiguration config,
			ShgRowMapper shgrowMapper
			) {
		this.jdbcTemplate = jdbcTemplate;
		this.producer = producer;
		this.config = config;
		this.shgrowMapper = shgrowMapper;
	}
	
	public void createGroup(SmidShgGroup shg) {
		NulmShgRequest infoWrapper = NulmShgRequest.builder().nulmShgRequest(shg).build();
	    producer.push(config.getSMIDSHGSaveTopic(), infoWrapper);
	}
	public List<SmidShgGroup> getGroup(SmidShgGroup shg,List<Role> role,Long userId) {
		List<SmidShgGroup> smid=new ArrayList<>();
		try {
			for (Role roleobj : role) {
				if((roleobj.getCode()).equalsIgnoreCase(config.getRoleCitizenUser())||(roleobj.getCode()).equalsIgnoreCase(config.getRoleNgoUser()))
				{
				return	smid= jdbcTemplate.query(
							NULMQueryBuilder.GET_SHG_QUERY, new Object[] {userId.toString(),userId.toString(),shg.getTenantId(),shg.getStatus() == null ? "" :shg.getStatus().toString(),shg.getStatus() == null ? "" :shg.getStatus().toString()
									,shg.getShgUuid(),shg.getShgUuid()},
							shgrowMapper);
			     }
			}
				
					return	smid= jdbcTemplate.query(
							NULMQueryBuilder.GET_SHG_QUERY, new Object[] {"","",shg.getTenantId(),shg.getStatus() == null ? "" :shg.getStatus().toString(),shg.getStatus() == null ? "" :shg.getStatus().toString()
									,shg.getShgUuid(),shg.getShgUuid()},
							shgrowMapper);		
		}
		 catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(CommonConstants.ROLE, e.getMessage());
			}
		
	
	}
	public void updateGroup(SmidShgGroup shg) {
		NulmShgRequest infoWrapper = NulmShgRequest.builder().nulmShgRequest(shg).build();
		producer.push(config.getSMIDSHGUpdateTopic(), infoWrapper);
	}
	public void updateGroupStatus(SmidShgGroup shg) {
		NulmShgRequest infoWrapper = NulmShgRequest.builder().nulmShgRequest(shg).build();
		producer.push(config.getSMIDSHGUpdateStatusTopic(), infoWrapper);
	}
	
}
