# CurioMundo

Aplicativo Android de conhecimentos gerais com artigos aprofundados, identidade visual roxa/turquesa, guias ilustrados e personalização por interesses.

**Marca:** CurioMundo  
**Slogan proposto:** Um mundo inteiro para descobrir.  
**Direção visual:** personagens em estilo 3D cartoon para todas as idades; os ícones de emoji atuais são marcadores provisórios enquanto as ilustrações originais são produzidas.

## Experiência atual de protótipo
- Seleção inicial de interesses antes da etapa de perfil
- Categorias como astronomia, ciência, animais, história, tecnologia, psicologia, corpo humano, oceanos, física e meio ambiente
- Feed com priorização local de assuntos selecionados
- Perfil local com nome, e-mail e interesses editáveis
- Pesquisa, filtros e favoritos persistidos no aparelho
- Fluxo de aprendizagem local: leitura concluída → quiz de três perguntas → resultado com explicação
- Pontuação da meta diária (10 pontos por resposta correta), com limite de 30 pontos por quiz concluído
- Progresso persistente no aparelho: artigos lidos, quizzes concluídos, sequência de dias e pontos do dia
- Tela de progresso com calendário de consistência e conquistas por marcos de leitura, quizzes e variedade de categorias
- Recompensas de quiz protegidas contra repetição para o mesmo artigo no protótipo local
- Catálogo inicial com 10 artigos, incluindo quatro novos temas sobre comunicação das abelhas, buracos negros, salinidade dos oceanos e funcionamento do Wi-Fi
- Leitor com introdução, seções detalhadas, pontos-chave e indicações de fontes para aprofundamento
- Primeira seção de guias/personagens: Octo, Cosmo, Dino e Byte (ícones provisórios; artes 3D originais pendentes)
- Interface em português brasileiro, Kotlin e Jetpack Compose

## Arquitetura atual da experiência de aprendizagem

A navegação é controlada por estado no Compose, preservando o protótipo existente. As telas de feed, leitura, quiz, resultado e progresso estão conectadas. Os dados de aprendizagem usam SharedPreferences local nesta fase; ainda não há Room, sincronização em nuvem ou validação de resultados no servidor. A meta diária é de 30 pontos e a pontuação é calculada localmente, portanto serve para prototipagem e não como mecanismo antifraude de produção.

A refatoração em pacotes dedicados para navegação, dados, domínio e funcionalidades pode continuar incrementalmente, sem substituir o catálogo editorial já existente. O conteúdo do quiz atual é derivado dos pontos-chave dos artigos e deve ser revisado editorialmente antes de lançamento.

## Importante: autenticação ainda não conectada
A tela de perfil atual é um protótipo local. Ela **não cria uma conta online**, não valida credenciais num servidor e não oferece login em vários dispositivos ou recuperação de senha. Não usar como sistema de autenticação de produção. A etapa seguinte é configurar um projeto de backend exclusivo para o CurioMundo, com autenticação, perfis e interesses protegidos por políticas de acesso. O projeto Supabase existente não deve ser reutilizado sem uma decisão explícita, pois já contém dados de outros produtos.

## Conteúdo editorial
Os artigos atuais são exemplos iniciais ampliados para testar a experiência de leitura. As fontes são indicações editoriais e devem ser verificadas e vinculadas a referências específicas antes da publicação comercial. O conteúdo deve ser revisado para precisão, clareza e atualização.

## Compilação do APK
Cada push para a branch `main` inicia o workflow **Android APK**. Quando a execução terminar com sucesso, abra **Actions → Android APK** e baixe o artefato `CurioMundo-debug-apk`.

O APK é uma versão de depuração para testes e instalação manual. A distribuição comercial na Google Play exige preparar a versão de lançamento, assinatura, política de privacidade, termos, classificação de conteúdo e demais requisitos da loja.

## Monetização
## Monetização planejada
O modelo escolhido para avaliação é uma assinatura mensal de R$ 19,90 com 7 dias de teste gratuito, usando Google Play Billing. Nada disso está ativo no protótipo atual. Antes do lançamento, será necessário criar/configurar o produto e a oferta na Play Console, informar claramente a data da primeira cobrança e as regras de renovação/cancelamento, validar tokens de compra no backend e sincronizar o estado da assinatura com a Google Play Developer API. O backend Supabase será tratado em uma etapa posterior, em um projeto separado e somente após autorização.
