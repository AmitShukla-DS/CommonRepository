package com.lnt.hmi.alerts.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lnt.hmi.alerts.entity.AlarmConfiguration;

import com.lnt.hmi.alerts.repository.AlarmConfigurationDao;


@Service
public class AlarmConfigurationServiceImpl {

	@Autowired
	private AlarmConfigurationDao alarmConfigurationDao;
	
//	@Autowired
	//private CodesDao codesDao;

	public List<AlarmConfiguration> getAllAlertRegisterMap() {

		List<AlarmConfiguration> alarmConfiguration = (List<AlarmConfiguration>) alarmConfigurationDao.findAll();

		return alarmConfiguration;
	}

	public Boolean deleteAlertConfiguration(Integer id) {
		
		if(alarmConfigurationDao.findById(id).isPresent())
		{
			alarmConfigurationDao.deleteById(id);
			return true;
		}

		return false;
		
	}
	
public String saveAlarmConfiguration(AlarmConfiguration dto) {
//Optional<Codes> codes =codesDao.findById(dto.getSeverity().getId());

Integer isRegister=alarmConfigurationDao.findByRegisterNo(dto.getRegisterNo());

AlarmConfiguration alarmConfiguration = new AlarmConfiguration();
if(isRegister==null)
{
	
//	if(codes.isPresent())
//	{
//		alarmConfiguration.setSeverity(codes.get());
//		
//	}
	
    BeanUtils.copyProperties(dto, alarmConfiguration);
	BeanUtils.copyProperties(alarmConfigurationDao.save(alarmConfiguration), dto);
	return "Successfully Added";
}
else
{
  return "Register Number Already Exists";
}

		
//Optional<AlarmConfiguration> assetGroupTypes =alarmConfigurationDao.

		

	}

	public AlarmConfiguration updateAlertConfigurationById(AlarmConfiguration dto, Integer id) {
		//Optional<Codes> codes =codesDao.findById(dto.getSeverity().getId());
		 if (alarmConfigurationDao.findById(id).isPresent()) 
		 {
			 AlarmConfiguration  alarmConfiguration= alarmConfigurationDao.findById(id).get();
			 alarmConfiguration.setAlertMessage(dto.getAlertMessage());
			 alarmConfiguration.setAlertName(dto.getAlertName());
			 alarmConfiguration.setAlertType(dto.getAlertType());
			 alarmConfiguration.setSeverity(dto.getSeverity());
			 alarmConfiguration.setRegisterNo(dto.getRegisterNo());
			 alarmConfiguration.setValue(dto.getValue());
			 alarmConfiguration.setAssets(dto.getAssets());
			 alarmConfiguration.setIsToaster(dto.getIsToaster());
			 
			return alarmConfigurationDao.save(alarmConfiguration);
		 } else 
		 {
		 return null;
		 }
		//return null;
	}

}
