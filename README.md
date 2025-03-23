# Apache Camel with Spring Boot 🚀

Este repositório foi criado com base no curso **[Apache Camel Framework with Spring Boot](https://www.udemy.com/course/apache-camel-framework-with-spring-boot)**, oferecendo uma implementação prática e estruturada para o desenvolvimento de integrações utilizando Apache Camel e Spring Boot.

## 📌 Sobre o Projeto

O **Apache Camel** é um framework poderoso para integração de sistemas, permitindo a interconexão de diferentes tecnologias utilizando um conjunto de componentes prontos para uso. Neste projeto, ele está integrado ao **Spring Boot**, facilitando a criação de microsserviços e fluxos de mensagens escaláveis.

## 🛠 Tecnologias Utilizadas

- **Java 8+**
- **Spring Boot**
- **Apache Camel**
- **ActiveMQ** (mensageria)
- **Kafka** (event streaming)
- **Docker** (para conteinerização dos serviços)

## 🚀 Como Executar o Projeto

### 🔧 **1. Clonar o Repositório**
```sh
 git clone https://github.com/EltonRiva1/apache-camel-with-spring-boot.git
 cd apache-camel-with-spring-boot
```

### 🐳 **2. Subir os Contêineres Docker** (ActiveMQ, Kafka, Zookeeper)

Certifique-se de ter o **Docker** instalado e execute:
```sh
 docker-compose up -d
```
Isso irá iniciar os serviços necessários para o funcionamento do projeto.

### ▶️ **3. Rodar a Aplicação Spring Boot**

Para executar o projeto, use o Maven:
```sh
 mvn spring-boot:run
```

Ou, se preferir, compile e execute manualmente:
```sh
 mvn clean package
 java -jar target/apache-camel-with-spring-boot.jar
```

## 📡 Testando a Integração

### **Interface Web do ActiveMQ**
Acesse: [http://localhost:8161](http://localhost:8161)  
Usuário: `admin` | Senha: `admin`

### **Verificando Tópicos do Kafka**
Se o Kafka estiver rodando corretamente, você pode listar os tópicos:
```sh
 docker exec -it <container_id_do_kafka> kafka-topics.sh --list --bootstrap-server localhost:9092
```

## 🏗 Estrutura do Projeto
```
apache-camel-with-spring-boot/
├── src/main/java/com/in28minutes/microservices/      # Código-fonte principal
│   ├── routes/                                       # Rotas do Apache Camel
│   ├── patterns/                                     # Configurações do Spring Boot e Camel
│
├── src/main/resources/
│   ├── application.properties                        # Configurações da aplicação
│   
├── docker-compose.yml                                # Configuração dos contêineres
└── pom.xml                                           # Dependências Maven
```

## 📚 Recursos Adicionais
- [Documentação Oficial do Apache Camel](https://camel.apache.org/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Curso Udemy: Apache Camel com Spring Boot](https://www.udemy.com/course/apache-camel-framework-with-spring-boot)

---

🔹 *Sinta-se à vontade para contribuir e sugerir melhorias!* 🚀

