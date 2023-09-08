package be.woutschoovaerts.mollie.handler.orders;

import be.woutschoovaerts.mollie.data.shipment.ShipmentLineRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentTrackingRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentUpdateRequest;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShipmentHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private ShipmentHandler handler;

    @Test
    void createShipment() throws Exception {
        String uri = "/orders/order_id/shipments";

        ShipmentRequest request = ShipmentRequest.builder()
                .lines(List.of(
                        ShipmentLineRequest.builder().id("id1").build(),
                        ShipmentLineRequest.builder().id("id2").build()
                ))
                .build();

        handler.createShipment("order_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getShipment() throws Exception {
        String uri = "/orders/order_id/shipments/shipment_id";

        handler.getShipment("order_id", "shipment_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void getShipments() throws Exception {
        String uri = "/orders/order_id/shipments";

        handler.getShipments("order_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void updateShipment() throws Exception {
        String uri = "/orders/order_id/shipments/shipment_id";

        ShipmentUpdateRequest request = ShipmentUpdateRequest.builder()
                .tracking(ShipmentTrackingRequest.builder()
                        .carrier("dhl")
                        .code("123456")
                        .build())
                .build();

        handler.updateShipment("order_id", "shipment_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

}