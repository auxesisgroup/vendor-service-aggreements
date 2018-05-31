package com.auxesis.kpmg.repository;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

//import org.hibernate.mapping.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.auxesis.kpmg.entity.smartContract;

@ComponentScan
public interface ContractI extends CrudRepository<smartContract, String> {

	@Query("SELECT t FROM smartContract t where t.vendorId = :id")
	List<smartContract> findByIdVendorId(@Param("id") String id);

	@Query("SELECT t FROM smartContract t where t.advisoryId = :id")
	List<smartContract> findByIdAdvisoryId(@Param("id") String id);

	@Query("SELECT t FROM smartContract t where t.clientId = :id")
	List<smartContract> findByIdClientId(@Param("id") String id);

	
	@Query("SELECT t FROM smartContract t where t.contractAddress = :address")
	List<smartContract> findByIdContractAddress(@Param("address") String address);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE smartContract sm SET sm.msl = ?1, "
			+ "sm.esl = ?2,sm.usl= ?3,sm.reward=?4,sm.panalty = ?5,sm.cutOff= ?6,sm.rolling=?7"
			+ "	WHERE sm.contractAddress= ?8")
	int updateSLA(BigInteger msl, BigInteger esl, BigInteger usl, BigInteger reward, BigInteger panalty,
			BigInteger cutOff, BigInteger rolling, String contractAddress);

	/*
	 * @Modifying
	 * 
	 * @Query("UPDATE smartContract sm SET sm.msl = :msl, " +
	 * "sm.esl = :esl ,sm.usl= :usl,sm.reward= :reward,sm.panalty = :panalty,sm.cutOff= :cutOff,sm.rolling= :rolling"
	 * + "	WHERE sm.contractAddress= :contractAddress") int updateSLA(@Param("msl")
	 * BigInteger msl, @Param("esl") BigInteger esl, @Param("usl") BigInteger usl,
	 * 
	 * @Param("reward") BigInteger reward, @Param("panalty") BigInteger panalty,
	 * 
	 * @Param("cutOff") BigInteger cutOff, @Param("rolling") BigInteger
	 * rolling,@Param("contractAddress") String contractAddress);
	 */

}
