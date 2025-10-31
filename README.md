# ICC027 - Programação Orientada a Objetos (2025/2)

Este é o repositório oficial do projeto prático da disciplina de **Programação Orientada a Objetos** (ICC027), ministrada pela professora **Anne Oliveira**. O projeto está sendo desenvolvido no segundo período letivo de 2025.

---

## 📅 Cronograma de Entregas

O projeto será dividido em quatro etapas, com as seguintes datas de entrega:

* **Entrega 1:** 12 de setembro de 2025
* **Entrega 2:** 10 de outubro de 2025
* **Entrega 3:** 07 de novembro de 2025
* **Entrega 4:** 12 de dezembro de 2025

---

## 👥 Membros da Equipe

O projeto está sendo desenvolvido pelos seguintes alunos:

* Luiz Gabriel Antunes Sena (**22401905**)
* Letícia Souza de Souza (**22450212**)
* Marcos Paulo Vieira Pedrosa (**22401906**)
* Paulo Victor Fernandes de Melo (**22400653**)

## Problemática

Universidade precisa gerenciar restaurantes universitários para direcionar o recurso financeiro para as empresas que os alunos consumiram.

---

## User Story de Exemplo

* A Universidade quer registrar empresas autorizadas
* A Empresa quer cadastrar seus restaurantes na universidade com autorização da universidade
* A Empresa quer saber a receita mensal
* O Aluno quer comprar tickets de comida
* O Aluno quer usar o ticket para resgatar comida
* O Aluno quer visualizar o cardápio da semana
* O Administrador do Restaurante quer cadastrar a comida da semana

---

## Organização da equipe

* Para cada feature do sistema, um integrante irá ficar responsável por uma camada, seguindo a arquitetura `MVC` com service layer, para mais detalhes, acesse [Introdução ao padrão MVC](https://www.devmedia.com.br/introducao-ao-padrao-mvc/29308).
* A camada `Model` está na pasta `/entities/<model>.java`
* A camada `View` ainda não está sendo usada pois se trata de UI, mas estará na pasta `/pages/<url-da-view>/<nome-view>.gsp`
* A camada `Controller` está na pasta `/features/<feature>/<nome-controller>Controller.java`
* A camada `Service` está na pasta `/features/<feature>/<nome-service>Service.java`
