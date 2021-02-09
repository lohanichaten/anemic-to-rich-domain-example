package learning.ddd.service;

import java.util.Date;

import learning.ddd.entity.LicensingModel;

public interface MovieService {
	public Date getExipirationDate(LicensingModel licensingModel);
}
