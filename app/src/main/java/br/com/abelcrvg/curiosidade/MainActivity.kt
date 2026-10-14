package br.com.abelcrvg.curiosidade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF18251F)
private val Green = Color(0xFF276749)
private val Cream = Color(0xFFF7F5EF)
private val Muted = Color(0xFF6E786F)
private val Paper = Color(0xFFFFFEFB)
private val Gold = Color(0xFFF0B84B)
private val categories = listOf("Todos", "Ciência", "Animais", "Espaço", "História", "Psicologia", "Tecnologia")

private data class Article(
    val id: Int, val title: String, val category: String, val summary: String,
    val minutes: Int, val emoji: String, val paragraphs: List<String>, val source: String
)

private val articles = listOf(
    Article(1, "Por que o polvo tem três corações?", "Animais", "Uma das criaturas mais inteligentes do oceano tem um sistema circulatório surpreendente.", 4, "🐙", listOf(
        "O polvo possui três corações. Dois bombeiam sangue para as brânquias, onde ocorre a troca de gases, enquanto o terceiro envia sangue oxigenado para o restante do corpo.",
        "Seu sangue é azulado porque utiliza hemocianina, uma proteína que contém cobre para transportar oxigênio. Em águas frias e com pouco oxigênio, essa molécula pode ser especialmente útil.",
        "Quando o polvo nada, o coração que abastece o corpo reduz bastante sua atividade. Por isso, rastejar pelo fundo do mar costuma exigir menos esforço do que nadar por longas distâncias.",
        "Grande parte dos neurônios do polvo está distribuída pelos braços, que conseguem explorar o ambiente e executar movimentos complexos."
    ), "Smithsonian Ocean — materiais sobre a biologia dos cefalópodes."),
    Article(2, "Estamos olhando para o passado quando observamos as estrelas?", "Espaço", "A luz leva tempo para viajar. O céu é uma janela para diferentes épocas do Universo.", 5, "🌌", listOf(
        "A luz viaja no vácuo a aproximadamente 300 mil quilômetros por segundo. Mesmo assim, as distâncias astronômicas são tão grandes que ela precisa de tempo para chegar até nós.",
        "A luz do Sol leva cerca de oito minutos e vinte segundos para alcançar a Terra. Quando olhamos para ele, vemos a luz que saiu de sua superfície alguns minutos antes.",
        "Se uma estrela estiver a cem anos-luz, a luz que observamos hoje partiu dela há aproximadamente cem anos. Telescópios também conseguem observar galáxias cuja luz viajou durante bilhões de anos.",
        "A astronomia permite investigar a história cósmica: alguns objetos podem ter mudado muito desde que emitiram a luz que vemos hoje."
    ), "NASA — materiais educativos sobre luz e observação astronômica."),
    Article(3, "Como os romanos transportavam água sem bombas elétricas?", "História", "Aquedutos e engenharia abasteceram cidades há milhares de anos.", 5, "🏛️", listOf(
        "Os romanos construíram aquedutos para transportar água de fontes distantes até cidades, termas e fontes públicas. Muitos trechos aproveitavam a inclinação natural do terreno.",
        "A água descia principalmente pela força da gravidade. Os engenheiros precisavam planejar o percurso e manter uma inclinação adequada para que o fluxo funcionasse.",
        "Nem todos os aquedutos eram pontes monumentais com arcos. Grande parte da água passava por canais subterrâneos; os arcos eram usados quando era necessário atravessar vales.",
        "O sistema exigia manutenção constante. Sedimentos precisavam ser removidos e canais reparados, mostrando como a engenharia romana combinava planejamento e conhecimento prático."
    ), "Encyclopaedia Britannica — verbete sobre aquedutos romanos."),
    Article(4, "Por que sentimos que já vivemos uma situação?", "Psicologia", "O déjà-vu é uma sensação de familiaridade que a ciência continua investigando.", 4, "🧠", listOf(
        "Déjà-vu é uma expressão francesa que significa algo como “já visto”. Ela descreve a impressão de que uma situação nova já aconteceu antes, mesmo sem uma lembrança concreta.",
        "Uma hipótese é que o fenômeno esteja relacionado a uma diferença momentânea entre reconhecer familiaridade e recuperar uma memória específica: sentimos que conhecemos a situação, mas não sabemos de onde.",
        "Experiências ocasionais são relatadas por muitas pessoas e, isoladamente, geralmente não indicam um problema de saúde. Ainda não existe uma única explicação para todos os casos.",
        "Se episódios forem frequentes ou vierem acompanhados de perda de consciência, confusão ou outros sintomas neurológicos, é prudente procurar avaliação profissional."
    ), "Cleveland Clinic — material informativo sobre déjà-vu."),
    Article(5, "Por que o céu é azul durante o dia?", "Ciência", "A cor do céu nasce da interação entre a luz solar e a atmosfera terrestre.", 3, "🌤️", listOf(
        "A luz do Sol parece branca, mas contém diferentes comprimentos de onda que correspondem às cores que enxergamos. Ao entrar na atmosfera, a luz interage com moléculas de gases presentes no ar.",
        "Moléculas pequenas espalham mais intensamente os comprimentos de onda curtos. Esse processo é chamado espalhamento de Rayleigh, e a luz azul é espalhada com mais intensidade do que a vermelha.",
        "A luz azul espalhada chega aos nossos olhos a partir de muitas direções do céu. A combinação do espectro solar com a sensibilidade dos olhos contribui para percebermos o céu predominantemente azul.",
        "No nascer e no pôr do sol, a luz atravessa uma camada maior de atmosfera, deixando mais tons avermelhados e alaranjados na luz direta que chega até nós."
    ), "NASA Space Place — explicação sobre a cor do céu."),
    Article(6, "Por que o cérebro precisa dormir?", "Ciência", "O sono participa da memória, da atenção e do funcionamento do organismo.", 5, "🌙", listOf(
        "Dormir é um processo biológico ativo. Durante a noite, o cérebro passa por diferentes estágios, incluindo fases de sono não REM e sono REM.",
        "O sono está associado à consolidação de diferentes tipos de memória e ao aprendizado. Descansar adequadamente também ajuda a manter a atenção e a regulação emocional.",
        "O organismo usa sinais internos e ambientais para regular o ciclo sono-vigília. A luz, os horários e a regularidade da rotina podem influenciar o relógio biológico.",
        "As necessidades variam com a idade e entre indivíduos. Recomendações de saúde pública geralmente indicam pelo menos sete horas por noite para adultos."
    ), "CDC — orientações gerais sobre sono saudável.")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("curiosa_preferences", MODE_PRIVATE)
        setContent {
            CuriosaApp(
                initialFavorites = prefs.getStringSet("favorites", emptySet()) ?: emptySet(),
                saveFavorites = { prefs.edit().putStringSet("favorites", it.toSet()).apply() }
            )
        }
    }
}

@Composable
private fun CuriosaApp(initialFavorites: Set<String>, saveFavorites: (Set<String>) -> Unit) {
    var tab by remember { mutableStateOf("Início") }
    var category by remember { mutableStateOf("Todos") }
    var search by remember { mutableStateOf("") }
    var opened by remember { mutableStateOf<Article?>(null) }
    var favorites by remember { mutableStateOf(initialFavorites) }

    MaterialTheme(colorScheme = lightColorScheme(primary = Green, background = Cream, surface = Paper, onSurface = Ink, secondary = Gold)) {
        Surface(Modifier.fillMaxSize(), color = Cream) {
            val article = opened
            if (article != null) {
                ArticlePage(
                    article = article,
                    favorite = favorites.contains(article.id.toString()),
                    back = { opened = null },
                    toggleFavorite = {
                        val id = article.id.toString()
                        favorites = if (favorites.contains(id)) favorites - id else favorites + id
                        saveFavorites(favorites)
                    },
                    openArticle = { opened = it }
                )
            } else {
                HomePage(
                    tab = tab, onTab = { tab = it }, category = category, onCategory = { category = it },
                    search = search, onSearch = { search = it }, favorites = favorites,
                    openArticle = { opened = it },
                    toggleFavorite = { a ->
                        val id = a.id.toString()
                        favorites = if (favorites.contains(id)) favorites - id else favorites + id
                        saveFavorites(favorites)
                    }
                )
            }
        }
    }
}

@Composable
private fun HomePage(
    tab: String, onTab: (String) -> Unit, category: String, onCategory: (String) -> Unit,
    search: String, onSearch: (String) -> Unit, favorites: Set<String>,
    openArticle: (Article) -> Unit, toggleFavorite: (Article) -> Unit
) {
    val filtered = articles.filter { a ->
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
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("C U R I O S A", color = Green, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                        Text(when (tab) { "Explorar" -> "Explore o desconhecido"; "Salvos" -> "Sua biblioteca"; else -> "O mundo é fascinante." },
                            color = Ink, fontSize = 26.sp, lineHeight = 32.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFE5EBDD)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Lightbulb, "Curiosidade", tint = Green, modifier = Modifier.size(26.dp))
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
                            .clickable { openArticle(articles[4]) }.padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("✦  CURIOSIDADE DO DIA", color = Color(0xFFE3EBD9), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Text("Por que o céu é azul?", color = Color.White, fontSize = 25.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)
                        Text("Descubra o que a luz faz ao atravessar nossa atmosfera.", color = Color(0xFFE3EBD9), fontSize = 14.sp)
                        Text("Descobrir agora  →", color = Gold, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            if ((tab == "Explorar" || tab == "Início") && search.isBlank()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Explore por assunto", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(categories) { c ->
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

@Composable
private fun ArticleCard(article: Article, favorite: Boolean, open: () -> Unit, toggle: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(Paper).clickable(onClick = open).padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(13.dp), verticalAlignment = Alignment.Top
    ) {
        Box(Modifier.size(76.dp).clip(RoundedCornerShape(17.dp)).background(Color(0xFFE8EDE2)), contentAlignment = Alignment.Center) {
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
            Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = back) { Icon(Icons.Default.ArrowBack, "Voltar", tint = Ink) }
                Text("Leitura", Modifier.weight(1f), color = Ink, fontWeight = FontWeight.SemiBold)
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
                Box(Modifier.fillMaxWidth().height(160.dp).clip(RoundedCornerShape(26.dp)).background(Green), contentAlignment = Alignment.Center) {
                    Text(article.emoji, fontSize = 76.sp)
                }
            }
            item {
                Text(article.category.uppercase(), color = Green, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(7.dp))
                Text(article.title, color = Ink, fontSize = 29.sp, lineHeight = 35.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(10.dp))
                Text(article.summary, color = Muted, fontSize = 16.sp, lineHeight = 24.sp)
                Spacer(Modifier.height(8.dp))
                Text(article.minutes.toString() + " minutos de leitura", color = Muted, fontSize = 12.sp)
            }
            items(article.paragraphs) { paragraph ->
                Text(paragraph, color = Ink, fontSize = 17.sp, lineHeight = 28.sp)
            }
            item {
                HorizontalDivider(color = Color(0xFFE1E4DA))
                Text("FONTES PARA SABER MAIS", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                Text(article.source, color = Muted, fontSize = 13.sp, lineHeight = 20.sp)
            }
            item { Text("Continue descobrindo", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold) }
            items(articles.filter { it.id != article.id }.take(2)) { related ->
                ArticleCard(related, false, { openArticle(related) }, {})
            }
        }
    }
}
