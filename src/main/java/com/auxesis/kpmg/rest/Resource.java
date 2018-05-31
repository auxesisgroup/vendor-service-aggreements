package com.auxesis.kpmg.rest;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auxesis.kpmg.contracts.ISmartContract;
import com.auxesis.kpmg.entity.Advisory;
import com.auxesis.kpmg.entity.Contract;
import com.auxesis.kpmg.entity.Invoice;
import com.auxesis.kpmg.entity.Performance;
import com.auxesis.kpmg.entity.Response;
import com.auxesis.kpmg.entity.User;
import com.auxesis.kpmg.entity.contractAndTxHash;
import com.auxesis.kpmg.entity.smartContract;
import com.auxesis.kpmg.service.ContractS;
import com.auxesis.kpmg.service.UserS;

//@Configuration
//@EnableJpaRepositories(basePackageClasses = smartContract.class)
@RestController
@RequestMapping("/api/kpmg")
public class Resource {
	final static Logger LOGGER = LoggerFactory.getLogger(Resource.class);

	@Autowired
	private ISmartContract contract;

	@Autowired
	private ContractS contractService;

	@Autowired
	private UserS _users;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> init() {
		Response res = new Response();
		try {
			// String webUrl = "http://139.59.213.205:7007";
			// @Value("${web3j.masterKey}")
			// private String masterKey;
			// BlochchainProperties _BlochchainProperties = new BlochchainProperties();
			// variable
			LOGGER.info("Started...........");
			res.setResult("Started...........");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			LOGGER.error("Exception :" + Ex);
			res.setMessage("Exception " + Ex);
			res.setResult(null);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ResponseEntity<Object> signup(@RequestBody User user) {
		Response res = new Response();
		try {
			String errorMsg = "";
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			for (ConstraintViolation<User> violation : violations) {
				errorMsg += violation.getMessage() + " ";
			}
			if (violations.size() != 0) {
				res.setResult(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			user.setId(UUID.randomUUID().toString());
			_users.users.save(user);
			res.setResult("Successfully sign up.");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setMessage("Exception : " + ex);
			return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody User user) {
		Response res = new Response();
		try {
			String errorMsg = "";
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			for (ConstraintViolation<User> violation : violations) {
				errorMsg += violation.getMessage() + " ";
			}
			if (violations.size() != 0) {
				res.setResult(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			Optional<User> _temp = _users.users.login(user.getUsername(), user.getPassword(), user.getRole());
			if (!_temp.isPresent()) {
				res.setMessage("invalid credentials.");
				return new ResponseEntity<Object>(res, HttpStatus.UNAUTHORIZED);
			}
			res.setResult(_temp);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Exception : " + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract", method = RequestMethod.POST)
	public ResponseEntity<Object> setNewPaymentContract(@Valid @RequestBody Contract _contract,
			BindingResult validationResult) {
		Response res = new Response();
		try {

			LOGGER.info("paymentcontract input : " + _contract.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}

				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res = contract.smartContractDeploy(_contract);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			contractAndTxHash txtDetails = (contractAndTxHash) res.getResult();
			smartContract con = new smartContract();
			con.setServices(_contract.getServices());
			con.setVendor(_contract.getVendor());
			con.setBudget(_contract.getBudget());
			con.setPoAmount(_contract.getPoAmount());
			con.setPoId(_contract.getPoId());
			con.setBudgetPerMonth(_contract.getBudgetPerMonth());
			con.setSubServiceType(_contract.getSubServiceType());
			con.setContractAddress(txtDetails.getContractAddress());
			con.setEndDate(_contract.getEndDate());
			con.setStartDate(_contract.getStartDate());
			con.setVendorId(_contract.getVendor().trim());
			con.setId(UUID.randomUUID().toString());
			con.setClientId(_contract.getClientId());
			contractService.contract.save(con);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/advisory", method = RequestMethod.POST)
	public ResponseEntity<Object> advisory(@Valid @RequestBody Advisory _advisory, BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("paymentcontract input : " + _advisory.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res = contract.advisory(_advisory);

			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			int val = contractService.contract.updateSLA(_advisory.getMsl(), _advisory.getEsl(), _advisory.getUsl(),
					_advisory.getReward(), _advisory.getPanalty(), _advisory.getCutOff(), _advisory.getRolling(),
					_advisory.getContractAddress());
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/performance", method = RequestMethod.POST)
	public ResponseEntity<Object> advisory(@Valid @RequestBody Performance _performance,
			BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("paymentcontract input : " + _performance.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res = contract.performance(_performance);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/payout", method = RequestMethod.POST)
	public ResponseEntity<Object> advisory(@Valid @RequestBody Invoice _invoice, BindingResult validationResult) {
		Response res = new Response();
		try {
			LOGGER.info("paymentcontract input : " + _invoice.toString());
			String errorMsg = "";
			if (validationResult.hasErrors()) {
				for (ObjectError msg : validationResult.getAllErrors()) {
					errorMsg += msg.getDefaultMessage() + " ";
				}
				LOGGER.error("invalid input : " + errorMsg);
				res.setMessage(errorMsg);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res = contract.payout(_invoice);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{contractaddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> advisory(@PathVariable("contractaddress") String contractaddress) {
		Response res = new Response();
		try {
			res = contract.contract(contractaddress);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/payout/{contractaddress}/{month}", method = RequestMethod.GET)
	public ResponseEntity<Object> advisory(@PathVariable("contractaddress") String contractaddress,
			@PathVariable("month") int month) {
		Response res = new Response();
		try {

			res = contract.invoice(contractaddress, month);
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public ResponseEntity<Object> contract() {
		Response res = new Response();
		try {
			res.setResult(contractService.contract.findAll());
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/vendor/{vendorid}", method = RequestMethod.GET)
	public ResponseEntity<Object> contract(@PathVariable("vendorid") String vendorId) {
		Response res = new Response();
		try {
			res.setResult(contractService.contract.findByIdVendorId(vendorId));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/client/{clientid}", method = RequestMethod.GET)
	public ResponseEntity<Object> contractClient(@PathVariable("clientid") String clientid) {
		Response res = new Response();
		try {
			res.setResult(contractService.contract.findByIdClientId(clientid));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/advisory/{advisoryid}", method = RequestMethod.GET)
	public ResponseEntity<Object> contractAdvisory(@PathVariable("advisoryid") String advisoryid) {
		Response res = new Response();
		try {
			res.setResult(contractService.contract.findByIdAdvisoryId(advisoryid));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/invoice/{contractAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> contractInvoice(@PathVariable("contractAddress") String contractAddress) {
		Response res = new Response();
		try {
			res = contract.allInvoice(contractAddress);
			if (res.getMessage() != null) {
				res.setResult(null);
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/{contractAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> contractDetails(@PathVariable("contractAddress") String contractAddress) {
		Response res = new Response();
		try {
			res.setResult(contractService.contract.findByIdContractAddress(contractAddress));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/performance/{contractAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> contractPerformance(@PathVariable("contractAddress") String contractAddress) {
		Response res = new Response();
		try {
			res = (contract.allPerformance(contractAddress));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/invoice/status", method = RequestMethod.POST)
	public ResponseEntity<Object> invoiceStatusUpdate(@RequestBody Invoice invoice) {
		Response res = new Response();
		try {
			System.out.println("status" + invoice.getStatus());
			res = (contract.updateInvoiceStatus(invoice));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/invoice/payoutstatus", method = RequestMethod.POST)
	public ResponseEntity<Object> invoiceStatusPayoutUpdate(@RequestBody Invoice invoice) {
		Response res = new Response();
		try {
			System.out.println("status" + invoice.getStatus());
			res = (contract.updateInvoicePayoutStatus(invoice));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/sla/vendorapproval", method = RequestMethod.POST)
	public ResponseEntity<Object> vendorApproval(@RequestBody Advisory advisory) {
		Response res = new Response();
		try {
			System.out.println("status" + advisory.getVendorApprovalStatus());

			if (advisory.getContractAddress() == null || advisory.getVendorApprovalStatus() == null) {
				res.setResult(null);
				res.setMethod("invalide input. ");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res = (contract.updateSlaVendorApprovalStatus(advisory));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/contract/sla/clientapproval", method = RequestMethod.POST)
	public ResponseEntity<Object> clientapproval(@RequestBody Advisory advisory) {
		Response res = new Response();

		System.out.println("status" + advisory.getClientApprovalStatus());

		System.out.println(advisory.getContractAddress() + "" + advisory.getClientApprovalStatus());
		try {
			if (advisory.getContractAddress() == null || advisory.getClientApprovalStatus() == null) {
				res.setResult(null);
				res.setMethod("invalide input. ");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			System.out.println("status" + advisory.getVendorApprovalStatus());
			res = (contract.updateSlaClientApprovalStatus(advisory));
			if (res.getMessage() != null) {
				res.setResult(null);
				res.setMethod("contract");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			LOGGER.error("Exception in paymentcontract : " + Ex);
			res.setMessage("Exception " + Ex);
			res.setMethod("contract");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
