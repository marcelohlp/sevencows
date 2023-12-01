
![SevenCows](https://github.com/marcelohlp/sevencows/assets/112910894/db596861-19ca-476b-b9eb-60cd6bf044e1)

<p align="center">
<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge"/>
</p>

# SevenCows

## Sobre:

SevenCows faz parte do projeto fintech, forma de avaliação do primeiro ano de Análise e Desenvolvimento de Sistemas da FIAP.
É uma aplicação web que auxilia no controle financeiro de seus usuários. Ela permite que sejam armazenados receitas, despesas e reservas financeiras e 
disponibiliza relatórios que facilitam compreender á sua real saúde financeira.

## Funcionalidades:

- `Gestão financeira` - O usuário consegue cadastrar, editar e excluir receitas, despesas e reservas de maneira prática;
- `Listar ocorrências por data` - O usuário conseque acessar todos os registros pela data que desejar;
- `Relatórios` - O usuário consegue gerar relatórios por data, o que permite que ele compreenda melhor qual o resultado financeiro do período; e
- `Gestão da conta` - Além do cadastro, o usuário pode alterar seus dados e excluir sua conta.

## Tecnologias:

O sitema teve sue backend totalmente desenvolvido em ``Java`` seguindo os princípios de ``Programação Orientada a Objetos``. Para o armazenamento de dados, 
foi utilizado o ``banco de dados relacional`` ``Oracle`` inteiramente implementado com linguagem ``SQL``. A interação ficou por conta das ``Servlets``, responsáveis pelo controle da aplicação; trabalhando com  as solicitações ao banco e enviando informações as páginas ``JSP`` que permitiram o dinamismo da aplicação. Toda a estilização das páginas foi feita pelo framework ``Bootstrap``. Por fim, nada seria possível sem a ``IDE Eclipse`` que facilitou toda a implementação do código e ``Apache Tomcat``, servidor que permitiu tornar o projeto funcional.

## Como utilizar a aplicação:

Primeiramente é preciso que o Apache Tomcat estejá configurado na sua IDE. Agora, faça o download de todo o código. Você vai precisar configurar o banco de dados. Então, abra o banco de dados, use o código DDL que está na pasta sql. Finalizado está etapa, você vai precisar configurar seu acesso; para isso abra o arquivo ConnectionManager.java e altere os valores para "url", "user" e "password" que estão vazios. Por fim, basta rodar o arquivo index.jsp com o servidor, realizar seu cadastro e organizar suas finanças.

## Telas do projeto
### Receitas
![Tela de receitas](https://github.com/marcelohlp/sevencows/assets/112910894/2d978dd8-4a27-436b-9b18-d3258132ce83)
### Despesas
![Tela de despesas](https://github.com/marcelohlp/sevencows/assets/112910894/a3194f4a-bb1d-4729-aaa2-60970c5fa7b4)
### Relatório
![Tela de relatorio](https://github.com/marcelohlp/sevencows/assets/112910894/b0c34613-a5bf-4959-99a1-4e99a7af2e01)

## Por fim, um pouco de documentação!
### Diagrama de Classes
![Diagrama de classes](https://github.com/marcelohlp/sevencows/assets/112910894/00da9fee-92da-4566-a177-2b011c9d7e56)
### Modelo Entidade-Relacionamento
![MER Relacional](https://github.com/marcelohlp/sevencows/assets/112910894/66010861-412e-438f-83b8-0cb91574dbeb)

