# Mollie API with Java 11+

![Build & Test](https://github.com/zwaldeck/mollie/actions/workflows/build.yml/badge.svg) 
[![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.woutschoovaerts/mollie/badge.svg)](https://search.maven.org/artifact/be.woutschoovaerts/mollie) 
[![Javadocs](https://www.javadoc.io/badge/be.woutschoovaerts/mollie.svg?color=red)](https://www.javadoc.io/doc/be.woutschoovaerts/mollie)


## License

The source code is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Java

This library requires Java 11+.

## Usage

1. Add the dependency to your project. For example with maven:
```
<dependency>
    <groupId>be.woutschoovaerts</groupId>
    <artifactId>mollie</artifactId>
    <version>4.6.1</version>
</dependency>
```

2. Build a client
```
Client client = new ClientBuilder()
                       .withApiKey("test_plsW7trRMaJphr8feGzMNtd5SwShQSwg")
                       .build();
```

3. Use the client to execute whatever action you want to do. For example retrieving a payment:
```
PaymentResponse payment = client.payments().getPayment("my-payment-id");
```
