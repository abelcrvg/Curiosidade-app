# Curiosa

Aplicativo Android de conhecimentos gerais com artigos aprofundados, leitura organizada e personalização por interesses.

## Experiência atual de protótipo
- Seleção inicial de interesses antes da etapa de perfil
- Categorias como astronomia, ciência, animais, história, tecnologia, psicologia, corpo humano, oceanos, física e meio ambiente
- Feed com priorização local de assuntos selecionados
- Perfil local com nome, e-mail e interesses editáveis
- Pesquisa, filtros e favoritos persistidos no aparelho
- Leitor com introdução, seções detalhadas, pontos-chave e fontes para aprofundamento
- Interface em português brasileiro, Kotlin e Jetpack Compose

## Importante: autenticação ainda não conectada
A tela de perfil atual é um protótipo local. Ela **não cria uma conta online**, não valida credenciais num servidor e não oferece login em vários dispositivos ou recuperação de senha. Não usar como sistema de autenticação de produção. A etapa seguinte é configurar um projeto de backend exclusivo para o Curiosa, com autenticação, perfis e interesses protegidos por políticas de acesso. O projeto Supabase existente não deve ser reutilizado sem uma decisão explícita, pois já contém dados de outros produtos.

## Conteúdo editorial
Os artigos atuais são exemplos iniciais ampliados para testar a experiência de leitura. As fontes são indicações editoriais e devem ser verificadas e vinculadas a referências específicas antes da publicação comercial. O conteúdo deve ser revisado para precisão, clareza e atualização.

## Compilação do APK
Cada push para a branch `main` inicia o workflow **Android APK**. Quando a execução terminar com sucesso, abra **Actions → Android APK** e baixe o artefato `Curiosa-debug-apk`.

O APK é uma versão de depuração para testes e instalação manual. A distribuição comercial na Google Play exige preparar a versão de lançamento, assinatura, política de privacidade, termos, classificação de conteúdo e demais requisitos da loja.

## Monetização
O modelo comercial ainda precisa ser configurado. Se a intenção for cobrar uma única vez pelo download, a compra será definida na Google Play Console; não se deve implementar uma tela de pagamento fictícia dentro do aplicativo.
