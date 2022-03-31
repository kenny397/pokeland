package com.ssafy.b208.api.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class SsafyNFT extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040518060400160405280600281526020017f63630000000000000000000000000000000000000000000000000000000000008152506040518060400160405280600281526020017f6363000000000000000000000000000000000000000000000000000000000000815250816000908051906020019062000096929190620000b8565b508060019080519060200190620000af929190620000b8565b505050620001cd565b828054620000c69062000197565b90600052602060002090601f016020900481019282620000ea576000855562000136565b82601f106200010557805160ff191683800117855562000136565b8280016001018555821562000136579182015b828111156200013557825182559160200191906001019062000118565b5b50905062000145919062000149565b5090565b5b80821115620001645760008160009055506001016200014a565b5090565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620001b057607f821691505b60208210811415620001c757620001c662000168565b5b50919050565b61258180620001dd6000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c806370a082311161008c578063a22cb46511610066578063a22cb4651461026f578063b88d4fde1461028b578063c87b56dd146102a7578063e985e9c5146102d7576100ea565b806370a08231146101f157806395d89b4114610221578063a15ab08d1461023f576100ea565b8063095ea7b3116100c8578063095ea7b31461016d57806323b872dd1461018957806342842e0e146101a55780636352211e146101c1576100ea565b806301ffc9a7146100ef57806306fdde031461011f578063081812fc1461013d575b600080fd5b61010960048036038101906101049190611602565b610307565b604051610116919061164a565b60405180910390f35b6101276103e9565b60405161013491906116fe565b60405180910390f35b61015760048036038101906101529190611756565b61047b565b60405161016491906117c4565b60405180910390f35b6101876004803603810190610182919061180b565b610500565b005b6101a3600480360381019061019e919061184b565b610618565b005b6101bf60048036038101906101ba919061184b565b610678565b005b6101db60048036038101906101d69190611756565b610698565b6040516101e891906117c4565b60405180910390f35b61020b6004803603810190610206919061189e565b61074a565b60405161021891906118da565b60405180910390f35b610229610802565b60405161023691906116fe565b60405180910390f35b61025960048036038101906102549190611a2a565b610894565b60405161026691906118da565b60405180910390f35b61028960048036038101906102849190611ab2565b6108ea565b005b6102a560048036038101906102a09190611b93565b610900565b005b6102c160048036038101906102bc9190611756565b610962565b6040516102ce91906116fe565b60405180910390f35b6102f160048036038101906102ec9190611c16565b610a07565b6040516102fe919061164a565b60405180910390f35b60007f80ac58cd000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614806103d257507f5b5e139f000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b806103e257506103e182610a9b565b5b9050919050565b6060600080546103f890611c85565b80601f016020809104026020016040519081016040528092919081815260200182805461042490611c85565b80156104715780601f1061044657610100808354040283529160200191610471565b820191906000526020600020905b81548152906001019060200180831161045457829003601f168201915b5050505050905090565b600061048682610b05565b6104c5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104bc90611d29565b60405180910390fd5b6004600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600061050b82610698565b90508073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16141561057c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161057390611dbb565b60405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff1661059b610b71565b73ffffffffffffffffffffffffffffffffffffffff1614806105ca57506105c9816105c4610b71565b610a07565b5b610609576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161060090611e4d565b60405180910390fd5b6106138383610b79565b505050565b610629610623610b71565b82610c32565b610668576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161065f90611edf565b60405180910390fd5b610673838383610d10565b505050565b61069383838360405180602001604052806000815250610900565b505050565b6000806002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415610741576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161073890611f71565b60405180910390fd5b80915050919050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156107bb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107b290612003565b60405180910390fd5b600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60606001805461081190611c85565b80601f016020809104026020016040519081016040528092919081815260200182805461083d90611c85565b801561088a5780601f1061085f5761010080835404028352916020019161088a565b820191906000526020600020905b81548152906001019060200180831161086d57829003601f168201915b5050505050905090565b60006108a06006610f77565b60006108ac6006610f8d565b90506108b88482610f9b565b826007600083815260200190815260200160002090805190602001906108df9291906114f3565b508091505092915050565b6108fc6108f5610b71565b8383611175565b5050565b61091161090b610b71565b83610c32565b610950576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161094790611edf565b60405180910390fd5b61095c848484846112e2565b50505050565b606060076000838152602001908152602001600020805461098290611c85565b80601f01602080910402602001604051908101604052809291908181526020018280546109ae90611c85565b80156109fb5780601f106109d0576101008083540402835291602001916109fb565b820191906000526020600020905b8154815290600101906020018083116109de57829003601f168201915b50505050509050919050565b6000600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b60007f01ffc9a7000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916149050919050565b60008073ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614159050919050565b600033905090565b816004600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff16610bec83610698565b73ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b6000610c3d82610b05565b610c7c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c7390612095565b60405180910390fd5b6000610c8783610698565b90508073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff161480610cf657508373ffffffffffffffffffffffffffffffffffffffff16610cde8461047b565b73ffffffffffffffffffffffffffffffffffffffff16145b80610d075750610d068185610a07565b5b91505092915050565b8273ffffffffffffffffffffffffffffffffffffffff16610d3082610698565b73ffffffffffffffffffffffffffffffffffffffff1614610d86576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d7d90612127565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610df6576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ded906121b9565b60405180910390fd5b610e0183838361133e565b610e0c600082610b79565b6001600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610e5c9190612208565b925050819055506001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610eb3919061223c565b92505081905550816002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a4610f72838383611343565b505050565b6001816000016000828254019250508190555050565b600081600001549050919050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141561100b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611002906122de565b60405180910390fd5b61101481610b05565b15611054576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161104b9061234a565b60405180910390fd5b6110606000838361133e565b6001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546110b0919061223c565b92505081905550816002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a461117160008383611343565b5050565b8173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156111e4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016111db906123b6565b60405180910390fd5b80600560008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31836040516112d5919061164a565b60405180910390a3505050565b6112ed848484610d10565b6112f984848484611348565b611338576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161132f90612448565b60405180910390fd5b50505050565b505050565b505050565b60006113698473ffffffffffffffffffffffffffffffffffffffff166114d0565b156114c3578373ffffffffffffffffffffffffffffffffffffffff1663150b7a02611392610b71565b8786866040518563ffffffff1660e01b81526004016113b494939291906124bd565b6020604051808303816000875af19250505080156113f057506040513d601f19601f820116820180604052508101906113ed919061251e565b60015b611473573d8060008114611420576040519150601f19603f3d011682016040523d82523d6000602084013e611425565b606091505b5060008151141561146b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161146290612448565b60405180910390fd5b805181602001fd5b63150b7a0260e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916149150506114c8565b600190505b949350505050565b6000808273ffffffffffffffffffffffffffffffffffffffff163b119050919050565b8280546114ff90611c85565b90600052602060002090601f0160209004810192826115215760008555611568565b82601f1061153a57805160ff1916838001178555611568565b82800160010185558215611568579182015b8281111561156757825182559160200191906001019061154c565b5b5090506115759190611579565b5090565b5b8082111561159257600081600090555060010161157a565b5090565b6000604051905090565b600080fd5b600080fd5b60007fffffffff0000000000000000000000000000000000000000000000000000000082169050919050565b6115df816115aa565b81146115ea57600080fd5b50565b6000813590506115fc816115d6565b92915050565b600060208284031215611618576116176115a0565b5b6000611626848285016115ed565b91505092915050565b60008115159050919050565b6116448161162f565b82525050565b600060208201905061165f600083018461163b565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561169f578082015181840152602081019050611684565b838111156116ae576000848401525b50505050565b6000601f19601f8301169050919050565b60006116d082611665565b6116da8185611670565b93506116ea818560208601611681565b6116f3816116b4565b840191505092915050565b6000602082019050818103600083015261171881846116c5565b905092915050565b6000819050919050565b61173381611720565b811461173e57600080fd5b50565b6000813590506117508161172a565b92915050565b60006020828403121561176c5761176b6115a0565b5b600061177a84828501611741565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006117ae82611783565b9050919050565b6117be816117a3565b82525050565b60006020820190506117d960008301846117b5565b92915050565b6117e8816117a3565b81146117f357600080fd5b50565b600081359050611805816117df565b92915050565b60008060408385031215611822576118216115a0565b5b6000611830858286016117f6565b925050602061184185828601611741565b9150509250929050565b600080600060608486031215611864576118636115a0565b5b6000611872868287016117f6565b9350506020611883868287016117f6565b925050604061189486828701611741565b9150509250925092565b6000602082840312156118b4576118b36115a0565b5b60006118c2848285016117f6565b91505092915050565b6118d481611720565b82525050565b60006020820190506118ef60008301846118cb565b92915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611937826116b4565b810181811067ffffffffffffffff82111715611956576119556118ff565b5b80604052505050565b6000611969611596565b9050611975828261192e565b919050565b600067ffffffffffffffff821115611995576119946118ff565b5b61199e826116b4565b9050602081019050919050565b82818337600083830152505050565b60006119cd6119c88461197a565b61195f565b9050828152602081018484840111156119e9576119e86118fa565b5b6119f48482856119ab565b509392505050565b600082601f830112611a1157611a106118f5565b5b8135611a218482602086016119ba565b91505092915050565b60008060408385031215611a4157611a406115a0565b5b6000611a4f858286016117f6565b925050602083013567ffffffffffffffff811115611a7057611a6f6115a5565b5b611a7c858286016119fc565b9150509250929050565b611a8f8161162f565b8114611a9a57600080fd5b50565b600081359050611aac81611a86565b92915050565b60008060408385031215611ac957611ac86115a0565b5b6000611ad7858286016117f6565b9250506020611ae885828601611a9d565b9150509250929050565b600067ffffffffffffffff821115611b0d57611b0c6118ff565b5b611b16826116b4565b9050602081019050919050565b6000611b36611b3184611af2565b61195f565b905082815260208101848484011115611b5257611b516118fa565b5b611b5d8482856119ab565b509392505050565b600082601f830112611b7a57611b796118f5565b5b8135611b8a848260208601611b23565b91505092915050565b60008060008060808587031215611bad57611bac6115a0565b5b6000611bbb878288016117f6565b9450506020611bcc878288016117f6565b9350506040611bdd87828801611741565b925050606085013567ffffffffffffffff811115611bfe57611bfd6115a5565b5b611c0a87828801611b65565b91505092959194509250565b60008060408385031215611c2d57611c2c6115a0565b5b6000611c3b858286016117f6565b9250506020611c4c858286016117f6565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680611c9d57607f821691505b60208210811415611cb157611cb0611c56565b5b50919050565b7f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860008201527f697374656e7420746f6b656e0000000000000000000000000000000000000000602082015250565b6000611d13602c83611670565b9150611d1e82611cb7565b604082019050919050565b60006020820190508181036000830152611d4281611d06565b9050919050565b7f4552433732313a20617070726f76616c20746f2063757272656e74206f776e6560008201527f7200000000000000000000000000000000000000000000000000000000000000602082015250565b6000611da5602183611670565b9150611db082611d49565b604082019050919050565b60006020820190508181036000830152611dd481611d98565b9050919050565b7f4552433732313a20617070726f76652063616c6c6572206973206e6f74206f7760008201527f6e6572206e6f7220617070726f76656420666f7220616c6c0000000000000000602082015250565b6000611e37603883611670565b9150611e4282611ddb565b604082019050919050565b60006020820190508181036000830152611e6681611e2a565b9050919050565b7f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f60008201527f776e6572206e6f7220617070726f766564000000000000000000000000000000602082015250565b6000611ec9603183611670565b9150611ed482611e6d565b604082019050919050565b60006020820190508181036000830152611ef881611ebc565b9050919050565b7f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460008201527f656e7420746f6b656e0000000000000000000000000000000000000000000000602082015250565b6000611f5b602983611670565b9150611f6682611eff565b604082019050919050565b60006020820190508181036000830152611f8a81611f4e565b9050919050565b7f4552433732313a2062616c616e636520717565727920666f7220746865207a6560008201527f726f206164647265737300000000000000000000000000000000000000000000602082015250565b6000611fed602a83611670565b9150611ff882611f91565b604082019050919050565b6000602082019050818103600083015261201c81611fe0565b9050919050565b7f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860008201527f697374656e7420746f6b656e0000000000000000000000000000000000000000602082015250565b600061207f602c83611670565b915061208a82612023565b604082019050919050565b600060208201905081810360008301526120ae81612072565b9050919050565b7f4552433732313a207472616e736665722066726f6d20696e636f72726563742060008201527f6f776e6572000000000000000000000000000000000000000000000000000000602082015250565b6000612111602583611670565b915061211c826120b5565b604082019050919050565b6000602082019050818103600083015261214081612104565b9050919050565b7f4552433732313a207472616e7366657220746f20746865207a65726f2061646460008201527f7265737300000000000000000000000000000000000000000000000000000000602082015250565b60006121a3602483611670565b91506121ae82612147565b604082019050919050565b600060208201905081810360008301526121d281612196565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061221382611720565b915061221e83611720565b925082821015612231576122306121d9565b5b828203905092915050565b600061224782611720565b915061225283611720565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115612287576122866121d9565b5b828201905092915050565b7f4552433732313a206d696e7420746f20746865207a65726f2061646472657373600082015250565b60006122c8602083611670565b91506122d382612292565b602082019050919050565b600060208201905081810360008301526122f7816122bb565b9050919050565b7f4552433732313a20746f6b656e20616c7265616479206d696e74656400000000600082015250565b6000612334601c83611670565b915061233f826122fe565b602082019050919050565b6000602082019050818103600083015261236381612327565b9050919050565b7f4552433732313a20617070726f766520746f2063616c6c657200000000000000600082015250565b60006123a0601983611670565b91506123ab8261236a565b602082019050919050565b600060208201905081810360008301526123cf81612393565b9050919050565b7f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560008201527f63656976657220696d706c656d656e7465720000000000000000000000000000602082015250565b6000612432603283611670565b915061243d826123d6565b604082019050919050565b6000602082019050818103600083015261246181612425565b9050919050565b600081519050919050565b600082825260208201905092915050565b600061248f82612468565b6124998185612473565b93506124a9818560208601611681565b6124b2816116b4565b840191505092915050565b60006080820190506124d260008301876117b5565b6124df60208301866117b5565b6124ec60408301856118cb565b81810360608301526124fe8184612484565b905095945050505050565b600081519050612518816115d6565b92915050565b600060208284031215612534576125336115a0565b5b600061254284828501612509565b9150509291505056fea26469706673582212208320e1aa439da0828054e53e25416f31bfea067690abc9a300f6c552af4e1edb64736f6c634300080a0033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_CREATE = "create";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("1646975318279", "0x22f2adDA30AD305aCFf405674708172d83555A75");
        _addresses.put("1647926862751", "0xFb7d4988b7306F6c2D72a955D520D65D9B5167b2");
        _addresses.put("1647836682201", "0xe2798e7585BD0c48e82d9b1569bBa5aE092345a3");
        _addresses.put("1647221383882", "0xF52346e72465E07dF19d5e505DF852684fEec12B");
        _addresses.put("1646980921534", "0x9d0f92CBA1a0A9A9822ef715bFf92136B36D57c0");
        _addresses.put("1647930717041", "0x7267EF4827e39706d33027955B730A3024635749");
        _addresses.put("202112031219", "0xE83005A30B85A696D870E478CB922f5dE56874F4");
        _addresses.put("1647934721580", "0x62D56d9899A8E594522955EAa5941f9CC31eC1C3");
        _addresses.put("1647225707062", "0xCfCdf694956D7b121D6d0f1D1B5bb29278271b19");
    }

    @Deprecated
    protected SsafyNFT(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SsafyNFT(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SsafyNFT(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SsafyNFT(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.Address(operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> create(String to, String _tokenURI) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.Utf8String(_tokenURI)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SsafyNFT load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SsafyNFT(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SsafyNFT load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SsafyNFT(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SsafyNFT load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SsafyNFT(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SsafyNFT load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SsafyNFT(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SsafyNFT.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SsafyNFT.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SsafyNFT.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SsafyNFT> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SsafyNFT.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
