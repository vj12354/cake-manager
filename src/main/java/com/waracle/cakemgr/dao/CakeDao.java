package com.waracle.cakemgr.dao;

import java.util.List;

import com.waracle.cakemgr.CakeEntity;

public interface CakeDao {
	public List<CakeEntity> getCakes();
	public void save(CakeEntity cake);
}