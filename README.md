# 🏥 VezCerta — Saúde Digital

<p align="center">
  <b>Aplicativo Android moderno para gestão de atendimento em saúde pública e privada.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-2026.02-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Material%203-1.4.0-757575?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material 3" />
  <img src="https://img.shields.io/badge/Min%20SDK-30-green?style=for-the-badge&logo=android" alt="Min SDK 30" />
  <img src="https://img.shields.io/badge/Target%20SDK-37-brightgreen?style=for-the-badge&logo=android" alt="Target SDK 37" />
</p>

---

## 📌 Sobre o Projeto

O **VezCerta** é uma solução mobile desenvolvida em **Android nativo com Jetpack Compose** focada em otimizar a experiência do paciente no sistema de saúde. O aplicativo permite acompanhar filas de atendimento em tempo real, localizar unidades de saúde próximas e gerenciar o perfil do usuário com acessibilidade e fluidez.

---

## ✨ Funcionalidades Principais

- 📋 **Fila de Atendimento (`FilaScreen`)**: Acompanhamento do status de atendimento na fila, ordem de chamada e monitoramento em tempo real.
- 📍 **Unidades de Saúde (`UnidadesScreen`)**: Localização e consulta de postos de saúde, UPAs e hospitais integrados à rede.
- 👤 **Perfil do Paciente (`PerfilScreen`)**: Gestão de dados pessoais, histórico de consultas e preferências do usuário.
- 🎨 **Design System Adaptativo & Edge-to-Edge**: Interface moderna baseada em Material Design 3, com barra de navegação customizada e suporte total a insets do sistema.

---

## 🛠️ Tecnologias & Bibliotecas

| Tecnologia | Descrição / Versão |
| :--- | :--- |
| **Linguagem** | [Kotlin](https://kotlinlang.org/) `2.2.10` |
| **UI Toolkit** | [Jetpack Compose](https://developer.android.com/jetpack/compose) com Material 3 (`BOM 2026.02.01`) |
| **Arquitetura** | Modern Android Development (MAD) + Unidirectional Data Flow (UDF) |
| **Build System** | Android Gradle Plugin `9.4.1` + Version Catalogs (`libs.versions.toml`) |
| **Design System** | Material Design 3 com identidade visual própria (`ThemeBlue`) |
| **Compatibilidade** | Min SDK `30` (Android 11) \| Target SDK `37` (Android 16) |

---

## 📂 Estrutura do Projeto

```
com.example.vezcerta/
├── components/          # Componentes de UI reutilizáveis (TopAppBar, BottomNavigationBar)
│   ├── NavigationBar.kt # Barra de navegação inferior com suporte a callbacks de estado
│   └── TopBar.kt        # Barra superior customizada (Saúde Digital)
├── screens/             # Telas e fluxos principais
│   ├── fila/            # Gestão da fila de atendimento e estado da UI (HomeUiState)
│   ├── perfil/          # Tela de perfil do paciente
│   └── unidades/        # Consulta e busca de unidades de saúde
├── ui/theme/            # Tema Material 3 (Color, Type, Theme)
└── MainActivity.kt      # Ponto de entrada, Scaffold principal e controle de navegação
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Android Studio** (Ladybug ou mais recente recomendado)
- **JDK 17** ou superior configurado no ambiente
- Dispositivo físico ou Emulador Android rodando **Android 11 (API 30)** ou superior

### Passos
1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/vezcerta.git
   cd vezcerta
   ```

2. **Abrir no Android Studio:**
   - Abra o Android Studio, escolha `Open...` e selecione a pasta raiz do projeto.
   - Aguarde a sincronização automática do Gradle.

3. **Compilar e Executar:**
   - No terminal, compile via Gradle:
     ```bash
     ./gradlew assembleDebug
     ```
   - Ou clique em **Run (`Shift + F10`)** dentro do Android Studio com o dispositivo/emulador selecionado.

---

## 🎨 Identidade Visual

O projeto utiliza a cor temática **ThemeBlue** (`#0052A5`) como identidade principal, garantindo alto contraste, alinhamento com padrões de saúde digital e conformidade com as diretrizes de acessibilidade e legibilidade.

---

## 📄 Licença

Este projeto é disponibilizado sob a licença [MIT](LICENSE).

---

<p align="center">
  Desenvolvido com ❤️ para transformar a experiência de saúde digital.
</p>
