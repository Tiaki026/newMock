package newMock.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import newMock.Model.RequestDTO;
import newMock.Model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();
    Random random = new Random();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String currency;
            String RqUID = requestDTO.getRqUID();

            if (firstDigit == '8') {
                currency = "US";
                maxLimit = new BigDecimal(2000);
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000);
                currency = "RUB";
            }

            BigDecimal balance = new BigDecimal(
                    random.nextDouble() * maxLimit.doubleValue())
                    .setScale(2, RoundingMode.HALF_UP);
            ResponseDTO responseDTO = new ResponseDTO();

//            ResponseDTO responseDTO1 = new ResponseDTO(
//                    RqUID,
//                    clientId,
//                    requestDTO.getAccount(),
//                    "RUB",
//                    new BigDecimal(777),
//                    maxLimit
//            )

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);
            responseDTO.setMaxLimit(maxLimit);

            log.error(
                    "********** RequestSTO **********" +
                            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO)
            );
            log.error(
                    "********** ResponseSTO **********" +
                            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO)
            );

            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
