package learning.ddd.service;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import learning.ddd.entity.LicensingModel;

@Service
public class MovieServiceImpl implements MovieService{

	@Override
	public Date getExipirationDate(LicensingModel licensingModel) {
		
		Date result=null;
		switch(licensingModel) {
			case TWODAYS: result=new Date(Duration.ofDays(2).toMillis());
										 break;
			case LIFELONG: result=null;
			               break;
			default: throw new IllegalArgumentException("Illegal licensing model");
		}
		
		return result;
	}

}
