package pl.sikorski.kamil.springapp.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import pl.sikorski.kamil.springapp.entities.ContractForTable;
import pl.sikorski.kamil.springapp.entities.System;
import pl.sikorski.kamil.springapp.entities.SystemContract;

@Repository
public class SystemContractRepository {

	@Autowired
	EntityManager entityManager;

	@Autowired
	List<System> systemList;

	Gson json;
	
	public List<SystemContract> addContractsTest() {

		List<SystemContract> contractList = new ArrayList<>();

		SystemContract systemContract = new SystemContract();
		systemContract.setRequest(1234);
		systemContract.setSystem(systemList.get(0));

		SystemContract systemContract2 = new SystemContract();
		systemContract2.setRequest(333);
		systemContract2.setSystem(systemList.get(1));

		contractList.add(systemContract);
		contractList.add(systemContract2);

		entityManager.persist(systemContract2);
		entityManager.persist(systemContract);

		return contractList;

	};

	public List<SystemContract> addContractsList(List<SystemContract> contractList) {
		for (SystemContract sc : contractList)
			entityManager.persist(sc);
		return contractList;

	};

	public SystemContract addContract(SystemContract systemContract) {
		entityManager.persist(systemContract);
		return systemContract;

	};

	public String findAllSysCont() {
		Query query = entityManager.createQuery("SELECT s FROM SystemContract s");
		java.lang.System.out.println(query.getResultList().toString() + "bang");
		
		List<ContractForTable> contractListForTable = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		List<SystemContract> list = (List<SystemContract>) query.getResultList();
		for (SystemContract e : list) {

			contractListForTable.add(makeContractForTable(e));
		}
		 	
		
		json = new Gson();
		json.serializeNulls();
		
		return json.toJson(contractListForTable);
	}

	private ContractForTable makeContractForTable(SystemContract e) {
		ContractForTable contractForTable = new ContractForTable();
		contractForTable.setToDate(e.getToDate());
		contractForTable.setActive(e.isActive());
		contractForTable.setAmount(e.getAmount());
		contractForTable.setAmountPeriod(e.getAmountPeriod());
		contractForTable.setAmountType(e.getAmountType());
		contractForTable.setAuthorizationPercent(e.getAuthorizationPercent());
		contractForTable.setFromDate(e.getFromDate());
		contractForTable.setId(e.getId());
		contractForTable.setOrderNumber(e.getOrderNumber());
		contractForTable.setRequest(e.getRequest());
		contractForTable.setSystemId(e.getSystem().getId());
		
		return contractForTable;
	}

	public void updateContract(SystemContract systemContractToUpdate) {
		SystemContract systemContract = entityManager.find(SystemContract.class, systemContractToUpdate.getId());
		systemContract.setSystem(systemContractToUpdate.getSystem());
		systemContract.setRequest(systemContractToUpdate.getRequest());
		systemContract.setOrderNumber(systemContractToUpdate.getOrderNumber());
		systemContract.setFromDate(systemContractToUpdate.getFromDate());
		systemContract.setToDate(systemContractToUpdate.getToDate());
		systemContract.setAmount(systemContractToUpdate.getAmount());
		systemContract.setAmountType(systemContractToUpdate.getAmountType());
		systemContract.setAmountPeriod(systemContractToUpdate.getAmountPeriod());
		systemContract.setAuthorizationPercent(systemContractToUpdate.getAuthorizationPercent());
		systemContract.setActive(systemContractToUpdate.isActive());
		
		
	}
	




}
