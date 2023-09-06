package be.woutschoovaerts.mollie.data.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDestination {

    private TransferDestinationType type;

    private String bankAccount;

    private String beneficiaryName;
}
