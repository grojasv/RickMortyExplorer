# APP: Rick & Morty Explorer
## Summary 📋
This is a simple Rick and Morty APP using the [Rick and Morty GraphQL API](https://rickandmortyapi.com/graphql)

<img src="https://github.com/grojasv/RickMortyExplorer/blob/master/assets/app_showcase_splashscreen.png" alt="splashscreen-image" width="150"/> 

## Dark theme showcase 🌝
![](https://github.com/grojasv/GitHubRepoViewerPrivate/blob/master/app_showcase_dark_theme.gif)

## Light theme showcase 🌞
![](https://github.com/grojasv/GitHubRepoViewerPrivate/blob/master/app_showcase_light_theme.gif)

## Deep Link support 🕸️

This app supports deep links to go directly to the Character Details Screen.

Usage:
```
adb shell am start -d rickandmortyexplorer://character/5
```

## Built with 🔨
- Kotlin
- Compose & compose navigation
- Clean architecture patterns (Data, Domain, and Presentation layers)
- Dagger Hilt
- GraphQL API with Apollo
- Room
