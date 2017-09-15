package pl.sikorski.kamil.springapp.aspects;

import javax.persistence.EntityManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAspect {

	@Autowired
	EntityManager entityManager;

	@Around("allSystemTransactions() || allSystemContractTransactions()")
	public Object loggingAdvice(ProceedingJoinPoint proceedingJoinPoint) {

		Object retValue = null;

		try {
			entityManager.getTransaction().begin();
			retValue = proceedingJoinPoint.proceed();
			entityManager.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}

		return retValue;
	}

	@Pointcut("within(pl.sikorski.kamil.springapp.repositories.SystemRepository)")
	public void allSystemTransactions() {
	}

	@Pointcut("within(pl.sikorski.kamil.springapp.repositories.SystemContractRepository)")
	public void allSystemContractTransactions() {
	}
}
