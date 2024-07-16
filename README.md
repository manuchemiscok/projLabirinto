**README - Projeto Labirinto**

Este projeto Java foi desenvolvido para resolver labirintos, determinando se existe um caminho válido da entrada para a saída. Ele lê um arquivo de texto que representa o labirinto, valida sua estrutura e encontra um caminho se houver. Este README fornecerá instruções para compilar, executar o programa, bem como informações sobre o formato do arquivo do labirinto.

### **Compilação e Execução**

Para compilar o projeto, certifique-se de ter o Java JDK instalado em seu sistema. No terminal, navegue até o diretório contendo os arquivos do projeto e execute o seguinte comando para compilar:


javac Arquivo.java Coordenada.java Main.java Resolvedor.java Validador.java


Após compilar, execute o programa usando:


java Main


O programa solicitará o nome do arquivo de texto que contém a estrutura do labirinto. Insira o nome do arquivo (por exemplo, `labirinto.txt`) e o programa processará o labirinto, validará sua estrutura e tentará encontrar um caminho da entrada para a saída.

### **Formato do Arquivo do Labirinto**

O arquivo do labirinto deve seguir o seguinte formato:

- A primeira linha contém o número de linhas no labirinto.
- A segunda linha contém o número de caracteres em cada linha do labirinto.
- As linhas subsequentes representam o labirinto, onde `#` indica parede, `E` indica a entrada, `S` indica a saída e espaços em branco indicam caminhos livres.

Exemplo de arquivo de labirinto válido:

```
5
10
##########
#E       #
#  #######
#S       #
##########
```

### Funcionamento do Projeto

1. **Classe `Arquivo`:** Processa o arquivo de labirinto, validando sua estrutura e convertendo-o em uma matriz para processamento.

2. **Classe `Coordenada`:** Representa as coordenadas no labirinto (linha e coluna).

3. **Classe `Main`:** Classe principal que interage com o usuário. Solicita o nome do arquivo do labirinto, processa o labirinto e utiliza o `Resolvedor` para encontrar um caminho da entrada para a saída, se existir.

4. **Classe `Resolvedor`:** Resolve o labirinto usando um algoritmo de busca. Encontra um caminho da entrada para a saída, se possível.

5. **Classe `Validador`:** Valida se o labirinto é válido. Verifica se há uma entrada e uma saída, além de validar os caracteres do labirinto.

### Resultado da Execução

O programa exibirá no terminal se o labirinto é válido e, se for, mostrará o caminho da entrada para a saída no labirinto.

