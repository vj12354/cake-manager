package com.waracle.cakemgr.dao;

import java.util.List;

import org.hibernate.Session;

import com.waracle.cakemgr.CakeEntity;
import com.waracle.cakemgr.HibernateUtil;

public class CakeDaoImpl implements CakeDao {

	public CakeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CakeEntity> getCakes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<CakeEntity> list = session.createCriteria(CakeEntity.class).list();
		session.close();
		return list;
	}

	@Override
	public void save(CakeEntity cake) {
		// TODO Auto-generated method stub

	}

}