# TWITTER DATA APPLICATION

# Resumo
Este conjunto de aplicação tem como objetivo realizar a extração de dados a partir da API pública do Twitter, e com base nestes dados realizar os disponibilizar um dashboard com  informações referentes a relevância dos assuntos e representatividade das postagens.

# Tecnologias Utilizadas

 1. **Node.JS**: Aplicação responsável pela extração do raw Data a partir da API do Twitter
 2. **Apache Cassandra**: Banco de dados não relacional onde serão armazenados os dados raw e posteriormente os dados refinados
 3. **Java / Spring Boot**: Aplicação responsável por produzir as mensagens que serão consumidas pela aplicação **SPARK** solicitando o processamento dos dados raw para a montagem do dashboard
 4. **Amazon SQS**: Responsável por coletar e gerenciar a mensagens geradas pela aplicação Java e consumidas pela Aplicação **SPARK**, conforme mencionado anteriormente.
 5. **Scala & Apache Spark**: Aplicação responsável pelo processamento e preparação dos dados raw para a contrução do Dashboard

# Arquitetura da Aplicação

![Application Architeture](https://raw.githubusercontent.com/jeduardodomingos/big-data-study-case/master/architeture/main-structure.png)
