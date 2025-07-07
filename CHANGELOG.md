## 4.6.3

- Due date is a LocalDate
- PaymentStatusResponse has correct fields
- Added terminalId to PaymentDetailsResponse
- Added Locale en_GB

## 4.6.2

- Added payment method KLARNA
- Added payment method RIVERTY

## 4.6.1

- Format expiresAt as yyyy-MM-dd'T'HH:mm:ssXXX in PaymentLinkRequest
- Replaced HashMap with Map
- Replaced owner field with email in ClientLinkOwnerRequest

## 4.6.0

- Added support for releasing payment authorization
- Fixed invalid datatypes in SubscriptionResponse
- Fixed serialization issue for JPY currency

## 4.5.1

- Added remainderDetails and vouchers to PaymentDetailsResponse

## 4.5.0

- Updated request objects to add missing properties
- Updated response objects to add missing properties
- Added beta voucher properties
- Removed QueryParams.EMPTY
- Deprecated OrderHandler
- Deprecated ShipmentHandler
- Added Client links API

## 4.4.3
 - Switched to OffsetDateTime

## 4.4.2
- added paypal for mandates API
- added alma, bacs, bancomatpay, blik, satispay for payments API
- added paybybank for payments API, see https://docs.mollie.com/docs/pay-by-bank 
- Added "email" and "phone" fields to AddressRequest for 3DS validation
- Added expiresAt field to PaymentResponse

## 4.4.1
- Added "mobileAppCheckout" mapping in PaymentLinks

## 4.3.1
- Added support for TRUSTLY in payment API

## 4.3.0
 - Added Terminals API
 - Added parameters "description" and "countriesOfActivity" to ProfileRequest
 - Added parameters "description" and "countriesOfActivity" to UpdateProfileRequest
 - Use DELETE instead of POST for revoking tokens
 
## 4.2.1
- Added extra fields to AddressRequest

## 4.2.0
 - Add support for payment method TWINT in payment API

## 4.1.0
 - Added API Idempotency

## 4.0.2
 - Bugfix for BigDecimals serialization

## 4.0.0
 - Switched to Java 11+
 - Added Balance endpoints
 - Added Client endpoints
 - Removed Miscellaneous handler

## 3.8.0
 - Add fee region to method pricing
 - Fix integration tests for chargeback reason and order expiry date

## 3.7.2
 - Added payment link support
 - Mark 'categoryCode' parameter in Profile API as deprecated
 - Added 'businessCategory' as parameter in Profile API together with a new BusinessCategory enumeration


## 3.7.1
 - Make chargeback reason a working Optional instead of null.

## 3.7.0
 - Add support for the chargeback reason field

## 3.6.8
 - Sending testmode as boolean instead of String

## 3.6.7
 - Added testmode optionals to Request objects that needed it

## 3.6.6
 - Added testmode to PaymentRequest

## 3.6.5
 - Updated the object mapper

## 3.6.4
- Bugfix: Somme endpoints do not support 'testmode' parameter

## 3.6.3
 - Deprecated QueryParams.EMPTY
 - Added the list all payment methods endpoint

## 3.6.2
 - add applePayPaymentToken to PaymentRequest
 - rename MiscellaneousHandler to WalletHandler
 - Updated Log4j to 2.17.1
 - Bumped all other dependencies

## 3.6.1
 - Made field 'RedirectUri' in TokenRequest an optional

## 3.6.0
- Added 'metaData' field to OrderLineResponse
- Added the possibility to add proxy config to Unirest
- Fixed a wrong JSON mapped value in SettlementStatus
- Added fields 'Locale' & 'LandingPage' to AuthorizeRequest

## 3.5.0
- Updated Log4j to 2.16.0
- Bumped all other dependencies and made their versions a property

## 3.4.1
- Added missing fields to 'UpdateSubscriptionRequest'

## 3.4.0
- Changed `java.util.Date` fields to its `java.time` representatives

## 3.3.0
- Changed OrderRequest `expiresAt` field date serialization format to `yyyy-MM-dd`

## 3.2.3
- Add "voucher" as a known payment method

## 3.2.2
- Allow a custom user agent string

## 3.2.0
- Add support for category attribute in OrderLineRequest
- Exposed the dashboard link in the OrderLink


## 3.1.0
- Fixed camel case issue for 'metadata' in OrderRequest
- Allow multiple methods in PaymentRequest
- Fixed isCancelable always false
- Mapped Status fields to Enums
- Mapped 'value' from Amount to a BigDecimal
- Fixed an issue where the TokenType from TokenResponse was not mapping

## 3.0.1
- Optionals default to empty instead of `null`

## 2.2.7
- Fixed a typo in PaymentEmbedded.chargebacks

## 2.2.6
- Made Config object part of the Client, instead of being a singleton.
  This allows multiple clients to have different configurations

## 2.2.5
- Fixed a typo in RefundResponse
- Fixed a wrong enum value in ResponseType

## 2.2.4
- Removed the description field from SimpleMethodResponse
- Added the name field from SimpleMethodResponse

## 2.2.3
- Added lombok annotations to SimpleMethodResponse class

## 2.2.2
- cardToken added to PaymentRequest
- Issue with order links resolved
- Updated unirest version to 3.6.01

## 2.2.0
- Default 'UNKNOWN' value for Locale enum when the enum is not found
- Bumped Jackson to 2.10.1
- Added CONTRIBUTING, CONTRIBUTORS and CHANGELOG files
