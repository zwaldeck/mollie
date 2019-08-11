package be.feelio.mollie.handler;

import be.feelio.mollie.data.request.ShipmentRequest;
import be.feelio.mollie.data.request.ShipmentUpdateRequest;
import be.feelio.mollie.data.response.ShipmentListResponse;
import be.feelio.mollie.data.response.ShipmentResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ShipmentHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentHandler.class);

    public ShipmentHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public ShipmentResponse createShipment(String orderId, ShipmentRequest body) throws MollieException {
        return createShipment(orderId, body, QueryParams.EMPTY);
    }

    public ShipmentResponse createShipment(String orderId, ShipmentRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ShipmentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ShipmentResponse getShipment(String orderId, String shipmentId) throws MollieException {
        return getShipment(orderId, shipmentId, QueryParams.EMPTY);
    }

    public ShipmentResponse getShipment(String orderId, String shipmentId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ShipmentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ShipmentListResponse getShipments(String orderId) throws MollieException {
        return getShipments(orderId, QueryParams.EMPTY);
    }

    public ShipmentListResponse getShipments(String orderId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), ShipmentListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ShipmentResponse updateShipment(String orderId, String shipmentId, ShipmentUpdateRequest body)
            throws MollieException {
        return updateShipment(orderId, shipmentId, body, QueryParams.EMPTY);
    }

    public ShipmentResponse updateShipment(String orderId, String shipmentId,
                                           ShipmentUpdateRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ShipmentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
