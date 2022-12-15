# GoldenRaspberryAwards
API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Dependências

O projeto foi desenvolvido utilizando o Spring Boot, versão 3.0.0 e possui como dependência:

- JDK 17

As demais dependências são gerenciadas pelo Maven.

## Como executar o projeto

1) Clone o projeto em seu workspace
2) Importe o projeto:

No eclipse:

![image](https://user-images.githubusercontent.com/1282312/207864878-70b342df-6423-4e23-903c-487c87459ed9.png)

![image](https://user-images.githubusercontent.com/1282312/207864955-53faae70-8d51-4ab7-a701-6bfa7c6025a8.png)

3) Execute o método main da classe: br.com.emanuel.gra.GoldenRaspberryAwardsApplication

Ao iniciar, os dados contidos no arquivo configurado na propriedade "movielist.file.location" são importados para o banco de dados da aplicação. 

![image](https://user-images.githubusercontent.com/1282312/207854519-c822fa12-f466-475a-a619-68708b4b6221.png)

Caso nenhum arquivo seja configurado, a aplicação é iniciada com o banco de dados vazio.

![image](https://user-images.githubusercontent.com/1282312/207854757-aca3a743-0a45-44a1-bb10-8f0fffd60cc1.png)


### Configurações

As configurações do default projeto estão contidas no arquivo GoldenRaspberryAwards/src/main/resources/application.properties. 

A maior parte das configurações é relacionada à infraestrutura da aplicação, contudo, as seguintes propriedades permitem alterar o seu comportamento:

- movielist.file.location: Localização do arquivo csv que contém os dados iniciais da aplicação. Por padrão é carregado o arquivo "movielist.csv", presente na raiz do projeto. 
- movielist.file.idx.year: Índice da coluna relativa ao ano.
- movielist.file.idx.title: Índice da coluna relativa ao título.
- movielist.file.idx.studios: Índice da coluna relativa ao estudio.
- movielist.file.idx.producers: Índice da coluna relativa à lista de produtores.
- movielist.file.idx.winner: Índice da coluna que contem a informação do filme ter sido vitorioso (yes) ou não (vazio).

## Como executar os testes

Para executar os testes, basta executar o goal "test" do maven. 

### Executando os testes automatizados no eclipse

![image](https://user-images.githubusercontent.com/1282312/207855991-c234ca72-5713-4125-919a-eedeed6d9782.png)

![image](https://user-images.githubusercontent.com/1282312/207857001-a529df51-ae85-48cb-b01a-c5c005faf4f2.png)

![image](https://user-images.githubusercontent.com/1282312/207856681-ef52d58a-7100-4f66-aedf-aee0955b2e85.png)

## Utilização da API

### Nominees

Um Nominee, representa um indicado da categoria Pior Filme do Golden Raspberry Awards.

O webservice RESTful /nominees foi implementado com com base no nível 2 de maturidade de Richardson, contendo os métodos:

| Verbo HTTP| Endereço | Descrição |
| --------- | -------- | --------- |
| GET | /nominees | Lista todos os indicados |
| GET | /nominees/{id} | Retorna o indicado relacionado ao id |
| POST | /nominees | Salva o indicado, retornando-o |
| UPDATE | /nominees/{id} | Altera todas as propriedades do indicado, retornando-o |
| PATCH | /nominees/{id} | Altera todas as propriedades não vazias do indicado, retornando-o |
| DELETE | /nominees/{id} | Exclui o indicado relacionado ao id |

### Winners

| Verbo HTTP| Endereço | Descrição |
| --------- | -------- | --------- |
| GET | /winners | Retorna o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido|

![image](https://user-images.githubusercontent.com/1282312/207860324-9d786190-a825-4199-a25f-4f29b581978b.png)

