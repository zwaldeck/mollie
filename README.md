# Mollie API with Java 8+

[![Build Status](https://travis-ci.org/zwaldeck/mollie.svg?branch=master)](https://travis-ci.org/zwaldeck/mollie)  [![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.woutschoovaerts/mollie/badge.svg)](https://search.maven.org/artifact/be.woutschoovaerts/mollie) [![Javadocs](https://www.javadoc.io/badge/be.woutschoovaerts/mollie.svg?color=red)](https://www.javadoc.io/doc/be.woutschoovaerts/mollie)


## License

The source code is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Java

This library requires Java 8+.

## Usage

1. Add the dependency to your project. For example with maven:
```
<dependency>
    <groupId>be.woutschoovaerts</groupId>
    <artifactId>mollie</artifactId>
    <version>3.6.2</version>
</dependency>
```

2. Build a client
```
Client client = new ClientBuilder()
                       .withApiKey("test_hW7trRMaJphr8feGzMNtd5SwShQSwg")
                       .build();
```

3. Use the client to execute whatever action you want to do. For example retrieving a payment:
```
PaymentResponse payment = client.payments().getPayment("my-payment-id");
```

## Features

- [X] Mollie Connect / OAuth
- [X] Api Key Authentication
- [X] **Payment**
    - [X] Create payment
    - [X] Get payment
    - [X] Cancel payment
    - [X] List payment

- [X] **Methods**
    - [X] List payment methods
    - [X] Get payment method

- [X] **Refunds**
    - [X] Create payment refund
    - [X] Get payment refund
    - [X] Cancel payment refund
    - [X] List payment refunds

- [X] **Chargebacks**
    - [X] Get chargeback
    - [X] List chargebacks

- [X] **Captures**
    - [X] Get capute
    - [X] List captures

- [X] **Orders**
    - [X] Create order
    - [X] Get order
    - [X] Update order
    - [X] Cancel order
    - [X] List orders
    - [X] Cancel order lines
    - [X] Create order refund
    - [X] List order refunds

- [X] **Shipments**
    - [X] Create Shipment
    - [X] Get Shipment
    - [X] Update Shipment
    - [X] List Shipments

- [X] **Customers**
    - [X] Create Customer
    - [X] Get Customer
    - [X] Update Customer
    - [X] Cancel Customer
    - [X] List Customers
    - [X] Create customer payment
    - [X] List customer payments

- [X] **Mandates**
    - [X] Create mandate
    - [X] Get mandate
    - [X] Revoke mandate
    - [X] List Customers

- [X] **Subscriptions**
    - [X] Create subscription
    - [X] Get subscription
    - [X] Update subscription
    - [X] Cancel subscription
    - [X] List subscriptions

- [X] **Connect**
    - [X] Authorize
    - [X] Tokens

- [X] **Permissions**
    - [X] Get permission
    - [X] List permissions

- [X] **Organizations**
    - [X] Get current organization
    - [X] Get organization
    - [X] List organizations

- [X] **Organizations**
    - [X] Create profile
    - [X] Get profile
    - [X] Get current profile
    - [X] Update profile
    - [X] Delete profile
    - [X] List profiles

- [X] **Settlements**
    - [X] Get settlement
    - [X] Get next settlement
    - [X] Get open settlement
    - [X] List settlements
    - [X] List settlement payments
    - [X] List settlement refunds
    - [X] List settlement chargebacks
    - [X] List settlement captures

- [X] **Invoices**
    - [X] Get invoice
    - [X] List invoices

- [X] **Wallets**
    - [X] Request Apple Pay Payment Session
