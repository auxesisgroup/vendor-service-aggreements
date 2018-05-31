
package com.auxesis.kpmg.smartcontract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple11;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j
 * command line tools</a>, or the
 * org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.0.2.
 */
public final class SLA extends Contract {
	private static final String BINARY = "6060604052341561000f57600080fd5b604051610120806120f38339810160405280805191906020018051919060200180519190602001805191906020018051919060200180519190602001805191906020018051919060200180519150610067905061011b565b61012060405190810160409081528b8252602082018b90528101899052606081018890526080810187905260a0810186905260c0810185905260e0810184905261010081018390529050806000815181556020820151600182015560408201516002820155606082015181600301556080820151816004015560a0820151816005015560c0820151816006015560e08201518160070155610100820151600890910155506101679950505050505050505050565b6101206040519081016040908152600080835260208301819052908201819052606082018190526080820181905260a0820181905260c0820181905260e0820181905261010082015290565b611f7d806101766000396000f3006060604052600436106100ef5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416622c1a9e81146100f457806309887fc8146101225780630deeed031461029d578063105fee12146102e457806315603d98146103115780631d9cfe06146104475780632dc540de1461045d57806335a5cc881461047c57806339571d6e146104c0578063561889151461052057806389ff164f146106e0578063975ffe921461075e578063b075eb511461077d578063bf0c8c0014610796578063cba24159146107b5578063e4bf972a1461082d578063fc54fc7b14610843575b600080fd5b34156100ff57600080fd5b610110600435602435604435610862565b60405190815260200160405180910390f35b341561012d57600080fd5b610135610888565b60405180806020018060200180602001806020018060200186810386528b818151815260200191508051906020019060200280838360005b8381101561018557808201518382015260200161016d565b5050505090500186810385528a818151815260200191508051906020019060200280838360005b838110156101c45780820151838201526020016101ac565b50505050905001868103845289818151815260200191508051906020019060200280838360005b838110156102035780820151838201526020016101eb565b50505050905001868103835288818151815260200191508051906020019060200280838360005b8381101561024257808201518382015260200161022a565b50505050905001868103825287818151815260200191508051906020019060200280838360005b83811015610281578082015183820152602001610269565b505050509050019a505050505050505050505060405180910390f35b34156102a857600080fd5b6102b3600435610b03565b604051808560ff1660ff16815260200184815260200183815260200182815260200194505050505060405180910390f35b34156102ef57600080fd5b6102fd60ff60043516610b2e565b604051901515815260200160405180910390f35b341561031c57600080fd5b610324610b46565b6040518080602001806020018060200180602001858103855289818151815260200191508051906020019060200280838360005b83811015610370578082015183820152602001610358565b50505050905001858103845288818151815260200191508051906020019060200280838360005b838110156103af578082015183820152602001610397565b50505050905001858103835287818151815260200191508051906020019060200280838360005b838110156103ee5780820151838201526020016103d6565b50505050905001858103825286818151815260200191508051906020019060200280838360005b8381101561042d578082015183820152602001610415565b505050509050019850505050505050505060405180910390f35b341561045257600080fd5b6102b3600435610d19565b341561046857600080fd5b6102fd60043560ff60243516604435610d55565b341561048757600080fd5b6102fd60ff60043581169060243581169060443581169060643581169060843581169060a43581169060c4351660e43561010435610fac565b34156104cb57600080fd5b6104d3611161565b60405198895260208901979097526040808901969096526060880194909452608087019290925260a086015260c085015260e0840152610100830191909152610120909101905180910390f35b341561052b57600080fd5b61053361117f565b6040518080602001806020018060200180602001806020018060200187810387528d818151815260200191508051906020019060200280838360005b8381101561058757808201518382015260200161056f565b5050505090500187810386528c818151815260200191508051906020019060200280838360005b838110156105c65780820151838201526020016105ae565b5050505090500187810385528b818151815260200191508051906020019060200280838360005b838110156106055780820151838201526020016105ed565b5050505090500187810384528a818151815260200191508051906020019060200280838360005b8381101561064457808201518382015260200161062c565b50505050905001878103835289818151815260200191508051906020019060200280838360005b8381101561068357808201518382015260200161066b565b50505050905001878103825288818151815260200191508051906020019060200280838360005b838110156106c25780820151838201526020016106aa565b505050509050019c5050505050505050505050505060405180910390f35b34156106eb57600080fd5b6106f6600435611428565b6040519a8b5260208b01999099526040808b019890985260608a019690965260ff94851660808a015292841660a089015290831660c0880152151560e08701528116610100860152610120850191909152909116610140830152610160909101905180910390f35b341561076957600080fd5b61011060043560ff602435166044356114a2565b341561078857600080fd5b6102fd60ff6004351661170e565b34156107a157600080fd5b6102fd60043560ff6024351660443561172b565b34156107c057600080fd5b6107c8611975565b60405160ff9b8c168152998b1660208b0152978a166040808b0191909152968a1660608a0152948916608089015292881660a088015290871660c087015260e08601526101008501528416610120840152921661014082015261016001905180910390f35b341561083857600080fd5b6106f66004356119d0565b341561084e57600080fd5b610110600435602435604435606435611a39565b6103e8600a6064600194909401810a949094029290920460050192909204919091020490565b610890611dcf565b610898611dcf565b6108a0611dcf565b6108a8611dcf565b6108b0611dcf565b60006108ba611dcf565b6108c2611dcf565b6108ca611dcf565b6108d2611dcf565b6108da611dcf565b60105495506000866040518059106108ef5750595b90808252806020026020018201604052509550866040518059106109105750595b90808252806020026020018201604052509450866040518059106109315750595b90808252806020026020018201604052509350866040518059106109525750595b90808252806020026020018201604052509250866040518059106109735750595b90808252806020026020018201604052509150600090505b86811015610af05760108054829081106109a157fe5b6000918252602090912060079091020160040154610100900460ff168682815181106109c957fe5b6020908102909101015260108054829081106109e157fe5b600091825260209091206007909102016004015462010000900460ff16858281518110610a0a57fe5b602090810290910101526010805482908110610a2257fe5b906000526020600020906007020160040160039054906101000a900460ff16848281518110610a4d57fe5b9115156020928302909101909101526010805482908110610a6a57fe5b906000526020600020906007020160040160049054906101000a900460ff16838281518110610a9557fe5b60ff9092166020928302909101909101526010805482908110610ab457fe5b600091825260209091206006600790920201015460ff16828281518110610ad757fe5b60ff90921660209283029091019091015260010161098b565b50939a9299509097509550909350915050565b600d60205260009081526040902080546001820154600283015460039093015460ff90921692909184565b600c805460ff831660ff199091161790556001919050565b610b4e611dcf565b610b56611dcf565b610b5e611dcf565b610b66611dcf565b6000610b70611dcf565b610b78611dcf565b610b80611dcf565b610b88611dcf565b600f549450600085604051805910610b9d5750595b9080825280602002602001820160405250945085604051805910610bbe5750595b9080825280602002602001820160405250935085604051805910610bdf5750595b9080825280602002602001820160405250925085604051805910610c005750595b90808252806020026020018201604052509150600090505b85811015610d0957600f805482908110610c2e57fe5b600091825260209091206004909102015460ff16858281518110610c4e57fe5b60ff909216602092830290910190910152600f805482908110610c6d57fe5b906000526020600020906004020160010154848281518110610c8b57fe5b60209081029091010152600f805482908110610ca357fe5b906000526020600020906004020160020154838281518110610cc157fe5b60209081029091010152600f805482908110610cd957fe5b906000526020600020906004020160030154828281518110610cf757fe5b60209081029091010152600101610c18565b5092989197509550909350915050565b600f805482908110610d2757fe5b6000918252602090912060049091020180546001820154600283015460039093015460ff9092169350919084565b6000610d5f611de1565b60008061016060405190810160409081526000898152600e6020818152838320805486526001810154828701526002810154948601949094526003840154606086015260ff808c1660808701526004850154610100808204831660a0890152620100008204831660c0890152630100000082048316151560e08901526401000000009091048216908701526005850154610120870152600685015416610140860152918b90529052909350839081518155602082015181600101556040820151816002015560608201518160030155608082015160048201805460ff191660ff9290921691909117905560a08201518160040160016101000a81548160ff021916908360ff16021790555060c08201518160040160026101000a81548160ff021916908360ff16021790555060e082015160048201805491151563010000000263ff000000199092169190911790556101008201518160040160046101000a81548160ff021916908360ff1602179055506101208201518160050155610140820151600691909101805460ff191660ff9092169190911790555050601054905060005b81811015610f9f576010805486919083908110610f1b57fe5b6000918252602090912060079091020154148015610f58575086601082815481101515610f4457fe5b906000526020600020906007020160030154145b15610f975785601082815481101515610f6d57fe5b906000526020600020906007020160040160006101000a81548160ff021916908360ff1602179055505b600101610f02565b5060019695505050505050565b6000610fb6611de1565b610160604051908101604052808c60ff1681526020018b60ff1681526020018a60ff1681526020018960ff1681526020018860ff1681526020018760ff1681526020018660ff168152602001858152602001848152602001600060ff168152602001600060ff1681525090508060096000820151815460ff191660ff919091161781556020820151815460ff919091166101000261ff00199091161781556040820151815460ff91909116620100000262ff0000199091161781556060820151815460ff9190911663010000000263ff000000199091161781556080820151815460ff919091166401000000000264ff000000001990911617815560a0820151815460ff91909116650100000000000265ff00000000001990911617815560c0820151815460ff9190911666010000000000000266ff0000000000001990911617815560e08201518160010155610100820151816002015561012082015160038201805460ff191660ff929092169190911790556101408201516003909101805460ff929092166101000261ff00199092169190911790555060019150509998505050505050505050565b60005460015460025460035460045460055460065460075460085489565b611187611dcf565b61118f611dcf565b611197611dcf565b61119f611dcf565b6111a7611dcf565b6111af611dcf565b60006111b9611dcf565b6111c1611dcf565b6111c9611dcf565b6111d1611dcf565b6111d9611dcf565b6111e1611dcf565b60105496506000876040518059106111f65750595b90808252806020026020018201604052509650876040518059106112175750595b90808252806020026020018201604052509550876040518059106112385750595b90808252806020026020018201604052509450876040518059106112595750595b908082528060200260200182016040525093508760405180591061127a5750595b908082528060200260200182016040525092508760405180591061129b5750595b90808252806020026020018201604052509150600090505b878110156114135760108054829081106112c957fe5b9060005260206000209060070201600001548782815181106112e757fe5b6020908102909101015260108054829081106112ff57fe5b90600052602060002090600702016001015486828151811061131d57fe5b60209081029091010152601080548290811061133557fe5b90600052602060002090600702016002015485828151811061135357fe5b60209081029091010152601080548290811061136b57fe5b90600052602060002090600702016003015484828151811061138957fe5b6020908102909101015260108054829081106113a157fe5b600091825260209091206004600790920201015460ff168382815181106113c457fe5b60ff90921660209283029091019091015260108054829081106113e357fe5b90600052602060002090600702016005015482828151811061140157fe5b602090810290910101526001016112b3565b50949c939b5091995097509550909350915050565b601080548290811061143657fe5b6000918252602090912060079091020180546001820154600283015460038401546004850154600586015460069096015494965092949193909260ff8082169361010083048216936201000084048316936301000000810484169364010000000090910481169291168b565b6000806114ad611e3d565b6000806114b8611e3d565b600c5460ff1660011415806114d95750600c5460ff61010090910416600114155b156114e75760009550611702565b6009546000955060ff65010000000000909104811690891610156115cc576080604051908101604052808960ff1681526020018a8152602001868152602001888152509350600f80548060010182816115409190611e69565b600092835260209092208691600402018151815460ff191660ff919091161781556020820151816001015560408201518160020155606082015160039091015550506000898152600d6020526040902084908151815460ff191660ff91909116178155602082015181600101556040820151816002015560608201518160030155905050849550611702565b60095460ff90811690891610156115fe576009546006546115fb91640100000000900460ff1690600390610862565b92505b60095460ff620100009091048116908916111561163557600954600654611632916301000000900460ff1690600390610862565b91505b600654820183900394506080604051908101604052808960ff1681526020018a8152602001868152602001888152509050600f805480600101828161167a9190611e69565b600092835260209092208391600402018151815460ff191660ff919091161781556020820151816001015560408201518160020155606082015160039091015550506000898152600d6020526040902081908151815460ff191660ff919091161781556020820151816001015560408201518160020155606082015181600301559050508495505b50505050509392505050565b600c805460ff83166101000261ff00199091161790556001919050565b6000611735611de1565b60008061016060405190810160409081526000898152600e6020818152838320805486526001810154828701526002810154948601949094526003840154606086015260ff808c16608087018190526004860154610100808204841660a08a0152620100008204841660c08a0152630100000082048416151560e08a0152640100000000909104909216918701919091526005850154610120870152610140860152918b90529052909350839081518155602082015181600101556040820151816002015560608201518160030155608082015160048201805460ff191660ff9290921691909117905560a08201518160040160016101000a81548160ff021916908360ff16021790555060c08201518160040160026101000a81548160ff021916908360ff16021790555060e082015160048201805491151563010000000263ff000000199092169190911790556101008201518160040160046101000a81548160ff021916908360ff1602179055506101208201518160050155610140820151600691909101805460ff191660ff9092169190911790555050601054905060005b81811015610f9f5760108054869190839081106118f157fe5b600091825260209091206007909102015414801561192e57508660108281548110151561191a57fe5b906000526020600020906007020160030154145b1561196d578560108281548110151561194357fe5b906000526020600020906007020160060160006101000a81548160ff021916908360ff1602179055505b6001016118d8565b600954600a54600b54600c5460ff80851694610100808204831695620100008304841695630100000084048516956401000000008504861695650100000000008604811695660100000000000090048116948282169204168b565b600e602052600090815260409020805460018201546002830154600384015460048501546005860154600690960154949593949293919260ff8083169361010084048216936201000081048316936301000000820484169364010000000090920482169291168b565b6000806000806000806000611a4c611de1565b600c5460ff166001141580611a6d5750600c5460ff61010090910416600114155b15611a7b5760009750611dc0565b60008c8152600d602052604081205490975087965086955085945084935060ff169150811515611aad57869750611dc0565b60095460ff65010000000000909104811690831610611b465760095460ff9081169083161015611afc5760095460065464010000000090910460ff169350611af9908490600390610862565b95505b60095460ff6201000090910481169083161115611b3757600954600654630100000090910460ff169450611b34908590600390610862565b94505b60065485018690039650611b4b565b600096505b61016060405190810160409081528c825260208083018d90528183018a9052606083018f905260006080840181905260ff88811660a086015287811660c086015260e08501829052861661010085015261012084018d905261014084018190528f8152600e90915220909150819081518155602082015181600101556040820151816002015560608201518160030155608082015160048201805460ff191660ff9290921691909117905560a08201518160040160016101000a81548160ff021916908360ff16021790555060c08201518160040160026101000a81548160ff021916908360ff16021790555060e082015160048201805491151563010000000263ff000000199092169190911790556101008201518160040160046101000a81548160ff021916908360ff1602179055506101208201518160050155610140820151600691909101805460ff191660ff909216919091179055506010805460018101611cb88382611e9a565b6000928352602090922083916007020181518155602082015181600101556040820151816002015560608201518160030155608082015160048201805460ff191660ff9290921691909117905560a08201518160040160016101000a81548160ff021916908360ff16021790555060c08201518160040160026101000a81548160ff021916908360ff16021790555060e082015160048201805491151563010000000263ff000000199092169190911790556101008201518160040160046101000a81548160ff021916908360ff16021790555061012082015181600501556101408201518160060160006101000a81548160ff021916908360ff1602179055505050508697505b50505050505050949350505050565b60206040519081016040526000815290565b6101606040519081016040908152600080835260208301819052908201819052606082018190526080820181905260a0820181905260c0820181905260e082018190526101008201819052610120820181905261014082015290565b608060405190810160405280600060ff1681526020016000815260200160008152602001600081525090565b815481835581811511611e9557600402816004028360005260206000209182019101611e959190611ec6565b505050565b815481835581811511611e9557600702816007028360005260206000209182019101611e959190611f00565b611efd91905b80821115611ef957805460ff19168155600060018201819055600282018190556003820155600401611ecc565b5090565b90565b611efd91905b80821115611ef957600080825560018201819055600282018190556003820181905560048201805464ffffffffff19169055600582015560068101805460ff19169055600701611f065600a165627a7a72305820b3927aa2c455298405c25915aa98fe9a3183f042ed6e3cab7f97218575c570d30029";

	private SLA(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	private SLA(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<BigInteger> percent(BigInteger numerator, BigInteger precision, BigInteger percentageOf) {
		Function function = new Function("percent",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(numerator),
						new org.web3j.abi.datatypes.generated.Uint256(precision),
						new org.web3j.abi.datatypes.generated.Uint256(percentageOf)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteCall<TransactionReceipt> updateSlaVendorApprovalStatus(BigInteger status) {
		Function function = new Function("updateSlaVendorApprovalStatus",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(status)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> updateSlaClientApprovalStatus(BigInteger status) {
		Function function = new Function("updateSlaClientApprovalStatus",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(status)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> performanceMapping(BigInteger param0) {
		final Function function = new Function("performanceMapping",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}));
		return new RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(
				new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
					@Override
					public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
								(BigInteger) results.get(0).getValue(), (BigInteger) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (BigInteger) results.get(3).getValue());
					}
				});
	}

	public RemoteCall<Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>>> allInvoice2() {
		final Function function = new Function("allInvoice2", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Bool>>() {
				}, new TypeReference<DynamicArray<Uint8>>() {
				}, new TypeReference<DynamicArray<Uint8>>() {
				}));
		return new RemoteCall<Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>>>(
				new Callable<Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>>>() {
					@Override
					public Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple5<List<Uint256>, List<Uint256>, List<Bool>, List<Uint8>, List<Uint8>>(
								(List<Uint256>) results.get(0).getValue(), (List<Uint256>) results.get(1).getValue(),
								(List<Bool>) results.get(2).getValue(), (List<Uint8>) results.get(3).getValue(),
								(List<Uint8>) results.get(4).getValue());
					}
				});
	}

	public RemoteCall<TransactionReceipt> updateInvoicePayoutStatus(BigInteger month, BigInteger status,
			Bytes32 invoiceId) {
		Function function = new Function("updateInvoicePayoutStatus",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(month),
						new org.web3j.abi.datatypes.generated.Uint8(status), (invoiceId)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>>> allPerformance() {
		final Function function = new Function("allPerformance", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint8>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}));
		return new RemoteCall<Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>>>(
				new Callable<Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>>>() {
					@Override
					public Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple4<List<Uint8>, List<Uint256>, List<Uint256>, List<Uint256>>(
								(List<Uint8>) results.get(0).getValue(), (List<Uint256>) results.get(1).getValue(),
								(List<Uint256>) results.get(2).getValue(), (List<Uint256>) results.get(3).getValue());
					}
				});
	}

	public RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> allPerformance(BigInteger param0) {
		final Function function = new Function("allPerformance",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}));
		return new RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(
				new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
					@Override
					public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
								(BigInteger) results.get(0).getValue(), (BigInteger) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (BigInteger) results.get(3).getValue());
					}
				});
	}

	public RemoteCall<TransactionReceipt> updateInvoice(BigInteger month, BigInteger status, Bytes32 invoiceId) {
		Function function = new Function("updateInvoice",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(month),
						new org.web3j.abi.datatypes.generated.Uint8(status), (invoiceId)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> advisorySet(BigInteger msl, BigInteger esl, BigInteger usl, BigInteger reward,
			BigInteger panalty, BigInteger cutOff, BigInteger roling, BigInteger credirTerm, BigInteger timestamp) {
		Function function = new Function("advisorySet", Arrays.<Type>asList(
				new org.web3j.abi.datatypes.generated.Uint8(msl), new org.web3j.abi.datatypes.generated.Uint8(esl),
				new org.web3j.abi.datatypes.generated.Uint8(usl), new org.web3j.abi.datatypes.generated.Uint8(reward),
				new org.web3j.abi.datatypes.generated.Uint8(panalty),
				new org.web3j.abi.datatypes.generated.Uint8(cutOff),
				new org.web3j.abi.datatypes.generated.Uint8(roling),
				new org.web3j.abi.datatypes.generated.Uint256(credirTerm),
				new org.web3j.abi.datatypes.generated.Uint256(timestamp)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>>> allInvoice() {
		final Function function = new Function("allInvoice", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Uint8>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}));
		return new RemoteCall<Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>>>(
				new Callable<Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>>>() {
					@Override
					public Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple6<List<Bytes32>, List<Uint256>, List<Uint256>, List<Uint256>, List<Uint8>, List<Uint256>>(
								(List<Bytes32>) results.get(0).getValue(), (List<Uint256>) results.get(1).getValue(),
								(List<Uint256>) results.get(2).getValue(), (List<Uint256>) results.get(3).getValue(),
								(List<Uint8>) results.get(4).getValue(), (List<Uint256>) results.get(5).getValue());
					}
				});
	}

	public RemoteCall<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> AllInvoiceMonths(
			BigInteger param0) {
		final Function function = new Function("AllInvoiceMonths",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint256>() {
				}));
		return new RemoteCall<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
				new Callable<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
					@Override
					public Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
								(byte[]) results.get(0).getValue(), (BigInteger) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (BigInteger) results.get(3).getValue(),
								(BigInteger) results.get(4).getValue(), (BigInteger) results.get(5).getValue());
					}
				});
	}

	public RemoteCall<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]>> contractDetails() {
		final Function function = new Function("contractDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {
				}, new TypeReference<Bytes32>() {
				}, new TypeReference<Bytes32>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Bytes32>() {
				}));
		return new RemoteCall<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]>>(
				new Callable<Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]>>() {
					@Override
					public Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple9<byte[], byte[], byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[]>(
								(byte[]) results.get(0).getValue(), (byte[]) results.get(1).getValue(),
								(byte[]) results.get(2).getValue(), (BigInteger) results.get(3).getValue(),
								(BigInteger) results.get(4).getValue(), (BigInteger) results.get(5).getValue(),
								(BigInteger) results.get(6).getValue(), (BigInteger) results.get(7).getValue(),
								(byte[]) results.get(8).getValue());
					}
				});
	}

	public RemoteCall<TransactionReceipt> performanceSet(BigInteger month, BigInteger _performance,
			BigInteger timestamp) {
		Function function = new Function("performanceSet",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(month),
						new org.web3j.abi.datatypes.generated.Uint8(_performance),
						new org.web3j.abi.datatypes.generated.Uint256(timestamp)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> advisoryDetail() {
		final Function function = new Function("advisoryDetail", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint8>() {
				}));
		return new RemoteCall<Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
				new Callable<Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
					@Override
					public Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple11<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
								(BigInteger) results.get(0).getValue(), (BigInteger) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (BigInteger) results.get(3).getValue(),
								(BigInteger) results.get(4).getValue(), (BigInteger) results.get(5).getValue(),
								(BigInteger) results.get(6).getValue(), (BigInteger) results.get(7).getValue(),
								(BigInteger) results.get(8).getValue(), (BigInteger) results.get(9).getValue(),
								(BigInteger) results.get(10).getValue());
					}
				});
	}

	public RemoteCall<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> invoiceDetail(
			BigInteger param0) {
		final Function function = new Function("invoiceDetail",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Uint8>() {
				}, new TypeReference<Uint256>() {
				}));
		return new RemoteCall<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
				new Callable<Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
					@Override
					public Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple6<byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
								(byte[]) results.get(0).getValue(), (BigInteger) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (BigInteger) results.get(3).getValue(),
								(BigInteger) results.get(4).getValue(), (BigInteger) results.get(5).getValue());
					}
				});
	}

	public RemoteCall<TransactionReceipt> invoiceUpdate(BigInteger month, Bytes32 invoiceId, BigInteger invoiceAmount,
			BigInteger timestamp) {
		Function function = new Function("invoiceUpdate",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(month), (invoiceId),
						new org.web3j.abi.datatypes.generated.Uint256(invoiceAmount),
						new org.web3j.abi.datatypes.generated.Uint256(timestamp)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public static RemoteCall<SLA> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit,
			Bytes32 services, Bytes32 vendor, Bytes32 subSrviceType, BigInteger budget, BigInteger startDate,
			BigInteger endData, BigInteger budgetPerMonth, BigInteger poAmount, Bytes32 poId) {
		String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList((services), (vendor),
				(subSrviceType), new org.web3j.abi.datatypes.generated.Uint256(budget),
				new org.web3j.abi.datatypes.generated.Uint256(startDate),
				new org.web3j.abi.datatypes.generated.Uint256(endData),
				new org.web3j.abi.datatypes.generated.Uint256(budgetPerMonth),
				new org.web3j.abi.datatypes.generated.Uint256(poAmount), (poId)));
		return deployRemoteCall(SLA.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
	}

	public static RemoteCall<SLA> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
			BigInteger gasLimit, byte[] services, byte[] vendor, byte[] subSrviceType, BigInteger budget,
			BigInteger startDate, BigInteger endData, BigInteger budgetPerMonth, BigInteger poAmount, byte[] poId) {
		String encodedConstructor = FunctionEncoder
				.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(services),
						new org.web3j.abi.datatypes.generated.Bytes32(vendor),
						new org.web3j.abi.datatypes.generated.Bytes32(subSrviceType),
						new org.web3j.abi.datatypes.generated.Uint256(budget),
						new org.web3j.abi.datatypes.generated.Uint256(startDate),
						new org.web3j.abi.datatypes.generated.Uint256(endData),
						new org.web3j.abi.datatypes.generated.Uint256(budgetPerMonth),
						new org.web3j.abi.datatypes.generated.Uint256(poAmount),
						new org.web3j.abi.datatypes.generated.Bytes32(poId)));
		return deployRemoteCall(SLA.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
	}

	public static SLA load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return new SLA(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static SLA load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new SLA(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}
}