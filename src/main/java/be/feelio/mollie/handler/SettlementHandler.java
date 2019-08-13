package be.feelio.mollie.handler;

import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.response.*;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SettlementHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(SettlementHandler.class);

    public SettlementHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public SettlementResponse getSettlement(String id) throws MollieException {
        return getSettlement(id, QueryParams.EMPTY);
    }

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

    public SettlementListResponse getSettlements() throws MollieException {
        return getSettlements(QueryParams.EMPTY);
    }

    public SettlementListResponse getSettlements(QueryParams params) throws MollieException {
        try {
            String uri = "/settlements";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SettlementListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public SettlementResponse getNextSettlement() throws MollieException {
        return getNextSettlement(QueryParams.EMPTY);
    }

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

    public SettlementResponse getOpenSettlement() throws MollieException {
        return getOpenSettlement(QueryParams.EMPTY);
    }

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

    public PaymentListResponse getSettlementPayments(String settlementId) throws MollieException {
        return getSettlementPayments(settlementId, QueryParams.EMPTY);
    }

    public PaymentListResponse getSettlementPayments(String settlementId, QueryParams params) throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/payments";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), PaymentListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public RefundListResponse getSettlementRefund(String settlementId) throws MollieException {
        return getSettlementRefund(settlementId, QueryParams.EMPTY);
    }

    public RefundListResponse getSettlementRefund(String settlementId, QueryParams params) throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/refunds";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), RefundListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ChargebackListResponse getSettlementChargebacks(String settlementId) throws MollieException {
        return getSettlementChargebacks(settlementId, QueryParams.EMPTY);
    }

    public ChargebackListResponse getSettlementChargebacks(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/chargebacks";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), ChargebackListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public Pagination<CaptureListResponse> getSettlementCaptures(String settlementId) throws MollieException {
        return getSettlementCaptures(settlementId, QueryParams.EMPTY);
    }

    public Pagination<CaptureListResponse> getSettlementCaptures(String settlementId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/settlements/" + settlementId + "/captures";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
