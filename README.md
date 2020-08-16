## TWITTER DATA EXTRACTOR APPLICATION

>Created By: **Juan Eduardo Domingos**
Scope: **POC and Study**
Tags: **Big Data, Spark, Dashboard, Twitter, HTTP, Node.JS, ETL, ELT, Data Extraction**

## Abstract
That application aim extract data of posts from Twitter public API and based on this data, build a dashboard with information such posts, what they represent and their relevance.

## Used Technologies

 1. **Node.JS**: Application responsible for raw data extration from Twitter API
 2. **Apache Cassandra**: NoSQL Database where will be store the raw data and after processing, the refined data.
 3. **Java / Spring Boot**:  Application reponsible for produce messages which will be consumed for **SPARK** application, requesting raw data processing to dashboard building.
 4. **Amazon SQS**:  Structure responsible for collect and manage the messages generated by Java application and consumed for **SPARK** appplication.
 5. **Scala & Apache Spark**: Application responsible for prepare and process raw data for the dashboard building.

## Application Architeture

![Application Architeture](https://raw.githubusercontent.com/jeduardodomingos/big-data-study-case/master/architeture/main-structure.png)
