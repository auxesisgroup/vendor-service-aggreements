package com.auxesis.kpmg.contracts;

import org.springframework.context.annotation.ComponentScan;

import com.auxesis.kpmg.entity.Advisory;
import com.auxesis.kpmg.entity.Contract;
import com.auxesis.kpmg.entity.Invoice;
import com.auxesis.kpmg.entity.Performance;
import com.auxesis.kpmg.entity.Response;

@ComponentScan
public interface ISmartContract {

	Response smartContractDeploy(Contract contract);

	Response advisory(Advisory advisory);

	Response performance(Performance performance);

	Response payout(Invoice invoce);

	Response contract(String contractAddress);

	public Response invoice(String contractAddress, int month);

	public Response allInvoice(String contractAddress);

	public Response allPerformance(String contractAddress);

	public Response updateInvoiceStatus(Invoice invoice);

	public Response updateInvoicePayoutStatus(Invoice invoice);

	public Response updateSlaVendorApprovalStatus(Advisory advisory);

	public Response updateSlaClientApprovalStatus(Advisory advisory);
}
