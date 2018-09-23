package be.feelio.mollie.json.response;

import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.links.SubscriptionLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponse {

    private String resource;

    private String id;

    private String mode;

    private Date createdAt;

    private String status;

    private Amount amount;

    private int times;

    private String interval;

    private Date startDate;

    private String description;

    private String method;

    private Date canceledAt;

    private String webhookUrl;

    private Map<String, Object> metadata;

    @JsonProperty("_links")
    private SubscriptionLinks links;
}
