package br.com.abelcrvg.curiosidade

import android.os.Bundle
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF19213D)
private val Green = Color(0xFF693EE8)
private val Cream = Color(0xFFF6F4FF)
private val Muted = Color(0xFF6D7185)
private val Paper = Color(0xFFFFFFFF)
private val Gold = Color(0xFFFFB84A)

private data class Topic(val name: String, val emoji: String, val description: String)
private val topics = listOf(
    Topic("Astronomia", "🌌", "Planetas, estrelas, galáxias e o Universo"),
    Topic("Ciência", "🔬", "Descobertas, experiências e fenômenos naturais"),
    Topic("Animais e natureza", "🐋", "Vida selvagem, ecossistemas e comportamento animal"),
    Topic("História", "🏛️", "Civilizações, acontecimentos e personagens"),
    Topic("Tecnologia", "🤖", "Invenções, inteligência artificial e futuro"),
    Topic("Psicologia", "🧠", "Mente, memória, emoções e comportamento"),
    Topic("Corpo humano", "🫀", "Anatomia, sentidos e funcionamento do corpo"),
    Topic("Geografia e culturas", "🌍", "Países, povos, lugares e costumes"),
    Topic("Física", "⚛️", "Energia, matéria, espaço e tempo"),
    Topic("Oceanos", "🌊", "Mares, profundezas e vida marinha"),
    Topic("Dinossauros e fósseis", "🦖", "Pré-história, evolução e paleontologia"),
    Topic("Meio ambiente", "🌱", "Clima, conservação e sustentabilidade"),
    Topic("Matemática", "➗", "Números, lógica e padrões"),
    Topic("Filosofia", "💭", "Ideias, ética e grandes perguntas"),
    Topic("Arqueologia", "🏺", "Vestígios, descobertas e sociedades antigas"),
    Topic("Engenharia", "⚙️", "Como construímos, projetamos e resolvemos problemas")
)

private data class ArticleSection(val heading: String, val paragraphs: List<String>)
private data class Article(
    val id: Int,
    val title: String,
    val category: String,
    val summary: String,
    val minutes: Int,
    val emoji: String,
    val intro: String,
    val sections: List<ArticleSection>,
    val keyTakeaways: List<String>,
    val source: String
)

private val articles = listOf(
    Article(
        1, "Por que o polvo tem três corações?", "Animais e natureza",
        "O polvo combina uma circulação incomum com sentidos sofisticados e uma inteligência surpreendente.",
        7, "🐙",
        "Os polvos pertencem ao grupo dos cefalópodes, que também inclui lulas e sépias. Seu corpo evoluiu para a vida marinha de uma maneira muito diferente da nossa: três corações, sangue azulado e braços capazes de explorar o ambiente com grande autonomia.",
        listOf(
            ArticleSection("Três corações, três tarefas", listOf(
                "Dois corações chamados branquiais bombeiam o sangue em direção às brânquias. É nas brânquias que o oxigênio dissolvido na água passa para o sistema circulatório e parte do dióxido de carbono é eliminada.",
                "O terceiro coração, conhecido como coração sistêmico, envia o sangue oxigenado para os órgãos e tecidos. Essa divisão permite que o sangue passe primeiro pela superfície respiratória e depois seja distribuído pelo corpo.",
                "A circulação dos cefalópodes é diferente da circulação fechada dos mamíferos em vários detalhes. O sistema precisa funcionar em um animal que se move por espaços estreitos, muda de cor e textura e pode realizar movimentos rápidos para capturar presas ou escapar de predadores."
            )),
            ArticleSection("Por que o sangue é azul?", listOf(
                "O sangue do polvo utiliza hemocianina para transportar oxigênio. Essa proteína contém cobre, ao contrário da hemoglobina humana, que utiliza ferro. Quando a hemocianina se liga ao oxigênio, sua química faz o sangue adquirir uma tonalidade azulada.",
                "A hemocianina funciona bem nas condições ambientais em que muitos cefalópodes vivem, incluindo águas frias. Isso não significa que seja universalmente superior à hemoglobina: cada sistema tem vantagens e limitações relacionadas ao ambiente e à fisiologia do animal.",
                "O sangue azul não é exclusivo dos polvos. Outros artrópodes e moluscos também utilizam hemocianina, embora a aparência e a concentração do pigmento variem entre espécies."
            )),
            ArticleSection("Nadar pode ser mais cansativo do que rastejar", listOf(
                "Quando um polvo nada por propulsão a jato, expulsa água pela cavidade do manto e pelo sifão. É uma forma rápida de deslocamento, útil em fugas, mas pode exigir bastante energia.",
                "Durante a natação, o coração sistêmico pode reduzir muito sua atividade. Esse é um dos fatores que ajudam a explicar por que muitos polvos preferem rastejar pelo fundo quando não precisam se deslocar rapidamente.",
                "O comportamento varia entre espécies e situações. Um polvo que procura alimento, se esconde em uma fenda ou foge de um predador enfrenta exigências físicas diferentes."
            )),
            ArticleSection("Uma inteligência distribuída pelos braços", listOf(
                "Os braços dos polvos têm grande quantidade de neurônios e receptores sensoriais. Eles podem tocar, explorar, agarrar e manipular objetos, enquanto o sistema nervoso central coordena o comportamento geral.",
                "Essa organização não significa que cada braço tenha uma mente independente. Significa que parte do processamento sensorial e motor acontece localmente, permitindo respostas rápidas e movimentos complexos.",
                "Experimentos e observações documentaram aprendizagem, exploração de objetos, resolução de problemas e comportamentos de escape. É importante, porém, não transformar resultados específicos em afirmações exageradas sobre consciência: a inteligência animal é estudada com métodos e limites próprios."
            )),
            ArticleSection("Camuflagem e sobrevivência", listOf(
                "Muitas espécies conseguem alterar a cor e a aparência da pele usando estruturas especializadas, como cromatóforos, além de modificar a textura. Essas mudanças ajudam a se esconder, confundir predadores e, em alguns contextos, comunicar sinais.",
                "A camuflagem depende do ambiente e da espécie. Um padrão eficaz sobre rochas pode ser inadequado sobre areia. Por isso, a capacidade de ajustar rapidamente a aparência é uma vantagem importante para um animal sem concha externa rígida."
            ))
        ),
        listOf("Dois corações levam sangue às brânquias; o terceiro distribui sangue pelo corpo.", "A hemocianina, que contém cobre, contribui para a cor azulada do sangue.", "Os braços têm muitos neurônios e participam ativamente da exploração e do movimento.", "A natação por propulsão a jato pode ser energeticamente exigente."),
        "Fontes para aprofundamento: Smithsonian Ocean; Monterey Bay Aquarium; literatura científica sobre fisiologia e comportamento dos cefalópodes."
    ),
    Article(
        2, "Estamos olhando para o passado quando observamos as estrelas?", "Astronomia",
        "A luz tem velocidade finita: observar objetos distantes é receber informações que partiram deles há muito tempo.",
        9, "🌌",
        "Olhar para o céu noturno é olhar para diferentes momentos da história do Universo. A luz não chega instantaneamente: mesmo viajando a cerca de 300 mil quilômetros por segundo no vácuo, ela precisa de tempo para cruzar distâncias astronômicas.",
        listOf(
            ArticleSection("A luz também precisa de tempo", listOf(
                "Na vida cotidiana, a luz parece instantânea porque as distâncias são pequenas em comparação com sua velocidade. Em escala cósmica, essa impressão deixa de funcionar. A luz percorre aproximadamente 300 mil quilômetros a cada segundo, mas o espaço entre os astros é imenso.",
                "A unidade ano-luz mede distância, não tempo: é a distância que a luz percorre em um ano. Ela equivale a cerca de 9,46 trilhões de quilômetros. A unidade facilita falar de estrelas e galáxias sem escrever números enormes.",
                "Quando dizemos que uma estrela está a cem anos-luz, queremos dizer que a luz que chega até nós levou aproximadamente cem anos para atravessar o espaço entre ela e a Terra."
            )),
            ArticleSection("O exemplo mais próximo: o Sol", listOf(
                "A luz do Sol leva cerca de oito minutos e vinte segundos para chegar à Terra. Portanto, a luz solar que vemos agora saiu da superfície do Sol pouco mais de oito minutos atrás.",
                "Se o Sol mudasse de aparência de maneira instantânea, nós só observaríamos essa mudança depois que a informação luminosa chegasse até aqui. Isso não significa que o Sol esteja atrasado ou que a observação esteja errada: é uma consequência normal da velocidade finita da luz.",
                "A luz refletida pela Lua chega à Terra em pouco mais de um segundo. Já a comunicação com sondas espaciais pode levar minutos ou horas, dependendo da distância, porque os sinais de rádio também viajam aproximadamente à velocidade da luz."
            )),
            ArticleSection("Estrelas, galáxias e tempo profundo", listOf(
                "A luz de uma estrela a cem anos-luz mostra a estrela como era quando aquela luz começou a viagem. Durante esse intervalo, a estrela pode ter mudado. Em geral, não podemos saber o estado atual de um objeto distante apenas observando a luz antiga que acabou de chegar.",
                "Quando telescópios observam galáxias a bilhões de anos-luz, recebem luz emitida em fases muito antigas da história cósmica. Isso permite estudar como as primeiras galáxias se formaram e como as estruturas do Universo evoluíram.",
                "A expansão do Universo torna a interpretação das distâncias cosmológicas mais complexa: em escalas muito grandes, a distância atual de uma galáxia não é simplesmente igual à distância que a luz percorreu. Astrônomos usam modelos e medidas para relacionar o desvio para o vermelho, o tempo de viagem e a expansão do espaço."
            )),
            ArticleSection("Ver o passado não é o mesmo que prever o futuro", listOf(
                "A astronomia fornece observações de eventos passados, não uma fotografia em tempo real de todo o cosmos. Cada objeto observado corresponde a uma época determinada pelo tempo de viagem da luz.",
                "Isso é uma ferramenta científica poderosa. Ao comparar objetos em diferentes distâncias, pesquisadores conseguem estudar fases diferentes da evolução estelar e galáctica. É como ter registros de vários capítulos da história cósmica disponíveis ao mesmo tempo.",
                "Há limites: a luz pode ser absorvida, desviada ou enfraquecida, e algumas regiões são difíceis de observar diretamente. Por isso, astrônomos combinam telescópios, diferentes comprimentos de onda, modelos físicos e medições independentes."
            ))
        ),
        listOf("A luz do Sol leva cerca de 8 minutos e 20 segundos até a Terra.", "Ano-luz é uma unidade de distância, não de duração.", "Objetos muito distantes são vistos como eram quando a luz observada foi emitida.", "A expansão do Universo precisa ser considerada ao interpretar distâncias cosmológicas."),
        "Fontes para aprofundamento: NASA; European Space Agency (ESA); materiais educativos sobre velocidade da luz, anos-luz e expansão do Universo."
    ),
    Article(
        3, "Como os romanos transportavam água sem bombas elétricas?", "História",
        "Aquedutos, gravidade e manutenção permitiram abastecer cidades muito antes da engenharia moderna.",
        8, "🏛️",
        "A engenharia hidráulica romana não dependia de eletricidade para conduzir água. Seus aquedutos aproveitavam diferenças de altura, canais cuidadosamente planejados e uma infraestrutura que precisava de inspeção e manutenção constantes.",
        listOf(
            ArticleSection("A força da gravidade", listOf(
                "A maior parte dos aquedutos transportava água de uma fonte situada em altitude superior à cidade abastecida. Ao manter uma inclinação gradual, os engenheiros permitiam que a gravidade conduzisse a água ao longo do percurso.",
                "O canal precisava ter uma queda adequada. Uma inclinação excessiva podia aumentar a velocidade e causar danos; uma inclinação insuficiente podia prejudicar o fluxo. A escolha do trajeto dependia do relevo, da qualidade da fonte e das necessidades da população.",
                "A engenharia não consistia apenas em construir arcos monumentais. Era necessário reconhecer nascentes, medir alturas, escavar túneis, construir canais e proteger a água de contaminações e perdas."
            )),
            ArticleSection("Os aquedutos não eram todos pontes gigantes", listOf(
                "A imagem popular dos aquedutos é a de grandes fileiras de arcos de pedra. Na realidade, muitos trechos percorriam o solo, passavam por túneis ou seguiam canais pouco visíveis. Os arcos eram úteis quando era preciso atravessar vales ou manter o canal em determinada altura.",
                "Construir uma estrutura elevada exigia materiais, mão de obra e planejamento. Por isso, os engenheiros preferiam adaptar o caminho ao terreno sempre que fosse possível, em vez de elevar todo o percurso.",
                "A água podia ser conduzida até reservatórios e distribuída a fontes públicas, termas e outros pontos. O acesso variava conforme a cidade, a época, a posição social e as regras locais."
            )),
            ArticleSection("Planejamento, manutenção e administração", listOf(
                "Um aqueduto não funcionava para sempre sem cuidados. Sedimentos podiam se acumular, paredes precisavam de reparos e vazamentos reduziam a quantidade de água entregue. A manutenção era parte essencial da infraestrutura.",
                "A distribuição também dependia de decisões administrativas: quem recebia água, para quais usos e em quais quantidades eram questões práticas e políticas. O abastecimento público estava ligado à saúde, à vida urbana e à demonstração de poder das autoridades.",
                "A tecnologia romana reuniu conhecimentos anteriores e soluções locais. Não se deve imaginar um único sistema idêntico em todo o Império: as estruturas variavam conforme a geografia, os recursos e o tamanho das cidades."
            )),
            ArticleSection("O que podemos aprender com essa engenharia?", listOf(
                "O princípio básico continua presente em sistemas hidráulicos modernos: a diferença de pressão e de altura pode mover fluidos sem uma bomba elétrica em cada trecho. Hoje, redes de água também combinam gravidade, bombas, reservatórios, válvulas e controle de qualidade.",
                "Os aquedutos demonstram que uma infraestrutura durável depende tanto do projeto quanto da operação. Materiais resistentes não substituem inspeção, limpeza, gestão e planejamento de longo prazo."
            ))
        ),
        listOf("A gravidade era o principal motor de muitos aquedutos.", "Grande parte dos canais era subterrânea ou seguia o terreno; nem tudo era uma ponte de arcos.", "Manutenção e administração eram tão importantes quanto a construção.", "O sistema variava entre cidades e épocas."),
        "Fontes para aprofundamento: Encyclopaedia Britannica; materiais arqueológicos sobre aquedutos romanos e abastecimento urbano na Antiguidade."
    ),
    Article(
        4, "Por que sentimos que já vivemos uma situação?", "Psicologia",
        "O déjà-vu é uma sensação de familiaridade que pode ocorrer mesmo quando não recuperamos uma lembrança correspondente.",
        7, "🧠",
        "Déjà-vu é uma expressão francesa que significa aproximadamente “já visto”. A pessoa reconhece uma situação como familiar, embora saiba que ela provavelmente está acontecendo pela primeira vez. A experiência é comum, mas seus mecanismos ainda são investigados.",
        listOf(
            ArticleSection("Familiaridade não é a mesma coisa que lembrança", listOf(
                "A memória não funciona como uma gravação de vídeo perfeita. O cérebro combina informações sensoriais, conhecimentos anteriores, contexto e pistas para reconhecer lugares, pessoas e acontecimentos.",
                "Uma distinção importante na pesquisa da memória é entre sentir familiaridade e recuperar detalhes específicos de uma experiência. Podemos achar um rosto conhecido sem conseguir lembrar onde o vimos; o déjà-vu pode envolver uma sensação de familiaridade sem uma lembrança que a explique.",
                "Uma hipótese científica propõe que o fenômeno aconteça quando processos de familiaridade e de recuperação de memória não coincidem perfeitamente. Essa hipótese é útil, mas não explica necessariamente todos os episódios."
            )),
            ArticleSection("O contexto pode contribuir", listOf(
                "Uma cena atual pode compartilhar a organização espacial ou alguns detalhes com uma experiência anterior, mesmo que não percebamos conscientemente a semelhança. A sensação resultante pode ser de que já estivemos naquele lugar.",
                "Cansaço, estresse e atenção dividida são mencionados em relatos de algumas pessoas, mas isso não significa que sejam causas universais ou comprovadas em todos os casos. Experiências subjetivas são difíceis de reproduzir em laboratório.",
                "Pesquisadores investigam o déjà-vu com relatos, tarefas experimentais e estudos sobre memória. Como o episódio costuma ser breve e inesperado, medir o fenômeno de maneira controlada é um desafio."
            )),
            ArticleSection("Quando é apenas uma experiência ocasional?", listOf(
                "Um episódio breve e isolado costuma ser uma experiência comum. Por si só, o déjà-vu não permite concluir que uma pessoa tenha um transtorno, nem comprova explicações sobrenaturais ou a existência de lembranças de vidas passadas.",
                "O contexto clínico é importante. Episódios muito frequentes ou acompanhados de confusão, alteração da consciência, movimentos involuntários ou outros sintomas neurológicos merecem avaliação profissional, pois algumas condições neurológicas podem produzir sensações de familiaridade intensa."
            )),
            ArticleSection("O que a ciência ainda não sabe", listOf(
                "Não existe uma explicação única que dê conta de todos os relatos. O termo é usado para experiências que podem não ser idênticas entre si, e diferentes mecanismos podem contribuir para episódios diferentes.",
                "A melhor atitude científica é distinguir observação de interpretação. Sentir que uma situação é familiar é um relato real da experiência subjetiva; afirmar exatamente por que aconteceu exige evidências adicionais."
            ))
        ),
        listOf("Déjà-vu descreve uma sensação de familiaridade, não uma prova de que o evento já ocorreu.", "Memória e familiaridade são processos relacionados, mas não idênticos.", "As explicações científicas ainda estão em estudo.", "Sintomas neurológicos associados ou episódios frequentes justificam avaliação profissional."),
        "Fontes para aprofundamento: Cleveland Clinic; revisões científicas sobre memória, familiaridade e déjà-vu."
    ),
    Article(
        5, "Por que o céu é azul durante o dia?", "Ciência",
        "A atmosfera espalha diferentes cores da luz solar de maneiras diferentes, fazendo o azul chegar aos nossos olhos de muitas direções.",
        6, "🌤️",
        "A luz solar parece branca, mas contém uma faixa de cores. Quando atravessa a atmosfera terrestre, interage com moléculas de gases e pequenas partículas. Esse processo altera a direção de parte da luz e explica por que o céu tem aparência azul em condições comuns.",
        listOf(
            ArticleSection("A luz branca contém muitas cores", listOf(
                "A luz visível inclui diferentes comprimentos de onda. Em termos simplificados, a luz violeta e azul tem comprimentos de onda menores, enquanto a luz laranja e vermelha tem comprimentos de onda maiores.",
                "Um prisma pode separar a luz branca em cores porque cada comprimento de onda se comporta de maneira um pouco diferente ao atravessar o material. A luz do Sol não é uma única cor: é uma mistura de diferentes comprimentos de onda."
            )),
            ArticleSection("O espalhamento de Rayleigh", listOf(
                "As moléculas do ar são muito menores do que os comprimentos de onda da luz visível. Elas espalham a luz de comprimentos de onda mais curtos com mais intensidade do que a luz de comprimentos de onda longos. Esse processo é conhecido como espalhamento de Rayleigh.",
                "Como a luz azul é espalhada com maior intensidade do que a vermelha, ela chega aos nossos olhos a partir de muitas direções do céu. É por isso que não vemos apenas um feixe brilhante vindo diretamente do Sol: vemos a luz espalhada pela atmosfera ao redor.",
                "A explicação não é simplesmente que o ar “é azul”. O efeito surge da interação entre a luz que chega, as moléculas atmosféricas e a maneira como nossos olhos respondem às cores."
            )),
            ArticleSection("Por que o céu não parece violeta?", listOf(
                "A luz violeta também sofre espalhamento intenso. Porém, o espectro da luz solar, a absorção de parte da radiação na atmosfera e a sensibilidade do sistema visual humano contribuem para que a percepção geral do céu seja predominantemente azul.",
                "A percepção de cor não depende apenas da física da luz. Ela também depende dos receptores da retina e do processamento feito pelo sistema visual. Por isso, a cor percebida resulta de um encontro entre fenômeno físico e biologia."
            )),
            ArticleSection("Por que o pôr do sol costuma ser alaranjado?", listOf(
                "Quando o Sol está próximo do horizonte, sua luz atravessa uma camada maior de atmosfera antes de chegar diretamente aos nossos olhos. Ao longo desse caminho, mais luz azul é espalhada para outras direções.",
                "A luz direta que resta tende a apresentar maior proporção de tons amarelos, laranjas e vermelhos. Poeira, fumaça, gotículas e outras partículas podem modificar bastante as cores, tornando alguns pores do sol mais intensos do que outros.",
                "Nuvens podem refletir a luz avermelhada do Sol baixo no horizonte. As cores exatas dependem das condições atmosféricas, da quantidade de partículas e da geometria entre o Sol, as nuvens e o observador."
            ))
        ),
        listOf("A luz solar é uma mistura de comprimentos de onda.", "Moléculas do ar espalham comprimentos de onda curtos com mais intensidade.", "A percepção azul também depende da resposta do olho humano.", "O pôr do sol muda de cor porque a luz atravessa uma camada maior da atmosfera."),
        "Fontes para aprofundamento: NASA Space Place; materiais educativos de física atmosférica e óptica."
    ),
    Article(
        6, "Por que o cérebro precisa dormir?", "Corpo humano",
        "O sono participa da memória, da atenção, da regulação emocional e de muitos processos de manutenção do organismo.",
        8, "🌙",
        "Dormir não é simplesmente desligar o cérebro. Durante o sono, a atividade cerebral muda de forma organizada e passa por diferentes estágios. O descanso adequado ajuda a manter o funcionamento físico e mental, embora a necessidade individual varie conforme a idade e outros fatores.",
        listOf(
            ArticleSection("O sono acontece em ciclos", listOf(
                "Ao longo da noite, passamos por estágios de sono não REM e sono REM. Esses estágios apresentam padrões diferentes de atividade cerebral, movimento dos olhos, tônus muscular e resposta a estímulos.",
                "Os ciclos se repetem, mas não são cópias exatas uns dos outros. A proporção de cada estágio pode mudar durante a noite, e fatores como idade, rotina, ambiente, medicamentos e condições de saúde influenciam o sono."
            )),
            ArticleSection("Memória e aprendizagem", listOf(
                "O sono está relacionado à consolidação de diferentes tipos de memória. Isso significa que o cérebro pode reorganizar e estabilizar informações aprendidas durante o dia, embora os mecanismos variem conforme o tipo de memória e o estágio do sono.",
                "Dormir pouco pode prejudicar a atenção, a velocidade de resposta, o raciocínio e a capacidade de aprender novas informações. Estudar por mais horas sacrificando repetidamente o sono pode, portanto, comprometer parte do benefício do estudo."
            )),
            ArticleSection("Emoções e tomada de decisões", listOf(
                "A privação de sono pode tornar mais difícil regular emoções, lidar com frustrações e avaliar riscos. Em tarefas complexas, a pessoa também pode ter mais dificuldade para manter a concentração e perceber os próprios erros.",
                "Os efeitos variam de pessoa para pessoa e dependem da duração e da qualidade do sono. Uma noite ruim não produz necessariamente o mesmo efeito em todos, mas a falta de sono recorrente pode afetar a saúde e a rotina."
            )),
            ArticleSection("O relógio biológico e a luz", listOf(
                "O corpo usa um sistema circadiano para organizar processos ao longo de aproximadamente 24 horas. A luz é uma das pistas ambientais mais importantes para sincronizar esse relógio, junto com horários de atividade, refeições e outros sinais.",
                "Horários relativamente regulares e um ambiente adequado podem ajudar a manter o ciclo sono-vigília. Trabalhos noturnos, mudanças frequentes de horário e exposição à luz em momentos biologicamente inadequados podem dificultar o descanso."
            )),
            ArticleSection("Quanto sono é suficiente?", listOf(
                "As necessidades variam conforme a idade e as características individuais. Para a maioria dos adultos, recomendações de saúde pública indicam pelo menos sete horas de sono por noite, mas duração, regularidade e qualidade também importam.",
                "Ronco intenso, pausas respiratórias observadas, sonolência excessiva durante o dia ou dificuldade persistente para dormir são motivos para buscar orientação profissional. Este artigo é informativo e não substitui uma avaliação individual."
            ))
        ),
        listOf("O sono tem estágios diferentes que se alternam durante a noite.", "Dormir ajuda a aprendizagem e está relacionado à consolidação da memória.", "A falta de sono pode afetar atenção e regulação emocional.", "Adultos geralmente precisam de pelo menos sete horas, mas necessidades individuais variam."),
        "Fontes para aprofundamento: Centers for Disease Control and Prevention (CDC); National Institutes of Health (NIH); materiais sobre sono e ritmos circadianos."
    ),
    Article(
        7, "Como as abelhas indicam onde há alimento?", "Animais e natureza",
        "A dança das abelhas é uma forma de comunicação que transmite informações sobre a localização de recursos.",
        8, "🐝",
        "Uma colmeia depende de milhares de decisões coordenadas. Abelhas campeiras deixam a colônia em busca de néctar, pólen, água e resinas; quando encontram recursos, precisam transmitir informações úteis às outras trabalhadoras. Em determinadas espécies, a dança é uma das ferramentas dessa comunicação.",
        listOf(
            ArticleSection("A dança em forma de oito", listOf(
                "Em abelhas melíferas, a dança do requebrado pode incluir uma sequência em que a abelha se move em linha, balança o abdômen e retorna por uma curva, formando um padrão semelhante a um oito. A duração e a orientação do trecho central ajudam a codificar informações sobre o recurso encontrado.",
                "A dança não é uma linguagem humana em miniatura. É um sistema de sinais evoluído, com regras próprias, que outras abelhas conseguem interpretar junto de cheiros, sons, contato físico e informações do ambiente.",
                "Nem todas as abelhas sem ferrão usam exatamente o mesmo sistema de comunicação. A diversidade de espécies significa que não devemos generalizar um comportamento observado em abelhas melíferas para todos os grupos."
            )),
            ArticleSection("Direção e distância", listOf(
                "Na dança de abelhas melíferas, a orientação do trecho de requebrado em relação à vertical do favo se relaciona com o ângulo entre a fonte de alimento e a direção do Sol. A abelha que observa a dança pode usar essa informação como uma indicação de rumo.",
                "A duração e outras características do movimento podem indicar aproximadamente a distância. A precisão depende da espécie, da experiência da abelha, das condições do ambiente e da qualidade do recurso; não se trata de coordenadas exatas como as de um mapa digital.",
                "Como a posição aparente do Sol muda ao longo do dia, as abelhas também precisam integrar referências temporais e visuais. A comunicação funciona dentro de um conjunto de capacidades sensoriais e comportamentais."
            )),
            ArticleSection("Cheiro, memória e escolha coletiva", listOf(
                "Além do movimento, o odor associado às flores pode ajudar as campeiras a reconhecer o tipo de recurso procurado. A informação visual da dança e as pistas químicas se complementam, reduzindo a chance de a colônia depender de um único sinal.",
                "As abelhas não obedecem mecanicamente a uma única dançarina. Várias exploradoras podem encontrar recursos diferentes, e as condições da paisagem mudam. A atividade coletiva emerge da interação entre muitas decisões individuais e o ambiente.",
                "A dança também é um exemplo importante para a ciência do comportamento: pesquisadores conseguem alterar experimentalmente pistas e observar como outras abelhas respondem, testando hipóteses sobre a forma como a informação é codificada."
            )),
            ArticleSection("Por que essa comunicação importa?", listOf(
                "Encontrar alimento pode ser caro em energia e tempo. Uma indicação confiável permite que outras campeiras visitem áreas promissoras sem repetir toda a busca às cegas.",
                "A eficiência depende do contexto ecológico. A disponibilidade de flores, a distância até os recursos, o clima e a competição com outros polinizadores influenciam o valor de uma descoberta.",
                "A dança mostra como a comunicação animal pode ser sofisticada sem usar palavras. Ela também reforça a importância de preservar habitats diversos, porque a colônia precisa de recursos florais ao longo do tempo."
            ))
        ),
        listOf("A dança do requebrado pode transmitir pistas sobre direção e distância.", "Cheiros e outros sinais complementam os movimentos.", "Nem todas as espécies de abelhas usam o mesmo sistema.", "A comunicação ajuda a colônia a explorar recursos de forma eficiente."),
        "Fontes para aprofundamento: Smithsonian National Zoo; materiais da University of California sobre comunicação de abelhas; revisões científicas sobre dança de recrutamento em Apis mellifera."
    ),
    Article(
        8, "O que realmente acontece perto de um buraco negro?", "Astronomia",
        "A gravidade extrema altera trajetórias, o tempo observado e o destino da matéria que se aproxima demais.",
        10, "🪐",
        "Buracos negros são regiões em que a gravidade é tão intensa que, dentro de uma fronteira chamada horizonte de eventos, nada consegue escapar para o exterior, nem mesmo a luz. Eles não são aspiradores cósmicos que sugam automaticamente tudo ao redor: a influência gravitacional depende da massa e da distância, como ocorre com outros objetos.",
        listOf(
            ArticleSection("Como um buraco negro se forma", listOf(
                "Alguns buracos negros se formam quando o núcleo de uma estrela muito massiva deixa de sustentar a pressão necessária contra a gravidade. O resultado depende da massa inicial, da perda de matéria e de outros processos durante a evolução estelar.",
                "Existem também buracos negros supermassivos nos centros de muitas galáxias, incluindo a Via Láctea. A origem e o crescimento desses objetos envolvem questões ainda estudadas, como a formação de sementes iniciais, fusões e acreção de matéria ao longo de bilhões de anos.",
                "Não é correto afirmar que toda estrela termina como um buraco negro. Muitas estrelas deixam remanescentes como anãs brancas ou estrelas de nêutrons; a formação de um buraco negro exige condições específicas."
            )),
            ArticleSection("O horizonte de eventos", listOf(
                "O horizonte de eventos é uma fronteira causal: depois de cruzá-la para dentro, nenhuma trajetória futura pode levar um sinal de volta a um observador distante. Ele não é necessariamente uma superfície sólida e não funciona como uma casca material.",
                "Para um buraco negro simples e sem rotação, o tamanho característico do horizonte cresce com a massa. Isso significa que buracos negros mais massivos podem ter horizontes maiores, embora a densidade média calculada dentro desse raio não se comporte como a intuição cotidiana sugere.",
                "O que ocorre no interior exige a relatividade geral e provavelmente uma teoria mais completa que inclua efeitos quânticos. As equações clássicas indicam regiões onde a descrição matemática deixa de ser suficiente, e não temos observações diretas de tudo o que se passa lá dentro."
            )),
            ArticleSection("Disco de acreção e jatos", listOf(
                "O material que cai em direção a um buraco negro normalmente possui movimento lateral. Em vez de cair em linha reta, pode formar um disco de acreção, no qual gás e poeira orbitam, colidem e aquecem enquanto perdem energia e momento angular.",
                "O disco pode emitir radiação intensa, especialmente quando a matéria atinge temperaturas muito elevadas. Em alguns sistemas, campos magnéticos e processos ligados à rotação ajudam a produzir jatos de partículas que viajam a velocidades próximas à da luz.",
                "A radiação observada frequentemente vem do material ao redor do buraco negro, não do interior do horizonte. É por isso que certos buracos negros podem ser detectados indiretamente mesmo quando a região central não emite luz que consiga escapar."
            )),
            ArticleSection("O tempo e a observação", listOf(
                "Na relatividade geral, a gravidade está relacionada à geometria do espaço-tempo. Para um observador distante, sinais emitidos perto do horizonte podem chegar cada vez mais enfraquecidos e com maior desvio para o vermelho, tornando a região difícil de observar.",
                "Isso não deve ser interpretado como se uma pessoa que cai necessariamente percebesse o próprio relógio parar no horizonte. A descrição depende do observador e das coordenadas usadas; para um buraco negro suficientemente grande, cruzar o horizonte não precisa produzir uma sensação local especial naquele instante.",
                "Astrônomos estudam esses objetos por meio do movimento de estrelas e gás, da radiação emitida pela matéria aquecida, de ondas gravitacionais produzidas por fusões e de imagens da região próxima ao horizonte obtidas por redes de radiotelescópios."
            )),
            ArticleSection("Eles são perigosos para tudo ao redor?", listOf(
                "A grandes distâncias, um buraco negro exerce gravidade como qualquer objeto com a mesma massa. Se o Sol fosse substituído, hipoteticamente, por um buraco negro com a mesma massa, os planetas não seriam automaticamente sugados; a órbita terrestre continuaria aproximadamente a mesma, embora a Terra perdesse a luz e o calor solares.",
                "O perigo cresce quando a aproximação é extrema, devido às forças de maré, à radiação do material em acreção e às condições locais. O ambiente de cada buraco negro é diferente, e muitos não estão consumindo matéria em ritmo elevado.",
                "Buracos negros são laboratórios naturais para testar teorias físicas em condições extremas. Apesar dos avanços observacionais, várias perguntas permanecem abertas, especialmente sobre a relação entre gravidade e mecânica quântica."
            ))
        ),
        listOf("O horizonte de eventos é a fronteira a partir da qual não há retorno para sinais enviados ao exterior.", "Buracos negros não sugam automaticamente objetos distantes.", "A matéria ao redor pode formar um disco quente e emitir radiação.", "Ondas gravitacionais e movimentos estelares ajudam a estudá-los."),
        "Fontes para aprofundamento: NASA; Event Horizon Telescope; European Southern Observatory (ESO); materiais de relatividade geral e ondas gravitacionais."
    ),
    Article(
        9, "Por que a água do mar é salgada?", "Oceanos",
        "Sais dissolvidos chegam ao oceano por processos geológicos, enquanto a água circula pelo planeta.",
        8, "🌊",
        "A água do mar contém uma mistura de sais dissolvidos, principalmente íons de sódio e cloreto. A salinidade não surgiu de uma única fonte ou em um único momento: ela resulta de processos geológicos, químicos e hidrológicos que atuam em escalas de tempo muito longas.",
        listOf(
            ArticleSection("O papel das rochas e dos rios", listOf(
                "A água da chuva interage com a atmosfera e com as rochas. Ao longo do tempo, o intemperismo químico libera íons minerais que podem ser transportados por córregos e rios até o oceano.",
                "Os rios levam diversos elementos dissolvidos, mas a composição não é igual em todos os lugares. Ela depende das rochas da região, do clima, da vegetação, do solo e do tempo que a água permanece em contato com os materiais.",
                "O oceano recebe sais por mais de um caminho. A erosão continental é importante, mas processos hidrotermais no fundo do mar, reações entre água e rochas e outras fontes também participam do balanço químico global."
            )),
            ArticleSection("Por que o sal não desaparece quando a água evapora?", listOf(
                "Quando a água do mar evapora, as moléculas de água passam para a atmosfera, mas a maior parte dos sais dissolvidos permanece no oceano. Depois, a água retorna por precipitação e escoamento, enquanto muitos íons continuam dissolvidos.",
                "Esse ciclo contribui para concentrar sais ao longo do tempo, mas não significa que a salinidade aumente sem limite. Minerais também são removidos por processos como precipitação química, formação de sedimentos, interação com a crosta oceânica e incorporação em organismos.",
                "A salinidade média dos oceanos resulta de um equilíbrio dinâmico entre entradas e saídas. O tempo de permanência de cada íon varia bastante: alguns são removidos ou reciclados mais rapidamente do que outros."
            )),
            ArticleSection("Todos os mares têm a mesma salinidade?", listOf(
                "Não. Evaporação intensa tende a elevar a salinidade quando não é compensada por chuva ou entrada de água doce. Regiões com muita precipitação, degelo ou descarga de rios podem apresentar salinidade menor, sobretudo perto da superfície.",
                "Em mares parcialmente isolados, a circulação com o oceano aberto também faz diferença. O Mar Vermelho, por exemplo, apresenta salinidade relativamente elevada em parte por causa da forte evaporação e do balanço entre entrada e saída de água.",
                "A salinidade influencia a densidade da água e, junto com a temperatura, participa da circulação oceânica. Essa circulação redistribui calor e nutrientes e afeta o clima, os ecossistemas e as condições de vida marinha."
            )),
            ArticleSection("Salinidade e vida marinha", listOf(
                "Os organismos precisam lidar com diferenças entre a concentração de sais dentro do corpo e no ambiente. Muitos animais marinhos possuem mecanismos de osmorregulação para equilibrar água e íons; as estratégias variam entre peixes, invertebrados, algas e outros seres vivos.",
                "Mudanças rápidas de salinidade podem causar estresse em espécies adaptadas a condições mais estáveis. Estuários, onde água doce e salgada se misturam, abrigam espécies que toleram variações e funcionam como importantes áreas de alimentação e reprodução.",
                "Entender a salinidade ajuda a interpretar a circulação oceânica, a distribuição de espécies e as consequências de alterações no ciclo da água. É um exemplo de como química, geologia, física e biologia se conectam."
            ))
        ),
        listOf("Rios transportam íons liberados pelo intemperismo das rochas.", "A evaporação remove água, mas deixa a maior parte dos sais no oceano.", "A salinidade varia com evaporação, chuva, degelo e circulação.", "Salinidade e temperatura afetam a densidade e a circulação oceânica."),
        "Fontes para aprofundamento: NOAA Ocean Service; Woods Hole Oceanographic Institution; materiais de oceanografia química."
    ),
    Article(
        10, "Como o Wi-Fi leva dados pelo ar?", "Tecnologia",
        "Roteadores e aparelhos trocam informações usando ondas de rádio, protocolos e mecanismos para reduzir interferências.",
        9, "📶",
        "Wi-Fi é uma família de tecnologias de rede sem fio que permite que celulares, computadores e outros dispositivos troquem dados com um ponto de acesso. As informações são codificadas em sinais de rádio, enviados e recebidos por antenas, sem que seja necessário um cabo entre cada aparelho e o roteador.",
        listOf(
            ArticleSection("Os dados viram sinais de rádio", listOf(
                "Quando você abre uma página, o aparelho divide as informações em unidades de dados e as prepara segundo protocolos de rede. A camada física representa esses dados por sinais de rádio que variam de maneira controlada.",
                "As redes Wi-Fi usam bandas de frequência autorizadas, incluindo 2,4 GHz, 5 GHz e, em equipamentos compatíveis e regiões onde disponível, 6 GHz. Cada banda tem características diferentes de alcance, capacidade e sensibilidade a obstáculos e interferências.",
                "A frequência, sozinha, não determina toda a velocidade. A largura do canal, o padrão Wi-Fi, o número de antenas, a qualidade do sinal, a quantidade de aparelhos conectados e a capacidade da conexão com a internet também influenciam o desempenho."
            )),
            ArticleSection("Como o roteador e o aparelho se entendem", listOf(
                "O ponto de acesso anuncia a rede e os dispositivos realizam procedimentos para se associar a ela. Depois da autenticação e da configuração de rede, os aparelhos podem enviar quadros de dados, respeitando as regras de acesso ao meio compartilhado.",
                "Como vários aparelhos podem transmitir na mesma área, o Wi-Fi precisa coordenar o uso do canal e lidar com colisões, ruído e retransmissões. Quando um quadro não chega corretamente, protocolos podem solicitar seu envio novamente.",
                "A comunicação é bidirecional: o celular não apenas recebe dados do roteador, como também transmite solicitações, confirmações e informações de controle. Uma conexão boa depende tanto do caminho de ida quanto do de volta."
            )),
            ArticleSection("Por que paredes e distância afetam o sinal?", listOf(
                "As ondas de rádio podem ser absorvidas, refletidas ou espalhadas por paredes, móveis, água e outros materiais. A perda depende da composição e da espessura dos obstáculos, da frequência utilizada e da geometria do ambiente.",
                "A distância também importa: à medida que a onda se propaga, a energia recebida tende a diminuir. Em apartamentos e casas, posicionar o roteador em local aberto e relativamente central pode melhorar a cobertura, embora a planta e os materiais da construção tenham grande influência.",
                "Uma rede que mostra sinal forte ainda pode estar lenta se o canal estiver congestionado, se o roteador estiver sobrecarregado ou se a conexão com a operadora apresentar problemas. Barras de sinal não são uma medida direta de velocidade da internet."
            )),
            ArticleSection("Segurança: senha não é detalhe", listOf(
                "A proteção da rede ajuda a impedir que pessoas não autorizadas se conectem e reduz a exposição de dados. É recomendável usar mecanismos modernos de segurança compatíveis com os dispositivos, uma senha longa e exclusiva e firmware atualizado no roteador.",
                "A criptografia Wi-Fi protege o enlace sem fio, mas não substitui todas as outras camadas de segurança. Serviços HTTPS, atualizações, autenticação robusta e cuidado com páginas falsas continuam importantes.",
                "Redes públicas podem ter configurações e riscos variados. Evite presumir que uma rede é confiável apenas por ter um nome conhecido; confira o ponto de acesso e mantenha os mecanismos de proteção do aparelho ativos."
            )),
            ArticleSection("Wi-Fi não é a mesma coisa que internet", listOf(
                "Wi-Fi é o enlace local entre o dispositivo e o ponto de acesso. A internet é uma rede global de redes que pode ser alcançada por meio do roteador e da conexão contratada. É possível estar conectado ao Wi-Fi e, mesmo assim, ficar sem internet se a ligação externa falhar.",
                "Um teste de velocidade mede uma combinação de fatores: a capacidade da conexão externa, o desempenho do aparelho, a qualidade do enlace sem fio e as condições do servidor de teste. Para diagnosticar problemas, compare resultados perto do roteador e por cabo, se possível.",
                "Essa distinção ajuda a evitar conclusões precipitadas. Se um computador tem velocidade muito menor que um celular na mesma rede, a causa pode estar no adaptador, no driver, na banda utilizada, na posição ou em configurações do próprio computador."
            ))
        ),
        listOf("Wi-Fi usa ondas de rádio para transportar dados codificados.", "Bandas diferentes equilibram alcance, interferência e capacidade.", "Sinal forte não garante internet rápida.", "Wi-Fi é a conexão local; internet é a rede externa acessada pelo roteador."),
        "Fontes para aprofundamento: Wi-Fi Alliance; IEEE 802.11; materiais educativos da Cisco sobre redes sem fio e segurança."
    )
)

private data class LocalProfile(val name: String, val email: String, val interests: Set<String>)

private data class LearningState(
    val completedArticles: Set<String> = emptySet(),
    val completedQuizzes: Set<String> = emptySet(),
    val currentStreak: Int = 0,
    val lastActivityDate: String = "",
    val dailyPoints: Int = 0,
    val dailyPointsDate: String = ""
)

private fun todayKey(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

private fun previousDayKey(key: String): String {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = format.parse(key) ?: return ""
        val calendar = Calendar.getInstance().apply { time = date; add(Calendar.DAY_OF_YEAR, -1) }
        format.format(calendar.time)
    } catch (_: Exception) { "" }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("curiomundo_preferences", MODE_PRIVATE)
        setContent {
            CurioMundoApp(
                initialFavorites = prefs.getStringSet("favorites", emptySet()) ?: emptySet(),
                initialInterests = prefs.getStringSet("interests", emptySet()) ?: emptySet(),
                initialName = prefs.getString("profile_name", "") ?: "",
                initialEmail = prefs.getString("profile_email", "") ?: "",
                initialOnboardingDone = prefs.getBoolean("onboarding_done", false),
                initialLearningState = LearningState(
                    completedArticles = prefs.getStringSet("completed_articles", emptySet()) ?: emptySet(),
                    completedQuizzes = prefs.getStringSet("completed_quizzes", emptySet()) ?: emptySet(),
                    currentStreak = prefs.getInt("current_streak", 0),
                    lastActivityDate = prefs.getString("last_activity_date", "") ?: "",
                    dailyPoints = prefs.getInt("daily_points", 0),
                    dailyPointsDate = prefs.getString("daily_points_date", "") ?: ""
                ),
                saveLearningState = { state ->
                    prefs.edit()
                        .putStringSet("completed_articles", state.completedArticles.toSet())
                        .putStringSet("completed_quizzes", state.completedQuizzes.toSet())
                        .putInt("current_streak", state.currentStreak)
                        .putString("last_activity_date", state.lastActivityDate)
                        .putInt("daily_points", state.dailyPoints)
                        .putString("daily_points_date", state.dailyPointsDate)
                        .apply()
                },
                saveFavorites = { prefs.edit().putStringSet("favorites", it.toSet()).apply() },
                saveProfile = { profile ->
                    prefs.edit()
                        .putString("profile_name", profile.name)
                        .putString("profile_email", profile.email)
                        .putStringSet("interests", profile.interests.toSet())
                        .putBoolean("onboarding_done", true)
                        .apply()
                },
                saveInterests = { interests -> prefs.edit().putStringSet("interests", interests.toSet()).apply() }
            )
        }
    }
}

@Composable
private fun CurioMundoApp(
    initialFavorites: Set<String>,
    initialInterests: Set<String>,
    initialName: String,
    initialEmail: String,
    initialOnboardingDone: Boolean,
    initialLearningState: LearningState,
    saveLearningState: (LearningState) -> Unit,
    saveFavorites: (Set<String>) -> Unit,
    saveProfile: (LocalProfile) -> Unit,
    saveInterests: (Set<String>) -> Unit
) {
    var interests by remember { mutableStateOf(initialInterests) }
    var name by remember { mutableStateOf(initialName) }
    var email by remember { mutableStateOf(initialEmail) }
    var onboardingDone by remember { mutableStateOf(initialOnboardingDone) }
    var showAccount by remember { mutableStateOf(false) }
    var editingInterests by remember { mutableStateOf(false) }
    var tab by remember { mutableStateOf("Início") }
    var category by remember { mutableStateOf("Todos") }
    var search by remember { mutableStateOf("") }
    var opened by remember { mutableStateOf<Article?>(null) }
    var favorites by remember { mutableStateOf(initialFavorites) }
    var learning by remember { mutableStateOf(initialLearningState) }
    var quizArticle by remember { mutableStateOf<Article?>(null) }
    var quizResult by remember { mutableStateOf<Pair<Article, Int>?>(null) }

    fun persistLearning(next: LearningState) {
        learning = next
        saveLearningState(next)
    }

    fun markArticleRead(article: Article) {
        persistLearning(learning.copy(completedArticles = learning.completedArticles + article.id.toString()))
        opened = null
        quizArticle = article
    }

    fun finishQuiz(article: Article, score: Int) {
        if (learning.completedQuizzes.contains(article.id.toString())) {
            quizArticle = null
            quizResult = article to score
            return
        }
        val today = todayKey()
        val newStreak = when {
            learning.lastActivityDate == today -> learning.currentStreak.coerceAtLeast(1)
            learning.lastActivityDate == previousDayKey(today) -> learning.currentStreak + 1
            else -> 1
        }
        val pointsToday = if (learning.dailyPointsDate == today) learning.dailyPoints else 0
        persistLearning(learning.copy(
            completedQuizzes = learning.completedQuizzes + article.id.toString(),
            currentStreak = newStreak,
            lastActivityDate = today,
            dailyPoints = pointsToday + score * 10,
            dailyPointsDate = today
        ))
        quizArticle = null
        quizResult = article to score
    }

    MaterialTheme(colorScheme = lightColorScheme(primary = Green, background = Cream, surface = Paper, onSurface = Ink, secondary = Gold)) {
        Surface(Modifier.fillMaxSize(), color = Cream) {
            when {
                !onboardingDone && !showAccount -> InterestsScreen(
                    selected = interests,
                    onToggle = { topic -> interests = if (interests.contains(topic)) interests - topic else interests + topic },
                    onContinue = { showAccount = true },
                    onSkip = { interests = setOf("Ciência", "Astronomia"); saveInterests(interests); showAccount = true }
                )
                showAccount -> AccountScreen(
                    initialName = name,
                    initialEmail = email,
                    isEditing = onboardingDone,
                    onBack = { showAccount = false },
                    onContinue = { newName, newEmail ->
                        name = newName
                        email = newEmail
                        saveProfile(LocalProfile(newName, newEmail, interests))
                        onboardingDone = true
                        showAccount = false
                    }
                )
                editingInterests -> InterestsScreen(
                    selected = interests,
                    title = "Personalize seu CurioMundo",
                    subtitle = "Escolha os assuntos que você quer ver com mais frequência. Você pode mudar isso quando quiser.",
                    onToggle = { topic -> interests = if (interests.contains(topic)) interests - topic else interests + topic },
                    onContinue = {
                        saveInterests(interests)
                        saveProfile(LocalProfile(name, email, interests))
                        editingInterests = false
                    },
                    onSkip = { editingInterests = false }
                )
                opened != null -> ArticlePage(
                    article = opened!!,
                    favorite = favorites.contains(opened!!.id.toString()),
                    back = { opened = null },
                    toggleFavorite = {
                        val id = opened!!.id.toString()
                        favorites = if (favorites.contains(id)) favorites - id else favorites + id
                        saveFavorites(favorites)
                    },
                    openArticle = { opened = it }
                )
                else -> HomePage(
                    name = name,
                    email = email,
                    tab = tab,
                    onTab = { tab = it },
                    category = category,
                    onCategory = { category = it },
                    search = search,
                    onSearch = { search = it },
                    favorites = favorites,
                    interests = interests,
                    openArticle = { opened = it },
                    toggleFavorite = { a ->
                        val id = a.id.toString()
                        favorites = if (favorites.contains(id)) favorites - id else favorites + id
                        saveFavorites(favorites)
                    },
                    onEditInterests = { editingInterests = true },
                    onEditProfile = { showAccount = true }
                )
            }
        }
    }
}

@Composable
private fun InterestsScreen(
    selected: Set<String>,
    title: String = "O que desperta sua curiosidade?",
    subtitle: String = "Escolha pelo menos 3 assuntos. Vamos usar suas preferências para organizar seu feed.",
    onToggle: (String) -> Unit,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    val valid = selected.size >= 3
    Column(Modifier.fillMaxSize().background(Cream)) {
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 22.dp, vertical = 24.dp)) {
            Text("CURIOMUNDO", color = Green, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            Spacer(Modifier.height(22.dp))
            Text(title, color = Ink, fontSize = 31.sp, lineHeight = 37.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(10.dp))
            Text(subtitle, color = Muted, fontSize = 15.sp, lineHeight = 23.sp)
            Spacer(Modifier.height(14.dp))
            Text("${selected.size} selecionado(s)", color = Green, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            topics.chunked(2).forEach { pair ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    pair.forEach { topic ->
                        val isSelected = selected.contains(topic.name)
                        Column(
                            Modifier.weight(1f).padding(bottom = 10.dp).clip(RoundedCornerShape(18.dp))
                                .background(if (isSelected) Color(0xFFE4F7F4) else Paper)
                                .clickable { onToggle(topic.name) }
                                .padding(13.dp)
                                .heightIn(min = 112.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(topic.emoji, fontSize = 25.sp)
                                Spacer(Modifier.weight(1f))
                                Icon(
                                    if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                    contentDescription = if (isSelected) "Selecionado" else "Selecionar",
                                    tint = if (isSelected) Green else Color(0xFFBBC2B8),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(topic.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Spacer(Modifier.height(4.dp))
                            Text(topic.description, color = Muted, fontSize = 11.sp, lineHeight = 15.sp)
                        }
                    }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Dica: marque assuntos diferentes. Seu feed vai dar prioridade a eles, mas você continuará podendo explorar todo o catálogo.", color = Muted, fontSize = 12.sp, lineHeight = 18.sp)
        }
        Column(Modifier.fillMaxWidth().background(Paper).padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onContinue, enabled = valid, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Green), shape = RoundedCornerShape(15.dp)) {
                Text("Continuar", modifier = Modifier.padding(vertical = 5.dp), fontWeight = FontWeight.Bold)
            }
            if (!valid) Text("Selecione mais ${3 - selected.size} assunto(s) para continuar.", color = Muted, fontSize = 12.sp)
            TextButton(onClick = onSkip, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Escolher depois", color = Muted) }
        }
    }
}

@Composable
private fun AccountScreen(
    initialName: String,
    initialEmail: String,
    isEditing: Boolean,
    onBack: () -> Unit,
    onContinue: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var email by remember { mutableStateOf(initialEmail) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().background(Cream)) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Voltar", tint = Ink) }
            Text(if (isEditing) "Editar perfil" else "Seu perfil", color = Ink, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 24.dp)) {
            Text("Quase lá!", color = Green, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            Spacer(Modifier.height(10.dp))
            Text(if (isEditing) "Atualize seus dados" else "Crie seu perfil CurioMundo", color = Ink, fontSize = 30.sp, lineHeight = 36.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(8.dp))
            Text("Seus assuntos favoritos vão orientar as recomendações. Você poderá alterá-los no perfil.", color = Muted, fontSize = 14.sp, lineHeight = 22.sp)
            Spacer(Modifier.height(22.dp))
            OutlinedTextField(value = name, onValueChange = { name = it; error = "" }, modifier = Modifier.fillMaxWidth(), label = { Text("Como podemos chamar você?") }, singleLine = true, shape = RoundedCornerShape(14.dp))
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = email, onValueChange = { email = it; error = "" }, modifier = Modifier.fillMaxWidth(), label = { Text("E-mail") }, singleLine = true, shape = RoundedCornerShape(14.dp))
            Spacer(Modifier.height(12.dp))
            if (!isEditing) {
                OutlinedTextField(value = password, onValueChange = { password = it; error = "" }, modifier = Modifier.fillMaxWidth(), label = { Text("Senha") }, singleLine = true, visualTransformation = PasswordVisualTransformation(), shape = RoundedCornerShape(14.dp))
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it; error = "" }, modifier = Modifier.fillMaxWidth(), label = { Text("Confirme a senha") }, singleLine = true, visualTransformation = PasswordVisualTransformation(), shape = RoundedCornerShape(14.dp))
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = acceptedTerms, onCheckedChange = { acceptedTerms = it }, colors = CheckboxDefaults.colors(checkedColor = Green))
                    Text("Entendo que esta versão salva meu perfil somente neste aparelho.", color = Muted, fontSize = 12.sp, lineHeight = 17.sp)
                }
            }
            if (error.isNotBlank()) {
                Text(error, color = Color(0xFFB42318), fontSize = 12.sp)
                Spacer(Modifier.height(6.dp))
            }
            Spacer(Modifier.height(8.dp))
            Surface(color = Color(0xFFFFF0D2), shape = RoundedCornerShape(12.dp)) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF8A5A00), modifier = Modifier.size(18.dp))
                    Text("Esta versão é um protótipo: o perfil será salvo neste aparelho. A criação de conta segura, login entre dispositivos e recuperação de senha dependem de conectar o serviço de autenticação antes da publicação.", color = Color(0xFF73521B), fontSize = 12.sp, lineHeight = 17.sp)
                }
            }
            Spacer(Modifier.height(18.dp))
        }
        Column(Modifier.fillMaxWidth().background(Paper).padding(18.dp)) {
            Button(
                onClick = {
                    error = when {
                        name.trim().length < 2 -> "Informe seu nome."
                        !email.contains("@") || !email.substringAfter("@", "").contains(".") -> "Informe um e-mail válido."
                        !isEditing && password.length < 8 -> "A senha precisa ter pelo menos 8 caracteres."
                        !isEditing && password != confirmPassword -> "As senhas não coincidem."
                        !isEditing && !acceptedTerms -> "Confirme que entende como o perfil é armazenado nesta versão."
                        else -> ""
                    }
                    if (error.isBlank()) onContinue(name.trim(), email.trim())
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                shape = RoundedCornerShape(15.dp)
            ) { Text(if (isEditing) "Salvar perfil" else "Continuar para o CurioMundo", modifier = Modifier.padding(vertical = 5.dp), fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
private fun HomePage(
    name: String,
    email: String,
    tab: String,
    onTab: (String) -> Unit,
    category: String,
    onCategory: (String) -> Unit,
    search: String,
    onSearch: (String) -> Unit,
    favorites: Set<String>,
    interests: Set<String>,
    openArticle: (Article) -> Unit,
    toggleFavorite: (Article) -> Unit,
    onEditInterests: () -> Unit,
    onEditProfile: () -> Unit
) {
    val orderedArticles = articles.sortedByDescending { article ->
        when {
            interests.contains(article.category) -> 3
            interests.contains("Astronomia") && article.category == "Astronomia" -> 3
            interests.contains("Ciência") && article.category == "Ciência" -> 3
            interests.contains("Animais e natureza") && article.category == "Animais e natureza" -> 3
            else -> 0
        }
    }
    val filtered = orderedArticles.filter { a ->
        (category == "Todos" || a.category == category) &&
        (search.isBlank() || a.title.contains(search, true) || a.summary.contains(search, true) || a.category.contains(search, true)) &&
        (tab != "Salvos" || favorites.contains(a.id.toString()))
    }
    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(containerColor = Paper) {
                NavigationBarItem(tab == "Início", { onTab("Início") }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Início") })
                NavigationBarItem(tab == "Explorar", { onTab("Explorar") }, icon = { Icon(Icons.Default.Explore, null) }, label = { Text("Explorar") })
                NavigationBarItem(tab == "Salvos", { onTab("Salvos") }, icon = { Icon(Icons.Default.Bookmark, null) }, label = { Text("Salvos") })
                NavigationBarItem(tab == "Perfil", { onTab("Perfil") }, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") })
            }
        }
    ) { padding ->
        if (tab == "Perfil") {
            LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(22.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    Text("C U R I O S A", color = Green, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 3.sp)
                    Spacer(Modifier.height(14.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        Box(Modifier.size(64.dp).clip(CircleShape).background(Color(0xFFEAE4FF)), contentAlignment = Alignment.Center) {
                            Text(name.firstOrNull()?.uppercase() ?: "C", color = Green, fontSize = 27.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(name.ifBlank { "Seu perfil" }, color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(email, color = Muted, fontSize = 13.sp)
                            Text("Perfil local de demonstração", color = Muted, fontSize = 11.sp)
                        }
                    }
                }
                item {
                    Text("Seus interesses", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    if (interests.isEmpty()) Text("Você ainda não escolheu assuntos.", color = Muted)
                    else FlowInterestChips(interests)
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(onClick = onEditInterests, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
                        Icon(Icons.Default.Tune, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Editar interesses")
                    }
                }
                item {
                    Text("Sua experiência", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    ProfileOption("Artigos salvos", "Acesse seus favoritos", Icons.Default.Bookmark) { onTab("Salvos") }
                    ProfileOption("Editar dados do perfil", "Nome e e-mail", Icons.Default.ManageAccounts, onEditProfile)
                    ProfileOption("Personalização", "Escolha os assuntos do seu feed", Icons.Default.Tune, onEditInterests)
                }
                item {
                    Surface(color = Color(0xFFFFF0D2), shape = RoundedCornerShape(12.dp)) {
                        Text("Atenção: a autenticação online ainda não foi conectada. Este perfil de protótipo fica neste aparelho e não deve ser tratado como uma conta segura para publicação.", Modifier.padding(12.dp), color = Color(0xFF73521B), fontSize = 12.sp, lineHeight = 18.sp)
                    }
                }
            }
        } else {
            LazyColumn(
                Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("C U R I O S A", color = Green, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
                            Text(
                                when (tab) { "Explorar" -> "Explore o desconhecido"; "Salvos" -> "Sua biblioteca"; else -> if (name.isNotBlank()) "Olá, ${name.substringBefore(" ")}." else "Um mundo inteiro para descobrir." },
                                color = Ink, fontSize = 26.sp, lineHeight = 32.sp, fontWeight = FontWeight.Bold
                            )
                        }
                        IconButton(onClick = onEditProfile) {
                            Box(Modifier.size(42.dp).clip(CircleShape).background(Color(0xFFE5EBDD)), contentAlignment = Alignment.Center) {
                                Text(name.firstOrNull()?.uppercase() ?: "C", color = Green, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            }
                        }
                    }
                }
                item {
                    OutlinedTextField(
                        value = search, onValueChange = onSearch, modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("O que você quer descobrir?") },
                        leadingIcon = { Icon(Icons.Default.Search, null) }, singleLine = true,
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Green, unfocusedBorderColor = Color(0xFFE0E3DA), focusedContainerColor = Paper, unfocusedContainerColor = Paper)
                    )
                }
                if (tab == "Início" && search.isBlank()) {
                    item {
                        Column(
                            Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp)).background(Green)
                                .clickable { openArticle(articles[1]) }.padding(22.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("✦  UMA DESCOBERTA PARA VOCÊ", color = Color(0xFFE3EBD9), fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                            Text(if (interests.contains("Astronomia")) "Explore o universo das descobertas" else "O mundo é fascinante.", color = Color.White, fontSize = 24.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)
                            Text("Ciência, histórias e personagens para todas as curiosidades.", color = Color(0xFFE3EBD9), fontSize = 14.sp)
                            Text("Ler artigo completo  →", color = Gold, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Feito para seus interesses", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            if (interests.isEmpty()) Text("Escolha temas no seu perfil para personalizar o feed.", color = Muted, fontSize = 12.sp)
                            else LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(interests.toList()) { topic ->
                                    Surface(color = Color(0xFFE4EDE1), shape = RoundedCornerShape(50.dp)) {
                                        Text(topic, Modifier.padding(horizontal = 12.dp, vertical = 8.dp), color = Green, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }
                    }
                }
                if (tab == "Início" && search.isBlank()) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("Conheça os guias do CurioMundo", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            Text("Cada personagem acompanha você por um universo de descobertas.", color = Muted, fontSize = 12.sp)
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                item { MascotCard("🐙", "Octo", "Oceanos", Color(0xFFE8E0FF)) }
                                item { MascotCard("🪐", "Cosmo", "Astronomia", Color(0xFFDDF5F2)) }
                                item { MascotCard("🦖", "Dino", "Pré-história", Color(0xFFFFEBCB)) }
                                item { MascotCard("🤖", "Byte", "Tecnologia", Color(0xFFE5E9FF)) }
                            }
                        }
                    }
                }
                if ((tab == "Explorar" || tab == "Início") && search.isBlank()) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text("Explore por assunto", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(listOf("Todos") + articles.map { it.category }.distinct()) { c ->
                                    FilterChip(
                                        selected = category == c, onClick = { onCategory(c) }, label = { Text(c) },
                                        shape = RoundedCornerShape(50.dp),
                                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = Green, selectedLabelColor = Color.White)
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Text(when (tab) { "Salvos" -> "Artigos salvos"; "Explorar" -> "Conhecimento para explorar"; else -> "Continue descobrindo" },
                        color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                }
                if (filtered.isEmpty()) {
                    item {
                        Column(Modifier.fillMaxWidth().padding(vertical = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(if (tab == "Salvos") "🔖" else "🔎", fontSize = 36.sp)
                            Text(if (tab == "Salvos") "Nenhum artigo salvo ainda" else "Nenhum artigo encontrado", color = Ink, fontWeight = FontWeight.Bold)
                            Text("Explore os conteúdos e guarde seus favoritos.", color = Muted)
                        }
                    }
                } else {
                    items(filtered, key = { it.id }) { a ->
                        ArticleCard(a, favorites.contains(a.id.toString()), { openArticle(a) }, { toggleFavorite(a) })
                    }
                }
            }
        }
    }
}

@Composable
private fun FlowInterestChips(interests: Set<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        interests.chunked(2).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                row.forEach { item ->
                    Surface(color = Color(0xFFE4EDE1), shape = RoundedCornerShape(50.dp)) {
                        Text(item, Modifier.padding(horizontal = 10.dp, vertical = 7.dp), color = Green, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileOption(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(Paper).clickable(onClick = onClick).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = Green, modifier = Modifier.size(23.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = Muted, fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Muted)
    }
}

@Composable
private fun MascotCard(emoji: String, name: String, subject: String, tint: Color) {
    Column(
        Modifier.width(116.dp).clip(RoundedCornerShape(20.dp)).background(tint).padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(Modifier.size(68.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.72f)), contentAlignment = Alignment.Center) {
            Text(emoji, fontSize = 39.sp)
        }
        Text(name, color = Ink, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
        Text(subject, color = Muted, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun ArticleCard(article: Article, favorite: Boolean, open: () -> Unit, toggle: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(Paper).clickable(onClick = open).padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(13.dp), verticalAlignment = Alignment.Top
    ) {
        Box(Modifier.size(76.dp).clip(RoundedCornerShape(17.dp)).background(Color(0xFFE8E7F7)), contentAlignment = Alignment.Center) {
            Text(article.emoji, fontSize = 34.sp)
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(article.category.uppercase(), color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Text(article.title, color = Ink, fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.Bold, maxLines = 3, overflow = TextOverflow.Ellipsis)
            Text(article.summary, color = Muted, fontSize = 12.sp, lineHeight = 17.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccessTime, null, tint = Muted, modifier = Modifier.size(13.dp))
                Spacer(Modifier.width(4.dp))
                Text(article.minutes.toString() + " min de leitura", color = Muted, fontSize = 11.sp)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = toggle, modifier = Modifier.size(28.dp)) {
                    Icon(if (favorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, "Salvar artigo", tint = if (favorite) Green else Muted, modifier = Modifier.size(19.dp))
                }
            }
        }
    }
}

@Composable
private fun ArticlePage(article: Article, favorite: Boolean, back: () -> Unit, toggleFavorite: () -> Unit, openArticle: (Article) -> Unit) {
    Scaffold(
        containerColor = Cream,
        topBar = {
            Row(Modifier.fillMaxWidth().background(Cream).padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = back) { Icon(Icons.Default.ArrowBack, "Voltar", tint = Ink) }
                Text("Leitura aprofundada", Modifier.weight(1f), color = Ink, fontWeight = FontWeight.SemiBold)
                IconButton(onClick = toggleFavorite) {
                    Icon(if (favorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder, "Salvar", tint = Green)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            item {
                Box(Modifier.fillMaxWidth().height(170.dp).clip(RoundedCornerShape(26.dp)).background(Green), contentAlignment = Alignment.Center) {
                    Text(article.emoji, fontSize = 78.sp)
                }
            }
            item {
                Text(article.category.uppercase(), color = Green, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
                Spacer(Modifier.height(7.dp))
                Text(article.title, color = Ink, fontSize = 29.sp, lineHeight = 35.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(10.dp))
                Text(article.summary, color = Muted, fontSize = 16.sp, lineHeight = 24.sp)
                Spacer(Modifier.height(8.dp))
                Text("${article.minutes} minutos de leitura • Artigo aprofundado", color = Muted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                Text(article.intro, color = Ink, fontSize = 17.sp, lineHeight = 28.sp)
            }
            items(article.sections) { section ->
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(section.heading, color = Ink, fontSize = 21.sp, lineHeight = 27.sp, fontWeight = FontWeight.Bold)
                    section.paragraphs.forEach { paragraph ->
                        Text(paragraph, color = Ink, fontSize = 17.sp, lineHeight = 29.sp)
                    }
                }
            }
            item {
                Surface(color = Color(0xFFE4EDE1), shape = RoundedCornerShape(18.dp)) {
                    Column(Modifier.padding(17.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("O que você precisa lembrar", color = Green, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        article.keyTakeaways.forEach { takeaway ->
                            Row(horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.Top) {
                                Icon(Icons.Default.CheckCircle, null, tint = Green, modifier = Modifier.size(18.dp))
                                Text(takeaway, color = Ink, fontSize = 14.sp, lineHeight = 21.sp)
                            }
                        }
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    HorizontalDivider(color = Color(0xFFE1E4DA))
                    Text("FONTES E LEITURA COMPLEMENTAR", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                    Text(article.source, color = Muted, fontSize = 13.sp, lineHeight = 21.sp)
                    Text("Nota editorial: o CurioMundo deve revisar e validar as referências de cada artigo antes de disponibilizar a versão comercial.", color = Muted, fontSize = 11.sp, lineHeight = 17.sp)
                }
            }
            item { Text("Continue descobrindo", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            items(articles.filter { it.id != article.id }.take(2)) { related ->
                ArticleCard(related, false, { openArticle(related) }, {})
            }
        }
    }
}


@Composable
private fun QuizPage(article: Article, onBack: () -> Unit, onFinish: (Int) -> Unit) {
    val questions = remember(article.id) {
        article.keyTakeaways.take(3).map { correct ->
            val wrong = articles.filter { it.id != article.id }.flatMap { it.keyTakeaways }.filter { it != correct }.distinct().take(2)
            QuizQuestion("Qual afirmação está de acordo com o artigo?", correct, (listOf(correct) + wrong).shuffled())
        }
    }
    var questionIndex by remember(article.id) { mutableIntStateOf(0) }
    var selected by remember(article.id, questionIndex) { mutableStateOf<String?>(null) }
    var score by remember(article.id) { mutableIntStateOf(0) }
    var answered by remember(article.id, questionIndex) { mutableStateOf(false) }
    val current = questions.getOrNull(questionIndex)
    Scaffold(containerColor = Cream, topBar = {
        Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Voltar", tint = Ink) }
            Column(Modifier.weight(1f)) {
                Text("QUIZ DO CURIOMUNDO", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("Pergunta ${questionIndex + 1} de ${questions.size}", color = Ink, fontWeight = FontWeight.SemiBold)
            }
        }
    }) { padding ->
        if (current != null) LazyColumn(
            Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(22.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LinearProgressIndicator(
                    progress = { (questionIndex + 1).toFloat() / questions.size.coerceAtLeast(1) },
                    modifier = Modifier.fillMaxWidth().height(7.dp).clip(CircleShape),
                    color = Green,
                    trackColor = Color(0xFFE2DDF7)
                )
            }
            item {
                Text(article.title, color = Muted, fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
                Text(current.prompt, color = Ink, fontSize = 24.sp, lineHeight = 30.sp, fontWeight = FontWeight.ExtraBold)
            }
            items(current.options) { option ->
                val isCorrect = option == current.answer
                val optionColor = when {
                    answered && isCorrect -> Color(0xFFDDF4E9)
                    answered && selected == option && !isCorrect -> Color(0xFFFFE5E5)
                    selected == option -> Color(0xFFE8E1FF)
                    else -> Paper
                }
                Row(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(17.dp)).background(optionColor)
                        .clickable(enabled = !answered) { selected = option }.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RadioButton(selected == option, onClick = { if (!answered) selected = option }, enabled = !answered)
                    Text(option, color = Ink, fontSize = 14.sp, lineHeight = 20.sp, modifier = Modifier.weight(1f))
                }
            }
            if (answered) item {
                Surface(color = Color(0xFFE4EDE1), shape = RoundedCornerShape(15.dp)) {
                    Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(if (selected == current.answer) "Muito bem! Resposta correta." else "Vamos aprender com a resposta.", color = Green, fontWeight = FontWeight.Bold)
                        Text(current.answer, color = Ink, fontSize = 13.sp, lineHeight = 19.sp)
                    }
                }
            }
            item {
                Button(
                    onClick = {
                        if (!answered) {
                            if (selected == current.answer) score += 1
                            answered = true
                        } else if (questionIndex < questions.lastIndex) {
                            questionIndex += 1
                        } else {
                            onFinish(score)
                        }
                    },
                    enabled = answered || selected != null,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(when {
                        !answered -> "Conferir resposta"
                        questionIndex < questions.lastIndex -> "Próxima pergunta"
                        else -> "Ver resultado"
                    })
                }
            }
        }
    }
}

private data class QuizQuestion(val prompt: String, val answer: String, val options: List<String>)

@Composable
private fun QuizResultPage(article: Article, score: Int, onContinue: () -> Unit) {
    Column(
        Modifier.fillMaxSize().background(Cream).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(104.dp).clip(CircleShape).background(Color(0xFFE7E0FF)), contentAlignment = Alignment.Center) {
            Text(if (score == 3) "🏆" else if (score >= 2) "⭐" else "🌱", fontSize = 52.sp)
        }
        Spacer(Modifier.height(22.dp))
        Text("Aprendizado concluído!", color = Ink, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
        Text(article.title, color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(12.dp))
        Text("$score de 3 respostas corretas", color = Green, fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Text("+${score * 10} pontos para sua meta diária", color = Ink, fontSize = 15.sp)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onContinue, modifier = Modifier.fillMaxWidth().height(54.dp), shape = RoundedCornerShape(17.dp)) {
            Text("Ver meu progresso")
        }
    }
}

@Composable
private fun ProgressPage(learning: LearningState, onOpenArticle: (Article) -> Unit, onBackToFeed: () -> Unit) {
    val todayPoints = if (learning.dailyPointsDate == todayKey()) learning.dailyPoints else 0
    val goal = 30
    val completed = learning.completedArticles.size
    val quizCount = learning.completedQuizzes.size
    val categoryCount = articles.filter { learning.completedArticles.contains(it.id.toString()) }.map { it.category }.distinct().size
    val achievements = listOf(
        "Primeira descoberta" to (completed >= 1),
        "Leitor curioso" to (completed >= 5),
        "Mente afiada" to (quizCount >= 3),
        "Explorador do conhecimento" to (categoryCount >= 4)
    )
    LazyColumn(
        Modifier.fillMaxSize().background(Cream),
        contentPadding = PaddingValues(22.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("SEU CURIOMUNDO", color = Green, fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            Text("Seu progresso", color = Ink, fontSize = 29.sp, fontWeight = FontWeight.ExtraBold)
            Text("Pequenas descobertas constroem grandes conhecimentos.", color = Muted, fontSize = 14.sp)
        }
        item {
            Surface(color = Green, shape = RoundedCornerShape(22.dp)) {
                Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Meta diária", color = Color.White.copy(alpha = .8f), fontSize = 13.sp)
                            Text("$todayPoints de $goal pontos", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        }
                        Text("🎯", fontSize = 34.sp)
                    }
                    LinearProgressIndicator(
                        progress = { (todayPoints.toFloat() / goal).coerceIn(0f, 1f) },
                        modifier = Modifier.fillMaxWidth().height(9.dp).clip(CircleShape),
                        color = Gold,
                        trackColor = Color.White.copy(alpha = .25f)
                    )
                    Text(if (todayPoints >= goal) "Meta diária concluída! Excelente trabalho." else "Ganhe 10 pontos por resposta correta.", color = Color.White, fontSize = 12.sp)
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatTile("🔥", "${learning.currentStreak}", "dias seguidos", Modifier.weight(1f))
                StatTile("📚", "$completed", "leituras", Modifier.weight(1f))
                StatTile("🧠", "$quizCount", "quizzes", Modifier.weight(1f))
            }
        }
        item {
            Text("Calendário de consistência", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("A sequência é atualizada quando você conclui um quiz em um novo dia.", color = Muted, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_YEAR, -6)
                repeat(7) {
                    val key = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
                    val active = key == learning.lastActivityDate
                    Column(
                        Modifier.weight(1f).clip(RoundedCornerShape(10.dp))
                            .background(if (active) Color(0xFFDDF4E9) else Paper).padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(SimpleDateFormat("EE", Locale("pt", "BR")).format(calendar.time).take(1).uppercase(), color = Muted, fontSize = 10.sp)
                        Text(SimpleDateFormat("dd", Locale.US).format(calendar.time), color = Ink, fontWeight = FontWeight.Bold)
                        Text(if (active) "✓" else "·", color = if (active) Color(0xFF20856A) else Muted)
                    }
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                }
            }
        }
        item {
            Text("Conquistas", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            achievements.forEach { (title, unlocked) ->
                Row(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                        .background(if (unlocked) Color(0xFFFFF0D2) else Paper).padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(if (unlocked) "🏅" else "🔒", fontSize = 25.sp)
                    Column(Modifier.weight(1f)) {
                        Text(title, color = Ink, fontWeight = FontWeight.Bold)
                        Text(if (unlocked) "Conquista desbloqueada" else "Continue lendo e respondendo quizzes", color = Muted, fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
        }
        item {
            Text("Continue aprendendo", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            val next = articles.firstOrNull { !learning.completedArticles.contains(it.id.toString()) } ?: articles.first()
            ArticleCard(next, false, { onOpenArticle(next) }, {}, false)
            TextButton(onClick = onBackToFeed, modifier = Modifier.fillMaxWidth()) { Text("Voltar ao feed") }
        }
    }
}

@Composable
private fun StatTile(emoji: String, value: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(16.dp)).background(Paper).padding(13.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(emoji, fontSize = 21.sp)
        Text(value, color = Ink, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, color = Muted, fontSize = 10.sp)
    }
}
