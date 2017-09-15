package pl.sikorski.kamil.springapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.sikorski.kamil.springapp.entities.System;

@Repository
public class SystemRepository {
	@Autowired
	EntityManager entityManager;

	@Autowired
	List<System> systemList;

	public List<System> addSystems() {
		for (System s : systemList) {
			entityManager.persist(s);
		}

		return systemList;
	};

	public void findSys() {
		System system = entityManager.find(System.class, 1L);
		java.lang.System.out.println(system.getName());
	}

	public Query findAllSysName() {
		Query query = entityManager.createQuery("SELECT s.name, s.id FROM System s");
		java.lang.System.out.println(query.toString() + "bang");
		return query;
	}
	
	public List<System> findAllSys(){
		Query query = entityManager.createQuery("SELECT s FROM System s");
		@SuppressWarnings("unchecked")
		List<System> systemListFromSQL = query.getResultList();
		return systemListFromSQL;
	}
	
}
