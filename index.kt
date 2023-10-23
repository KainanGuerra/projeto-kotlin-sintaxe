import kotlin.random.Random

data class Registro(val nome: String, val sexo: String, val idade: Int, val cargo: String, val salario: Double)
           
val nomesMasculinos = listOf("João", "José", "Pedro", "Luís", "Ricardo")
val nomesFemininos = listOf("Maria", "Ana", "Mariana", "Carla", "Sofia")
val sexos = listOf("Masculino", "Feminino")
val cargos = listOf("Programador", "Designer", "Gerente", "Diretor", "Vendedor")
var registros = registerRandom(nomesMasculinos, nomesFemininos, sexos, cargos).toMutableList()

fun registerRandom(nomesMasculinos: List<String>, nomesFemininos: List<String>, sexos: List<String>, cargos: List<String>): List<Registro> {
    val registrosGerados = mutableListOf<Registro>()
    val sobrenomes = listOf("Silva", "Santos", "Oliveira", "Ferreira", "Pereira", "Cruz", "Almeida", "Lima", "Gomes", "Rodrigues")
    
    repeat(50) {
        val sexo = sexos[Random.nextInt(sexos.size)]

        val nomeAleatorio = if (sexo == "Masculino") {
            nomesMasculinos[Random.nextInt(nomesMasculinos.size)]
        } else {
            nomesFemininos[Random.nextInt(nomesFemininos.size)]
        }
        
        val sobrenomeAleatorio = sobrenomes[Random.nextInt(sobrenomes.size)]
        val nomeCompleto = "$nomeAleatorio $sobrenomeAleatorio"

        val idade = Random.nextInt(18, 66)
        val cargo = cargos[Random.nextInt(cargos.size)]
        val salario = (30..300).random() * 50.0 

        val registro = Registro(nomeCompleto, sexo, idade, cargo, salario)
        registrosGerados.add(registro)
    }
    return registrosGerados
}

fun adicionarRegistro() {
    print("Informe o nome: ")
    val nome = readLine() ?: ""
    
    print("Informe o sexo (Masculino ou Feminino): ")
    val sexo = readLine() ?: ""
    
    print("Informe a idade: ")
    val idade = readLine()?.toIntOrNull() ?: 0
    
    print("Informe o cargo: ")
    val cargo = readLine() ?: ""
    
    print("Informe o salário (múltiplo de 50): ")
    val salario = readLine()?.toDoubleOrNull() ?: 0.0
    
    val registro = Registro(nome, sexo, idade, cargo, salario)
    registros.add(registro)
    println("Registro adicionado com sucesso!")
}

fun mostrarTodos() {
    if (registros.isEmpty()) {
        println("Nenhum registro encontrado.")
    } else {
        registros.forEachIndexed { index, registro ->
            println("========================================================================")
            println("$index: $registro")
        }
    }
}

fun submenuListagemIdade() {
    var submenuIdadeContinuar = true
    while (submenuIdadeContinuar) {
        println("========================================================================")
        println("\nSubmenu - Listagem por Idade:")
        println("1. Mostrar homem mais velho")
        println("2. Mostrar mulher mais velha")
        println("3. Homem mais novo")
        println("4. Mulher mais nova")
        println("5. Média de idade dos homens")
        println("6. Média de idade das mulheres")
        println("7. Média de idade geral")
        println("8. Voltar ao menu anterior")
        println("========================================================================")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                val homemMaisVelho = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                    .maxByOrNull { it.idade }
                if (homemMaisVelho != null) {
                    println("========================================================================")
                    println("Homem mais velho: $homemMaisVelho")
                } else {
                    println("Nenhum homem encontrado.")
                }
            }
            2 -> {
                val mulherMaisVelha = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                    .maxByOrNull { it.idade }
                if (mulherMaisVelha != null) {
                    println("========================================================================")
                    println("Mulher mais velha: $mulherMaisVelha")
                } else {
                    println("Nenhuma mulher encontrada.")
                }
            }
            3 -> {
                val homemMaisNovo = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                    .minByOrNull { it.idade }
                if (homemMaisNovo != null) {
                    println("========================================================================")
                    println("Homem mais novo: $homemMaisNovo")
                } else {
                    println("Nenhum homem encontrado.")
                }
            }
            4 -> {
                val mulherMaisNova = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                    .minByOrNull { it.idade }
                if (mulherMaisNova != null) {
                    println("========================================================================")
                    println("Mulher mais nova: $mulherMaisNova")
                } else {
                    println("Nenhuma mulher encontrada.")
                }
            }
            5 -> {
                val homens = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                val mediaIdadeHomens = homens.map { it.idade }.average()
                println("========================================================================")
                println("Média de idade dos homens: %.2f".format(mediaIdadeHomens))
            }
            6 -> {
                val mulheres = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                val mediaIdadeMulheres = mulheres.map { it.idade }.average()
                println("========================================================================")
                println("Média de idade das mulheres: %.2f".format(mediaIdadeMulheres))
            }
            7 -> {
                val mediaIdadeGeral = registros.map { it.idade }.average()
                println("========================================================================")
                println("Média de idade geral: %.2f".format(mediaIdadeGeral))
            }
            8 -> {
                submenuIdadeContinuar = false
                println("========================================================================")
                println("Retornando ao menu anterior.")
            }
            else -> {
                println("========================================================================")
                println("Opção inválida. Tente novamente.")
            }
        }
    }
}


fun submenuListagemSalarios() {
    var submenuSalariosContinuar = true
    while (submenuSalariosContinuar) {
        println("========================================================================")
        println("Listagem por Salários:")
        println("1. Média de salários dos homens")
        println("2. Média de salários das mulheres")
        println("3. Média de salários geral")
        println("4. Homem que ganha mais")
        println("5. Mulher que ganha mais")
        println("6. Homem que ganha menos")
        println("7. Mulher que ganha menos")
        println("8. Voltar ao menu anterior")
        println("========================================================================")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                val homens = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                val mediaSalariosHomens = homens.map { it.salario }.average()
                println("Média de salários dos homens: %.2f".format(mediaSalariosHomens))
            }
            2 -> {
                val mulheres = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                val mediaSalariosMulheres = mulheres.map { it.salario }.average()
                println("Média de salários das mulheres: %.2f".format(mediaSalariosMulheres))
            }
            3 -> {
                val mediaSalariosGeral = registros.map { it.salario }.average()
                println("Média de salários geral: %.2f".format(mediaSalariosGeral))
            }
            4 -> {
                val homemMaisBemPago = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                    .maxByOrNull { it.salario }
                if (homemMaisBemPago != null) {
                    println("Homem que ganha mais: $homemMaisBemPago")
                } else {
                    println("Nenhum homem encontrado.")
                }
            }
            5 -> {
                val mulherMaisBemPago = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                    .maxByOrNull { it.salario }
                if (mulherMaisBemPago != null) {
                    println("Mulher que ganha mais: $mulherMaisBemPago")
                } else {
                    println("Nenhuma mulher encontrada.")
                }
            }
            6 -> {
                val homemMenosBemPago = registros.filter { it.sexo.equals("Masculino", ignoreCase = true) }
                    .minByOrNull { it.salario }
                if (homemMenosBemPago != null) {
                    println("Homem que ganha menos: $homemMenosBemPago")
                } else {
                    println("Nenhum homem encontrado.")
                }
            }
            7 -> {
                val mulherMenosBemPago = registros.filter { it.sexo.equals("Feminino", ignoreCase = true) }
                    .minByOrNull { it.salario }
                if (mulherMenosBemPago != null) {
                    println("Mulher que ganha menos: $mulherMenosBemPago")
                } else {
                    println("Nenhuma mulher encontrada.")
                }
            }
            8 -> {
                submenuSalariosContinuar = false
                println("Retornando ao menu anterior.")
            }
            else -> {
                println("Opção inválida. Tente novamente.")
            }
        }
    }
}
fun encontraDiretorComMaiorSalario(): Registro? {
    val diretores = registros.filter { it.cargo.equals("Diretor", ignoreCase = true) }
    return diretores.maxByOrNull { it.salario }
}

fun encontraPessoaComMaiorNome(): Registro? {
    return registros.maxByOrNull { it.nome.length }
}

fun listarPessoasComIdadeDezoitoAnos(): List<Registro> {
    return registros.filter { it.idade == 18 }
}

fun listarPessoasComNomeIniciadoComA(): List<Registro> {
    return registros.filter { it.nome.startsWith("A", ignoreCase = true) }
}

fun submenuOutrasBuscas() {
    var submenuOutrasBuscasContinuar = true
    while (submenuOutrasBuscasContinuar) {
        println("\nSubmenu - Outras Buscas:")
        println("1. Encontrar Diretor com Maior Salário")
        println("2. Encontrar Pessoa com Maior Nome")
        println("3. Listar Pessoas com 18 anos")
        println("4. Listar Pessoas com Nome Iniciado com A")
        println("5. Voltar ao menu anterior")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                val diretorComMaiorSalario = encontraDiretorComMaiorSalario()
                if (diretorComMaiorSalario != null) {
                    println("Diretor com Maior Salário: $diretorComMaiorSalario")
                } else {
                    println("Nenhum diretor encontrado.")
                }
            }
            2 -> {
                val pessoaComMaiorNome = encontraPessoaComMaiorNome()
                if (pessoaComMaiorNome != null) {
                    println("Pessoa com Maior Nome: $pessoaComMaiorNome")
                } else {
                    println("Nenhuma pessoa encontrada.")
                }
            }
            3 -> {
                val pessoasComIdadeDezoito = listarPessoasComIdadeDezoitoAnos()
                if (pessoasComIdadeDezoito.isNotEmpty()) {
                    println("Pessoas com 18 anos:")
                    pessoasComIdadeDezoito.forEachIndexed { index, pessoa ->
                        println("$index: $pessoa")
                    }
                } else {
                    println("Nenhuma pessoa com 18 anos encontrada.")
                }
            }
            4 -> {
                val pessoasComNomeIniciadoComA = listarPessoasComNomeIniciadoComA()
                if (pessoasComNomeIniciadoComA.isNotEmpty()) {
                    println("Pessoas com nome iniciado com A:")
                    pessoasComNomeIniciadoComA.forEachIndexed { index, pessoa ->
                        println("$index: $pessoa")
                    }
                } else {
                    println("Nenhuma pessoa com nome iniciado com A encontrada.")
                }
            }
            5 -> {
                submenuOutrasBuscasContinuar = false
                println("Retornando ao menu anterior.")
            }
            else -> {
                println("Opção inválida. Tente novamente.")
            }
        }
    }
}

fun submenuListagem() {
    var submenuContinuar = true
    while (submenuContinuar) {
        println("========================================================================")
        println("Opções de Listagem:")
        println("1. Listas por idade")
        println("2. Listas por salários")
        println("3. Outras Buscas")
        println("4. Voltar ao menu principal")
        println("========================================================================")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> submenuListagemIdade()
            2 -> submenuListagemSalarios()
            3 -> submenuOutrasBuscas()
            4 -> {
                submenuContinuar = false
                println("Retornando ao menu principal.")
            }
            else -> {
                println("Opção inválida. Tente novamente.")
            }
        }
    }
}

fun mainMenu() {
    var continuar = true

    while (continuar) {
        println("========================================================================")
        println("1. Adicionar registro")
        println("2. Mostrar todos")
        println("3. Listagem")
        println("4. Sair")
        println("========================================================================")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> adicionarRegistro()
            2 -> mostrarTodos()
            3 -> submenuListagem()
            4 -> {
                continuar = false
                println("Saindo do programa.")
            }
            else -> {
                println("Opção inválida. Tente novamente.")
            }
        }
    }
}

fun main() {
    mainMenu()
}