package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.shipment.ShipmentListResponse;
import be.woutschoovaerts.mollie.data.shipment.ShipmentRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentResponse;
import be.woutschoovaerts.mollie.data.shipment.ShipmentUpdateRequest;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the shipments API <a href="https://docs.mollie.com/reference/v2/shipments-api/create-shipment">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class ShipmentHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ShipmentHandler.class);

    public ShipmentHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
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
     * @return ShipmentResponse object
     * @throws MollieException when something went wrong
     */
    public ShipmentResponse createShipment(String orderId, ShipmentRequest body) throws MollieException {
        return createShipment(orderId, body, QueryParams.EMPTY);
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
        String uri = "/orders/" + orderId + "/shipments";

        return post(uri, body, params, new TypeReference<>() {
        });
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
        return getShipment(orderId, shipmentId, QueryParams.EMPTY);
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
        String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

        return get(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve all shipments for an order.
     *
     * @param orderId An order ID
     * @return Pagination<ShipmentListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ShipmentListResponse> getShipments(String orderId) throws MollieException {
        return getShipments(orderId, QueryParams.EMPTY);
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
        String uri = "/orders/" + orderId + "/shipments";

        return get(uri, params, new TypeReference<>() {
        });
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
        return updateShipment(orderId, shipmentId, body, QueryParams.EMPTY);
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
        String uri = "/orders/" + orderId + "/shipments/" + shipmentId;

        return patch(uri, body, params, new TypeReference<>() {
        });
    }
}
