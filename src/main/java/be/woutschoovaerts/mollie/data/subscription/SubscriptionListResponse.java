package be.woutschoovaerts.mollie.data.subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionListResponse {

    private List<SubscriptionResponse> subscriptions;
}
