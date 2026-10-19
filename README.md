# Estrutura do Projeto 
Documentação da estrutura de pasta para auxiliação na navegação do projeto e entender onde cada coisa se enconta
```text
gestao-revendedora/
├── .mvn/                    # Configurações do gerenciador de dependências 
├── assets/                  # Arquivos estáticos da aplicação
│   ├── images/              # Ícones de botões, logotipos, favicons 
│   ├── styles/              # Arquivos de estilo 
│   └── database/            # Banco de dados local/embarcado 
├── src/
│   ├── main/
│   │   ├── java/            # Todo o seu código-fonte .java (pacotes)
│   │   └── resources/       # Telas em FXML, propriedades ou configurações locais
│   └── test/
│       └── java/            # Seus testes unitários (JUnit)
├── .gitignore               # Arquivos a serem ignorados pelo Git (.class, /target, etc.)
├── pom.xml (ou build.gradle)# Gerenciador de dependências e bibliotecas externas
└── README.md                # Instruções de instalação, execução e requisitos
```

## Estrutura Lógica de Pacotes
Dentro da pasta "java", todo o código é dividido em pacotes que respresentam a responsabildiade claras

O mapa completo dos pacotes do projeto:
```text 
br.com.example/
│
├── Main.java                        # Classe principal contendo o método `public static void main`
│
├── model/                           # 1. DOMÍNIO E DADOS 
│   ├── entity/                      # Objetos do negócio (ex: Cliente.java)
│   └── enums/                       # Enumerações (ex: StatusCliente.java)
│
├── view/                            # 2. INTERFACE VISUAL (Telas e Componentes)
│   ├── components/                  # Componentes reutilizáveis 
│   ├── dialogs/                     # Janelas modais de aviso, confirmação ou edição rápida
│   ├── screen/                      # Formulários e janelas inteiras 
│   └── util/                        # Utilitários de tela 
│
├── controller/                      # 3. INTERMEDIAÇÃO (Liga a View aos Serviços/Dados)
│   ├── ClienteController.java       # Escuta os eventos da tela (clique de botão) e executa ações
│   └── VendaController.java
│
├── service/                         # 4. REGRAS DE NEGÓCIO (Validações e Cálculos)
│   ├── ClienteService.java          # Valida CPF, checa se cliente já existe antes de salvar
│   └── EstoqueService.java          # Calcula desconto, atualiza estoque disponível
│
├── dao/                             # 5. PERSISTÊNCIA DE DADOS LOCAL (Data Access Object)
│   ├── ConnectionFactory.java       # Gerencia a conexão local 
│   ├── ClienteDAO.java              # Códigos SQL de CRUD (INSERT, UPDATE, DELETE, SELECT)
│   └── VendaDAO.java
│
└── util/                            # 6. UTILITÁRIOS GERAIS DA APLICAÇÃO
    ├── DateUtils.java               # Métodos para conversão/formatação de datas
    ├── LogUtils.java                # Gerenciador de erros e geração de arquivos de log
    └── SystemConfig.java            # Leitura de preferências/configurações do sistema local
```

Exemplo de como funciona a comunicação entre diretórios/camadas:
```text
[Usuário clica no Botão "Salvar"]
           │
           ▼
        [VIEW] (TelaPrincipal)
           │
           ▼
     [CONTROLLER] (ClienteController)
           │
           ▼
      [SERVICE] (ClienteService -> Aplica validações de negócio)
           │
           ▼
        [DAO]     (ClienteDAO -> Conecta ao banco local e faz o SQL)
           │
           ▼
      [DATABASE]  (SQLite)
```