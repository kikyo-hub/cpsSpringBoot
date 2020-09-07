package cn.nrzt.cps.energyuse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nrzt.cps.energyuse.mapper.EnergyConsumptionMapper;
import cn.nrzt.cps.energyuse.po.Lighte;

@Service
public class EnergyConsumptionService {
    @Autowired
    EnergyConsumptionMapper mapper;

	public List<Lighte> electric(String date) {
		// TODO Auto-generated method stub
		return mapper.electric(date);
	}
    
}
