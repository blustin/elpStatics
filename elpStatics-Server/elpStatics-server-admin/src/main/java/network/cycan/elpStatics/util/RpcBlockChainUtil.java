package network.cycan.elpStatics.util;

import lombok.extern.slf4j.Slf4j;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RpcBlockChainUtil {

    private static  Web3j web3j;
   static {
        if(web3j==null)
        {
            web3j = Web3j.build(new HttpService("https://bsc-dataseed1.defibit.io/"));
        }
    }

    private final   static String USERINFO_DATA_PREFIX=  "0x93f1a40b0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    public static BigDecimal getBalance(String fromAddress, String contractAddress)  {
        String methodName = "userInfo";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();
        Uint param=new Uint(BigInteger.ZERO);
        Address address = new Address(fromAddress);
        inputParameters.add(param);
        inputParameters.add(address);

        TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
        };
        TypeReference<Uint256> typeReference1 = new TypeReference<Uint256>() {
        };
        outputParameters.add(typeReference);
        outputParameters.add(typeReference1);
        Function function = new Function(methodName, inputParameters, outputParameters);
        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, USERINFO_DATA_PREFIX+fromAddress.substring(2));

        EthCall ethCall;
        BigDecimal balanceValue = BigDecimal.ZERO;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            balanceValue = new BigDecimal(results.get(0).getValue().toString()).divide(HttpBlockChainUtil.RADIX_POINT);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return balanceValue;
    }


}
