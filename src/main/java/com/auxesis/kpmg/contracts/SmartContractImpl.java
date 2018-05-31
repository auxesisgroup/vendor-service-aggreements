
package com.auxesis.kpmg.contracts;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple11;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple9;

import com.auxesis.kpmg.blockchain.AddressHelper;
import com.auxesis.kpmg.entity.Advisory;
import com.auxesis.kpmg.entity.Contract;
import com.auxesis.kpmg.entity.Invoice;
import com.auxesis.kpmg.entity.Performance;
import com.auxesis.kpmg.entity.Response;
import com.auxesis.kpmg.entity.contractAndTxHash;
import com.auxesis.kpmg.smartcontract.SLA;

@Service
public class SmartContractImpl implements ISmartContract {

	// @Value("${web3j.url}")
	private String webUrl = "http://139.59.213.205:7007";

	// @Value("${web3j.masterKey}")
	// private String masterKey;

	// BlochchainProperties _BlochchainProperties = new BlochchainProperties();
	// variable
	final static Logger LOGGER = LoggerFactory.getLogger(SmartContractImpl.class);

	private Web3j WEB3J = Web3j.build(new HttpService(webUrl));
	private String senderPrivKey = "0xf989fac1c1c1b1d1e66d1e0873e129626ad18ddc329a578d25fde961c56c24bf";
	// f989fac1c1c1b1d1e66d1e0873e129626ad18ddc329a578d25fde961c56c24bf
	// Addrress : 0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB
	private String gasPrice = "99999999999";
	private BigInteger _gasPrice = new BigInteger(gasPrice);
	BigInteger _gasLimit = BigInteger.valueOf(3999756);

	// private KeyPair keyPair = new KeyPair();
	// private Balance balance = new Balance();
	private AddressHelper addressHelper = new AddressHelper();

	@Override
	public Response smartContractDeploy(Contract contract) {
		Response res = new Response();
		try {
			// boolean feesCheck =
			// balance.transactionFeeCalculation(senderPrivKey,
			// _gasPrice,
			// _gasLimit, senderPrivKey);
			// LOGGER.info("feesCheck : " + feesCheck);
			// if (!feesCheck) {
			// LOGGER.error("smartContractDeploy : Insuffient funds.");
			// res.setMessage("Insuffient funds.");
			// return res;
			// }
			// res = keyPair.getKeyFromPrivateKey(MarchantAndVendor.getPrivateKey());
			// if (res.getMessage() != null) {
			// LOGGER.error("smartContractDeploy : " + res.getMessage());
			// return res;
			// }
			Credentials creds = Credentials.create(senderPrivKey);
			Bytes32 services = addressHelper.stringToBytes32Conversion(contract.getServices());
			Bytes32 vendor = addressHelper.stringToBytes32Conversion(contract.getVendor());
			Bytes32 subServiceType = addressHelper.stringToBytes32Conversion(contract.getSubServiceType());
			BigInteger startDate = BigInteger.valueOf(contract.getStartDate().getTime());
			BigInteger endData = BigInteger.valueOf(contract.getEndDate().getTime());
			Bytes32 poId = addressHelper.stringToBytes32Conversion(contract.getPoId());
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA con = SLA
					.deploy(WEB3J, creds, _gasPrice, _gasLimit, services, vendor, subServiceType, contract.getBudget(),
							startDate, endData, contract.getBudgetPerMonth(), contract.getPoAmount(), poId)
					.sendAsync().get();
			if (!con.isValid()) {
				LOGGER.error("invalid contract in  :" + con.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(con.getContractAddress());
			result.setTransactionHash(con.getTransactionReceipt().get().getTransactionHash());

			res.setResult(result);
			LOGGER.info("output: " + result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	@Override
	public Response advisory(Advisory advisory) {

		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(advisory.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			Timestamp date = new Timestamp(System.currentTimeMillis());
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}

			TransactionReceipt con = _contract.advisorySet(advisory.getMsl(), advisory.getUsl(), advisory.getEsl(),
					advisory.getReward(), advisory.getPanalty(), advisory.getCutOff(), advisory.getRolling(),
					advisory.getCreditTerm(), BigInteger.valueOf(date.getTime())).sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(advisory.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			return res;
		} catch (

		Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	@Override
	public Response performance(Performance performance) {

		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(performance.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			Timestamp date = new Timestamp(System.currentTimeMillis());
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}

			Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> r2 = _contract
					.advisoryDetail().sendAsync().get();

			System.out.println("output: " + r2.getValue10() + !r2.getValue10().equals(BigInteger.valueOf(1)));
			if (!r2.getValue10().equals(BigInteger.valueOf(1))) {
				contractAndTxHash result = new contractAndTxHash();
				res.setMessage("SLA is not approved by Client.");
				res.setResult(null);
				LOGGER.info("output: " + result);
				return res;
			}
			if (!r2.getValue11().equals(BigInteger.valueOf(1))) {
				contractAndTxHash result = new contractAndTxHash();
				res.setMessage("SLA is not approved by vendor.");
				res.setResult(null);
				LOGGER.info("output: " + result);
				return res;
			}
			BigInteger _month = BigInteger.valueOf(performance.getMonth());
			BigInteger _performance = BigInteger.valueOf(performance.getPerformance());
			TransactionReceipt con = _contract.performanceSet(_month, _performance, BigInteger.valueOf(date.getTime()))
					.sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(performance.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}

	}

	@Override
	public Response payout(Invoice invoce) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(invoce.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			Timestamp date = new Timestamp(System.currentTimeMillis());
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}

			Bytes32 invoiceId = addressHelper.stringToBytes32Conversion(invoce.getInvoiceId());
			BigInteger _month = BigInteger.valueOf(invoce.getMonth());
//			Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]> data = _contract
//					.contractDetails().sendAsync().get();
//			System.out.println("################");
//			System.out.println(data.getValue3() + "\n" + invoce.getInvoiceAmount());
//			if ((invoce.getInvoiceAmount()).compareTo(data.getValue7()) == 1) {
//				res.setMessage("Invoice amount is high then budget per month amount " + data.getValue7() + ".");
//				return res;
//			}

			Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> r2 = _contract
					.advisoryDetail().sendAsync().get();

			System.out.println("output: " + r2.getValue10() + !r2.getValue10().equals(BigInteger.valueOf(1)));
			if (!r2.getValue10().equals(BigInteger.valueOf(1))) {
				contractAndTxHash result = new contractAndTxHash();
				res.setMessage("SLA is not approved by Client.");
				res.setResult(null);
				LOGGER.info("output: " + result);
				return res;
			}
			if (!r2.getValue11().equals(BigInteger.valueOf(1))) {
				contractAndTxHash result = new contractAndTxHash();
				res.setMessage("SLA is not approved by vendor.");
				res.setResult(null);
				LOGGER.info("output: " + result);
				return res;
			}
			TransactionReceipt con = _contract
					.invoiceUpdate(_month, invoiceId, invoce.getInvoiceAmount(), BigInteger.valueOf(date.getTime()))
					.sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(invoce.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			LOGGER.info("output: " + result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}

	}

	@Override
	public Response contract(String contractAddress) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(contractAddress, WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]> r1 = _contract
					.contractDetails().sendAsync().get();
			Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> r2 = _contract
					.advisoryDetail().sendAsync().get();
			Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>> r3 = _contract
					.allInvoice().sendAsync().get();
			Contract con = new Contract();
			con.setServices(new String(r1.getValue1()).trim());
			con.setVendor(new String(r1.getValue2()).trim());
			con.setSubServiceType(new String(r1.getValue3()).trim());
			con.setBudget((r1.getValue4()));
			con.setStartDate((new Date(r1.getValue5().longValue())));
			con.setEndDate(new Date(r1.getValue6().longValue()));
			con.setBudgetPerMonth((r1.getValue7()));
			con.setPoAmount((r1.getValue8()));
			con.setPoId(new String(r1.getValue9()).trim());
			con.setMsl(r2.getValue1());
			con.setEsl(r2.getValue2());
			con.setUsl(r2.getValue3());
			con.setReward(r2.getValue4());
			con.setPanalty(r2.getValue5());
			con.setCutOff(r2.getValue6());
			con.setRolling(r2.getValue7());
			con.setCreditTerm(r2.getValue8());
			con.setTimestamp(r2.getValue9());
			con.setClientApprovalStatus(r2.getValue10());
			con.setVendorApprovalStatus(r2.getValue11());
			res.setResult(con);
			LOGGER.info("output: " + r1);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	@Override
	public Response invoice(String contractAddress, int month) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(contractAddress, WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			// Tuple4<byte[], BigInteger, BigInteger, BigInteger> r1 =
			// _contract.invoiceDetail(BigInteger.valueOf(month))
			// .sendAsync().get();
			//
			// Tuple5<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint8>,List<Uint8>>
			// r = _contract.allInvoice().sendAsync()
			// .get();
			// Invoice con = new Invoice();
			// con.setInvoiceAmount(r1.getValue2());
			// con.setInvoiceId(new String(r1.getValue1()).trim());
			// con.setPayout(r1.getValue3());
			// res.setResult(con);
			// LOGGER.info("output: " + r1);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	@Override
	public Response allInvoice(String contractAddress) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(contractAddress.trim(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>> r1 = _contract
					.allInvoice().sendAsync().get();

			Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>> r2 = _contract.allInvoice2()
					.sendAsync().get();

			List<Invoice> in = new ArrayList<Invoice>();
			for (int i = 0; i < r1.getValue1().size(); i++) {

				Invoice con = new Invoice();
				con.setInvoiceAmount(r1.getValue2().get(i).getValue());
				con.setInvoiceId(new String(r1.getValue1().get(i).getValue()).trim());
				con.setPayout(r1.getValue3().get(i).getValue());
				con.setMonth(r1.getValue4().get(i).getValue().intValue());
				con.setStatus(r1.getValue5().get(i).getValue().intValue());
				con.setTimestamp(r1.getValue6().get(i).getValue());
				con.setReward(r2.getValue1().get(i).getValue().intValue());
				con.setPenalty(r2.getValue2().get(i).getValue().intValue());
				con.setCutOff(r2.getValue3().get(i).getValue());
				con.setPerformance(r2.getValue4().get(i).getValue().intValue());
				con.setPayoutStatus(r2.getValue5().get(i).getValue().intValue());
				in.add(con);
			}
			res.setResult(in);
			LOGGER.info("output: " + r1);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}

	}

	@Override
	public Response allPerformance(String contractAddress) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(contractAddress, WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>> r1 = _contract.allPerformance().sendAsync()
					.get();
			List<Performance> in = new ArrayList<Performance>();
			for (int i = 0; i < r1.getValue1().size(); i++) {
				Performance con = new Performance();
				con.setPerformance(r1.getValue1().get(i).getValue().intValue());
				con.setMonth(r1.getValue2().get(i).getValue().intValue());
				con.setExpectedPayout(r1.getValue3().get(i).getValue());
				con.setTimestamp(r1.getValue4().get(i).getValue());
				in.add(con);
			}
			res.setResult(in);
			LOGGER.info("output: " + r1);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	public Response updateInvoiceStatus(Invoice invoice) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(invoice.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			Bytes32 invoiceId = addressHelper.stringToBytes32Conversion(invoice.getInvoiceId().trim());
			BigInteger month = BigInteger.valueOf(invoice.getMonth());
			BigInteger status = BigInteger.valueOf(invoice.getStatus());
			TransactionReceipt con = _contract.updateInvoice(month, status, invoiceId).sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(invoice.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	public Response updateInvoicePayoutStatus(Invoice invoice) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(invoice.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			Bytes32 invoiceId = addressHelper.stringToBytes32Conversion(invoice.getInvoiceId().trim());
			BigInteger month = BigInteger.valueOf(invoice.getMonth());
			BigInteger status = BigInteger.valueOf(invoice.getStatus());
			TransactionReceipt con = _contract.updateInvoicePayoutStatus(month, status, invoiceId).sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(invoice.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("smartContractDeploy");
			return res;
		}
	}

	public Response updateSlaClientApprovalStatus(Advisory advisory) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(advisory.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}
			TransactionReceipt con = _contract.updateSlaClientApprovalStatus(advisory.getClientApprovalStatus())
					.sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(advisory.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("updateSlaClientApprovalStatus");
			return res;
		}
	}

	public Response updateSlaVendorApprovalStatus(Advisory advisory) {
		Response res = new Response();
		try {
			Credentials creds = Credentials.create(senderPrivKey);
			BigInteger _gasLimit = BigInteger.valueOf(4000000);
			SLA _contract = SLA.load(advisory.getContractAddress(), WEB3J, creds, _gasPrice, _gasLimit);
			if (!_contract.isValid()) {
				LOGGER.error("invalid contract in  :" + _contract.getContractAddress());
				res.setMessage("Invalid contract");
				res.setResult(null);
				return res;
			}

			TransactionReceipt con = _contract.updateSlaVendorApprovalStatus(advisory.getVendorApprovalStatus())
					.sendAsync().get();
			contractAndTxHash result = new contractAndTxHash();
			result.setContractAddress(advisory.getContractAddress());
			result.setTransactionHash(con.getTransactionHash());
			res.setResult(result);
			return res;
		} catch (Exception ex) {
			res.setResult(null);
			LOGGER.error("Exception :" + ex);
			res.setMessage("Exception in updateSlaVendorApprovalStatus" + ex);
			res.setMethod("updateSlaVendorApprovalStatus");
			return res;
		}
	}

}
