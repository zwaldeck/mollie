package be.woutschoovaerts.mollie.handler.orders;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.shipment.ShipmentListResponse;
import be.woutschoovaerts.mollie.data.shipment.ShipmentRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentResponse;
import be.woutschoovaerts.mollie.data.shipment.ShipmentUpdateRequest;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the shipments API <a href="https://docs.mollie.com/reference/v2/shipments-api/create-shipment">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
@Deprecated
public class ShipmentHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentHandler.class);

    private static final TypeReference<ShipmentResponse> SHIPMENT_RESPONSE_TYPE = new TypeReference<>() {
    };private static final TypeReference<Pagination<ShipmentListResponse>> SHIPMENT_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * In addition to the Orders API, the create shipment endpoint can be used to ship order lines.
     * <p>
     * When using Klarna Pay later and Klarna Slice it this is mandatory for the order amount to be captured. An capture will automatically be created for the shipment.
     * <p>
     * The word “shipping” is used in the figurative sense here. It can also mean that a service was provided or digital content was delivered.
     *
     * @param orderId An order ID
     * @param body    ShipmentRequest object
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse createShipment(String orderId, ShipmentRequest body) throws MollieException {
        return createShipment(orderId, body, new QueryParams());
    }

    /**
     * In addition to the Orders API, the create shipment endpoint can be used to ship order lines.
     * <p>
     * When using Klarna Pay later and Klarna Slice it this is mandatory for the order amount to be captured. An capture will automatically be created for the shipment.
     * <p>
     * The word “shipping” is used in the figurative sense here. It can also mean that a service was provided or digital content was delivered.
     *
     * @param orderId An order ID
     * @param body    ShipmentRequest object
     * @param params  A map of query params
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse createShipment(String orderId, ShipmentRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments";

            return restService.post(uri, body, params, SHIPMENT_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single shipment and the order lines shipped by a shipment’s ID.
     *
     * @param orderId    An order ID
     * @param shipmentId A shipment ID
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse getShipment(String orderId, String shipmentId) throws MollieException {
        return getShipment(orderId, shipmentId, new QueryParams());
    }

    /**
     * Retrieve a single shipment and the order lines shipped by a shipment’s ID.
     *
     * @param orderId    An order ID
     * @param shipmentId A shipment ID
     * @param params     a map of query params
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse getShipment(String orderId, String shipmentId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

            return restService.get(uri, params, true, SHIPMENT_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all shipments for an order.
     *
     * @param orderId An order ID
     * @return Pagination<ShipmentListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ShipmentListResponse> getShipments(String orderId) throws MollieException {
        return getShipments(orderId, new QueryParams());
    }

    /**
     * Retrieve all shipments for an order.
     *
     * @param orderId An order ID
     * @param params  a map of query params
     * @return Pagination<ShipmentListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ShipmentListResponse> getShipments(String orderId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments";

            return restService.get(uri, params, true, SHIPMENT_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }


    /**
     * This endpoint can be used to update the tracking information of a shipment.
     *
     * @param orderId An order ID
     * @param body    ShipmentUpdateRequest object
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse updateShipment(String orderId, String shipmentId, ShipmentUpdateRequest body)
            throws MollieException {
        return updateShipment(orderId, shipmentId, body, new QueryParams());
    }

    /**
     * This endpoint can be used to update the tracking information of a shipment.
     *
     * @param orderId An order ID
     * @param body    ShipmentUpdateRequest object
     * @param params  A map of query params
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse updateShipment(String orderId, String shipmentId,
                                           ShipmentUpdateRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

            return restService.patch(uri, body, params, SHIPMENT_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
