package be.woutschoovaerts.mollie.handler.business;

import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.chargeback.ChargebackListResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.refund.RefundListResponse;
import be.woutschoovaerts.mollie.data.settlement.SettlementListResponse;
import be.woutschoovaerts.mollie.data.settlement.SettlementResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the Settlements API <a href="https://docs.mollie.com/reference/v2/settlements-api/get-settlement">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class SettlementHandler {

    private static final TypeReference<SettlementResponse> SETTLEMENT_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<SettlementListResponse>> SETTLEMENTS_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PaymentListResponse>> PAYMENTS_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<RefundListResponse>> REFUND_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<ChargebackListResponse>> CHARGEBACK_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<CaptureListResponse>> CAPTURE_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

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
            String uri = "/settlements/" + id;

            return restService.get(uri, params, false, SETTLEMENT_RESPONSE_TYPE);
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
            String uri = "/settlements";

            return restService.get(uri, params, false, SETTLEMENTS_LIST_RESPONSE_TYPE);
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
            String uri = "/settlements/next";

            return restService.get(uri, params, false, SETTLEMENT_RESPONSE_TYPE);
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
            String uri = "/settlements/open";

            return restService.get(uri, params, false, SETTLEMENT_RESPONSE_TYPE);
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
            String uri = "/settlements/" + settlementId + "/payments";

            return restService.get(uri, params, false, PAYMENTS_LIST_RESPONSE_TYPE);
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
            String uri = "/settlements/" + settlementId + "/refunds";

            return restService.get(uri, params, false, REFUND_LIST_RESPONSE_TYPE);
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
            String uri = "/settlements/" + settlementId + "/chargebacks";

            return restService.get(uri, params, false, CHARGEBACK_LIST_RESPONSE_TYPE);
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
            String uri = "/settlements/" + settlementId + "/captures";

            return restService.get(uri, params, false, CAPTURE_LIST_RESPONSE_TYPE);
    }

}
