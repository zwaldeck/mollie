package be.woutschoovaerts.mollie.handler.business;

import be.woutschoovaerts.mollie.data.balance.BalanceReportResponse;
import be.woutschoovaerts.mollie.data.balance.BalanceResponse;
import be.woutschoovaerts.mollie.data.balance.BalanceTransactionsListResponse;
import be.woutschoovaerts.mollie.data.balance.BalancesListResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
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
 * Handles the Balances API <a href="https://docs.mollie.com/reference/v2/balances-api/get-balance">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class BalanceHandler {

    private static final Logger log = LoggerFactory.getLogger(BalanceHandler.class);

    private static final TypeReference<BalanceResponse> BALANCE_RESPONSE_TYPE = new TypeReference<>() {};
    private static final TypeReference<Pagination<BalancesListResponse>> BALANCE_LIST_RESPONSE_TYPE = new TypeReference<>() {};
    private static final TypeReference<BalanceReportResponse> BALANCE_REPORT_RESPONSE_TYPE = new TypeReference<>() {};
    private static final TypeReference<Pagination<BalanceTransactionsListResponse>> BALANCE_TRANSACTIONS_LIST_RESPONSE_TYPE = new TypeReference<>() {};

    private final RestService restService;

    /**
     * Retrieve the primary balance. This is the balance of your account’s primary currency, where all payments are settled to by default.
     * <p></p>
     * This endpoint is an alias of the Get balance. Refer to the documentation of that endpoint for more information.
     *
     * @return balance response
     */
    public BalanceResponse getPrimaryBalance() throws MollieException {
        return getPrimaryBalance(new QueryParams());
    }

    /**
     * Retrieve the primary balance. This is the balance of your account’s primary currency, where all payments are settled to by default.
     * <p></p>
     * This endpoint is an alias of the Get balance. Refer to the documentation of that endpoint for more information.
     *
     * @param params A map of query params
     * @return balance response
     */
    public BalanceResponse getPrimaryBalance(QueryParams params) throws MollieException {
        return getBalance("primary", params);
    }

    /**
     * When processing payments with Mollie, we put all pending funds — minus Mollie fees — on a balance. Once you have linked a bank account to your Mollie account, we can pay out your balance towards this bank account.
     * <p></p>
     * With the Balances API you can retrieve your current balance. The response includes two amounts:
     * <p></p>
     * The ‘pending amount’. These are payments that have been marked as ‘paid’, but are not yet available for paying out.
     * The ‘available amount’. This is the amount that you can get paid out to your bank account.
     * With instant payment methods like iDEAL, payments are moved to the available balance instantly. With slower payment methods, like credit card for example, it can take a few days before the funds are available on your balance. These funds will be shown under the ‘pending amount’ in the meanwhile.
     *
     * @param balanceId balance id
     * @return balance response
     */
    public BalanceResponse getBalance(String balanceId) throws MollieException {
        return getBalance(balanceId, new QueryParams());
    }

    /**
     * When processing payments with Mollie, we put all pending funds — minus Mollie fees — on a balance. Once you have linked a bank account to your Mollie account, we can pay out your balance towards this bank account.
     * <p></p>
     * With the Balances API you can retrieve your current balance. The response includes two amounts:
     * <p></p>
     * The ‘pending amount’. These are payments that have been marked as ‘paid’, but are not yet available for paying out.
     * The ‘available amount’. This is the amount that you can get paid out to your bank account.
     * With instant payment methods like iDEAL, payments are moved to the available balance instantly. With slower payment methods, like credit card for example, it can take a few days before the funds are available on your balance. These funds will be shown under the ‘pending amount’ in the meanwhile.
     *
     * @param balanceId balance id
     * @param params    A map of query params
     * @return balance response
     */
    public BalanceResponse getBalance(String balanceId, QueryParams params) throws MollieException {
        try {
            String uri = "/balances/" + balanceId;

            return restService.get(uri, params, false, BALANCE_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all the organization’s balances, including the primary balance, ordered from newest to oldest.
     *
     * @return Pagination<BalancesListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalancesListResponse> getBalances() throws MollieException {
        return getBalances(new QueryParams());
    }

    /**
     * Retrieve all the organization’s balances, including the primary balance, ordered from newest to oldest.
     *
     * @param params A map of query params
     * @return Pagination<BalancesListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalancesListResponse> getBalances(QueryParams params) throws MollieException {
        try {
            String uri = "/balances";

            return restService.get(uri, params, false, BALANCE_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * With the Get primary balance report endpoint you can retrieve a summarized report for all movements on your primary balance within a given timeframe.
     * <p>
     * The API also provides a detailed report on all Mollie ‘fee-prepayments’ that were deducted from your balance during the reported period ahead of your Mollie invoice.
     *
     * @return BalanceReportResponse response
     */
    public BalanceReportResponse getPrimaryBalanceReport() throws MollieException {
        return getBalanceReport("primary", new QueryParams());
    }

    /**
     * With the Get primary balance report endpoint you can retrieve a summarized report for all movements on your primary balance within a given timeframe.
     * <p>
     * The API also provides a detailed report on all Mollie ‘fee-prepayments’ that were deducted from your balance during the reported period ahead of your Mollie invoice.
     *
     * @param params A map of query params
     * @return BalanceReportResponse response
     */
    public BalanceReportResponse getPrimaryBalanceReport(QueryParams params) throws MollieException {
        return getBalanceReport("primary", params);
    }

    /**
     * With the Get balance report endpoint you can retrieve a summarized report for all movements on a given balance within a given timeframe.
     * <p>
     * The API also provides a detailed report on all Mollie ‘fee-prepayments’ that were deducted from your balance during the reported period ahead of your Mollie invoice.
     *
     * @param balanceId balance id
     * @return BalanceReportResponse response
     */
    public BalanceReportResponse getBalanceReport(String balanceId) throws MollieException {
        return getBalanceReport(balanceId, new QueryParams());
    }

    /**
     * With the Get balance report endpoint you can retrieve a summarized report for all movements on a given balance within a given timeframe.
     * <p>
     * The API also provides a detailed report on all Mollie ‘fee-prepayments’ that were deducted from your balance during the reported period ahead of your Mollie invoice.
     *
     * @param balanceId balance id
     * @param params    A map of query params
     * @return BalanceReportResponse response
     */
    public BalanceReportResponse getBalanceReport(String balanceId, QueryParams params) throws MollieException {
        try {
            String uri = "/balances/" + balanceId + "/report";

            return restService.get(uri, params, false, BALANCE_REPORT_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * With the List primary balance transactions endpoint you can retrieve a list of all the movements on your primary balance. This includes payments, refunds, chargebacks, and settlements.
     *
     * @return Pagination<BalanceTransactionsListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalanceTransactionsListResponse> getPrimaryBalanceTransactions() throws MollieException {
        return getBalanceTransactions("primary", new QueryParams());
    }

    /**
     * With the List primary balance transactions endpoint you can retrieve a list of all the movements on your primary balance. This includes payments, refunds, chargebacks, and settlements.
     *
     * @param params A map of query params
     * @return Pagination<BalanceTransactionsListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalanceTransactionsListResponse> getPrimaryBalanceTransactions(QueryParams params) throws MollieException {
        return getBalanceTransactions("primary", params);
    }

    /**
     * With the List balance transactions endpoint you can retrieve a list of all the movements on your balance. This includes payments, refunds, chargebacks, and settlements.
     *
     * @param balanceId The balance ID
     * @return Pagination<BalanceTransactionsListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalanceTransactionsListResponse> getBalanceTransactions(String balanceId) throws MollieException {
        return getBalanceTransactions(balanceId, new QueryParams());
    }

    /**
     * With the List balance transactions endpoint you can retrieve a list of all the movements on your balance. This includes payments, refunds, chargebacks, and settlements.
     *
     * @param balanceId The balance ID
     * @param params A map of query params
     * @return Pagination<BalanceTransactionsListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<BalanceTransactionsListResponse> getBalanceTransactions(String balanceId, QueryParams params) throws MollieException {
        try {
            String uri = "/balances/" + balanceId + "/transactions";

            return restService.get(uri, params, false, BALANCE_TRANSACTIONS_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
