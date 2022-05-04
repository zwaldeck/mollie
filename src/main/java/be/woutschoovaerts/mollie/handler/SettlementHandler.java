package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.chargeback.ChargebackListResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.refund.RefundListResponse;
import be.woutschoovaerts.mollie.data.settlement.SettlementListResponse;
import be.woutschoovaerts.mollie.data.settlement.SettlementResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Settlements API <a href="https://docs.mollie.com/reference/v2/settlements-api/get-settlement">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class SettlementHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(SettlementHandler.class);

    public SettlementHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * Successful payments, together with refunds, captures and chargebacks are collected into settlements, which are then paid out according to your organization’s payout schedule. By retrieving a single settlement, you can check which payments were paid out with it, when the settlement took place, and what invoice reference was used for it.
     * <p>
     * Beside payments, settlements can be composed of other entities such as refunds, chargebacks or captures.
     *
     * @param id A settlement ID
     * @return SettlementResponse object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getSettlement(String id) throws MollieException {
        return getSettlement(id, new QueryParams());
    }

    /**
     * Successful payments, together with refunds, captures and chargebacks are collected into settlements, which are then paid out according to your organization’s payout schedule. By retrieving a single settlement, you can check which payments were paid out with it, when the settlement took place, and what invoice reference was used for it.
     * <p>
     * Beside payments, settlements can be composed of other entities such as refunds, chargebacks or captures.
     *
     * @param id     A settlement ID
     * @param params a map of query params
     * @return SettlementResponse object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getSettlement(String id, QueryParams params) throws MollieException {
        try {
            String uri = "/settlements/" + id;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SettlementResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all settlements, ordered from new to old.
     *
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<SettlementListResponse> getSettlements() throws MollieException {
        return getSettlements(new QueryParams());
    }


    /**
     * Retrieve all settlements, ordered from new to old.
     *
     * @param params a map of query params
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<SettlementListResponse> getSettlements(QueryParams params) throws MollieException {
        try {
            String uri = "/settlements";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<SettlementListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve the details of the current settlement that has not yet been paid out.
     *
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getNextSettlement() throws MollieException {
        return getNextSettlement(new QueryParams());
    }

    /**
     * Retrieve the details of the current settlement that has not yet been paid out.
     *
     * @param params a map of query params
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getNextSettlement(QueryParams params) throws MollieException {
        try {
            String uri = "/settlements/next";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SettlementResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve the details of the open balance of the organization. This will return a settlement object representing your organization’s balance.
     *
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getOpenSettlement() throws MollieException {
        return getOpenSettlement(new QueryParams());
    }

    /**
     * Retrieve the details of the open balance of the organization. This will return a settlement object representing your organization’s balance.
     *
     * @param params a map of query params
     * @return Pagination<SettlementListResponse> object
     * @throws MollieException when something went wrong
     */
    public SettlementResponse getOpenSettlement(QueryParams params) throws MollieException {
        try {
            String uri = "/settlements/open";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SettlementResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all payments included in a settlement.
     * <p>
     * Note that payments for pay after delivery methods (such as Klarna Pay Later) are not listed in here. These payment methods are settled using captures. To retrieve the captures, use the List settlement captures endpoint.
     *
     * @param settlementId A settlement ID
     * @return Pagination<PaymentListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> getSettlementPayments(String settlementId) throws MollieException {
        return getSettlementPayments(settlementId, new QueryParams());
    }

    /**
     * Retrieve all payments included in a settlement.
     * <p>
     * Note that payments for pay after delivery methods (such as Klarna Pay Later) are not listed in here. These payment methods are settled using captures. To retrieve the captures, use the List settlement captures endpoint.
     *
     * @param settlementId A settlement ID
     * @param params       a map of query params
     * @return Pagination<PaymentListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> getSettlementPayments(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/payments";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all refunds included in a settlement.
     *
     * @param settlementId A settlement ID
     * @return Pagination<RefundListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> getSettlementRefund(String settlementId) throws MollieException {
        return getSettlementRefund(settlementId, new QueryParams());
    }

    /**
     * Retrieve all refunds included in a settlement.
     *
     * @param settlementId A settlement ID
     * @param params       a map of query params
     * @return Pagination<RefundListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> getSettlementRefund(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/refunds";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<RefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all chargebacks included in a settlement.
     *
     * @param settlementId A settlement ID
     * @return Pagination<ChargebackListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> getSettlementChargebacks(String settlementId) throws MollieException {
        return getSettlementChargebacks(settlementId, new QueryParams());
    }

    /**
     * Retrieve all chargebacks included in a settlement.
     *
     * @param settlementId A settlement ID
     * @param params       a map of query params
     * @return Pagination<ChargebackListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> getSettlementChargebacks(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/chargebacks";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all captures in a certain settlement.
     * <p>
     * Captures are used for pay after delivery payment methods. The only payment methods at the moment that have this flow are Klarna Pay later and Klarna Slice it. Captures are created when (part of) an Order is shipped. The capture is then settled to the merchant
     *
     * @param settlementId A settlement ID
     * @return Pagination<CaptureListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<CaptureListResponse> getSettlementCaptures(String settlementId) throws MollieException {
        return getSettlementCaptures(settlementId, new QueryParams());
    }

    /**
     * Retrieve all captures in a certain settlement.
     * <p>
     * Captures are used for pay after delivery payment methods. The only payment methods at the moment that have this flow are Klarna Pay later and Klarna Slice it. Captures are created when (part of) an Order is shipped. The capture is then settled to the merchant
     *
     * @param settlementId A settlement ID
     * @param params       a map of query params
     * @return Pagination<CaptureListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<CaptureListResponse> getSettlementCaptures(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/captures";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<CaptureListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
