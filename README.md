# Entrega Expressa - Teste técnico - Android

>Por Alexandre Porto Masquio

Este app tem como objetivo permitir o cadastro de entregas, salvando-as em um banco de dados local.
Para cadastrar uma entrega, basta clicar no botão com um "+". A lista de estados e cidades do
endereço são obtidas por meio de API externa.

O app lista as entregas salvas em cards clicáveis, que permitem exibir uma tela de detalhes, onde é
possível editar ou excluir as entregas, por meio de ícones do menu superior.

Algumas das tecnologias utilizadas no projeto:

- Padrão de arquitetura MVVM;
- Injeção de dependências com Hilt;
- Coroutines;
- ViewBinding.
- Retrofit
- Room