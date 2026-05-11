# aplicacao_bancaria
Sistema bancário em Java utilizando console

![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Microsoft Excel](https://img.shields.io/badge/Microsoft_Excel-217346?style=for-the-badge&logo=microsoft-excel&logoColor=white)

## Sobre o Projeto - 🚧 Em Desenvolvimento 🚧

Este projeto consiste em um sistema bancário desenvolvido em Java utilizando os conceitos de Programação Orientada a Objetos (POO). 
A aplicação funciona via terminal/console e permite operações bancárias básicas como:

* Criação de contas
* Login de usuários
* Depósitos
* Saques
* Transferências
* Controle de limite
* Histórico de transações
* Exportação de histórico em CSV

---

## Sumário

- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Segurança e Regras de Negócio](#segurança-e-regras-de-negócio)
- [Conceitos de POO Aplicados](#conceitos-de-poo-aplicados)
- [Guia de Execução do Projeto](#guia-de-execução-do-projeto)
- [Fluxo de Operação da Aplicação](#fluxo-de-operação-da-aplicação)
- [Diagramação do Sistema](#diagramação-do-sistema)
  - [Diagrama de Classe](#1-diagrama-de-classe)
  - [Diagramas de Sequência](#2-diagrama-de-sequência)

---

## Estrutura do Projeto
O sistema foi estruturado seguindo a `arquitetura MVC`, onde cada camada tem sua responsabilidade:

### 1. Model - com.aplicacao_bancaria.model

A camada `Model` representa as entidades do sistema.
Ela é responsável por armazenar os dados e comportamentos principais das contas, clientes e transações.

#### Exemplos:

* Cliente (Cliente.java);
* Conta (Conta.java);
* Conta Corrente (ContaCorrente.java);
* Conta Poupanca (ContaPoupanca.java);
* Transação (Transacao.java);

Essa camada contém:

* Atributos 
* Construtores
* Getters
* Regras básicas da entidade

### 2. Repository - com.aplicacao_bancaria.repository

A camada `Repository` é responsável pelo armazenamento e busca de dados.
Atualmente o sistema utiliza armazenamento em memória através de listas (ArrayList).

#### Ela realiza operações como:

* Adicionar contas (adicionarConta);
* Buscar contas por CPF (buscarPorCpf);
* Buscar contas por número (buscarPorNumero);

Além disso, utiliza uma interface (ContaInterfaceRepository) para reduzir o acoplamento entre o Service e a implementação concreta do repositório.

### 3. Service - com.aplicacao_bancaria.service

A camada `Service` contém as regras de negócio do sistema.
#### Ela é responsável por:

* Criação de contas (ContaService)
* Login
* Transferências
* Validações de regras bancárias
* Limite noturno
* Validações de saldo

Essa camada funciona como intermediária entre a interface do usuário e os dados.

### 4. UI - com.aplicacao_bancaria.ui

A camada `UI` (User Interface) é responsável pela interação com o usuário. Ela exibe menus, recebe entradas e mostra mensagens no console.

#### Exemplos:

* Menu de login/criação de conta
* Menu principal
* Opções bancárias
* Mensagens de erro

### 5. Util - com.aplicacao_bancaria.util

A camada `util` contém classes utilitárias reutilizáveis.
Ela centraliza funcionalidades auxiliares utilizadas em várias partes do sistema, evitando a repetição de código e melhorando a organização do projeto.

#### Exemplos:

* Validações
* Leitura segura de dados
* Exportação CSV (com caminho peronalizado)
* Formatação

---

## Funcionalidades
### Conta
* Cadastro de conta corrente
* Cadastro de conta poupança
* Validação de CPF
* Validação de nome
* Validação de número da conta
* Login

### Operações Bancárias
* Ver saldo
* Depositar
* Sacar
* Transferir
* Consultar limite
* Alterar limite
* Consultar histórico
* Exportar histórico

---

## Segurança e Regras de Negócio

### Limite Noturno

O sistema possui restrições para transferências em horários específicos:

| Horário  | Regra                     |
| -------- | ------------------------- |
| Até 20h  | Transferências livres     |
| Após 20h | Máximo de R$1000          |
| Após 22h | Transferências bloqueadas |

### Histórico

Todas as operações são registradas e formatadas para consulta:

* Depósitos
* Saques
* Transferências
* Alterações de limite
* Consultas de limite

### Exportação CSV

O usuário pode exportar o histórico da conta em formato `CSV`.
O arquivo é exportado automaticamente para a pasta Downloads do usuário contendo todas as transações realizadas na conta. 
O caminho é personalizável a partir do:

```text
Downloads/
```
---

## Conceitos de POO Aplicados

### Encapsulamento

Os atributos das classes são privados/protected e acessados através de métodos.

### Herança

A classe abstrata `Conta` é herdada por:

* ContaCorrente
* ContaPoupanca

### Polimorfismo

Utilização de métodos abstratos e interfaces.

### Interface

A interface `ContaInterfaceRepository` desacopla o service da implementação concreta do repositório.

### Baixo Acoplamento e  Alta Coesão

As responsabilidades foram separadas para que cada classe possua uma função específica dentro do sistema.

---

## Guia de Execução do Projeto

### Pré-requisitos

* Java JDK 17+ instalado
* Eclipse IDE (ou outra IDE Java)
* Git e SpringTools instalado

### Clonar o Repositório

```bash
git clone https://github.com/BottoYA/aplicaco_bancaria.git
```

### Abrir no Eclipse

1. Abra o Eclipse
2. Clique em:

```text
File → Open Projects from File System
```

3. Selecione a pasta do projeto e clique em Finish

### Inicializar Sistema

Localize a classe:

```text
Main.java
```

Depois:

```text
Botão direito → Run As → Java Application
```

### Fluxo de Operação da Aplicação

#### 1. Menu Inicial

Ao inicializar a aplicação, teremos as seguintes opções:

```text
1 - Criar conta
2 - Login
0 - Sair
```

Durante o primeiro uso, escolha a opção `1` para criar uma conta.

#### 1.1 Criar Conta

O usuário deverá informar, na seguinte ordem:

* Nome
* CPF
* Senha
* Número da conta
* Agência
* Tipo da conta

O sistema realiza validações a cada etapa antes de criar a conta, para que `não` seja possível:

* Entrar com números no campo `Nome`
* Adicionar um `CPF` com menos/mais de 11 números
* Digitar letras no campo `CPF` e `Número da conta`
* Cadastrar um `CPF` já incluído no sistema
* Digitar qualquer outra entrada a não ser `corrente` ou `poupanca` no campo `Tipo da conta`

Ao realizar a criação da conta, o usuário será mandado de volta ao menu inicial, onde pode criar outra conta ou realizar login.

#### 1.2. Login

O login é realizado utilizando os dados já cadastrados:

* CPF
* Senha

### 2. Menu da Conta

Após o login, será apresentado as opções dentro da conta:

```text
1 - Ver saldo
2 - Depositar
3 - Sacar
4 - Ver limite
5 - Alterar limite
6 - Transferir
7 - Ver histórico
8 - Exportar CSV
0 - Logout
```

As validações feitas nessas etapas são:

#### 2.2. Depósito
* Não aceita letras
* Não aceita valores negativos

#### 2.3. Saque

* Não aceita letras
* Não aceita valores negativos
* Verifica saldo + limite

#### 2.4. e 2.5. Ver/Alterar limite

* Contas do tipo poupança não possuem limite, logo não será possível visualizar nem alterar

#### 2.6. Transferência

* Verifica existência da conta destino
* Não aceita letras
* Não aceita valores negativos
* Possui limite noturno

---

## Diagramação do Sistema
### 1. Diagrama de Classe

```mermaid
classDiagram
class Cliente {
    -String nome
    -String cpf
    -String senha

    +getNome() String
    +getCpf() String
    +getSenha() String
}

class Conta {
    <<abstract>>
    #String numeroConta
    #String agencia
    #Cliente cliente
    #double saldo
    #double limite
    #List~Transacao~ historico

    +depositar(double valor) void
    +sacar(double valor) boolean
    +alterarLimite(double novoLimite) boolean
    +getSaldo() double
    +getLimite() double
    +getNumeroConta() String
    +getCliente() Cliente
    +getHistorico() List
    +getTipoConta() String
}

class ContaCorrente {
    +getTipoConta() String
}

class ContaPoupanca {
    +getTipoConta() String
}

class Transacao {
    -String tipo
    -double valor
    -LocalDateTime data
    -String descricao

    +toCSV() String
    +toString() String
}

class ContaInterfaceRepository {
    <<interface>>

    +adicionarConta(Conta conta) void
    +buscarPorNumero(String numero) Conta
    +buscarPorCpf(String cpf) Conta
}

class ContaRepository {
    -List~Conta~ contas

    +adicionarConta(Conta conta) void
    +buscarPorNumero(String numero) Conta
    +buscarPorCpf(String cpf) Conta
}

class ContaService {
    -ContaInterfaceRepository repository

    +criarConta(String nome, String cpf, String senha, String numero, String agencia, String tipo) Conta
    +login(String cpf, String senha) Conta
    +transferir(String origem, String destino, double valor) String
    +buscarPorNumero(String numero) Conta
    +buscarPorCpf(String cpf) Conta
}

class Menu {
    -Scanner scanner
    -InputUtil input
    -ContaService service

    +iniciar() void
    +criarConta() void
    +realizarLogin() void
    +menuConta(Conta conta) void
    +depositar(Conta conta) void
    +sacar(Conta conta) void
    +transferir(Conta conta) void
    +verLimite(Conta conta) void
    +alterarLimite(Conta conta) void
    +mostrarHistorico(Conta conta) void
}

class CSVExporter {
    +exportar(Conta conta) void
}

class InputUtil {
    -Scanner scanner

    +lerInt(String msg) int
    +lerDouble(String msg) double
    +lerString(String msg) String
}

class Validador {
    +validarNome(String nome) boolean
    +validarCPF(String cpf) boolean
    +formatarCPF(String cpf) String
    +validarTipoConta(String tipo) boolean
    +validarNumeroConta(String numero) boolean
}

Conta <|-- ContaCorrente
Conta <|-- ContaPoupanca

Conta --> Cliente
Conta --> "many" Transacao

ContaRepository ..|> ContaInterfaceRepository

ContaService --> ContaInterfaceRepository
Menu --> ContaService

Menu --> InputUtil
Menu --> CSVExporter
Menu --> Validador

ContaRepository --> Conta
CSVExporter --> Conta
```

### 2. Diagrama de Sequência
#### 2.1. Criar Conta

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant Validador
participant ContaService
participant ContaRepository
participant Cliente
participant Conta

Usuario->>Menu: Seleciona "Criar Conta"

Menu->>Usuario: Solicita nome
Usuario-->>Menu: Digita nome

Menu->>Validador: validarNome(nome)
Validador-->>Menu: true/false

Menu->>Usuario: Solicita CPF
Usuario-->>Menu: Digita CPF

Menu->>Validador: validarCPF(cpf)
Validador-->>Menu: true/false

Menu->>ContaService: buscarPorCpf(cpf)
ContaService->>ContaRepository: buscarPorCpf(cpf)
ContaRepository-->>ContaService: Conta/null
ContaService-->>Menu: Resultado

Menu->>Usuario: Solicita senha
Usuario-->>Menu: Digita senha

Menu->>Usuario: Solicita número da conta
Usuario-->>Menu: Digita número

Menu->>ContaService: buscarPorNumero(numero)
ContaService->>ContaRepository: buscarPorNumero(numero)
ContaRepository-->>ContaService: Conta/null
ContaService-->>Menu: Resultado

Menu->>Usuario: Solicita agência
Usuario-->>Menu: Digita agência

Menu->>Usuario: Solicita tipo da conta
Usuario-->>Menu: corrente/poupanca

Menu->>Validador: validarTipoConta(tipo)
Validador-->>Menu: true/false

Menu->>ContaService: criarConta(...)

ContaService->>Cliente: criar Cliente

alt Conta Corrente
    ContaService->>Conta: criar ContaCorrente
else Conta Poupança
    ContaService->>Conta: criar ContaPoupanca
end

ContaService->>ContaRepository: adicionarConta(conta)

ContaRepository-->>ContaService: Conta salva
ContaService-->>Menu: Conta criada
Menu-->>Usuario: Exibe sucesso
```


#### 2.2. Login

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant Validador
participant ContaService
participant ContaRepository

Usuario->>Menu: Seleciona "Login"

Menu->>Usuario: Solicita CPF
Usuario-->>Menu: Digita CPF

Menu->>Validador: validarCPF(cpf)
Validador-->>Menu: true/false

Menu->>Usuario: Solicita senha
Usuario-->>Menu: Digita senha

Menu->>ContaService: login(cpf, senha)

ContaService->>ContaRepository: buscarPorCpf(cpf)
ContaRepository-->>ContaService: Conta/null

alt Login válido
    ContaService-->>Menu: Conta
    Menu-->>Usuario: Login realizado
else Login inválido
    ContaService-->>Menu: null
    Menu-->>Usuario: CPF ou senha inválidos
end
```

#### 2.3. Depositar

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant InputUtil
participant Conta
participant Transacao

Usuario->>Menu: Seleciona "Depositar"

Menu->>InputUtil: lerDouble()
InputUtil-->>Menu: valor

alt Valor válido
    Menu->>Conta: depositar(valor)

    Conta->>Conta: saldo += valor
    Conta->>Transacao: criar transação

    Conta-->>Menu: depósito realizado
    Menu-->>Usuario: Exibe sucesso
else Valor inválido
    Menu-->>Usuario: Exibe erro
end
```

#### 2.4. Sacar

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant InputUtil
participant Conta
participant Transacao

Usuario->>Menu: Seleciona "Sacar"

Menu->>InputUtil: lerDouble()
InputUtil-->>Menu: valor

Menu->>Conta: sacar(valor)

alt Saldo suficiente
    Conta->>Conta: saldo -= valor
    Conta->>Transacao: criar transação
    Conta-->>Menu: true
    Menu-->>Usuario: Saque realizado
else Saldo insuficiente
    Conta-->>Menu: false
    Menu-->>Usuario: Exibe erro
end
```

#### 2.5. Ver Limite

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant Conta
participant Transacao

Usuario->>Menu: Seleciona "Ver Limite"

alt Conta Corrente
    Menu->>Conta: getLimite()
    Conta-->>Menu: limite

    Menu->>Transacao: registrar consulta

    Menu-->>Usuario: Exibe limite
else Conta Poupança
    Menu-->>Usuario: Conta não possui limite
end
```

#### 2.6. Alterar Limite

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant InputUtil
participant Conta
participant Transacao

Usuario->>Menu: Seleciona "Alterar Limite"

Menu->>InputUtil: lerDouble()
InputUtil-->>Menu: novoLimite

Menu->>Conta: alterarLimite(novoLimite)

alt Limite válido
    Conta->>Conta: atualiza limite
    Conta->>Transacao: cria transação
    Conta-->>Menu: true
    Menu-->>Usuario: Limite alterado
else Limite inválido
    Conta-->>Menu: false
    Menu-->>Usuario: Exibe erro
end
```

#### 2.7. Transferir

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant ContaService
participant ContaRepository
participant ContaOrigem
participant ContaDestino
participant Transacao

Usuario->>Menu: Seleciona "Transferir"

Menu->>Usuario: Solicita conta destino
Usuario-->>Menu: Digita conta

Menu->>ContaService: buscarPorNumero(destino)

ContaService->>ContaRepository: buscarPorNumero(destino)
ContaRepository-->>ContaService: Conta/null
ContaService-->>Menu: Resultado

Menu->>Usuario: Solicita valor
Usuario-->>Menu: Digita valor

Menu->>ContaService: transferir(origem,destino,valor)

ContaService->>ContaRepository: buscar origem
ContaRepository-->>ContaService: Conta origem

ContaService->>ContaRepository: buscar destino
ContaRepository-->>ContaService: Conta destino

ContaService->>ContaOrigem: sacar(valor)

alt Transferência permitida
    ContaOrigem-->>ContaService: true

    ContaService->>ContaDestino: saldo += valor

    ContaService->>Transacao: registrar envio
    ContaService->>Transacao: registrar recebimento

    ContaService-->>Menu: OK
    Menu-->>Usuario: Transferência realizada
else Erro
    ContaOrigem-->>ContaService: false
    ContaService-->>Menu: erro
    Menu-->>Usuario: Exibe erro
end
```

#### 2.8. Mostrar Histórico

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant Conta

Usuario->>Menu: Seleciona "Ver Histórico"

Menu->>Conta: getHistorico()

alt Histórico vazio
    Conta-->>Menu: lista vazia
    Menu-->>Usuario: Nenhuma transação
else Histórico encontrado
    Conta-->>Menu: lista de transações
    Menu-->>Usuario: Exibe histórico
end
```

#### 2.9. Exportar CSV

```mermaid
sequenceDiagram

actor Usuario
participant Menu
participant CSVExporter
participant Conta
participant Transacao
participant FileWriter

Usuario->>Menu: Seleciona "Exportar CSV"

Menu->>CSVExporter: exportar(conta)

CSVExporter->>Conta: getHistorico()
Conta-->>CSVExporter: lista transações

loop Para cada transação
    CSVExporter->>Transacao: toCSV()
    Transacao-->>CSVExporter: linha CSV
end

CSVExporter->>FileWriter: escreve arquivo

alt Exportação bem sucedida
    FileWriter-->>CSVExporter: arquivo salvo
    CSVExporter-->>Menu: sucesso
    Menu-->>Usuario: CSV exportado
else Erro na exportação
    FileWriter-->>CSVExporter: erro
    CSVExporter-->>Menu: erro
    Menu-->>Usuario: Exibe erro
end
```
