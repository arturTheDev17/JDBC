# Sistema de Gestão de Eventos

### Contexto

Os alunos desenvolverão um sistema para gerenciar eventos culturais onde é possível cadastrar eventos, registrar participantes e inscrevê-los em eventos. Cada evento pode ter múltiplos participantes, e cada participante pode se inscrever em vários eventos.

### Objetivo

Criar um sistema que permita:

- Cadastrar eventos.
- Cadastrar participantes.
- Inscrever participantes em eventos.
- Buscar eventos e participantes.
- Remover inscrições.

### Classes Sugeridas

1. **Evento**
    - Atributos: `id`, `nome`, `local`, `data`, `descricao`
    - Métodos: Getters e Setters
2. **Participante**
    - Atributos: `id`, `nome`, `email`
    - Métodos: Getters e Setters
3. **Inscricao**
    - Atributos: `id`, `evento`, `participante`
    - Métodos: Getters e Setters
4. **BancoEvento**
    - Métodos:
        - `void adicionarEvento(Evento evento)`
        - `Evento buscarEventoPorNome(String nome)`
        - `void removerEvento(int id)`
5. **BancoParticipante**
    - Métodos:
        - `void adicionarParticipante(Participante participante)`
        - `Participante buscarParticipantePorEmail(String email)`
        - `void removerParticipante(int id)`
6. **BancoInscricao**
    - Métodos:
        - `void inscreverParticipante(int eventoId, int participanteId)`
        - `void removerInscricao(int id)`
7. **Executável**
    - Método `main`  e demais para interagir com o usuário e testar as funcionalidades.

### Passos da Atividade

1. **Configuração do Banco de Dados**
    - Criar as tabelas `eventos`, `participantes` e `inscricoes` no banco de dados.
2. **Implementação das Classes**
    - Criar as classes `Evento`, `Participante`, `Inscricao` com os atributos e métodos necessários.
3. **Implementação das Classes Banco**
    - Conectar ao banco de dados e implementar os métodos para adicionar, buscar e remover eventos e participantes, além de inscrever e remover inscrições, incluindo tratamento de exceções.
4. **Implementação da Classe Executável**
    - Criar um menu interativo com opções para adicionar eventos, participantes, inscrever participantes em eventos e remover inscrições.
    - Capturar entradas do usuário e chamar os métodos apropriados.