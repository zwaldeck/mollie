package be.woutschoovaerts.mollie.data.client;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLinks {

    private Link self;

    private Link organization;

    private Link onboarding;

    private Link documentation;
}
