package be.feelio.mollie.data.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoicesListResponse {

    private List<InvoiceResponse> invoices;
}
