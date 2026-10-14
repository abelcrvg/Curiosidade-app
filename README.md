# Curiosa

Aplicativo Android de conhecimentos gerais, curiosidades e artigos curtos. O MVP funciona sem login, sem servidor próprio e sem inteligência artificial.

## Recursos iniciais
- Curiosidade do dia
- Artigos organizados por categoria
- Pesquisa local por título, resumo e categoria
- Leitura de artigos com indicação de fontes
- Favoritos persistidos no aparelho
- Interface em português brasileiro
- Compilação automática do APK pelo GitHub Actions

## Tecnologias
- Kotlin
- Jetpack Compose
- Material 3
- Gradle
- GitHub Actions

## Gerar o APK
Cada push para a branch `main` inicia o workflow **Android APK**. Quando a execução terminar com sucesso, abra **Actions → Android APK** e baixe o artefato `Curiosa-debug-apk`.

O APK é uma versão de depuração para testes e instalação manual. Publicar na Google Play exige preparação adicional de versão e assinatura.

## Conteúdo
Os primeiros artigos são conteúdo editorial inicial com indicação de fontes para revisão. Antes de uma publicação ampla, confira a precisão dos textos e confirme as referências.
