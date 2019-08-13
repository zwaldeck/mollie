package be.feelio.mollie.data.profile;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileListLinks {

    private Link self;

    private Link previous;

    private Link next;

    private Link documentation;
}
