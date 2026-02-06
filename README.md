# API GESTÃO DE VENDAS <br>
📱 **Sobre o Projeto**<br>
Projeto criado para uma simulação de um sistema de gestão de vendas. O projeto inclui topicos como Produtos, clientes, vendas e entre alguns desses objetos um relacionamento many to many, many to one etc.<br>

🎥 **Demonstração do Projeto**<br>
O projeto segue o padrão MVC(MODEL VIEW CONTROLLER) e também DTO para controle de campos para response e request
<img width="1902" height="1040" alt="projetomvc" src="https://github.com/user-attachments/assets/44291341-d002-417e-a61b-69dfe6399d00" /><br>
<img width="1919" height="1012" alt="dto" src="https://github.com/user-attachments/assets/6807d3bb-7ee5-409f-9d7a-0de6065aa7dc" /><br>
Gestão de erros para erros não tratados(evita cenários de erro 500):
<img width="1916" height="1033" alt="erros" src="https://github.com/user-attachments/assets/5a2b1810-84af-4fbd-92be-ef33374e6fb8" /><br>
Utilização de JPA para acesso ao banco ou personalização de algumas queries especificas:
<img width="1919" height="1036" alt="query" src="https://github.com/user-attachments/assets/7ccbae3d-f9a6-473f-9b84-2ae34c282f37" /><br>
Aplicação:
<img width="1919" height="1021" alt="aplicacao" src="https://github.com/user-attachments/assets/b0524f9a-ef5d-4a79-8f89-466f18b69bae" /><br>
Entre outros termos tecnicos<br>

📦 **Como Instalar**<br>
Clone o repositório: 
git clone https://github.com/Jhonzz/APIRestESpringBootVendas.git<br>
Instale as dependências pelo arquivo maven do projeto: Acesse o arquivo pom.xml, abra a aba do maven -> lifecycle -> clean -> install<br>

📦 **COMO INICIAR APLICAÇÃO**<br>
Basta escrever o comando docker-compose up no terminal para subir a imagem do banco de dados<br>
Rodar a aplicação na classe GestaoVendasApplication(O flyway ficará encarregado de criar as tabelas e incluir dados básicos para os testes das APIs)<br>
 Todo o funcionamento das APIs estarão no swagger, sinta-se a vontade para fazer testes(irá ficar disponivel ao rodar a aplicação em sua máquina): http://localhost:8080/swagger-ui/index.html<br><br>
 
**Demonstração funcional do projeto**<br>
DTO fazendo efeito:<br>
<img width="1547" height="940" alt="post" src="https://github.com/user-attachments/assets/06fc4ff3-553b-4e3e-b1a5-7102a3338bf3" /><br>

🚀 **Tecnologias Utilizadas**<br>
Java<br>
Spring boot<br>
Docker<br>
JPA<br>
Flyway<br>
maven<br>
mySQL<br>
springdoc<br>
Irei incluir testes unitarios no futuro<br>
