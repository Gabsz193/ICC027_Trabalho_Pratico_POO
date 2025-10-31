# Estrutura MVC

Caso você tenha ficado perdido em relação à estrutura do projeto, não se preocupe, vou detalhar os aspectos mais importantes quando se trata de padrão de projeto.

## O que é MVC?

MVC é uma organização comum para aplicação que separa as principais camadas de uma aplicação, seja web ou qualquer outra, que são:

* **(M)odel**: Camada do modelo, é aqui que vão ser declarados as entidades do sistema e quais atributos e regras de negócio elas possuem. É também onde vai ser feita a persistência (por meio de repositórios) e armazenamento de estado dos diversos atores do sistema.
* **(V)iew**: Camada de apresentação, é aqui que vai ficar tudo o que aparece para usuário em relação a interface, como botões, páginas, forms e qualquer elemento gráfico, também seria onde é definido as rotas de API.
* **(C)ontroller**: Camada de controle, é aqui que vai ficar a lógica das funcionalidades, que vão pegar requisições da **View** e fazer alterações ou buscas nos **Models** correspondentes.

Há também uma outra camada não citada, mas é quase que automática nessa estrutura que são os **Services**, que fazem a ponte do **Controller** com o **Model**.

Esta arquitetura garante a independência de cada componente, fazendo com que a implementação de um não afete no outro.

Um exemplo:

Vamos supor que temos uma funcionalidade do nosso sistema `Realizar controle de acesso do usuário`. Os componentes que poderíamos ter são:

* `Usuario.java`, `Perfil.java` - Model
* `TelaLogin.gsp`, `FormLogin.gsp`, `LogoutButton.gsp`, `TelaEsqueceuSenha.gsp`, `FormEsqueceuSenha`, `TelaTrocaPerfil.gsp` - View
* `AuthController.java`, `PerfilController` - Controller
* `AuthenticationService.java`, `PerfilService.java` - Service

O fluxo de login do nosso sistema seria:

1. O usuário preenche os campos na **view** `FormLogin.gsp`
2. O form realiza uma requisição para uma rota do **controller** `AuthController.java` (literalmente uma função autenticaUsuario).
3. Esse controller chama o **serviço** `AuthenticationService.java` correspondente:
   3.1 se for com **Cpf e Senha** (autenticaCpfESenha),
   3.2 se for via **Conta do Google** (autenticaGoogle).
4. Dentro **service** implementa a lógica de como autenticar o usuário, por exemplo, busca o usuário no **model** `Usuario.java` pelo cpf e compara as senhas.
5. O model, por sua vez, faz a busca via **repositório** (importante notar que o SpringBoot já implementa as funções padrões do repositório)
6. (Adicional) Pode ser feito também uma outra lógica dentro do controller que chama o controller `PerfilController.java` para outras validações.
7. Por fim, o resultado é retornado para a **view** `TelaLogin.gsp`.

Perceba que cada camada tem sua funções específica e não depende dos detalhes da outra. A view não precisa saber qual autenticação vai ser feita, ela só chama o controller da autenticação. Nenhuma lógica de como é feito a autenticação por cpf fica no controller, somente a chamada para o serviço. Isso trás diversos benefícios como:

* **Segurança**: Quanto mais distanciarmos a lógica de implementação do cliente, melhor. Não só por causa de ataques, mas também por questões de abstração na parte do frontend.
* **Modularização**: Quanto mais definirmos a função de cada camada, melhor noção temos onde achar determinadas chamadas no sistema.
* **Reutilização**: Qualquer lugar que possuir uma lógica parecida, pode se comunicar: Se for do cliente, via controller; se for do servidor, via service/controller.
* **Manutenção**: Se por algum acaso a lógica de alguma função mudar, teremos que apenas trocar seu escopo, sem se preocupar com outras partes do sistema.

Por isso, a arquitetura `MVC` é muito bem consolidada no mercado.

## Tá, mas agora, como faço para implementar essa arquitetura?

Não há uma maneira exata de implementá-la. Pelo menos pra mim, eu sigo esses princípios:

* O único meio de comunicação com o banco é via **service**, se por algum acaso você faz uma chamada do repositório na **view**, tenha certeza que isto é um erro gravíssimo. Até existem algumas chamadas que podem ser feitas pelo **controller** por razões de conveniência, porém o certo mesmo é que tudo seja bem encapsulado para que qualquer detalhes de implementação seja feito apenas pelo serviço.
* Utilize o máximo os conceitos de **Herança** e **Interface**! Esses dois são conceitos muito foderosos quando se trata de:
  
  1. Esse componente é uma extensão desse outro serviço (**Herança**)
  2. Esses componentes têm um comportamento semelhante (**Interface**).
  
  Ambos forçam os componentes a terem uma certa padronização e moldes, o que ajuda muito caso você tenha que generalizar uma função para não ter que repetí-la em todos os lugares (dê uma olhada no princípio DRY).
* Os **controllers** são ótimos para você visualizar quais são os subprocessos de uma funcionalidade. Por exemplo:
    No controller de autenticação, há como se autenticar, deslogar da conta, trocar de senha, fazer validação duplo fator, etc.
* Em relação a pacotes e pastas, eu estou tentando mater o padrão do design pattern [FSD](https://feature-sliced.design/). É um padrão bem intuitivo que podemos usar para organizar nossos projetos de acordo com conceitos fora do MVC, mas que pode ser incoropado.
  
    O `/features` são todas as ações que podem ser feitas pelos usuário, como `realizar-autenticacao`, `trocar-perfil`, `consumir-saldo`, `comprar-ticket`. Aqui têm todos os componentes de service, controller e view, todos relacionados a essas ações.
    Em `/entities` seriam os models, que determinam os aspectos das entidades e seus repositórios.
    Os outros fogem do tema de `MVC`