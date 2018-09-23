# Mollie API with Java 8+

[![Build Status](https://travis-ci.org/Feel-IO/mollie.svg?branch=master)](https://travis-ci.org/Feel-IO/mollie)  [ ![Download](https://api.bintray.com/packages/feel-io/Mollie/mollie/images/download.svg) ](https://bintray.com/feel-io/Mollie/mollie/_latestVersion)  

## License

The source code is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Java

This library requires Java 8.

## Usage

1. Add dependency to your project (For example maven:)
```
<dependency>
    <groupId>be.feelio</groupId>
    <artifactId>mollie</artifactId>
    <version>0.8.0</version>
</dependency>
```
2. Build a client and use it (For getting a payment)
```
Client client = new ClientBuilder()
                       .withApiKey("test_hW7trRMaJphr8feGzMNtd5SwShQSwg")
                       .build();
                       
PaymentResponse payment = client.payments().getPayment("my-payment-id");
```

## Features

- [ ] Mollie Connect / OAuth
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
    
- [ ] **Orders**
    - [ ] Create order    
    - [ ] Get order    
    - [ ] Update order    
    - [ ] Cancel order    
    - [ ] List orders    
    - [ ] Cancel order lines    
    - [ ] Create order refund    
    - [ ] List order refunds 
    
- [ ] **Shipments**
    - [ ] Create Shipment 
    - [ ] Get Shipment    
    - [ ] Update Shipment    
    - [ ] List Shipments
     
- [ ] **Customers**
    - [ ] Create Customer    
    - [ ] Get Customer    
    - [ ] Update Customer    
    - [ ] Cancel Customer    
    - [ ] List Customers          
    - [ ] Create customer payment          
    - [ ] List customer payments
     
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
     
- [ ] **Connect**
    - [ ] Authorize
    - [ ] Tokens
  
- [ ] **Permissions**
    - [ ] Get permission
    - [ ] List permissions
                 
- [ ] **Organizations**
    - [ ] Get current organization
    - [ ] Get organization
    - [ ] List organizations
    
- [ ] **Organizations**
    - [ ] Create profile    
    - [ ] Get profile    
    - [ ] Get current profile    
    - [ ] Update profile    
    - [ ] Delete profile    
    - [ ] List profiles
    
- [ ] **Settlements**
    - [ ] Get settlement    
    - [ ] Get next settlement    
    - [ ] Get open settlement    
    - [ ] List settlements    
    - [ ] List settlement payments    
    - [ ] List settlement refunds    
    - [ ] List settlement chargebacks    
    - [ ] List settlement captures    
    
- [ ] **Invoices**
    - [ ] Get invoice
    - [ ] List invoices